package se.skltp.loghandler.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by parlin on 2017-10-10.
 */
@XmlRootElement(name = "tjanstekontrakts")
public class TjanstekontraktsConfig {

    @XmlElement(name = "headerparsingconfig")
    public HeaderParsingConfig headerParsingConfig;

    @XmlElement(name = "tjanstekontraktdefault")
    public TjanstekontraktDefaultConfig tjanstekontraktDefaultConfig;

    @XmlElement(name = "tjanstekontrakt")
    public List<TjanstekontraktConfig> tjanstekontrakts;
}
