package com.silverlink;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;

public class LastMerge {

    String ruta2doPaquete = "Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\Subsanaciones" +
                            "\\EXPEDIENTE SJL PAQUETE 2";
    Scanner scanner = new Scanner(Path.of(ruta2doPaquete + "\\SVs2doPaquete.txt"));
//    Scanner userInput = new Scanner(System.in);
    String sv;
    ArrayList<String> archivos;
    ArrayList<String> numsSV;

    boolean flag = true;
    int choice;

//    Flash flash;

    PDFMergerUtility merger;

    public LastMerge() throws IOException {
    }

//    public void test(){
//        getNumsSV();
//        for (String s : numsSV) {
//            System.out.println(s);
//        }
//    }

    public void start() throws IOException, InterruptedException {
        getNumsSV();

        int i = 0;
        for(String sv : numsSV) {
            i++;
            merger = new PDFMergerUtility();
            archivos = new ArrayList<>();
//            sv = scanner.nextLine();
            merger.addSource(ruta2doPaquete + "\\unificados\\uni" + sv + ".pdf");
            Files.walkFileTree(Path.of(ruta2doPaquete + "\\Synergia\\" + sv), new Flash(archivos));

//            while(flag){
//                choice = userInput.nextInt();
//                if(choice == 99){
//                    break;
//                } else {
//                    merger.addSource(archivos.get(choice));
//                }
//            }

            for (String s : archivos) {
                merger.addSource(s);
//                Thread.sleep(1000);
            }

            merger.setDestinationFileName(ruta2doPaquete + "\\finales\\" + sv + ".pdf");
            merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
//            Thread.sleep(1000);
            System.out.println(i + ". " + ruta2doPaquete + "\\finales\\" + sv + ".pdf");

        }
    }

    private void getNumsSV(){
        numsSV = new ArrayList<>();
        while(scanner.hasNextLine()){
            numsSV.add(scanner.nextLine());
        }
    }

    class Flash extends SimpleFileVisitor<Path> {

        ArrayList<String> archivos;
        int i = -1;

        Flash(ArrayList<String> archivos){
            this.archivos = archivos;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            i++;
            //TODO añadir filtro de .PDF (en mayúsculas)
            if(file.getName(file.getNameCount()-1).toString().endsWith(".pdf") || file.getName(file.getNameCount()-1).toString().endsWith(".PDF")){
                archivos.add(file.toString());
//                System.out.println(i + ". " + file.getName(file.getNameCount()-1) +
//                        " | " + Files.size(file)/1024 + "kb");
            }

            return super.visitFile(file, attrs);
        }
    }
}
