package se.skltp.loghandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import se.skltp.loghandler.models.Anslutning;
import se.skltp.loghandler.models.AnslutningDao;

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
import java.util.regex.Pattern;

/**
 * Created by parlin on 2017-09-27.
 */
@Controller
public class LogpostHandler {

    public static final String SERVICECONTRACT_NAMESPACE = "-servicecontract_namespace=";
    public static final String ORIGINAL_SERVICECONSUMER_HSAID = "-originalServiceconsumerHsaid=";
    @Autowired
    private AnslutningDao anslutningDao;
    public static LogpostHandler instance;

    public LogpostHandler() {
        instance = this;
    }

    public void addLogpost(String logPost) {
        List<Anslutning> anslutning = parseLogpost(logPost);
        for (Anslutning a :anslutning) {
            anslutningDao.create(a);
        }
    }

    private List<Anslutning> parseLogpost(String logPost) {
        List<Anslutning> anslutningar = new ArrayList<>();
        String tjanstekontrakt = "";
        String ursprungligkonsument = "";
        Date vpdate = null;

        try (BufferedReader reader = new BufferedReader(new StringReader(logPost))) {
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith(SERVICECONTRACT_NAMESPACE)) {
                    tjanstekontrakt = line.substring(SERVICECONTRACT_NAMESPACE.length());
                    System.out.println("tjanstekontrakt:" + tjanstekontrakt);
                } else if(line.startsWith(ORIGINAL_SERVICECONSUMER_HSAID)) {
                    ursprungligkonsument = line.substring(ORIGINAL_SERVICECONSUMER_HSAID.length());
                    System.out.println("ursprungligkonsument:" + ursprungligkonsument);
                } else if(Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d.*", line)) {
                    System.out.println("dateline:" + line);
                    String datestring = line.substring(0,19);
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    vpdate = format.parse(datestring);
                } else if(line.contains("<SOAP-ENV:Envelope")) {
                    StringBuilder strBuilder = new StringBuilder();
                    strBuilder.append(line);

                    while(!line.contains("</SOAP-ENV:Envelope>")) {
                        line = reader.readLine();
                        strBuilder.append(line);
                    }
                    List<Anslutning> anslutningList = new ArrayList<>();
                    System.out.println("bodyline:" + strBuilder.toString());
                    parseBodyAndUpdateAnslutning(strBuilder.toString(), anslutningList);

                    for (Anslutning anslutning:anslutningList) {
                        if(anslutning.getKategori() == null) {
                            anslutning.setKategori("");
                        }
                        anslutning.setOldest(vpdate); //TODO: Ska bara sättas vid nya poster
                        anslutning.setTjanstekontrakt(tjanstekontrakt);
                        anslutning.setYoungest(vpdate);
                        anslutning.setUrsprungligkonsument(ursprungligkonsument);
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

        return anslutningar;
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
                    System.out.println("StartDocument: " + event.toString());
                } else if(event.isStartElement()) {
                    System.out.println("StartElement: " + event.toString());
                    System.out.println("-" + event.asStartElement().getName().getLocalPart());
                    lastElement = event.asStartElement().getName().getLocalPart();
                } else if(event.isCharacters()) {
                    System.out.println("Characters: " + event.toString());
                    System.out.println("-" + event.asCharacters().getData());
                    switch (lastElement) {
                        case "sourceSystemHSAid": if(anslutning != null) {anslutningList.add(anslutning);}
                                anslutning = new Anslutning();
                                anslutning.setKallsystem(event.asCharacters().getData().toString());
                            ; break;
                        case "healthcareProfessionalCareGiverHSAId": anslutning.setVardgivare(event.asCharacters().getData().toString()); break;
                        case "healthcareProfessionalCareUnitHSAId": anslutning.setVardenhet(event.asCharacters().getData().toString()); break;
                        case "healthcareProfessionalOrgUnit": anslutning.setOrganisatoriskenhet(event.asCharacters().getData().toString()); break;
                        case "assessmentCategory": anslutning.setKategori(event.asCharacters().getData().toString()); break;
                    }
                } else if(event.isEndElement()) {
                    System.out.println("EndElement: " + event.toString());
                    System.out.println("-" + event.asEndElement().getName().getLocalPart());
                } else if(event.isEndDocument()) {
                    System.out.println("EndDocument: " + event.toString());
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
