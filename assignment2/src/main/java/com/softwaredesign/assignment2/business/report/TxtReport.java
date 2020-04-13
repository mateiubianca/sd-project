package com.softwaredesign.assignment2.business.report;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class TxtReport implements Report{

    public void generateReport(String orders){
        try{
            PrintWriter writer = new PrintWriter("Report.txt", "UTF-8");
            writer.println(orders);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e){
            System.out.println(e);
        }

    }
}
