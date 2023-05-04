package com.silverlink;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {

    public void downloadUsingStream(){
        String url = "https://wsynergia.endesa.es/edelnor/eventhandler?_action_id=ViewModelContentEvent&_viewContentAction_ID=AdministrarDocumentosAsociadosSolicitudFormFactory__documentoVentaSeleccionado__adjuntos__1__verContenidos.ViewModelContentAction&_form_id=6798";
        String destination = "D:\\pruebaURLdownload\\a";
        downloadUsingStream(url, destination);
    }

    public void downloadUsingStream(String urlStr, String file){

        try{
            URL url = new URL(urlStr);

            try(BufferedInputStream bis = new BufferedInputStream(url.openStream());
                FileOutputStream fis = new FileOutputStream(file)){
                System.out.println("Descargando");
                byte[] buffer = new byte[1024];
                int count=0;
                while((count = bis.read(buffer,0,1024)) != -1)
                {
                    fis.write(buffer, 0, count);
                }
                System.out.println("Archivo descargado");
            } catch (IOException ioe){
                System.out.println("La descarga falló");
                ioe.printStackTrace();
            }
        } catch (MalformedURLException mue){
            mue.printStackTrace();
        }









    }
}
