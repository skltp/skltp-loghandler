package se.skltp.loghandler;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
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

    @XmlElementWrapper(name="tjanstekontrakterna")
    @XmlElement(name = "tjanstekontrakt")
    List<String> tjanstekontrakt;

    @XmlElementWrapper(name="katrgorier")
    @XmlElement(name = "katrgori")
    List<String> katrgori;

    @XmlElementWrapper(name="kallsystemen")
    @XmlElement(name = "kallsystem")
    List<String> kallsystem;

    @XmlElementWrapper(name="ursprungligkonsumenterna")
    @XmlElement(name = "ursprungligkonsument")
    List<String> ursprungligkonsument;
}
