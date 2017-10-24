package se.skltp.loghandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.ConfigurationException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by martul on 2017-10-17.
 */
public class PresetValuesConfigService {
    private static final Logger logger = LogManager.getLogger(PresetValuesConfigService.class);
    private static final String configName = "PresetValuesConfig.xml";


    public PresetValuesConfig getPresetValues() {
        PresetValuesConfig presetValuesConfig;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PresetValuesConfig.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            presetValuesConfig = (PresetValuesConfig) jaxbUnmarshaller.unmarshal(getConfigFile());
        } catch (Exception e) {
            logger.warn("Använder default configuration. Finns ett problem med config fil: " + e);
            presetValuesConfig = generteConfigWithDefaultValues();

        }
        return presetValuesConfig;
    }

    private File getConfigFile() throws ConfigurationException {
        return new File(getConfigDir() + configName);
    }

    private String getConfigDir() throws ConfigurationException {
        String configDir = System.getProperty("configDir");
        if (configDir == null) {
            logger.warn("Finns ingen 'configDir' system property. Använder default configuration.");
            configDir = "src/main/resources/";
        }
        return configDir;
    }


    private PresetValuesConfig generteConfigWithDefaultValues() {
        PresetValuesConfig presetValuesConfig = new PresetValuesConfig();
        presetValuesConfig.addVardgivare("SE2321000164-7381037590003");
        presetValuesConfig.addVardenhet("SE2321000164-7381037594780");
        presetValuesConfig.addUrsprungligkonsument("T_SERVICES_SE165565594230-1062");
        presetValuesConfig.addTjanstekontrakt("GetCareDocumentation");
        presetValuesConfig.addOrganisatoriskenhet("SE2321000164-7381037594780");
        presetValuesConfig.addKatrgori("");
        presetValuesConfig.addKallsystem("SE2321000164-1004");
        return presetValuesConfig;
    }

}
