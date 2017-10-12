package se.skltp.loghandler;

import se.skltp.loghandler.configs.TjanstekontraktSettingsConfig;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by parlin on 2017-10-06.
 */
public class LogpostMocker {

    public static String getMockLogpost() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getLogStart());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(getTjanstekontrakt());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(getUrsprungligkonsument());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(getMockPayload());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(getLogEnd());

        return stringBuilder.toString();
    }

    private static String getLogEnd() {
        return "** logEvent-debug.end";
    }

    private static String getMockPayload() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(TjanstekontraktSettingsConfig.payloadProperty+"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("<SOAP-ENV:Envelope xmlns:SOAP-ENV='http://schemas.xmlsoap.org/soap/envelope/' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:s='http://www.w3.org/2001/XMLSchema'>");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(getSoapBody());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("</SOAP-ENV:Envelope>");

        return stringBuilder.toString();
    }

    private static String getSoapBody() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<SOAP-ENV:Body>");
        stringBuilder.append("<GetCareDocumentationResponse xmlns=\"urn:riv:clinicalprocess:healthcond:description:GetCareDocumentationResponder:2\">");
        stringBuilder.append(getCareDocumentationResponse());
        stringBuilder.append(getCareDocumentationResponse());
        stringBuilder.append("</GetCareDocumentationResponse>");
        stringBuilder.append("</SOAP-ENV:Body>");

        return stringBuilder.toString();
    }

    private static String getCareDocumentationResponse() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<careDocumentation>");
        stringBuilder.append("<careDocumentationHeader xmlns=\"urn:riv:clinicalprocess:healthcond:description:2\">");
        stringBuilder.append("<sourceSystemHSAid>SE2321000164-1004</sourceSystemHSAid>");
        stringBuilder.append("<accountableHealthcareProfessional>");
        stringBuilder.append("<healthcareProfessionalOrgUnit>");
        stringBuilder.append("<orgUnitHSAId>SE2321000164-7381037594780</orgUnitHSAId>");
        stringBuilder.append("</healthcareProfessionalOrgUnit>");
        stringBuilder.append("<healthcareProfessionalCareUnitHSAId>SE2321000164-7381037594780</healthcareProfessionalCareUnitHSAId>");
        stringBuilder.append("<healthcareProfessionalCareGiverHSAId>SE2321000164-7381037590003</healthcareProfessionalCareGiverHSAId>");
        stringBuilder.append("</accountableHealthcareProfessional>");
        stringBuilder.append("</careDocumentationHeader>");
        stringBuilder.append("</careDocumentation>");

        return stringBuilder.toString();
    }

    private static String getUrsprungligkonsument() {
        return TjanstekontraktSettingsConfig.originalConsumerProperty + "Konsument";
    }

    private static String getTjanstekontrakt() {
        return TjanstekontraktSettingsConfig.contractNameProperty + "urn:riv:clinicalprocess:healthcond:description:GetCareDocumentationResponder:2";
    }

    private static String getLogStart() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(TjanstekontraktSettingsConfig.dateFormat);
        return format.format(date) + "DEBUG.";
    }
}
