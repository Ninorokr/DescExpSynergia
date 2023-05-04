package com.silverlink;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;

public class Unificador {

    ArrayList<String> forms = new ArrayList<>();
    ArrayList<String> exps = new ArrayList<>();
    ArrayList<String> fotos = new ArrayList<>();

    //TODO Que el scanner lea el txt y llene una lista antes de recorrer el bucle.

    Inspector inspector;

    String rutaPrincipal = "Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL";
    String rutaCartaPresentacion = rutaPrincipal + "\\1- CARTA PRESENTACION LSF.pdf";
    String ruta2doPaquete = rutaPrincipal + "\\Subsanaciones\\EXPEDIENTE SJL PAQUETE 2";
    String rutaFut = ruta2doPaquete + "\\FORMULARIO SJL_2";
        String suffixFut = "FORMULARIO SJL_SV ";
            int a1 = suffixFut.length();
            int b1 = a1 + 7;
    String rutaCartaYMemoria = ruta2doPaquete + "\\EXPEDIENTE MUNICIPAL SJL_2";
    String rutaFotoYCroquis = ruta2doPaquete + "\\PAQUETE 2 - CROQUIS Y FOTO SJL";
        String suffixFoto = "CROQUIS Y FOTO ";
            int a2 = suffixFoto.length();
            int b2 = a2 + 7;
    String rutaDestino = ruta2doPaquete + "\\unificados";
    PDFMergerUtility merger;

    Scanner scanner = new Scanner(Path.of(ruta2doPaquete + "\\SVs2doPaquete.txt"));

    public Unificador() throws IOException {
    }

    public void unificar() throws IOException{
        for(int i = 0; i < 3; i++) {
            switch(i){
                case 0: Files.walkFileTree(Path.of(rutaFut), new Walker(forms)); break;
                case 1: Files.walkFileTree(Path.of(rutaCartaYMemoria), new Walker(exps)); break;
                case 2: Files.walkFileTree(Path.of(rutaFotoYCroquis), new Walker(fotos)); break;
            }
        }

        String numSV;

        for (int i = 0; i < forms.size(); i++) {
            numSV = scanner.nextLine();
            System.out.println(i+1 + ". " + numSV);
            merger = new PDFMergerUtility();
            merger.addSource(rutaCartaPresentacion);
            merger.addSource(forms.get(i));
            merger.addSource(exps.get(i));
            merger.addSource(fotos.get(i));
            merger.setDestinationFileName(rutaDestino + "\\uni" + numSV + ".pdf");
            merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        }

    }

    public void inspeccionar() throws IOException{
        for(int i = 0; i < 3; i++) {
            switch(i){
                case 0: Files.walkFileTree(Path.of(rutaFut), new Inspector(forms)); break;
                case 1: Files.walkFileTree(Path.of(rutaCartaYMemoria), new Inspector(exps)); break;
                case 2: Files.walkFileTree(Path.of(rutaFotoYCroquis), new Inspector(fotos)); break;
            }
        }

        for (int i = 0; i < 3; i++) {
            switch(i){
                case 0: forms.replaceAll(s -> s.substring(a1, b1)); break;
                case 1: exps.replaceAll(s -> s.substring(0, 7)); break;
                case 2: fotos.replaceAll(s -> s.substring(a2, b2)); break;
            }
        }

        for (int i = 0; i < forms.size(); i++) {
            System.out.println(forms.get(i) + " | " + exps.get(i) + " | " + fotos.get(i));
        }
        if(forms.equals(exps)){
            if(exps.equals(fotos)){
                System.out.println("OK");
            }
        }
    }

    class Walker extends SimpleFileVisitor<Path> {
        
        ArrayList<String> lista;
        
        public Walker(ArrayList<String> listaArchivos){
            lista = listaArchivos;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            lista.add(file.toString());
            return super.visitFile(file, attrs);
        }
    }

    class Inspector extends SimpleFileVisitor<Path>{

        ArrayList<String> namesList = new ArrayList<>();

        public Inspector(ArrayList<String> listaNombres){
            namesList = listaNombres;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            namesList.add(file.getName(file.getNameCount()-1).toString());
            return super.visitFile(file, attrs);
        }
    }
}
