package se.skltp.loghandler.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by parlin on 2017-10-12.
 */
public class TjanstekontraktDefaultConfig {

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
