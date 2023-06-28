package com.silverlink;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class MuestraENEL {

    public void start() throws IOException {
        Scanner scanner = new Scanner(new File("D:\\contrastes.txt"));
        Scanner scanner2 = new Scanner(new File("D:\\suministros.txt"));

        ArrayList<String> codbars = new ArrayList<>();
        ArrayList<String> suministros = new ArrayList<>();

        while(scanner.hasNextLine()){
            codbars.add(scanner.nextLine());
        }

        while(scanner2.hasNextLine()){
            suministros.add(scanner2.nextLine());
        }

        String rutaOrigen = "Z:\\Servicios ENEL\\001 - Reparto de avisos de contraste\\23\\";
        String rutaDestino = "Z:\\Servicios ENEL\\001 - Reparto de avisos de contraste\\Muestra OSINERGMIN\\";

        for(int i = 0; i < codbars.size(); i++){
            String codBar = codbars.get(i);
            String nroOS = codBar.substring(5, 9);

            try{
                Files.copy(Path.of(rutaOrigen + nroOS + "\\Cargos\\" + codBar + ".tif"),
                        Path.of(rutaDestino + "SUM " + suministros.get(i) + ".tif"));
                System.out.println(i + ". Se movió el " + codBar);
            } catch(IOException ioe){
                System.out.println(i+ ". No se ubicó el " + codBar);
            }

        }
    }
}
