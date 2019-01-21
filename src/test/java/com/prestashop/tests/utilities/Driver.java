package com.prestashop.tests.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    public static WebDriver driver;

    private Driver() {

    }

    public static WebDriver getDriver(){
        if(driver == null){

            switch (Config.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                  try {
                      throw new WebDriverException();
                  }catch (WebDriverException e){
                      System.out.println("You don't have safari man C'mon.....");
                  }
            }


        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    public static void closeDriver(){
        if(driver!=null)
            driver.close();
    }

    public static void nap(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep exception...");
        }

    }




}
