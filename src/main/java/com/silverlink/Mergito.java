package com.silverlink;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Mergito {

    String source1;
    String source2;
    String destination;

    String root = "Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\Paquete 4";

    Mergito(){
        source1 = root + "\\finales";
        source2 = root + "\\recibos";
        destination = root + "\\finalesCBoleta";
    }

    public void start() throws IOException {
        Files.walkFileTree(Path.of(source1), new Walkercito());
    }

    class Walkercito extends SimpleFileVisitor<Path> {

        PDFMergerUtility merger;
        String archivo2;
        String numSV;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            merger = new PDFMergerUtility();
            merger.addSource(file.toFile());
            numSV = file.getName(file.getNameCount()-1).toString().substring(11, 18);
            archivo2 = source2 + "\\" + "PAGO " + numSV + ".pdf";
            merger.addSource(archivo2);
            merger.setDestinationFileName(destination + "\\EXPEDIENTE " + numSV + ".pdf");
            merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

            return super.visitFile(file, attrs);
        }
    }
}
