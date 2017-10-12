package se.skltp.loghandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import se.skltp.loghandler.configs.TjanstekontraktSettingsConfig;
import se.skltp.loghandler.models.dao.*;
import se.skltp.loghandler.models.entity.Anslutning;
import se.skltp.loghandler.xml.TjanstekontraktConfig;

import javax.annotation.PostConstruct;
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
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by parlin on 2017-09-29.
 */
@Service
public class LogpostParserService {

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

                    while(!line.contains("</SOAP-ENV:Envelope>")) {
                        line = reader.readLine();
                        strBuilder.append(line);
                    }
                    List<Anslutning> anslutningList = new ArrayList<>();
                    parseBodyAndUpdateAnslutning(strBuilder.toString(), anslutningList);

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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        addAnlutningar(anslutningar);
    }

    private void parseBodyAndUpdateAnslutning(String line, List<Anslutning> anslutningList) {

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = new ByteArrayInputStream(line.getBytes());
        XMLEventReader eventReader = null;

        String lastElement = "";
        try {
            eventReader = inputFactory.createXMLEventReader(in);

            Anslutning anslutning = null;
            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if(event.isStartDocument()) {
                    //System.out.println("StartDocument: " + event.toString());
                } else if(event.isStartElement()) {
                    //System.out.println("StartElement: " + event.toString());
                    //System.out.println("-" + event.asStartElement().getName().getLocalPart());
                    lastElement = event.asStartElement().getName().getLocalPart();
                } else if(event.isCharacters()) {
                    //System.out.println("Characters: " + event.toString());
                    //System.out.println("-" + event.asCharacters().getData());
                    switch (lastElement) {
                        case "sourceSystemHSAid": if(anslutning != null) {anslutningList.add(anslutning);}
                            anslutning = new Anslutning();
                            anslutning.setKallsystem(kallsystemDao.getByNameCreateIfNew(event.asCharacters().getData().toString()));
                            break;
                        case "healthcareProfessionalCareGiverHSAId":
                            anslutning.setVardgivare(vardgivareDao.getByNameCreateIfNew(event.asCharacters().getData().toString()));
                            break;
                        case "healthcareProfessionalCareUnitHSAId":
                            anslutning.setVardenhet(vardenhetDao.getByNameCreateIfNew(event.asCharacters().getData().toString()));
                            break;
                        case "orgUnitHSAId":
                            anslutning.setOrganisatoriskenhet(organisatoriskenhetDao.getByNameCreateIfNew(event.asCharacters().getData().toString()));
                            break;
                        case "assessmentCategory":
                            anslutning.setKategori(kategoriDao.getByNameCreateIfNew(event.asCharacters().getData().toString()));
                            break;
                    }
                } else if(event.isEndElement()) {
                    //System.out.println("EndElement: " + event.toString());
                    //System.out.println("-" + event.asEndElement().getName().getLocalPart());
                } else if(event.isEndDocument()) {
                    //System.out.println("EndDocument: " + event.toString());
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
