package se.skltp.loghandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import se.skltp.loghandler.configs.TjanstekontraktSettingsConfig;
import se.skltp.loghandler.models.dao.*;
import se.skltp.loghandler.models.entity.Anslutning;
import se.skltp.loghandler.xml.TjanstekontraktConfig;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by parlin on 2017-09-29.
 */
@Service
public class LogpostParserService {
    private static final Logger logger = LogManager.getLogger(LogpostParserService.class);
    private Pattern envelopeStringPattern;

    @Autowired
    private TjanstekontraktDao tjanstekontraktDao;

    @Autowired
    private KallsystemDao kallsystemDao;

    @Autowired
    private KategoriDao kategoriDao;

    @Autowired
    private OrganisatoriskenhetDao organisatoriskenhetDao;

    @Autowired
    private UrsprungligkonsumentDao ursprungligkonsumentDao;

    @Autowired
    private VardenhetDao vardenhetDao;

    @Autowired
    private VardgivareDao vardgivareDao;

    @Autowired
    private TjanstekontraktSettingsConfig tjanstekontraktSettingsConfig;

    private static List<Anslutning> anslutningar = new ArrayList<>();

    public synchronized static void addAnlutning(Anslutning anslutning) {
        anslutningar.add(anslutning);
    }

    public synchronized static void addAnlutningar(List<Anslutning> anslutningar) {
        LogpostParserService.anslutningar.addAll(anslutningar);
    }

    public synchronized static List<Anslutning> getLatestAnlutningar() {
        List<Anslutning> latestAnslutningar = anslutningar;
        anslutningar = new ArrayList<>();
        return latestAnslutningar;
    }

    @Async("logpostParserPool")
    public void parseLogpost(LogEvent event)  {
        if(logger.isDebugEnabled()) {
            logger.debug("Tagit emot logpost och påbörjar parsning.");
        }
        Date vpdate = new Date(event.getTimeMillis());
        String logpost = event.getMessage().getFormattedMessage();

        List<Anslutning> anslutningar = parseAndGetAnslutningar(vpdate, logpost);
        if (anslutningar == null) return;

        addAnlutningar(anslutningar);
    }

    public List<Anslutning> parseAndGetAnslutningar(Date vpdate, String logpost) {
        List<Anslutning> anslutningar = new ArrayList<>();
        String tjanstekontrakt = "";
        String ursprungligkonsument = "";
        DateFormat format = new SimpleDateFormat(TjanstekontraktSettingsConfig.dateFormat);
        String datestring = format.format(vpdate);

        TjanstekontraktConfig tjanstekontraktConfig = null;

        if(envelopeStringPattern == null) {
            setEnvelopeStringPattern();
        }

        try (BufferedReader reader = new BufferedReader(new StringReader(logpost))) {
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith(TjanstekontraktSettingsConfig.contractNameProperty)) {
                    tjanstekontrakt = line.substring(TjanstekontraktSettingsConfig.contractNameProperty.length());
                    tjanstekontraktConfig = tjanstekontraktSettingsConfig.getTjanstekontraktConfigOnName(tjanstekontrakt);
                    if(tjanstekontraktConfig == null) {
                        return null;
                    }
                } else if(line.startsWith(TjanstekontraktSettingsConfig.originalConsumerProperty)) {
                    ursprungligkonsument = line.substring(TjanstekontraktSettingsConfig.originalConsumerProperty.length());
                } else if(line.contains(TjanstekontraktSettingsConfig.payloadProperty)) {
                    //Ingen idé att parsa payloaden om inte allt som förväntas hittas innan detta finns.
                    if(!tjanstekontrakt.equals("") && !ursprungligkonsument.equals("")) {
                        StringBuilder strBuilder = new StringBuilder();
                        strBuilder.append(line.substring(TjanstekontraktSettingsConfig.payloadProperty.length()));
                        Matcher matcher = envelopeStringPattern.matcher(line);
                        int lastElement = line.lastIndexOf('<');
                        int startFrom = lastElement < 0 ? 0 : lastElement;
                        while(!matcher.find(startFrom)) {
                            line = reader.readLine();
                            lastElement = line.lastIndexOf('<');
                            startFrom = lastElement < 0 ? 0 : lastElement;
                            matcher = envelopeStringPattern.matcher(line);
                            strBuilder.append(line);
                        }
                        List<Anslutning> anslutningList = new ArrayList<>();
                        parsePayloadAndUpdateAnslutning(strBuilder.toString(), tjanstekontraktConfig, anslutningList, tjanstekontrakt, ursprungligkonsument, vpdate);

                        anslutningar.addAll(anslutningList);
                    } else {
                        throw new NotAllHeaderValuesException();
                    }
                }

                line = reader.readLine();
            }
        } catch (IOException exc) {
            exc.printStackTrace();
            logger.error("Oväntat IOException" , exc);
            logger.error("\nIOException inträffade när kända värden på anslutningen var:"
                    + getStringForHeaderException(tjanstekontrakt, ursprungligkonsument, datestring));
        } catch (NotAllHeaderValuesException e) {
            e.printStackTrace();
            logger.error("Oväntat NotAllHeaderValuesException" , e);
            logger.error("\nNotAllHeaderValuesException inträffade när kända värden på anslutningen var:"
                    + getStringForHeaderException(tjanstekontrakt, ursprungligkonsument, datestring));
        }

        if(logger.isDebugEnabled()) {
            for (Anslutning a :anslutningar) {
                logger.debug("Adding anslutning: " + a.toString());
            }
        }
        return anslutningar;
    }

    private synchronized void setEnvelopeStringPattern() {
        if(envelopeStringPattern == null) {
            envelopeStringPattern = Pattern.compile(TjanstekontraktSettingsConfig.envelopeName, Pattern.CASE_INSENSITIVE);
        }
    }

    private String getStringForHeaderException(String tjanstekontrakt, String ursprungligkonsument, String datestring) {
        return "\n tjanstekontrakt: " + tjanstekontrakt
                + "\n ursprungligkonsument: " + ursprungligkonsument
                + "\n datestring: " + datestring;
    }

    private void parsePayloadAndUpdateAnslutning(String payload, TjanstekontraktConfig tjanstekontraktConfig, List<Anslutning> anslutningList, String tjanstekontrakt, String ursprungligkonsument, Date vpdate) {

        String kallsystem = tjanstekontraktConfig.kallsystemConfig != null ? tjanstekontraktConfig.kallsystemConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.kallsystemConfig.getElement();
        String kategori = tjanstekontraktConfig.kategoriConfig != null ? tjanstekontraktConfig.kategoriConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.kategoriConfig.getElement();
        String vardgivare = tjanstekontraktConfig.vardgivareConfig != null ? tjanstekontraktConfig.vardgivareConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.vardgivareConfig.getElement();
        String vardenhet = tjanstekontraktConfig.vardenhetConfig != null ? tjanstekontraktConfig.vardenhetConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.vardenhetConfig.getElement();
        String organisatoriskenhet = tjanstekontraktConfig.organisatoriskEnhetConfig != null ? tjanstekontraktConfig.organisatoriskEnhetConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.organisatoriskEnhetConfig.getElement();
        String huvudelement = tjanstekontraktConfig.huvudelementConfig != null ? tjanstekontraktConfig.huvudelementConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.huvudelementConfig.getElement();

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = new ByteArrayInputStream(payload.getBytes());
        XMLEventReader eventReader = null;

        Stack<String> elementHierarchy = new Stack<>();
        Anslutning anslutning = null;
        try {
            eventReader = inputFactory.createXMLEventReader(in);

            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if(event.isStartDocument()) {
                } else if(event.isStartElement()) {
                    if(huvudelement.equalsIgnoreCase(event.asStartElement().getName().getLocalPart())) {
                        anslutning = new Anslutning();
                        anslutning.setTjanstekontrakt(tjanstekontraktDao.getByNameCreateIfNew(tjanstekontrakt).getId());
                        anslutning.setSenasteAnslutningsDatum(vpdate);
                        anslutning.setUrsprungligkonsument(ursprungligkonsumentDao.getByNameCreateIfNew(ursprungligkonsument).getId());
                    }
                    elementHierarchy.add(event.asStartElement().getName().getLocalPart());
                } else if(event.isCharacters()) {
                    if(elementHierarchy.peek().equalsIgnoreCase(kallsystem)) {
                        anslutning.setKallsystem(kallsystemDao.getByNameCreateIfNew(event.asCharacters().getData()).getId());
                    } else if (elementHierarchy.peek().equalsIgnoreCase(vardgivare)) {
                        anslutning.setVardgivare(vardgivareDao.getByNameCreateIfNew(event.asCharacters().getData()).getId());
                    } else if (elementHierarchy.peek().equalsIgnoreCase(vardenhet)) {
                        anslutning.setVardenhet(vardenhetDao.getByNameCreateIfNew(event.asCharacters().getData()).getId());
                    } else if (elementHierarchy.peek().equalsIgnoreCase(organisatoriskenhet)) {
                        anslutning.setOrganisatoriskenhet(organisatoriskenhetDao.getByNameCreateIfNew(event.asCharacters().getData()).getId());
                    } else if (elementHierarchy.peek().equalsIgnoreCase(kategori)) {
                        anslutning.setKategori(kategoriDao.getByNameCreateIfNew(event.asCharacters().getData()).getId());
                    }
                } else if(event.isEndElement()) {
                    if(huvudelement.equalsIgnoreCase(event.asEndElement().getName().getLocalPart())) {
                        if(anslutning.getKategori() == 0) {
                            anslutning.setKategori(kategoriDao.getByNameCreateIfNew("").getId());
                        }
                        anslutningList.add(anslutning);
                    }
                    elementHierarchy.pop();
                } else if(event.isEndDocument()) {
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
            logger.error("Oväntat XMLStreamException", e);
            StringBuilder elementHierarchyStringBuilder = new StringBuilder();
            elementHierarchy.iterator().forEachRemaining(s -> elementHierarchyStringBuilder.append(" -> "+s));
            DateFormat format = new SimpleDateFormat(TjanstekontraktSettingsConfig.dateFormat);
            String datestring = vpdate == null ? "" : format.format(vpdate);

            if(anslutning == null) {
                logger.error("\nXMLStreamException inträffade innan anslutningens huvudelement var hittat. Kända värden på anslutningen var:"
                        + getStringForHeaderException(tjanstekontrakt, ursprungligkonsument, datestring)
                        + "\n elementHierarchy vid Exception: " + elementHierarchyStringBuilder.toString());
            } else {
                String kallsystemName = anslutning.getKallsystem() == 0 ? "" : kallsystemDao.getbyId(anslutning.getKallsystem());
                String vardgivareName = anslutning.getVardgivare() == 0 ? "" : vardgivareDao.getbyId(anslutning.getVardgivare());
                String vardenhetName = anslutning.getVardenhet() == 0 ? "" : vardenhetDao.getbyId(anslutning.getVardenhet());
                String organisatoriskenhetName = anslutning.getOrganisatoriskenhet() == 0 ? "" : organisatoriskenhetDao.getbyId(anslutning.getOrganisatoriskenhet());
                String kategoriName = anslutning.getKategori() == 0 ? "" : kategoriDao.getbyId(anslutning.getKategori());
                logger.error("\nXMLStreamException inträffade när kända värden på anslutningen var:"
                        + "\n tjanstekontrakt: " + tjanstekontrakt
                        + "\n ursprungligkonsument: " + ursprungligkonsument
                        + "\n vpdate: " + datestring
                        + "\n källsystem: " + kallsystemName
                        + "\n vardgivare: " + vardgivareName
                        + "\n vardenhet: " + vardenhetName
                        + "\n organisatoriskenhet: " + organisatoriskenhetName
                        + "\n kategori: " + kategoriName
                        + "\n elementHierarchy vid Exception:" + elementHierarchyStringBuilder.toString());
            }
        }
    }

    private class NotAllHeaderValuesException extends Exception {
    }
}
