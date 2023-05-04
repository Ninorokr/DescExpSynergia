package com.silverlink;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OtroMovedor {

    private Scanner scanner;
    private ArrayList<String> listaSV;
    String root = "Z:\\Alvaro Giron\\MUNICIPALIDADES\\Municipalidad de SJL\\Paquete 3\\";
    String source = root + "finalesCBoleta\\";
    String destination = root;

    public OtroMovedor() throws FileNotFoundException {
        scanner = new Scanner(new File(root + "listado.txt"));
        listaSV = new ArrayList<>();
    }

    public void start(){
        while(scanner.hasNextLine()){
            listaSV.add(scanner.nextLine());
        }
    int i = 1;
        for(String sv : listaSV){
            try{
                Files.copy(Path.of(source + "EXPEDIENTE " + sv + ".pdf"), Path.of(root + sv + "\\" + "EXPEDIENTE " + sv + ".pdf"), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(i++ + ". Copied: " + sv);
            } catch (IOException ioe){
                ioe.printStackTrace();
            }

        }


    }
}
