package se.skltp.loghandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by martul on 2017-10-17.
 */
public class PresetValuesService {
    public  PresetValuesConfig getPresetValues() {
        PresetValuesConfig presetValuesConfig;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PresetValuesConfig.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            presetValuesConfig = (PresetValuesConfig) jaxbUnmarshaller.unmarshal(getConfigFile());
        } catch (Exception e) {
            presetValuesConfig = generteConfigWithDefaultValues();
        }
        return presetValuesConfig;
    }

    private File getConfigFile(){
        return new File("src/main/resources/PresetValuesConfig.xml"); // todo förbättra
    }

    private PresetValuesConfig generteConfigWithDefaultValues(){
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
