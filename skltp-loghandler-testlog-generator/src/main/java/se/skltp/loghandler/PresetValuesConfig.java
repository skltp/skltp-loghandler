package se.skltp.loghandler;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by martul on 2017-10-16.
 */
@XmlRootElement(name = "testLogGenerator")
public class PresetValuesConfig {

    @XmlElementWrapper(name="vardgivarna")
    @XmlElement(name="vardgivare")
    List<String> vardgivare;

    @XmlElementWrapper(name="vardenheterna")
    @XmlElement(name = "vardenhet")
    List<String> vardenhet;

    @XmlElementWrapper(name="organisatoriskenheterna")
    @XmlElement(name = "organisatoriskenhet")
    List<String> organisatoriskenhet;

    @XmlElementWrapper(name="katrgorier")
    @XmlElement(name = "katrgori")
    List<String> katrgori;

    @XmlElementWrapper(name="kallsystemen")
    @XmlElement(name = "kallsystem")
    List<String> kallsystem;

    @XmlElementWrapper(name="ursprungligkonsumenterna")
    @XmlElement(name = "ursprungligkonsument")
    List<String> ursprungligkonsument;


    public void addVardgivare(String value) {
        if (vardgivare == null){
            this.vardgivare = new ArrayList<>();
        }
        this.vardgivare.add(value);
    }

    public void addVardenhet(String value) {
        if (vardenhet == null){
            this.vardenhet = new ArrayList<>();
        }
        this.vardenhet.add(value);
    }

    public void addOrganisatoriskenhet(String value) {
        if (organisatoriskenhet == null){
            this.organisatoriskenhet = new ArrayList<>();
        }
        this.organisatoriskenhet.add(value);
    }


    public void addKatrgori(String value) {
        if (katrgori == null){
            this.katrgori = new ArrayList<>();
        }
        this.katrgori.add(value);
    }

    public void addKallsystem(String value) {
        if (kallsystem == null){
            this.kallsystem = new ArrayList<>();
        }
        this.kallsystem.add(value);
    }

    public void addUrsprungligkonsument(String value) {
        if (ursprungligkonsument == null){
            this.ursprungligkonsument = new ArrayList<>();
        }
        this.ursprungligkonsument.add(value);
    }
}
