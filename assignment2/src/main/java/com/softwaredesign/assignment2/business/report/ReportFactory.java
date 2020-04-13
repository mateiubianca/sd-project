package com.softwaredesign.assignment2.business.report;

public class ReportFactory {

    public Report getReport(String s){
        if(s.equals("txt")){
            return new TxtReport();
        }
        if(s.equals("pdf")){
            return new PdfReport();
        }
        return null;
    }

}
