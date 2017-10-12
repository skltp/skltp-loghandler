package se.skltp.loghandler.xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by parlin on 2017-10-12.
 */
public class DateFormatConfig {
    private String dateregexp;
    private String dateformat;

    @XmlAttribute
    public String getDateregexp() {
        return dateregexp;
    }

    public void setDateregexp(String dateregexp) {
        this.dateregexp = dateregexp;
    }

    @XmlAttribute
    public String getDateformat() {
        return dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }
}
