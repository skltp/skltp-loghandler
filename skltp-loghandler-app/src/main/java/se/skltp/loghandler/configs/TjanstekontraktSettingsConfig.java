package se.skltp.loghandler.configs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import se.skltp.loghandler.xml.HeaderParsingConfig;
import se.skltp.loghandler.xml.TjanstekontraktConfig;
import se.skltp.loghandler.xml.TjanstekontraktDefaultConfig;
import se.skltp.loghandler.xml.TjanstekontraktsConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by parlin on 2017-10-10.
 */
@Configuration
public class TjanstekontraktSettingsConfig {
    private static Logger logger = LogManager.getLogger(TjanstekontraktSettingsConfig.class);
    @Autowired
    private Environment env;

    @Autowired
    private TjanstekontraktsConfig tjanstekontraktsConfig;

    private static final String configName = "tjanstekontraktConfig.xml";

    public static String contractNameProperty;
    public static String originalConsumerProperty;
    public static String dateRegexp;
    public static String dateFormat;
    public static String payloadProperty;

    public static TjanstekontraktDefaultConfig tjanstekontraktDefaultConfig;

    private Map<String, TjanstekontraktConfig> tjanstekontraktsMap;

    @Bean
    public TjanstekontraktsConfig tjanstekontraktsConfig() throws JAXBException {
        String configDir = env.getProperty("spring.config.location");

        File fileNytt;
        if(configDir == null) {
            fileNytt = new File("src/main/resources/" + configName);
        } else {
            fileNytt = new File(configDir + configName);
        }

//        logger.warn("---------------config " + fileNytt.getAbsolutePath());

        JAXBContext jaxbContext = JAXBContext.newInstance(TjanstekontraktsConfig.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        TjanstekontraktsConfig tjanstekontraktsConfigObject = (TjanstekontraktsConfig) jaxbUnmarshaller.unmarshal(fileNytt);

        tjanstekontraktsMap = new HashMap<>();
        for(TjanstekontraktConfig config : tjanstekontraktsConfigObject.tjanstekontrakts) {
            tjanstekontraktsMap.put(config.getName(), config);
        }

        contractNameProperty = tjanstekontraktsConfigObject.headerParsingConfig.contractNameConfig.getProperty();
        originalConsumerProperty = tjanstekontraktsConfigObject.headerParsingConfig.originalConsumerConfig.getProperty();
        dateRegexp = tjanstekontraktsConfigObject.headerParsingConfig.dateformatConfig.getDateregexp();
        dateFormat = tjanstekontraktsConfigObject.headerParsingConfig.dateformatConfig.getDateformat();
        payloadProperty = tjanstekontraktsConfigObject.headerParsingConfig.payloadConfig.getProperty();

        tjanstekontraktDefaultConfig = tjanstekontraktsConfigObject.tjanstekontraktDefaultConfig;

        return tjanstekontraktsConfigObject;
    }

    public TjanstekontraktConfig getTjanstekontraktConfigOnName(String name) {
        return tjanstekontraktsMap.get(name);
    }

    public HeaderParsingConfig getHeaderParsingConfig() {
        return tjanstekontraktsConfig.headerParsingConfig;
    }
}
