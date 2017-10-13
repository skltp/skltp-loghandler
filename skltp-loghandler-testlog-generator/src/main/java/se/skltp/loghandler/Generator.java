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
        builder.append("DEBUG [[vp-services].VPInsecureConnector.receiver.33] org.soitoolkit.commons.mule.messageLogger - soi-toolkit.log");
        builder.append("** logEvent-debug.start ***********************************************************");
        builder.append("IntegrationScenarioId=");
        builder.append("ContractId=");
        builder.append("LogMessage=xresp-out");
        builder.append("ServiceImpl=vagval-dynamic-routing-flow");
        builder.append("Host=ine-sit-app05.sth.basefarm.net (10.8.195.15)");
        builder.append("ComponentId=vp-services");
        builder.append("Endpoint=http://0.0.0.0:8080/vp/clinicalprocess/healthcond/description/GetCareDocumentation/2/rivtabp21");
        builder.append("MessageId=d9d1cf00-a370-11e7-b251-005056a15965");
        builder.append("BusinessCorrelationId=d8e720e0-a370-11e7-b251-005056a15965");
        builder.append("BusinessContextId=");
        builder.append("ExtraInfo=");
        builder.append("-senderIpAdress=10.128.4.1");
        builder.append("-servicecontract_namespace=urn:riv:clinicalprocess:healthcond:description:GetCareDocumentationResponder:2");
        builder.append("-senderid=T_SERVICES_SE165565594230-1062");
        builder.append("-receiverid=SE2321000164-1004");
        builder.append("-endpoint_url=https://esbtest.orebroll.sjunet.org/publicservices/rtp/clinicalprocess/healthcond/description/GetCareDocumentation/2/rivtabp21");
        builder.append("-routerVagvalTrace=(leaf)SE2321000164-1004");
        builder.append("-wsdl_namespace=urn:riv:clinicalprocess:healthcond:description:GetCareDocumentation:2:rivtabp21");
        builder.append("-originalServiceconsumerHsaid=T_SERVICES_SE165565594230-1062");
        builder.append("-source=se.skl.tp.vp.util.LogTransformer");
        builder.append("-rivversion=RIVTABP21");
        builder.append("-time.producer=1494");
        builder.append("-routerBehorighetTrace=(leaf)SE2321000164-1004");
        builder.append("Payload=<?xml version=\" 1.0 \" encoding=\" UTF - 8 \" ?>");
        builder.append("<SOAP-ENV:Envelope xmlns:SOAP-ENV='http://schemas.xmlsoap.org/soap/envelope/' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:s='http://www.w3.org/2001/XMLSchema'>");
        builder.append("<SOAP-ENV:Body></SOAP-ENV:Body>");
        builder.append("</SOAP-ENV:Envelope>");
        builder.append("** logEvent-debug.end");
        return builder.toString();
    }
}
