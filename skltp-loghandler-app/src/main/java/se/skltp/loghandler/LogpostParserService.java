package se.skltp.loghandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by parlin on 2017-09-29.
 */
@Service
public class LogpostParserService {

    private static final Logger logger = LogManager.getLogger(LogpostParserService.class);

    public static final String SOAP_ENV_ENVELOPE_END = "</SOAP-ENV:Envelope>";

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
    public void parseLogpost(String logpost)  {
        List<Anslutning> anslutningar = new ArrayList<>();
        String tjanstekontrakt = "";
        String ursprungligkonsument = "";
        String datestring = "";
        Date vpdate = null;
        TjanstekontraktConfig tjanstekontraktConfig = null;

        try (BufferedReader reader = new BufferedReader(new StringReader(logpost))) {
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith(TjanstekontraktSettingsConfig.contractNameProperty)) {
                    tjanstekontrakt = line.substring(TjanstekontraktSettingsConfig.contractNameProperty.length());
                    tjanstekontraktConfig = tjanstekontraktSettingsConfig.getTjanstekontraktConfigOnName(tjanstekontrakt);
                    if(tjanstekontraktConfig == null) {
                        return;
                    }
                } else if(line.startsWith(TjanstekontraktSettingsConfig.originalConsumerProperty)) {
                    ursprungligkonsument = line.substring(TjanstekontraktSettingsConfig.originalConsumerProperty.length());
                } else if(Pattern.matches(TjanstekontraktSettingsConfig.dateRegexp, line)) {
                    datestring = line.substring(0,19);
                    DateFormat format = new SimpleDateFormat(TjanstekontraktSettingsConfig.dateFormat);
                    vpdate = format.parse(datestring);
                } else if(line.contains(TjanstekontraktSettingsConfig.payloadProperty)) {
                    StringBuilder strBuilder = new StringBuilder();
                    strBuilder.append(line.substring(TjanstekontraktSettingsConfig.payloadProperty.length()));
                    while(!line.contains(SOAP_ENV_ENVELOPE_END)) {
                        line = reader.readLine();
                        strBuilder.append(line);
                    }
                    List<Anslutning> anslutningList = new ArrayList<>();
                    parsePayloadAndUpdateAnslutning(strBuilder.toString(), tjanstekontraktConfig, anslutningList, tjanstekontrakt, ursprungligkonsument, vpdate);

                    anslutningar.addAll(anslutningList);
                }

                line = reader.readLine();
            }
        } catch (IOException exc) {
            exc.printStackTrace();
            logger.error("Oväntat IOException" , exc);
            logger.error("\nIOException inträffade när kända värden på anslutningen var:"
                    + "\n tjanstekontrakt: " + tjanstekontrakt
                    + "\n ursprungligkonsument: " + ursprungligkonsument
                    + "\n datestring: " + datestring);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("Oväntat ParseException" , e);
            logger.error("\nParseException inträffade när kända värden på anslutningen var:"
                    + "\n tjanstekontrakt: " + tjanstekontrakt
                    + "\n ursprungligkonsument: " + ursprungligkonsument
                    + "\n datestring: " + datestring);
        }

        if(logger.isDebugEnabled()) {
            for (Anslutning a :anslutningar) {
                logger.debug("Adding anslutning: " + a.toString());
            }
        }

        addAnlutningar(anslutningar);
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
                    if(huvudelement.equals(event.asStartElement().getName().getLocalPart())) {
                        anslutning = new Anslutning();
                        anslutning.setTjanstekontrakt(tjanstekontraktDao.getByNameCreateIfNew(tjanstekontrakt).getId());
                        anslutning.setYoungest(vpdate);
                        anslutning.setUrsprungligkonsument(ursprungligkonsumentDao.getByNameCreateIfNew(ursprungligkonsument).getId());
                    }
                    elementHierarchy.add(event.asStartElement().getName().getLocalPart());
                } else if(event.isCharacters()) {
                    if(elementHierarchy.peek().equals(kallsystem)) {
                        anslutning.setKallsystem(kallsystemDao.getByNameCreateIfNew(event.asCharacters().getData()).getId());
                    } else if (elementHierarchy.peek().equals(vardgivare)) {
                        anslutning.setVardgivare(vardgivareDao.getByNameCreateIfNew(event.asCharacters().getData()).getId());
                    } else if (elementHierarchy.peek().equals(vardenhet)) {
                        anslutning.setVardenhet(vardenhetDao.getByNameCreateIfNew(event.asCharacters().getData()).getId());
                    } else if (elementHierarchy.peek().equals(organisatoriskenhet)) {
                        anslutning.setOrganisatoriskenhet(organisatoriskenhetDao.getByNameCreateIfNew(event.asCharacters().getData()).getId());
                    } else if (elementHierarchy.peek().equals(kategori)) {
                        anslutning.setKategori(kategoriDao.getByNameCreateIfNew(event.asCharacters().getData()).getId());
                    }
                } else if(event.isEndElement()) {
                    if(huvudelement.equals(event.asEndElement().getName().getLocalPart())) {
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
                        + "\n tjanstekontrakt: " + tjanstekontrakt
                        + "\n ursprungligkonsument: " + ursprungligkonsument
                        + "\n vpdate: " + datestring
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
}
