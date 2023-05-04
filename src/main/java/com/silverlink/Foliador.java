package com.silverlink;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.File;
import java.io.IOException;

public class Foliador {

    public void start(){

        try(PDDocument pdf = Loader.loadPDF(new File("D:\\EXPEDIENTE 3086727.pdf"))){
            for (int i = 0; i < pdf.getNumberOfPages(); i+=2) {
                try(PDPageContentStream ppcs = new PDPageContentStream(pdf, pdf.getPage(i), PDPageContentStream.AppendMode.APPEND, false, true)){
                    ppcs.setStrokingColor(0, 0, 0);
                    float x = pdf.getPage(0).getBBox().getUpperRightX();
                    float y = pdf.getPage(0).getBBox().getUpperRightY();
                    ppcs.addRect(x-(10+30), y-(10+40), 30, 40);
                    ppcs.stroke();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            pdf.save("D:\\EXPEDIENTE 3086727 foliado.pdf");
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    public void comprimir(){
        try(PDDocument pdf = Loader.loadPDF(new File("D:\\EXPEDIENTE 3086727.pdf"))){
            for (int i = 0; i < pdf.getNumberOfPages(); i++) {
                try(PDPageContentStream ppcs = new PDPageContentStream(pdf, pdf.getPage(i), PDPageContentStream.AppendMode.APPEND, false, true)){

                    ppcs.setStrokingColor(0, 0, 0);
                    float x = pdf.getPage(0).getBBox().getUpperRightX();
                    float y = pdf.getPage(0).getBBox().getUpperRightY();
                    ppcs.addRect(x-(10+30), y-(10+40), 30, 40);
                    ppcs.stroke();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            pdf.save("D:\\EXPEDIENTE 3086727 foliado.pdf");
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
