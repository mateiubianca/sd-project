package com.softwaredesign.assignment2.business.report;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PdfReport implements Report{

    public void generateReport(String orders, String path){

        try{
            String lines[] = orders.split("\n");
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.beginText();
            contentStream.setLeading(14.5f);

            contentStream.newLineAtOffset(25, 700);

            for(int i = 0; i < lines.length; i++){
                contentStream.showText(lines[i]);
                contentStream.newLine();
            }
            //contentStream.showText(lines[0]);
            contentStream.endText();
            contentStream.close();

            document.save(path);
            document.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
