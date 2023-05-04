package com.silverlink;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Nombrador {

    private String origin = "Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\Subsanaciones\\EXPEDIENTE SJL PAQUETE 1\\autorizaciones";

    public void start() throws IOException{
        Files.walkFileTree(Path.of(origin), new Walker());
    }

    class Walker extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            String sv = file.getName(file.getNameCount()-1).toString().substring(0, 7);
            String newName = "\\AUTORIZACION " + sv + ".pdf";
            Files.copy(file, Path.of(origin + newName));

            return super.visitFile(file, attrs);
        }
    }
}
