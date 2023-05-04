package com.silverlink;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Movedor {

    String destination;

    public void start() throws IOException{
        Files.walkFileTree(Path.of("Z:\\Alvaro Giron\\MUNICIPALIDADES"), new Aber());
    }

    class Aber extends SimpleFileVisitor<Path>{

//        String suffix1 = "AUTORIZACION ";
//        int length1 = suffix1.length();
//        String suffix2 = "CARGO SOLICITUD ";
//        int length2 = suffix2.length();

        String filename;
        String sv;
        String preffix;
//        String destination;
        int i = 0;
        boolean fileType;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
           /*if(file.getNameCount() < 4)*/ if(file.getNameCount() == 3) {
                filename = file.getName(file.getNameCount() - 1).toString();
                fileType = filename.endsWith(".pdf") || filename.endsWith(".PDF") || filename.endsWith(".jpg")
                        || filename.endsWith(".JPG") || filename.endsWith(".JPEG") || filename.endsWith(".jpeg");
                if (fileType) {
                    i++;
                    preffix = filename.substring(0, 5);

                    char ini = 'X';

                    switch (preffix) {
                        case "AUTOR":
                            sv = filename.substring(13, 20);
                            ini = 'A';
                            break;
                        case "CARGO":
                            sv = filename.substring(16, 23);
                            ini = 'C';
                            break;
                        case "OBSER":
                            sv = filename.substring(12, 19);
                            ini = 'O';
                            break;
                        case "RECHA":
                            sv = filename.substring(8, 15);
                            ini = 'R';
                            break;
                        case "RECIB":
                            sv = filename.substring(7, 14);
                            ini = 'B';
                            break;
                        case "SUBSA":
                            sv = filename.substring(12, 19);
                            ini = 'S';
                            break;
                        default: return super.visitFile(file, attrs);

                    }
                    System.out.println(i+ ". " + ini + " - " + sv);
                    Files.walkFileTree(Path.of("Z:\\Alvaro Giron\\MUNICIPALIDADES"), new Seeker(sv));
                    try{
                        Files.copy(file, Path.of("Z:\\" + destination + filename));
                    } catch (FileAlreadyExistsException faee){
                        System.out.println("File already exists ¯\\(o_o)/¯");
                    }

                    System.out.println("Z:\\" + destination);
                }
            }
            return super.visitFile(file, attrs);
        }
    }

    class Seeker extends SimpleFileVisitor<Path>{

        private String sv;
//        public String path;

        Seeker(String sv){
            this.sv = sv;
//            this.path = destination;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if(dir.getName(dir.getNameCount()-1).toString().equals(sv)){
                destination = "";
                for (int i = 0; i < dir.getNameCount(); i++) {
                    destination += dir.getName(i) + "\\";
                }
            }

            return super.preVisitDirectory(dir, attrs);
        }

    }
}
