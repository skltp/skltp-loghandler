package se.skltp.loghandler.xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by parlin on 2017-10-12.
 */
public class HuvudelementConfig {

    private String element;

    @XmlAttribute
    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
}
