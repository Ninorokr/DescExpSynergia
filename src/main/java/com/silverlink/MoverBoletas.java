package com.silverlink;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;

public class MoverBoletas {

    String ruta = "Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\Paquete 4\\";
    Scanner scanner = new Scanner(Path.of(ruta + "\\listado.txt"));

    ArrayList<String> numsSV;

    public MoverBoletas() throws IOException {
        numsSV = new ArrayList<>();
    }

    private void getNumsSV(){
        numsSV = new ArrayList<>();
        while(scanner.hasNextLine()){
            numsSV.add(scanner.nextLine());
        }
    }

    public void start() throws IOException {
        getNumsSV();
        Files.walkFileTree(Path.of("C:\\Escaner\\2610"), new Boletero(numsSV));
    }

    class Boletero extends SimpleFileVisitor<Path> {

        ArrayList<String> SVs;
        int i = -1;
        String tempDir;

        Boletero(ArrayList<String> svs){
            SVs = svs;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            i++;
            tempDir = ruta + SVs.get(i);
//            Files.createDirectory(Path.of(tempDir));
            Files.copy(file, Path.of(tempDir + "\\PAGO " + SVs.get(i) + ".pdf"), StandardCopyOption.REPLACE_EXISTING);
            return super.visitFile(file, attrs);
        }
    }
}
