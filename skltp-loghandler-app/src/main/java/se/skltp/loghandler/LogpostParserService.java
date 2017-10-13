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
                    String datestring = line.substring(0,19);
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
                    parseBodyAndUpdateAnslutning(strBuilder.toString(), tjanstekontraktConfig, anslutningList);

                    for (Anslutning anslutning:anslutningList) {
                        anslutning.setTjanstekontrakt(tjanstekontraktDao.getByNameCreateIfNew(tjanstekontrakt));
                        anslutning.setYoungest(vpdate);
                        anslutning.setUrsprungligkonsument(ursprungligkonsumentDao.getByNameCreateIfNew(ursprungligkonsument));
                        if(anslutning.getKategori() == null) {
                            anslutning.setKategori(kategoriDao.getByNameCreateIfNew(""));
                        }
                    }

                    anslutningar.addAll(anslutningList);
                }

                line = reader.readLine();
            }
        } catch (IOException exc) {
            exc.printStackTrace();
            logger.error("Oväntat IOException" , exc);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("Oväntat ParseException" , e);
        }

        if(logger.isDebugEnabled()) {
            for (Anslutning a :anslutningar) {
                logger.debug("Adding anslutning: " + a.toString());
            }
        }

        addAnlutningar(anslutningar);
    }

    private void parseBodyAndUpdateAnslutning(String line, TjanstekontraktConfig tjanstekontraktConfig, List<Anslutning> anslutningList) {

        String kallsystem = tjanstekontraktConfig.kallsystemConfig != null ? tjanstekontraktConfig.kallsystemConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.kallsystemConfig.getElement();
        String kategori = tjanstekontraktConfig.kategoriConfig != null ? tjanstekontraktConfig.kategoriConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.kategoriConfig.getElement();
        String vardgivare = tjanstekontraktConfig.vardgivareConfig != null ? tjanstekontraktConfig.vardgivareConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.vardgivareConfig.getElement();
        String vardenhet = tjanstekontraktConfig.vardenhetConfig != null ? tjanstekontraktConfig.vardenhetConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.vardenhetConfig.getElement();
        String organisatoriskenhet = tjanstekontraktConfig.organisatoriskEnhetConfig != null ? tjanstekontraktConfig.organisatoriskEnhetConfig.getElement() : TjanstekontraktSettingsConfig.tjanstekontraktDefaultConfig.organisatoriskEnhetConfig.getElement();

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = new ByteArrayInputStream(line.getBytes());
        XMLEventReader eventReader = null;

        Stack<String> elementHierarchy = new Stack<>();

        //String lastElement = "";
        try {
            eventReader = inputFactory.createXMLEventReader(in);

            Anslutning anslutning = null;
            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if(event.isStartDocument()) {
                } else if(event.isStartElement()) {
                    elementHierarchy.add(event.asStartElement().getName().getLocalPart());
                } else if(event.isCharacters()) {
                    if(elementHierarchy.peek().equals(kallsystem)) {
                        if(anslutning != null) {
                            anslutningList.add(anslutning);
                        }
                        anslutning = new Anslutning();
                        anslutning.setKallsystem(kallsystemDao.getByNameCreateIfNew(event.asCharacters().getData().toString()));
                    } else if (elementHierarchy.peek().equals(vardgivare)) {
                        anslutning.setVardgivare(vardgivareDao.getByNameCreateIfNew(event.asCharacters().getData().toString()));
                    } else if (elementHierarchy.peek().equals(vardenhet)) {
                        anslutning.setVardenhet(vardenhetDao.getByNameCreateIfNew(event.asCharacters().getData().toString()));
                    } else if (elementHierarchy.peek().equals(organisatoriskenhet)) {
                        anslutning.setOrganisatoriskenhet(organisatoriskenhetDao.getByNameCreateIfNew(event.asCharacters().getData().toString()));
                    } else if (elementHierarchy.peek().equals(kategori)) {
                        anslutning.setKategori(kategoriDao.getByNameCreateIfNew(event.asCharacters().getData().toString()));
                    }
                } else if(event.isEndElement()) {
                    elementHierarchy.pop();
                } else if(event.isEndDocument()) {
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
            logger.debug("Oväntat XMLStreamException", e);
        }
    }
}
