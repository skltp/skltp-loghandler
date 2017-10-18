package se.skltp.loghandler;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.xml.bind.JAXBException;
import java.text.MessageFormat;


/**
 * Created by martul on 2017-10-13.
 */
public class Application {
    static Logger logger = LogManager.getLogger(Application.class);
    private  static LogGeneratorService logGeneratorService = new LogGeneratorService();


    public static void main(String[] args) throws JAXBException {
        Application application = new Application();
        int antalPoster = args.length > 0 ? Integer.parseInt(args[0]) : 1;
        for (int i = 0; i < antalPoster; i++) {
            logger.error(logGeneratorService.generateLog());
        }
    }


}
