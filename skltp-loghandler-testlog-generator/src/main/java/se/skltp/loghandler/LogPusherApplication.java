package se.skltp.loghandler;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.xml.bind.JAXBException;

/**
 * Created by martul on 2017-10-13.
 */
public class LogPusherApplication {
    private static Logger logger = LogManager.getLogger(LogPusherApplication.class);
    private static LogGeneratorService logGeneratorService = new LogGeneratorService();


    public static void main(String[] args) throws JAXBException {
        int antalPoster = Integer.parseInt(System.getProperty("n", "1"));
        for (int i = 0; i < antalPoster; i++) {
            logger.info(logGeneratorService.generateLog());
        }
    }
}
