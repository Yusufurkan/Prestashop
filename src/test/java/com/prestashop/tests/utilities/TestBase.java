package com.prestashop.tests.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public abstract class TestBase {
    protected static WebDriver driver=  Driver.getDriver();
    protected WebElement product;
    protected Faker faker = new Faker();
   List<WebElement> products = new ArrayList<WebElement>(driver.findElements(By.xpath("//a[@class='product-name']"))); // first seven(6) is product


    @Ignore
//    @BeforeClass
//    public void setUp(){
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("http://automationpractice.com/index.php");
//
//    }

    @BeforeMethod
    public void pageLoader(){
        driver.get("http://automationpractice.com/index.php");
    }

  // @AfterClass
    public void goPage(){
        driver.quit();
    }

    protected void clickRandomProduct(){
        List<WebElement> products = new ArrayList<WebElement>(driver.findElements(By.xpath("//a[@class='product-name']")));
        products.get(makeRandom(0,6)).click();

    }

    protected void waitImp(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    protected int makeRandom(int start , int end){
        int dif = end-start+1;
        return (int)(Math.random()*dif+start);
    }

    protected void login(String username , String password) throws InterruptedException {

        Thread.sleep(1000);
        //sign in button on main page
        driver.findElement(By.className("login")).click();

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(username);

        WebElement pass = driver.findElement(By.id("passwd"));
        pass.sendKeys(password);

        driver.findElement(By.id("SubmitLogin")).click();
    }
    protected void logOut(){
        driver.findElement(By.className("logout")).click();
    }

    public boolean isOnSale(){
        String oldPrice= driver.findElement(By.id("old_price_display")).getText();
        if(!oldPrice.equals("")) {
            driver.navigate().back();
            return true;
        }
        return false;
    }

    protected void mainPage(){
        driver.get("http://automationpractice.com/index.php");
    }
}
