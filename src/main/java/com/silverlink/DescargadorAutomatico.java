package com.silverlink;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DescargadorAutomatico {

    public void start() throws InterruptedException {
        // declaration and instantiation of objects/variables
//        System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
        //comment the above 2 lines and uncomment below 2 lines to use Chrome
//        System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();

        System.setProperty("webdriver.edge.driver", "D:\\EdgeDriver\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new EdgeDriver(options);
        driver.get("https://wsynergia.endesa.es/edelnor/eventhandler?_action_id=CreateUseCaseEvent&_use_case_name=LoginUseCaseFactory");
        WebElement loginID = driver.findElement(By.id("LoginFormFactory__login"));
        WebElement passwordID = driver.findElement(By.id("LoginFormFactory__password"));
        WebElement submitXPath = driver.findElement(By.xpath("/html/body/form/div[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/a"));

        loginID.sendKeys("pe75065898");
        passwordID.sendKeys("27*Abr*2023*");
        submitXPath.click();

        //Store the ID of the original window
//        String ventanaPrincipalSynergia = driver.getWindowHandle();

        WebElement ventasTabID = driver.findElement(By.id("oCMenu_862e7157")); //Ventas
        ventasTabID.click();
        WebElement solicitudVentaMenuID = driver.findElement(By.id("oCMenu_b995ac40")); //Solicitud ventas
        solicitudVentaMenuID.click();
        WebElement consultaSVMenuID = driver.findElement(By.id("oCMenu_c9fb1c90")); //Consulta de SVs
        consultaSVMenuID.click();

        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));

//        Thread.sleep(3000);
//        for (String windowHandle : driver.getWindowHandles()) {
//            System.out.println(windowHandle);
//            if(!ventanaPrincipalSynergia.contentEquals(windowHandle)) {
//                driver.switchTo().window(windowHandle);
//                break;
//            }
//        }

//        String busquedaSVWindow = driver.getWindowHandle();

        WebElement numSVTxtID = driver.findElement(By.id("ConsultaSolicitudVentaFormFactory__com$synapsis$synergia$vta$ui$groups$ConsultaSolicitudVentaGroupFactory__numeroOrden"));
        numSVTxtID.sendKeys("3182059");
        WebElement buscarBtnID = driver.findElement(By.id("ConsultaSolicitudVentaFormFactory__com$synapsis$synergia$vta$ui$groups$ConsultaDeSolicitudDatosExtraGroupFactory__ConsultaDeSolicitudFilterGroupFactory.BuscarbotonCentro"));
//        WebElement buscarBtnID = driver.findElement(By.linkText("Buscar"));
        buscarBtnID.click();
//        Thread.sleep(1000);
        WebElement visualizarLinkID = driver.findElement(By.id("ConsultaSolicitudVentaFormFactory__resultadoBusqueda__1__AdministradorSolicitudVentaUseCaseFactory.visualizar"));
        visualizarLinkID.click();

        WebElement docsAsocBtnID = driver.findElement(By.id("AdministradorSolicitudVentaFormFactory____DocumentosAsociadosGrabarTarget.AdministrarDocumentosAsociadosSolicitudUseCaseFactorybotonCentro"));
        docsAsocBtnID.click();

        windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(2));

//        Thread.sleep(5000);
//        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"documentosVenta_table\"]/tbody/tr[1]")));

//        String docsAsocASVWindow = driver.getWindowHandle();



//        WebElement form = driver.findElement(By.xpath("//form[@id='AdministrarDocumentosAsociadosSolicitudFormFactory']"));
//        form.click();

//        WebElement docsFila1 = driver.findElement(By.xpath("//*[@id=\"documentosVenta_table\"]/tbody/tr[1]"));
//        docsFila1.click();

        WebElement seleccionar1 = driver.findElement(By.xpath("//*[@id=\"AdministrarDocumentosAsociadosSolicitudFormFactory__documentosVenta__1__Seleccionar\"]"));
        seleccionar1.click();

//        WebElement adjuntosFila1 = driver.findElement(By.xpath("//*[@id=\"adjuntos_table\"]/tbody/tr[1]"));
//        adjuntosFila1.click();

        WebElement visualizar1 = driver.findElement(By.id("AdministrarDocumentosAsociadosSolicitudFormFactory__documentoVentaSeleccionado__adjuntos__1__verContenidos.ViewModelContentAction"));
        System.out.println(visualizar1.getAttribute("href"));
        visualizar1.click();
        Thread.sleep(5000);
        driver.switchTo().window(windowHandles.get(2));
        WebElement visualizar2 = driver.findElement(By.id("AdministrarDocumentosAsociadosSolicitudFormFactory__documentoVentaSeleccionado__adjuntos__2__verContenidos.ViewModelContentAction"));
        System.out.println(visualizar2.getAttribute("href"));
        visualizar2.click();
        Thread.sleep(5000);
        driver.switchTo().window(windowHandles.get(2));
        WebElement visualizar3 = driver.findElement(By.id("AdministrarDocumentosAsociadosSolicitudFormFactory__documentoVentaSeleccionado__adjuntos__3__verContenidos.ViewModelContentAction"));
        System.out.println(visualizar3.getAttribute("href"));
        visualizar3.click();
        Thread.sleep(5000);
        driver.switchTo().window(windowHandles.get(2));
        WebElement visualizar4 = driver.findElement(By.id("AdministrarDocumentosAsociadosSolicitudFormFactory__documentoVentaSeleccionado__adjuntos__4__verContenidos.ViewModelContentAction"));
        System.out.println(visualizar4.getAttribute("href"));
        visualizar4.click();
        Thread.sleep(5000);
        driver.switchTo().window(windowHandles.get(2));


    }



}
