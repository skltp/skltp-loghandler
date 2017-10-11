package se.skltp.loghandler.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.skltp.loghandler.xml.TjanstekontraktConfig;
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

    @Autowired
    private TjanstekontraktsConfig tjanstekontraktsConfig;

    private Map<String, TjanstekontraktConfig> tjanstekontraktsMap;

    @Bean
    public TjanstekontraktsConfig tjanstekontraktsConfig() throws JAXBException {
        File fileNytt = new File("src/main/resources/tjanstekontraktConfig.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(TjanstekontraktsConfig.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        TjanstekontraktsConfig tjanstekontraktsConfigObject = (TjanstekontraktsConfig) jaxbUnmarshaller.unmarshal(fileNytt);

        tjanstekontraktsMap = new HashMap<>();
        for(TjanstekontraktConfig config : tjanstekontraktsConfigObject.tjanstekontrakts) {
            tjanstekontraktsMap.put(config.getName(), config);
        }

        return tjanstekontraktsConfigObject;
    }

    public boolean kontraktShoulBeSaved(String name) {
        return tjanstekontraktsMap.containsKey(name);
    }
}
