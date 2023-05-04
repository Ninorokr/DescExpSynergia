package com.silverlink;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class ContadorHojas {

    String ruta;

    public ContadorHojas(){
        ruta = "Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\Paquete 4\\finales";
    }

    public void start() throws IOException {
        Files.walkFileTree(Path.of(ruta), new Hojeador());
    }


    private class Hojeador extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            try(PDDocument tempPDF = Loader.loadPDF(file.toFile())){
                String numSV = file.getName(file.getNameCount()-1).toString().substring(11, 18);
                System.out.println(numSV + "|" + (tempPDF.getNumberOfPages()));
            } catch (IOException ioe){
                ioe.printStackTrace();
            }

            return super.visitFile(file, attrs);
        }
    }
}
