package se.skltp.loghandler;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * Created by martul on 2017-10-13.
 */
public class Generator {
    static Logger logger = LogManager.getLogger(Generator.class);

    public static void main(String[] args) {
        logger.error(getLogText());
    }

    private static String getLogText() {
        StringBuilder builder = new StringBuilder();
        builder.append("2017-09-27 12:44:34,432 DEBUG [[vp-services].VPInsecureConnector.receiver.33] org.soitoolkit.commons.mule.messageLogger - soi-toolkit.log").append(System.lineSeparator());
        builder.append("** logEvent-debug.start ***********************************************************").append(System.lineSeparator());
        builder.append("IntegrationScenarioId=").append(System.lineSeparator());
        builder.append("ContractId=").append(System.lineSeparator());
        builder.append("LogMessage=xresp-out").append(System.lineSeparator());
        builder.append("ServiceImpl=vagval-dynamic-routing-flow").append(System.lineSeparator());
        builder.append("Host=ine-sit-app05.sth.basefarm.net (10.8.195.15)").append(System.lineSeparator());
        builder.append("ComponentId=vp-services").append(System.lineSeparator());
        builder.append("Endpoint=http://0.0.0.0:8080/vp/clinicalprocess/healthcond/description/GetCareDocumentation/2/rivtabp21").append(System.lineSeparator());
        builder.append("MessageId=d9d1cf00-a370-11e7-b251-005056a15965").append(System.lineSeparator());
        builder.append("BusinessCorrelationId=d8e720e0-a370-11e7-b251-005056a15965").append(System.lineSeparator());
        builder.append("BusinessContextId=").append(System.lineSeparator());
        builder.append("ExtraInfo=").append(System.lineSeparator());
        builder.append("-senderIpAdress=10.128.4.1").append(System.lineSeparator());
        builder.append("-servicecontract_namespace=urn:riv:clinicalprocess:healthcond:description:GetCareDocumentationResponder:2").append(System.lineSeparator());
        builder.append("-senderid=T_SERVICES_SE165565594230-1062").append(System.lineSeparator());
        builder.append("-receiverid=SE2321000164-1004").append(System.lineSeparator());
        builder.append("-endpoint_url=https://esbtest.orebroll.sjunet.org/publicservices/rtp/clinicalprocess/healthcond/description/GetCareDocumentation/2/rivtabp21").append(System.lineSeparator());
        builder.append("-routerVagvalTrace=(leaf)SE2321000164-1004").append(System.lineSeparator());
        builder.append("-wsdl_namespace=urn:riv:clinicalprocess:healthcond:description:GetCareDocumentation:2:rivtabp21").append(System.lineSeparator());
        builder.append("-originalServiceconsumerHsaid=T_SERVICES_SE165565594230-1062").append(System.lineSeparator());
        builder.append("-source=se.skl.tp.vp.util.LogTransformer").append(System.lineSeparator());
        builder.append("-rivversion=RIVTABP21").append(System.lineSeparator());
        builder.append("-time.producer=1494").append(System.lineSeparator());
        builder.append("-routerBehorighetTrace=(leaf)SE2321000164-1004").append(System.lineSeparator());
        builder.append("Payload=<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append(System.lineSeparator());
        builder.append("<SOAP-ENV:Envelope xmlns:SOAP-ENV='http://schemas.xmlsoap.org/soap/envelope/' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:s='http://www.w3.org/2001/XMLSchema'>").append(System.lineSeparator());
        builder.append("<SOAP-ENV:Body></SOAP-ENV:Body>").append(System.lineSeparator());
        builder.append("</SOAP-ENV:Envelope>").append(System.lineSeparator());
        builder.append("** logEvent-debug.end");
        return builder.toString();
    }
}
