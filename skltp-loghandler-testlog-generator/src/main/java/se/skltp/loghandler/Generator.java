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
        builder.append("<SOAP-ENV:Body><GetCareDocumentationResponse xmlns=\"urn:riv:clinicalprocess:healthcond:description:GetCareDocumentationResponder:2\"><careDocumentation><careDocumentationHeader xmlns=\"urn:riv:clinicalprocess:healthcond:description:2\"><documentId>ADOTST_20266082_1_1_1</documentId><sourceSystemHSAid>SE2321000164-1004</sourceSystemHSAid><documentTitle>Journalanteckning</documentTitle><documentTime>20140312110300</documentTime><patientId><id>196201122808</id><type>1.2.752.129.2.1.3.1</type></patientId><accountableHealthcareProfessional><authorTime>20140312110300</authorTime><healthcareProfessionalHSAId>SE2321000164-GOL012</healthcareProfessionalHSAId><healthcareProfessionalName>Göran Olofsson</healthcareProfessionalName><healthcareProfessionalRoleCode><originalText>administratörSEK</originalText></healthcareProfessionalRoleCode><healthcareProfessionalOrgUnit><orgUnitHSAId>SE2321000164-7381037594780</orgUnitHSAId><orgUnitName>Adolfsbergs vårdcentral</orgUnitName><orgUnitTelecom>+46196022900</orgUnitTelecom><orgUnitEmail>adolfsberg.prim@regionorebrolan.se</orgUnitEmail><orgUnitAddress>Primärvården,Box 1613,701 16 Örebro,</orgUnitAddress><orgUnitLocation>ÖREBRO</orgUnitLocation></healthcareProfessionalOrgUnit><healthcareProfessionalCareUnitHSAId>SE2321000164-7381037594780</healthcareProfessionalCareUnitHSAId><healthcareProfessionalCareGiverHSAId>SE2321000164-7381037590003</healthcareProfessionalCareGiverHSAId></accountableHealthcareProfessional><legalAuthenticator><signatureTime>20140312111044</signatureTime><legalAuthenticatorHSAId>SE2321000164-GOL012</legalAuthenticatorHSAId><legalAuthenticatorName>Göran Olofsson</legalAuthenticatorName></legalAuthenticator><approvedForPatient>false</approvedForPatient></careDocumentationHeader><careDocumentationBody xmlns=\"urn:riv:clinicalprocess:healthcond:description:2\"><clinicalDocumentNote><clinicalDocumentNoteCode>bes</clinicalDocumentNoteCode><clinicalDocumentNoteText><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?><article xmlns=\"http://docbook.org/ns/docbook\" version=\"5.0\" xml:lang=\"sv\"><section><title>Kontaktorsak</title><para>Att det spritter i kroppen nu när våren är här</para></section><section><title> Muskelfunktion</title><para>Mycket mycket mycket bra</para></section><section><title>Anteckning</title><para>Promenader med hunden</para></section><section><title>Socialt</title><para>har det bra</para></section><section><title>Livsstilsfakta</title><para></para></section><section><title> Tobak</title><para></para></section><section><title>  Typ av tobak</title><para>Röker som en borstbindare</para></section><section><title> Aktivitet</title><para>Över genomsnittet med promenader</para></section><section><title>Aktuella mediciner</title><para>Lugnande</para></section><section><title>Status</title><para></para></section><section><title> Bihålor</title><para>Fina</para></section><section><title> Hjärta</title><para>Slår kraftigt</para></section><section><title> Blodtryck</title><para>120/70 </para></section><section><title> Buk</title><para>lite rund</para></section><section><title>Mål</title><para>Sitta stil mera</para></section><section><title> Diagnos</title><para>R066 Hicka (H) </para></section></article>]]></clinicalDocumentNoteText></clinicalDocumentNote></careDocumentationBody></careDocumentation><result xmlns=\"urn:riv:clinicalprocess:healthcond:description:2.1\"><resultCode>OK</resultCode><logId>WENBO:ENSTEST2012:478347</logId></result></GetCareDocumentationResponse></SOAP-ENV:Body>").append(System.lineSeparator());
        builder.append("</SOAP-ENV:Envelope>").append(System.lineSeparator());
        builder.append("** logEvent-debug.end");
        return builder.toString();
    }
}
