package com.silverlink;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Desmenusador {

    String origin;
    String root;

    Desmenusador(){
//        origin = "Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\Subsanaciones\\EXPEDIENTE SJL PAQUETE 1\\64 PERMISOS CONEX DOM ENEL 1ER GRUPO 1.pdf";
//        destination = "Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\Subsanaciones\\EXPEDIENTE SJL PAQUETE 1\\autorizaciones";
        root = "D:\\separar";
        origin = root + "\\AUTORIZACIONES N°975 AL N°993.pdf";
    }

    public void start(){
        int i = 1;
        try(PDDocument pdf = Loader.loadPDF(new File(origin))){
            for(PDPage page : pdf.getPages()){
                try(PDDocument autorizacion = new PDDocument()){
                    autorizacion.addPage(page);
                    autorizacion.save(root + "\\" + i++ + ".pdf");
                } catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

}
