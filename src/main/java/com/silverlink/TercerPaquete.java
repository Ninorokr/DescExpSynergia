package com.silverlink;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class TercerPaquete {
//    Scanner scanner = new Scanner(System.in);

    ArrayList<String> SVSinSolicitud = new ArrayList<>();
    ArrayList<String> SVSinFormulario  = new ArrayList<>();
    Path root = Path.of("Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\Paquete 4\\Paquete 4.5");
    int j = 1;

    public void start() throws IOException{
        Files.walkFileTree(root, new Nancy(SVSinSolicitud, SVSinFormulario));

        System.out.println("SV sin solicitud:");
        for (String sv : SVSinSolicitud) {
            System.out.println(sv);
        }
        System.out.println();

        System.out.println("SV sin formulario");
        for (String form : SVSinFormulario) {
            System.out.println(form);
        }
        System.out.println();
        //TODO recorrer las listas e imprimir cuales son las SV sin solicitud y sin formulario
    }

    private class Nancy extends SimpleFileVisitor<Path>{

        ArrayList<Path> archivosSV;
        ArrayList<String> sinSoli;
        ArrayList<String> sinForm;

        public Nancy(ArrayList<String> sinSoli, ArrayList<String> sinForm){
            this.sinSoli = sinSoli;
            this.sinForm = sinForm;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            archivosSV = new ArrayList<>();
            return super.preVisitDirectory(dir, attrs);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            archivosSV.add(file);
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.println(j++ + ". " + dir);

            int i = 0;
            Map.Entry<Boolean, Integer> soliEntry = containsSolicitud(archivosSV);
            Map.Entry<Boolean, Integer> formEntry = containsFormulario(archivosSV);

            int soliIndex;
            int formIndex;

            try{
//                soliEntry.getKey();
                soliIndex = soliEntry.getValue();
            } catch(NullPointerException npe){
                System.out.println("NPE by Solicitud " + dir.getName(dir.getNameCount()-1));
                return super.postVisitDirectory(dir, exc);
            }

            try{
//                formEntry.getKey();
                formIndex = formEntry.getValue();
            } catch(NullPointerException npe){
                System.out.println("NPE by Formulario " + dir.getName(dir.getNameCount()-1));
                return super.postVisitDirectory(dir, exc);
            }

//            if(soliEntry.getKey() == null)
//                return super.postVisitDirectory(dir, exc);
//
//            if(formEntry.getKey() == null)
//                return super.postVisitDirectory(dir, exc);

//            for (Path archivo : archivosSV){
//                System.out.println(i++ + ". " + archivo.getName(archivo.getNameCount()-1));
//            }

            PDFMergerUtility merger = new PDFMergerUtility();
            merger.addSource("Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\1- CARTA PRESENTACION LSF.pdf");
            merger.addSource(archivosSV.get(formIndex).toFile());
            merger.addSource(archivosSV.get(soliIndex).toFile());

            for(Path archivo : archivosSV){
                if(i == soliIndex | i == formIndex){
                    i++;
                    continue;
                }
                i++;
                merger.addSource(archivo.toFile());
            }

            merger.setDestinationFileName(dir + "\\EXPEDIENTE " + dir.getName(dir.getNameCount()-1) + ".pdf");
            merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

            return super.postVisitDirectory(dir, exc);
        }

        private Map.Entry<Boolean, Integer> containsSolicitud(ArrayList<Path> archivos){
            int i = -1;
            HashMap<Boolean, Integer> map = new HashMap<>();
            for (Path archivo : archivos){
                i++;
//                String preffix = archivo.toString().substring(0, 3);
//                System.out.println(archivo.getName(archivo.getNameCount()-1));
                if(archivo.getName(archivo.getNameCount()-1).toString().startsWith("PM ")){
                    map.put(true, i);
                    for(Map.Entry<Boolean, Integer> entry : map.entrySet()){
                        return entry;
                    }
                }
                if(!archivo.getName(archivo.getNameCount()-1).toString().startsWith("FORMULARIO "))
                    sinSoli.add(archivo.getName(archivo.getNameCount()-1).toString());
//                System.out.println(archivo.getName(archivo.getNameCount() - 1));
            }
            return null;
        }

        private Map.Entry<Boolean, Integer> containsFormulario(ArrayList<Path> archivos) {
            int i = -1;
            HashMap<Boolean, Integer> map = new HashMap<>();
            for (Path archivo : archivos) {
                i++;
//                String preffix = archivo.toString().substring(0, 3);
                if (archivo.getName(archivo.getNameCount()-1).toString().startsWith("FORMULARIO ")){
                    map.put(true, i);
                    for(Map.Entry<Boolean, Integer> entry : map.entrySet()){
                        return entry;
                    }
                }
                if(!archivo.getName(archivo.getNameCount()-1).toString().startsWith("PM "))
                    sinForm.add(archivo.getName(archivo.getNameCount() - 1).toString());
//                System.out.println(archivo.getName(archivo.getNameCount() - 1));
            }
            return null;
        }
    }
}
