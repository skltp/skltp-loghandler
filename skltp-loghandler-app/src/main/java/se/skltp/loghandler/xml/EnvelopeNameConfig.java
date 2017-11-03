package se.skltp.loghandler.xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by martul on 2017-11-03.
 */
public class EnvelopeNameConfig {
    private String property;

    @XmlAttribute
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
