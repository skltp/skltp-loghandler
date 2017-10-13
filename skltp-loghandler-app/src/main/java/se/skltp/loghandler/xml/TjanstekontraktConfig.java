package se.skltp.loghandler.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by parlin on 2017-10-10.
 */
public class TjanstekontraktConfig {

    private String name;

    @XmlAttribute
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "kallsystem")
    public KallsystemConfig kallsystemConfig;

    @XmlElement(name = "kategori")
    public KategoriConfig kategoriConfig;

    @XmlElement(name = "vardgivare")
    public VardgivareConfig vardgivareConfig;

    @XmlElement(name = "vardenhet")
    public VardenhetConfig vardenhetConfig;

    @XmlElement(name = "organisatoriskenhet")
    public OrganisatoriskEnhetConfig organisatoriskEnhetConfig;
}
