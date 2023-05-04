package com.silverlink;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;


public class DescargadorAutomatico {

    public void start() throws InterruptedException {
        // declaration and instantiation of objects/variables
//        System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
        //comment the above 2 lines and uncomment below 2 lines to use Chrome
//        System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();

        System.setProperty("webdriver.edge.driver", "D:\\EdgeDriver\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://wsynergia.endesa.es/edelnor/eventhandler?_action_id=CreateUseCaseEvent&_use_case_name=LoginUseCaseFactory");
        WebElement loginID = driver.findElement(By.id("LoginFormFactory__login"));
        WebElement passwordID = driver.findElement(By.id("LoginFormFactory__password"));
        WebElement submitXPath = driver.findElement(By.xpath("/html/body/form/div[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/a"));

        loginID.sendKeys("pe75065898");
        passwordID.sendKeys("04*Nov*2022*");
        submitXPath.click();

        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        WebElement ventasTabID = driver.findElement(By.id("oCMenu_862e7157"));
        ventasTabID.click();
        WebElement solicitudVentaMenuID = driver.findElement(By.id("oCMenu_b995ac40"));
        solicitudVentaMenuID.click();
        WebElement consultaSVMenuID = driver.findElement(By.id("oCMenu_c9fb1c90"));

        consultaSVMenuID.click();

        Thread.sleep(3000);

        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String busquedaSVWindow = driver.getWindowHandle();

        WebElement numSVTxtID = driver.findElement(By.id("ConsultaSolicitudVentaFormFactory__com$synapsis$synergia$vta$ui$groups$ConsultaSolicitudVentaGroupFactory__numeroOrden"));
        numSVTxtID.sendKeys("3182059");
        WebElement buscarBtnID = driver.findElement(By.id("ConsultaSolicitudVentaFormFactory__com$synapsis$synergia$vta$ui$groups$ConsultaDeSolicitudDatosExtraGroupFactory__ConsultaDeSolicitudFilterGroupFactory.Buscar"));
        buscarBtnID.click();
//        Thread.sleep(1000);
        WebElement visualizarLinkID = driver.findElement(By.id("ConsultaSolicitudVentaFormFactory__resultadoBusqueda__1__AdministradorSolicitudVentaUseCaseFactory.visualizar"));
        visualizarLinkID.click();

        WebElement docsAsocBtnID = driver.findElement(By.id("AdministradorSolicitudVentaFormFactory____DocumentosAsociadosGrabarTarget.AdministrarDocumentosAsociadosSolicitudUseCaseFactorybotonCentro"));
        docsAsocBtnID.click();



    }



}
