package se.skltp.loghandler.xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by parlin on 2017-10-12.
 */
public class PayloadConfig {
    private String property;

    @XmlAttribute
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
