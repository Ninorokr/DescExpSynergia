package com.silverlink;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Movedor2 {

    public void start() throws IOException{
        Walker walker = new Walker();
        Path inicio = Path.of("Z:\\Alvaro Giron\\MUNICIPALIDADES");

        System.out.println("Identificando árbol de directorios. Cargando... ");
        Files.walkFileTree(inicio, walker);
        System.out.println("Se ubicaron " + (walker.i - 1) + " carpetas.");

        Mover mover = new Mover(walker.rutas);
        Files.walkFileTree(inicio, mover);
        System.out.println("Se movieron " + (mover.i - 1) + " archivos.");
    }

}

class Walker extends SimpleFileVisitor<Path> {

    HashMap<String, Path> rutas = new HashMap<>();
    int i = 1;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if(dir.getNameCount() == 4){
            String sv = dir.getName(dir.getNameCount()-1).toString();
            rutas.put(sv, dir);
            System.out.println(i++ + ". " + sv + " -> " + dir);
//            i++;
        }

        return super.preVisitDirectory(dir, attrs);
    }
}

class Mover extends SimpleFileVisitor<Path> {

    String filename;

    int i = 1;
    boolean fileType;
    HashMap<String, Path> rutas;

    Mover(HashMap<String, Path> rutas){
        this.rutas = rutas;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.getNameCount() == 3) {
            filename = file.getName(file.getNameCount() - 1).toString();
            fileType = filename.endsWith(".pdf") || filename.endsWith(".PDF") || filename.endsWith(".jpg")
                    || filename.endsWith(".JPG") || filename.endsWith(".JPEG") || filename.endsWith(".jpeg");

            String sv = null;
            try{
                sv = filename.substring(filename.length() - 12).replaceAll("\\D+", "");
            } catch (IndexOutOfBoundsException ioobe){
                System.out.println(ioobe.getMessage());
            }

            try{
                if(rutas.containsKey(sv)){
                    Path ruta = rutas.get(sv);
                    Files.move(file, Path.of(ruta + "\\" + file.getName(file.getNameCount()-1)));
                    System.out.println(i++ + ". " + sv + " -> " + ruta);
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage() + "\n" +
                        "No se pudo mover archivo.");
            }
        }
        return super.visitFile(file, attrs);
    }
}
