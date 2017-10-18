package se.skltp.loghandler;

import javax.xml.bind.JAXBException;
import java.text.MessageFormat;

/**
 * Created by martul on 2017-10-18.
 */
public class LogGeneratorService {
    private PresetValuesService valuesService = new PresetValuesService();
    private PresetValuesRandomGenerator valuesRandomGenerator;

    public String generateLog() throws JAXBException {
        PresetValuesConfig presetValuesConfig = valuesService.getPresetValues();
        valuesRandomGenerator = new PresetValuesRandomGenerator(presetValuesConfig);

        String template = getLogText();
        String result = MessageFormat.format(template,
                valuesRandomGenerator.getRandomVargivare(), //0
                valuesRandomGenerator.getRandomVardenhet(), //1
                valuesRandomGenerator.getRandomOrganisatoriskenhet(), //2
                valuesRandomGenerator.getRandomTjanstekontrakt(), //3
                valuesRandomGenerator.getRandomKatrgori(), //4 ???
                valuesRandomGenerator.getRandomKallsystem(),  //5
                valuesRandomGenerator.getRandomUrsprungligkonsument(), //6
                valuesRandomGenerator.getRandomDate() //7
        );

        return result;
    }



    private String getLogText() {
        StringBuilder builder = new StringBuilder();
        builder.append("{7} DEBUG [[vp-services].VPInsecureConnector.receiver.33] org.soitoolkit.commons.mule.messageLogger - soi-toolkit.log").append(System.lineSeparator());
        builder.append("** logEvent-debug.start ***********************************************************").append(System.lineSeparator());
        builder.append("IntegrationScenarioId=").append(System.lineSeparator());
        builder.append("ContractId=").append(System.lineSeparator());
        builder.append("LogMessage=xresp-out").append(System.lineSeparator());
        builder.append("ServiceImpl=vagval-dynamic-routing-flow").append(System.lineSeparator());
        builder.append("Host=ine-sit-app05.sth.basefarm.net (10.8.195.15)").append(System.lineSeparator());
        builder.append("ComponentId=vp-services").append(System.lineSeparator());
        builder.append("Endpoint=http://0.0.0.0:8080/vp/clinicalprocess/healthcond/description/{3}/2/rivtabp21").append(System.lineSeparator());
        builder.append("MessageId=d9d1cf00-a370-11e7-b251-005056a15965").append(System.lineSeparator());
        builder.append("BusinessCorrelationId=d8e720e0-a370-11e7-b251-005056a15965").append(System.lineSeparator());
        builder.append("BusinessContextId=").append(System.lineSeparator());
        builder.append("ExtraInfo=").append(System.lineSeparator());
        builder.append("-senderIpAdress=10.128.4.1").append(System.lineSeparator());
        builder.append("-servicecontract_namespace=urn:riv:clinicalprocess:healthcond:description:{3}Responder:2").append(System.lineSeparator());
        builder.append("-senderid=T_SERVICES_SE165565594230-1062").append(System.lineSeparator());
        builder.append("-receiverid=SE2321000164-1004").append(System.lineSeparator());
        builder.append("-endpoint_url=https://esbtest.orebroll.sjunet.org/publicservices/rtp/clinicalprocess/healthcond/description/{3}/2/rivtabp21").append(System.lineSeparator());
        builder.append("-routerVagvalTrace=(leaf)SE2321000164-1004").append(System.lineSeparator());
        builder.append("-wsdl_namespace=urn:riv:clinicalprocess:healthcond:description:{3}:2:rivtabp21").append(System.lineSeparator());
        builder.append("-originalServiceconsumerHsaid={6}").append(System.lineSeparator());
        builder.append("-source=se.skl.tp.vp.util.LogTransformer").append(System.lineSeparator());
        builder.append("-rivversion=RIVTABP21").append(System.lineSeparator());
        builder.append("-time.producer=1494").append(System.lineSeparator());
        builder.append("-routerBehorighetTrace=(leaf)SE2321000164-1004").append(System.lineSeparator());
        builder.append("Payload=<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append(System.lineSeparator());
        builder.append("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:s=\"http://www.w3.org/2001/XMLSchema\">").append(System.lineSeparator());
        builder.append("<SOAP-ENV:Body>");
        builder.append("<{3}Response xmlns=\"urn:riv:clinicalprocess:healthcond:description:{3}Responder:2\">");

        builder.append("<careDocumentation>");
        builder.append("<careDocumentationHeader xmlns=\"urn:riv:clinicalprocess:healthcond:description:2\">");
        builder.append("<documentId>ADOTST_20266082_1_1_1</documentId><sourceSystemHSAid>{5}</sourceSystemHSAid>");
        builder.append("<documentTitle>Journalanteckning</documentTitle><documentTime>20140312110300</documentTime>");
        builder.append("<patientId><id>196201122808</id><type>1.2.752.129.2.1.3.1</type></patientId>");
        builder.append("<accountableHealthcareProfessional>");
        builder.append("<authorTime>20140312110300</authorTime>");
        builder.append("<healthcareProfessionalHSAId>SE2321000164-GOL012</healthcareProfessionalHSAId>");
        builder.append("<healthcareProfessionalName>Göran Olofsson</healthcareProfessionalName>");
        builder.append("<healthcareProfessionalRoleCode>");
        builder.append("<originalText>administratörSEK</originalText>");
        builder.append("</healthcareProfessionalRoleCode>");
        builder.append("<healthcareProfessionalOrgUnit>");
        builder.append("<orgUnitHSAId>{2}</orgUnitHSAId>");
        builder.append("<orgUnitName>Adolfsbergs vårdcentral</orgUnitName>");
        builder.append("<orgUnitTelecom>+46196022900</orgUnitTelecom>");
        builder.append("<orgUnitEmail>adolfsberg.prim@regionorebrolan.se</orgUnitEmail>");
        builder.append("<orgUnitAddress>Primärvården,Box 1613,701 16 Örebro,</orgUnitAddress>");
        builder.append("<orgUnitLocation>ÖREBRO</orgUnitLocation>");
        builder.append("</healthcareProfessionalOrgUnit>");
        builder.append("<healthcareProfessionalCareUnitHSAId>{1}</healthcareProfessionalCareUnitHSAId>");
        builder.append("<healthcareProfessionalCareGiverHSAId>{0}</healthcareProfessionalCareGiverHSAId>");
        builder.append("</accountableHealthcareProfessional>");
        builder.append("<legalAuthenticator>");
        builder.append("<signatureTime>20140312111044</signatureTime><legalAuthenticatorHSAId>SE2321000164-GOL012</legalAuthenticatorHSAId>");
        builder.append("<legalAuthenticatorName>Göran Olofsson</legalAuthenticatorName>");
        builder.append("</legalAuthenticator>");
        builder.append("<approvedForPatient>false</approvedForPatient>");
        builder.append("</careDocumentationHeader>");
        builder.append("<careDocumentationBody xmlns=\"urn:riv:clinicalprocess:healthcond:description:2\">");
        builder.append("<clinicalDocumentNote>");
        builder.append("<clinicalDocumentNoteCode>bes</clinicalDocumentNoteCode>");
        builder.append("<clinicalDocumentNoteText>");
        builder.append("<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?><article xmlns=\"http://docbook.org/ns/docbook\" version=\"5.0\" xml:lang=\"sv\"><section><title>Kontaktorsak</title><para>Att det spritter i kroppen nu när våren är här</para></section><section><title> Muskelfunktion</title><para>Mycket mycket mycket bra</para></section><section><title>Anteckning</title><para>Promenader med hunden</para></section><section><title>Socialt</title><para>har det bra</para></section><section><title>Livsstilsfakta</title><para></para></section><section><title> Tobak</title><para></para></section><section><title>  Typ av tobak</title><para>Röker som en borstbindare</para></section><section><title> Aktivitet</title><para>Över genomsnittet med promenader</para></section><section><title>Aktuella mediciner</title><para>Lugnande</para></section><section><title>Status</title><para></para></section><section><title> Bihålor</title><para>Fina</para></section><section><title> Hjärta</title><para>Slår kraftigt</para></section><section><title> Blodtryck</title><para>120/70 </para></section><section><title> Buk</title><para>lite rund</para></section><section><title>Mål</title><para>Sitta stil mera</para></section><section><title> Diagnos</title><para>R066 Hicka (H) </para></section></article>]]>");
        builder.append("</clinicalDocumentNoteText>");
        builder.append("</clinicalDocumentNote>");
        builder.append("</careDocumentationBody>");
        builder.append("</careDocumentation>");

        builder.append("<result xmlns=\"urn:riv:clinicalprocess:healthcond:description:2.1\"><resultCode>OK</resultCode><logId>WENBO:ENSTEST2012:478347</logId></result>");
        builder.append("</{3}Response></SOAP-ENV:Body>").append(System.lineSeparator());
        builder.append("</SOAP-ENV:Envelope>").append(System.lineSeparator());
        builder.append("** logEvent-debug.end");
        return builder.toString();
    }
}
