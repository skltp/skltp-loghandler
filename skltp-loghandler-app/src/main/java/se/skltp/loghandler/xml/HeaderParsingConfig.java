package se.skltp.loghandler.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by parlin on 2017-10-12.
 */
public class HeaderParsingConfig {

    @XmlElement(name = "dateformat")
    public DateFormatConfig dateformatConfig;

    @XmlElement(name = "contractnameproperty")
    public ContractNameConfig contractNameConfig;

    @XmlElement(name = "originalconsumerproperty")
    public OriginalConsumerConfig originalConsumerConfig;

    @XmlElement(name = "payloadproperty")
    public PayloadConfig payloadConfig;

    @XmlElement(name = "envelopeNameproperty")
    public EnvelopeNameConfig envelopeNameConfig;
}
