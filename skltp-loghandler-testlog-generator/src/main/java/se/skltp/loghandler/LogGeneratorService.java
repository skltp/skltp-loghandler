package se.skltp.loghandler;


/**
 * Created by martul on 2017-10-18.
 */
public class LogGeneratorService {
    private PresetValuesRandomSelector valuesRandomGenerator;


    public LogGeneratorService() {
        PresetValuesConfigService valuesService = new PresetValuesConfigService();
        valuesRandomGenerator = new PresetValuesRandomSelector(valuesService.getPresetValues());
    }

    public String generateRandomLog() {
        int rnd = valuesRandomGenerator.getRandomCount(2);
        if (rnd == 0) return  generateGetCareDocumentationLog();
        else return generateGetFunctionalStatusLog();
        //    return generateGetFunctionalStatusLog();

    }


    public String generateGetCareDocumentationLog() {
        String tjanstekontrakt = "GetCareDocumentation";

        StringBuilder builder = new StringBuilder();
        builder.append(valuesRandomGenerator.getRandomDate()).append(" DEBUG [[vp-services].VPInsecureConnector.receiver.33] org.soitoolkit.commons.mule.messageLogger - soi-toolkit.log").append(System.lineSeparator());
        builder.append("** logEvent-debug.start ***********************************************************").append(System.lineSeparator());
        builder.append("IntegrationScenarioId=").append(System.lineSeparator());
        builder.append("ContractId=").append(System.lineSeparator());
        builder.append("LogMessage=xresp-out").append(System.lineSeparator());
        builder.append("ServiceImpl=vagval-dynamic-routing-flow").append(System.lineSeparator());
        builder.append("Host=ine-sit-app05.sth.basefarm.net (10.8.195.15)").append(System.lineSeparator());
        builder.append("ComponentId=vp-services").append(System.lineSeparator());
        builder.append("Endpoint=http://0.0.0.0:8080/vp/clinicalprocess/healthcond/description/").append(tjanstekontrakt).append("/2/rivtabp21").append(System.lineSeparator());
        builder.append("MessageId=d9d1cf00-a370-11e7-b251-005056a15965").append(System.lineSeparator());
        builder.append("BusinessCorrelationId=d8e720e0-a370-11e7-b251-005056a15965").append(System.lineSeparator());
        builder.append("BusinessContextId=").append(System.lineSeparator());
        builder.append("ExtraInfo=").append(System.lineSeparator());
        builder.append("-senderIpAdress=10.128.4.1").append(System.lineSeparator());
        builder.append("-servicecontract_namespace=urn:riv:clinicalprocess:healthcond:description:").append(tjanstekontrakt).append("Responder:2").append(System.lineSeparator());
        builder.append("-senderid=T_SERVICES_SE165565594230-1062").append(System.lineSeparator());
        builder.append("-receiverid=SE2321000164-1004").append(System.lineSeparator());
        builder.append("-endpoint_url=https://esbtest.orebroll.sjunet.org/publicservices/rtp/clinicalprocess/healthcond/description/").append(tjanstekontrakt).append("/2/rivtabp21").append(System.lineSeparator());
        builder.append("-routerVagvalTrace=(leaf)SE2321000164-1004").append(System.lineSeparator());
        builder.append("-wsdl_namespace=urn:riv:clinicalprocess:healthcond:description:").append(tjanstekontrakt).append(":2:rivtabp21").append(System.lineSeparator());
        builder.append("-originalServiceconsumerHsaid=").append(valuesRandomGenerator.getRandomUrsprungligkonsument()).append(System.lineSeparator());
        builder.append("-source=se.skl.tp.vp.util.LogTransformer").append(System.lineSeparator());
        builder.append("-rivversion=RIVTABP21").append(System.lineSeparator());
        builder.append("-time.producer=1494").append(System.lineSeparator());
        builder.append("-routerBehorighetTrace=(leaf)SE2321000164-1004").append(System.lineSeparator());
        builder.append("Payload=<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append(System.lineSeparator());
        builder.append("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:s=\"http://www.w3.org/2001/XMLSchema\">").append(System.lineSeparator());
        builder.append("<SOAP-ENV:Body>");
        builder.append("<").append(tjanstekontrakt).append("Response xmlns=\"urn:riv:clinicalprocess:healthcond:description:").append(tjanstekontrakt).append("Responder:2\">");
        for (int i = 0; i < valuesRandomGenerator.getRandomCount(30); i++) {
            builder.append("<careDocumentation>");
            builder.append("<careDocumentationHeader xmlns=\"urn:riv:clinicalprocess:healthcond:description:2\">");
            builder.append("<documentId>ADOTST_20266082_1_1_1</documentId><sourceSystemHSAid>").append(valuesRandomGenerator.getRandomKallsystem()).append("</sourceSystemHSAid>");
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
            builder.append("<orgUnitHSAId>").append(valuesRandomGenerator.getRandomOrganisatoriskenhet()).append("</orgUnitHSAId>");
            builder.append("<orgUnitName>Adolfsbergs vårdcentral</orgUnitName>");
            builder.append("<orgUnitTelecom>+46196022900</orgUnitTelecom>");
            builder.append("<orgUnitEmail>adolfsberg.prim@regionorebrolan.se</orgUnitEmail>");
            builder.append("<orgUnitAddress>Primärvården,Box 1613,701 16 Örebro,</orgUnitAddress>");
            builder.append("<orgUnitLocation>ÖREBRO</orgUnitLocation>");
            builder.append("</healthcareProfessionalOrgUnit>");
            builder.append("<healthcareProfessionalCareUnitHSAId>").append(valuesRandomGenerator.getRandomVardenhet()).append("</healthcareProfessionalCareUnitHSAId>");
            builder.append("<healthcareProfessionalCareGiverHSAId>").append(valuesRandomGenerator.getRandomVargivare()).append("</healthcareProfessionalCareGiverHSAId>");
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
        }
        builder.append("<result xmlns=\"urn:riv:clinicalprocess:healthcond:description:2.1\"><resultCode>OK</resultCode><logId>WENBO:ENSTEST2012:478347</logId></result>");
        builder.append("</").append(tjanstekontrakt).append("Response></SOAP-ENV:Body>").append(System.lineSeparator());
        builder.append("</SOAP-ENV:Envelope>").append(System.lineSeparator());
        builder.append("** logEvent-debug.end");
        return builder.toString();
    }

    public String generateGetFunctionalStatusLog() {
        String tjanstekontrakt = "GetFunctionalStatus";

        StringBuilder builder = new StringBuilder();
        builder.append(valuesRandomGenerator.getRandomDate()).append(" DEBUG [[vp-services].VPInsecureConnector.receiver.33] org.soitoolkit.commons.mule.messageLogger - soi-toolkit.log").append(System.lineSeparator());
        builder.append("** logEvent-debug.start ***********************************************************").append(System.lineSeparator());
        builder.append("IntegrationScenarioId=").append(System.lineSeparator());
        builder.append("ContractId=").append(System.lineSeparator());
        builder.append("LogMessage=xreq-in").append(System.lineSeparator());
        builder.append("ServiceImpl=vagval-dynamic-routing-flow").append(System.lineSeparator());
        builder.append("Host=ine-sit-app05.sth.basefarm.net (10.8.195.15)").append(System.lineSeparator());
        builder.append("ComponentId=vp-services").append(System.lineSeparator());
        builder.append("Endpoint=http://0.0.0.0:8080/vp/clinicalprocess/healthcond/description/").append(tjanstekontrakt).append("/2/rivtabp21").append(System.lineSeparator());
        builder.append("MessageId=18fc5f20-ba1d-11e7-a3e0-005056a15965").append(System.lineSeparator());
        builder.append("BusinessCorrelationId=18fc5f24-ba1d-11e7-a3e0-005056a15965").append(System.lineSeparator());
        builder.append("BusinessContextId=").append(System.lineSeparator());
        builder.append("ExtraInfo=").append(System.lineSeparator());
        builder.append("-senderIpAdress=192.36.204.158").append(System.lineSeparator());
        builder.append("-servicecontract_namespace=urn:riv:clinicalprocess:healthcond:description:").append(tjanstekontrakt).append("Responder:2").append(System.lineSeparator());
        builder.append("-senderid=T_SERVICES_SE165565594230-10KD").append(System.lineSeparator());
        builder.append("-receiverid=5565594230").append(System.lineSeparator());
        builder.append("-httpXForwardedProto=https").append(System.lineSeparator());
        builder.append("-wsdl_namespace=urn:riv:clinicalprocess:healthcond:description:").append(tjanstekontrakt).append(":2:rivtabp21").append(System.lineSeparator());
        builder.append("-originalServiceconsumerHsaid=").append(valuesRandomGenerator.getRandomUrsprungligkonsument()).append(System.lineSeparator());
        builder.append("-httpXForwardedHost=qa.esb.ntjp.se").append(System.lineSeparator());
        builder.append("-source=se.skl.tp.vp.util.LogTransformer").append(System.lineSeparator());
        builder.append("-rivversion=RIVTABP21").append(System.lineSeparator());
        builder.append("-httpXForwardedPort=443").append(System.lineSeparator());
        builder.append("Payload=<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:riv:clinicalprocess:healthcond:description:GetFunctionalStatusResponder:2\" xmlns:urn1=\"urn:riv:clinicalprocess:healthcond:description:2\">\n" +
                "<soapenv:Header></soapenv:Header>" +
                "<soapenv:Body>" +
                "<urn:GetFunctionalStatusResponse>");

        builder.append("<urn:functionalStatusAssessment>" +
                "<urn1:functionalStatusAssessmentHeader>" +
                "<urn1:documentId>JOL-MOCK-GFS-02-01</urn1:documentId>" +
                "<urn1:sourceSystemHSAId>").append(valuesRandomGenerator.getRandomKallsystem()).append("</urn1:sourceSystemHSAId>" +
                "<urn1:documentTime>20160202110410</urn1:documentTime>" +
                "<urn1:patientId>" +
                "<urn1:id>193601286499</urn1:id>" +
                "<urn1:type>1.2.752.129.2.1.3.1</urn1:type>" +
                "</urn1:patientId>" +
                "<urn1:accountableHealthcareProfessional>" +
                "<urn1:authorTime>20160201110410</urn1:authorTime>" +
                "<urn1:healthcareProfessionalHSAId>FÖRFATTARENS_HSA_1</urn1:healthcareProfessionalHSAId>" +
                "<urn1:healthcareProfessionalName>healthcareProfessionalName</urn1:healthcareProfessionalName>" +
                "<urn1:healthcareProfessionalRoleCode>" +
                "<urn1:code>EXPL</urn1:code>" +
                "<urn1:codeSystem>2.16.840.1.113883.5.1002</urn1:codeSystem>" +
                "<urn1:codeSystemName>codeSystemName></urn1:codeSystemName>" +
                "<urn1:codeSystemVersion>codeSystemVersion</urn1:codeSystemVersion>" +
                "<urn1:displayName>displayName</urn1:displayName>" +
                "</urn1:healthcareProfessionalRoleCode>" +
                "<urn1:healthcareProfessionalOrgUnit>" +
                "<urn1:orgUnitHSAId>").append(valuesRandomGenerator.getRandomOrganisatoriskenhet()).append("</urn1:orgUnitHSAId>" +
                "<urn1:orgUnitName>orgUnitName</urn1:orgUnitName>" +
                "<urn1:orgUnitTelecom>orgUnitTelecom</urn1:orgUnitTelecom>" +
                "<urn1:orgUnitEmail>orgUnitEmail</urn1:orgUnitEmail>" +
                "<urn1:orgUnitAddress>orgUnitAddress</urn1:orgUnitAddress>" +
                "<urn1:orgUnitLocation>orgUnitLocation</urn1:orgUnitLocation>" +
                "</urn1:healthcareProfessionalOrgUnit>" +
                "<urn1:healthcareProfessionalCareUnitHSAId>").append(valuesRandomGenerator.getRandomVardenhet()).append("</urn1:healthcareProfessionalCareUnitHSAId>" +
                "<urn1:healthcareProfessionalCareGiverHSAId>").append(valuesRandomGenerator.getRandomVargivare()).append("</urn1:healthcareProfessionalCareGiverHSAId>" +
                "</urn1:accountableHealthcareProfessional>" +
                "<urn1:legalAuthenticator>" +
                "<urn1:signatureTime>20160203110410</urn1:signatureTime>" +
                "<urn1:legalAuthenticatorHSAId>legalAuthenticatorHSAId</urn1:legalAuthenticatorHSAId>" +
                "<urn1:legalAuthenticatorName>legalAuthenticatorName</urn1:legalAuthenticatorName>" +
                "</urn1:legalAuthenticator>" +
                "<urn1:approvedForPatient>true</urn1:approvedForPatient>" +
                "<urn1:careContactId>careContactId</urn1:careContactId>" +
                "</urn1:functionalStatusAssessmentHeader>" +
                "<urn1:functionalStatusAssessmentBody>" +
                "<urn1:assessmentCategory>").append(valuesRandomGenerator.getRandomKatrgori()).append("</urn1:assessmentCategory>" +
                "<urn1:comment>comment</urn1:comment>" +
                "<urn1:disability>" +
                "<urn1:disabilityAssessment>" +
                "<urn1:code>b3101</urn1:code>" +
                "<urn1:codeSystem>1.2.752.116.1.1.3.3.1</urn1:codeSystem>" +
                "<urn1:codeSystemName>ICF</urn1:codeSystemName>" +
                "<urn1:codeSystemVersion>1</urn1:codeSystemVersion>" +
                "<urn1:displayName>Röstkvalitet</urn1:displayName>" +
                "</urn1:disabilityAssessment>" +
                "<urn1:comment>Funktioner för att producera röstkarakteristika innefattande tonhöjd, resonans och andra drag </urn1:comment>" +
                "</urn1:disability>" +
                "</urn1:functionalStatusAssessmentBody>" +
                "<urn1:relation>" +
                "<urn1:code>" +
                "<urn1:code>code</urn1:code>" +
                "<urn1:codeSystem>codeSystem</urn1:codeSystem>" +
                "<urn1:codeSystemName>codeSystemName</urn1:codeSystemName>" +
                "<urn1:codeSystemVersion>codeSystemVersion</urn1:codeSystemVersion>" +
                "<urn1:displayName>displayName</urn1:displayName>" +
                "<urn1:originalText>originalText</urn1:originalText>" +
                "</urn1:code>" +
                "<urn1:referredInformation>" +
                "<urn1:id>" +
                "<urn1:root>root</urn1:root>" +
                "<urn1:extension>extension</urn1:extension>" +
                "</urn1:id>" +
                "<urn1:time>20120102122322</urn1:time>" +
                "<urn1:type>type</urn1:type>" +
                "<urn1:informationOwner>" +
                "<urn1:id>" +
                "<urn1:root>root</urn1:root>" +
                "<urn1:extension>extension</urn1:extension>" +
                "</urn1:id>" +
                "</urn1:informationOwner>" +
                "</urn1:referredInformation>" +
                "</urn1:relation>" +
                "</urn:functionalStatusAssessment>").append(System.lineSeparator());

//                "<urn:functionalStatusAssessment>" +
//                "<urn1:functionalStatusAssessmentHeader>" +
//                "<urn1:documentId>JOL-MOCK-GFS-02-02</urn1:documentId>" +
//                "<urn1:sourceSystemHSAId>").append(valuesRandomGenerator.getRandomKallsystem()).append("</urn1:sourceSystemHSAId>" +
//                "<urn1:documentTime>20150424110410</urn1:documentTime>" +
//                "<urn1:patientId>" +
//                "<urn1:id>193601286499</urn1:id>" +
//                "<urn1:type>1.2.752.129.2.1.3.1</urn1:type>" +
//                "</urn1:patientId>" +
//                "<urn1:accountableHealthcareProfessional>" +
//                "<urn1:authorTime>20160202110410</urn1:authorTime>" +
//                "<urn1:healthcareProfessionalCareUnitHSAId>").append(valuesRandomGenerator.getRandomVardenhet()).append("</urn1:healthcareProfessionalCareUnitHSAId>" +
//                "<urn1:healthcareProfessionalCareGiverHSAId>").append(valuesRandomGenerator.getRandomVargivare()).append("</urn1:healthcareProfessionalCareGiverHSAId>" +
//                "</urn1:accountableHealthcareProfessional>" +
//                "<urn1:approvedForPatient>true</urn1:approvedForPatient>" +
//                "</urn1:functionalStatusAssessmentHeader>" +
//                "<urn1:functionalStatusAssessmentBody>" +
//                "<urn1:assessmentCategory>").append(valuesRandomGenerator.getRandomKatrgori()).append("</urn1:assessmentCategory>" +
//                "<urn1:disability>" +
//                "<urn1:disabilityAssessment>" +
//                "<urn1:originalText>Talrytm</urn1:originalText>" +
//                "</urn1:disabilityAssessment>" +
//                "</urn1:disability>" +
//                "</urn1:functionalStatusAssessmentBody>" +
//                "</urn:functionalStatusAssessment>" +
//                //---------------------
//                "<urn:functionalStatusAssessment>" +
//                "<urn1:functionalStatusAssessmentHeader>" +
//                "<urn1:documentId>JOL-MOCK-GFS-02-03</urn1:documentId>" +
//                "<urn1:sourceSystemHSAId>").append(valuesRandomGenerator.getRandomKallsystem()).append("</urn1:sourceSystemHSAId>" +
//                "<urn1:documentTime>20160203110410</urn1:documentTime>" +
//                "<urn1:patientId>" +
//                "<urn1:id>193601286499</urn1:id>" +
//                "<urn1:type>1.2.752.129.2.1.3.1</urn1:type>" +
//                "</urn1:patientId>" +
//                "<urn1:accountableHealthcareProfessional>" +
//                "<urn1:authorTime>20160203110410</urn1:authorTime>" +
//                "<urn1:healthcareProfessionalHSAId>HSATEST2-CNTkgr</urn1:healthcareProfessionalHSAId>" +
//                "<urn1:healthcareProfessionalName>Berit Gudmundsdottir</urn1:healthcareProfessionalName>" +
//                "<urn1:healthcareProfessionalRoleCode>" +
//                "<urn1:code>EXPL</urn1:code>" +
//                "<urn1:codeSystem>2.16.840.1.113883.5.1002</urn1:codeSystem>" +
//                "<urn1:codeSystemName>KV Befattning></urn1:codeSystemName>" +
//                "<urn1:codeSystemVersion>version 2.3</urn1:codeSystemVersion>" +
//                "<urn1:displayName>ÖverDoktor</urn1:displayName>" +
//                "</urn1:healthcareProfessionalRoleCode>" +
//                "<urn1:healthcareProfessionalOrgUnit>" +
//                "<urn1:orgUnitHSAId>").append(valuesRandomGenerator.getRandomOrganisatoriskenhet()).append("</urn1:orgUnitHSAId>" +
//                "<urn1:orgUnitName>Vårdcentral 1</urn1:orgUnitName>" +
//                "<urn1:orgUnitTelecom>054-123456</urn1:orgUnitTelecom>" +
//                "<urn1:orgUnitEmail>vardcentral@mail.se</urn1:orgUnitEmail>" +
//                "<urn1:orgUnitAddress>Goda Hopps Udd Vägen 23</urn1:orgUnitAddress>" +
//                "<urn1:orgUnitLocation>Väringe</urn1:orgUnitLocation>" +
//                "</urn1:healthcareProfessionalOrgUnit>" +
//                "<urn1:healthcareProfessionalCareUnitHSAId>").append(valuesRandomGenerator.getRandomVardenhet()).append("</urn1:healthcareProfessionalCareUnitHSAId>" +
//                "<urn1:healthcareProfessionalCareGiverHSAId>").append(valuesRandomGenerator.getRandomVargivare()).append("</urn1:healthcareProfessionalCareGiverHSAId>" +
//                "</urn1:accountableHealthcareProfessional>" +
//                "<urn1:legalAuthenticator>" +
//                "<urn1:signatureTime>20160203122322</urn1:signatureTime>" +
//                "<urn1:legalAuthenticatorHSAId>legalAuthenticatorHSAId</urn1:legalAuthenticatorHSAId>" +
//                "<urn1:legalAuthenticatorName>Doktor Berit</urn1:legalAuthenticatorName>" +
//                "</urn1:legalAuthenticator>" +
//                "<urn1:approvedForPatient>true</urn1:approvedForPatient>" +
//                "<urn1:careContactId>careContactId</urn1:careContactId>" +
//                "</urn1:functionalStatusAssessmentHeader>" +
//                "<urn1:functionalStatusAssessmentBody>" +
//                "<urn1:assessmentCategory>").append(valuesRandomGenerator.getRandomKatrgori()).append("</urn1:assessmentCategory>" +
//                "<urn1:comment>comment</urn1:comment>" +
//                "<urn1:disability>" +
//                "<urn1:disabilityAssessment>" +
//                "<urn1:code>xxxyyy</urn1:code>" +
//                "<urn1:codeSystem>1.2.752.116.1.1.3.3.1</urn1:codeSystem>" +
//                "<urn1:codeSystemName>ICF</urn1:codeSystemName>" +
//                "<urn1:codeSystemVersion>1</urn1:codeSystemVersion><urn1:displayName>text</urn1:displayName>" +
//                "</urn1:disabilityAssessment>" +
//                "<urn1:comment>text</urn1:comment>" +
//                "</urn1:disability>" +
//                "</urn1:functionalStatusAssessmentBody>" +
//                "<urn1:relation>" +
//                "<urn1:code>" +
//                "<urn1:code>code</urn1:code>" +
//                "<urn1:codeSystem>codeSystem</urn1:codeSystem>" +
//                "<urn1:codeSystemName>codeSystemName</urn1:codeSystemName>" +
//                "<urn1:codeSystemVersion>codeSystemVersion</urn1:codeSystemVersion>" +
//                "<urn1:displayName>displayName</urn1:displayName>" +
//                "<urn1:originalText>originalText</urn1:originalText>" +
//                "</urn1:code>" +
//                "<urn1:referredInformation>" +
//                "<urn1:id>" +
//                "<urn1:root>root</urn1:root>" +
//                "<urn1:extension>extension</urn1:extension>" +
//                "</urn1:id>" +
//                "<urn1:time>20120102122322</urn1:time>" +
//                "<urn1:type>type</urn1:type>" +
//                "<urn1:informationOwner>" +
//                "<urn1:id>" +
//                "<urn1:root>root</urn1:root>" +
//                "<urn1:extension>extension</urn1:extension>" +
//                "</urn1:id>" +
//                "</urn1:informationOwner>" +
//                "</urn1:referredInformation>" +
//                "</urn1:relation>" +
//                "</urn:functionalStatusAssessment>" +
//                //--------------------
//                "<urn:functionalStatusAssessment>" +
//                "<urn1:functionalStatusAssessmentHeader>" +
//                "<urn1:documentId>JOL-MOCK-GFS-02-11</urn1:documentId>" +
//                "<urn1:sourceSystemHSAId>").append(valuesRandomGenerator.getRandomKallsystem()).append("</urn1:sourceSystemHSAId>" +
//                "<urn1:documentTime>20160211110410</urn1:documentTime>" +
//                "<urn1:patientId>" +
//                "<urn1:id>193601286499</urn1:id>" +
//                "<urn1:type>1.2.752.129.2.1.3.1</urn1:type>" +
//                "</urn1:patientId>" +
//                "<urn1:accountableHealthcareProfessional>" +
//                "<urn1:authorTime>20160212110410</urn1:authorTime>" +
//                "<urn1:healthcareProfessionalHSAId>HSATEST2-CNTkgr</urn1:healthcareProfessionalHSAId>" +
//                "<urn1:healthcareProfessionalName>Martin Luther</urn1:healthcareProfessionalName>" +
//                "<urn1:healthcareProfessionalRoleCode>" +
//                "<urn1:code>EXPL</urn1:code>" +
//                "<urn1:codeSystem>2.16.840.1.113883.5.1002</urn1:codeSystem>" +
//                "<urn1:codeSystemName>KV Befattning</urn1:codeSystemName>" +
//                "<urn1:codeSystemVersion>version 2.3</urn1:codeSystemVersion>" +
//                "<urn1:displayName>Läkare</urn1:displayName>" +
//                "</urn1:healthcareProfessionalRoleCode>" +
//                "<urn1:healthcareProfessionalOrgUnit>" +
//                "<urn1:orgUnitHSAId>").append(valuesRandomGenerator.getRandomOrganisatoriskenhet()).append("</urn1:orgUnitHSAId>" +
//                "<urn1:orgUnitName>Distrikt9</urn1:orgUnitName>" +
//                "<urn1:orgUnitTelecom>+46 (0)54 112233</urn1:orgUnitTelecom>" +
//                "<urn1:orgUnitEmail>orgunit@test.se</urn1:orgUnitEmail>" +
//                "<urn1:orgUnitAddress>Älvbrinken 3</urn1:orgUnitAddress>" +
//                "<urn1:orgUnitLocation>Korpilombolo</urn1:orgUnitLocation>" +
//                "</urn1:healthcareProfessionalOrgUnit>" +
//                "<urn1:healthcareProfessionalCareUnitHSAId>").append(valuesRandomGenerator.getRandomVardenhet()).append("</urn1:healthcareProfessionalCareUnitHSAId>" +
//                "<urn1:healthcareProfessionalCareGiverHSAId>").append(valuesRandomGenerator.getRandomVargivare()).append("</urn1:healthcareProfessionalCareGiverHSAId>" +
//                "</urn1:accountableHealthcareProfessional>" +
//                "<urn1:legalAuthenticator>" +
//                "<urn1:signatureTime>20160213110410</urn1:signatureTime>" +
//                "<urn1:legalAuthenticatorHSAId>HSATEST2-CNTkgr</urn1:legalAuthenticatorHSAId>" +
//                "<urn1:legalAuthenticatorName>Martin Luther</urn1:legalAuthenticatorName>" +
//                "</urn1:legalAuthenticator>" +
//                "<urn1:approvedForPatient>true</urn1:approvedForPatient>" +
//                "<urn1:careContactId>VARDKONTAKT_1</urn1:careContactId>" +
//                "</urn1:functionalStatusAssessmentHeader>" +
//                //----------------
//                "<urn1:functionalStatusAssessmentBody>" +
//                "<urn1:assessmentCategory>").append(valuesRandomGenerator.getRandomKatrgori()).append("</urn1:assessmentCategory>" +
//                "<urn1:comment>Trevlig och hjälpsam brukare</urn1:comment>" +
//                "<urn1:padl>" +
//                "<urn1:typeOfAssessment>" +
//                "<urn1:originalText>personlig hygien</urn1:originalText>" +
//                "</urn1:typeOfAssessment>" +
//                "<urn1:assessment>text</urn1:assessment>" +
//                "</urn1:padl>" +
//                "<urn1:padl>" +
//                "<urn1:typeOfAssessment>" +
//                "<urn1:originalText>födointag</urn1:originalText>" +
//                "</urn1:typeOfAssessment>" +
//                "<urn1:assessment>text</urn1:assessment>" +
//                "</urn1:padl>" +
//                "<urn1:padl>" +
//                "<urn1:typeOfAssessment>" +
//                "<urn1:originalText>På/avklädning</urn1:originalText>" +
//                "</urn1:typeOfAssessment>" +
//                "<urn1:assessment>text</urn1:assessment>" +
//                "</urn1:padl>" +
//                "<urn1:padl>" +
//                "<urn1:typeOfAssessment>" +
//                "<urn1:originalText>Förflyttning</urn1:originalText>" +
//                "</urn1:typeOfAssessment>" +
//                "<urn1:assessment>text</urn1:assessment>" +
//                "</urn1:padl>" +
//                "<urn1:padl>" +
//                "<urn1:typeOfAssessment>" +
//                "<urn1:originalText>Toalettbesök</urn1:originalText>" +
//                "</urn1:typeOfAssessment>" +
//                "<urn1:assessment>text </urn1:assessment>" +
//                "</urn1:padl>" +
//                "</urn1:functionalStatusAssessmentBody>" +
//                "</urn:functionalStatusAssessment>" +
//                //------------------
//                "<urn:functionalStatusAssessment>" +
//                "<urn1:functionalStatusAssessmentHeader>" +
//                "<urn1:documentId>JOL-MOCK-GFS-02-12</urn1:documentId>" +
//                "<urn1:sourceSystemHSAId>").append(valuesRandomGenerator.getRandomKallsystem()).append("</urn1:sourceSystemHSAId>" +
//                "<urn1:documentTime>20160212110410</urn1:documentTime>" +
//                "<urn1:patientId>" +
//                "<urn1:id>193601286499</urn1:id>" +
//                "<urn1:type>1.2.752.129.2.1.3.1</urn1:type>" +
//                "</urn1:patientId>" +
//                "<urn1:accountableHealthcareProfessional>" +
//                "<urn1:authorTime>20160212110410</urn1:authorTime>" +
//                "<urn1:healthcareProfessionalCareUnitHSAId>").append(valuesRandomGenerator.getRandomVardenhet()).append("</urn1:healthcareProfessionalCareUnitHSAId>" +
//                "<urn1:healthcareProfessionalCareGiverHSAId>").append(valuesRandomGenerator.getRandomVargivare()).append("</urn1:healthcareProfessionalCareGiverHSAId>" +
//                "</urn1:accountableHealthcareProfessional>" +
//                "<urn1:approvedForPatient>true</urn1:approvedForPatient>" +
//                "</urn1:functionalStatusAssessmentHeader>" +
//                "<urn1:functionalStatusAssessmentBody>" +
//                "<urn1:assessmentCategory>").append(valuesRandomGenerator.getRandomKatrgori()).append("</urn1:assessmentCategory>" +
//                "<urn1:comment>Trevlig och hjälpsam brukare</urn1:comment>" +
//                "<urn1:padl>" +
//                "<urn1:typeOfAssessment>" +
//                "<urn1:originalText>födointag</urn1:originalText>" +
//                "</urn1:typeOfAssessment>" +
//                "<urn1:assessment>Behöver hjälp.</urn1:assessment>" +
//                "</urn1:padl>" +
//                "</urn1:functionalStatusAssessmentBody>" +
//                "</urn:functionalStatusAssessment>" +
        builder.append("<urn:result>" +
                "<urn1:resultCode>OK</urn1:resultCode>" +
                "<urn1:logId>1</urn1:logId>" +
                "</urn:result>" +
                "</urn:GetFunctionalStatusResponse>" +
                "</soapenv:Body>").append(System.lineSeparator());
        builder.append("</soapenv:Envelope>").append(System.lineSeparator());
        builder.append("* logEvent-debug.end ************************************************************");
        return builder.toString();
    }
}
