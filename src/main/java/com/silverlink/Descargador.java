package com.silverlink;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;

public class Descargador{

    Scanner txtScanner;
    ArrayList<String> SVs = new ArrayList<>();
    String rutaPrincipal = "Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\" +
            "Subsanaciones\\EXPEDIENTE SJL PAQUETE 2";
    File listadoSVs = new File(rutaPrincipal + "\\SVs2doPaquete.txt");

    public void start() throws IOException{
        getNumsSV();
        int i = 0;

        Scanner userInput = new Scanner(System.in);

//        String sv;
        Path nuevaCarpetaSV;

//        for (int i = 0; i < 31; i++) {
//            sv = txtScanner.nextLine();
//            System.out.println(i+1 + ". " + sv);
//            nuevaCarpetaSV = Files.createDirectory(Path.of(rutaPrincipal + "\\Synergia\\" + sv));
//            userInput.nextLine();
//            Files.walkFileTree(Path.of(rutaPrincipal + "\\tempDescarga"), new Pantera(nuevaCarpetaSV.toString()));
//        }
//    }

        for (String s : SVs) {
            System.out.println(i++ + ". " + s);
            nuevaCarpetaSV = Files.createDirectory(Path.of(rutaPrincipal + "\\Synergia\\" + s));
            userInput.nextLine();
            Files.walkFileTree(Path.of(rutaPrincipal + "\\tempDescarga"), new Pantera(nuevaCarpetaSV.toString()));
        }
    }

    private void getNumsSV() throws IOException{
        txtScanner = new Scanner(listadoSVs);
        SVs = new ArrayList<>();
        while(txtScanner.hasNextLine()){
            SVs.add(txtScanner.nextLine());
        }
    }

    class Pantera extends SimpleFileVisitor<Path> {

        String destino;

        Pantera(String destino){
            this.destino = destino;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.move(file, Path.of(destino + "\\" + file.getName(file.getNameCount()-1)));
            return super.visitFile(file, attrs);
        }
    }
}
