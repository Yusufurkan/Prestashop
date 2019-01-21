package com.prestashop.tests.smoke_tests;


import com.prestashop.tests.utilities.Config;
import com.prestashop.tests.utilities.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class Price extends TestBase {


    @Test(priority = 0)
    public void price() {

       product = driver.findElement(By.xpath("(//a[@class='product-name'])[2]"));

        // get the price info from main page
        String price = driver.findElement(By.xpath("(//span[@itemprop='price'])[4]")).getText();
        String itemName = product.getText();

        product.click(); // click on the second item

        // get the price info from product page
        String verifyPrice = driver.findElement(By.id("our_price_display")).getText();
        String verifyItem = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();

        assertEquals(itemName, verifyItem);
        assertEquals(price, verifyPrice);
    }

    @Test(priority = 1)
    public void details() throws InterruptedException {

        clickRandomProduct();

        String  quantity = driver.findElement(By.cssSelector("input#quantity_wanted")).getAttribute("value");
        assertEquals("1" , quantity);

        Select list = new Select(driver.findElement(By.cssSelector("select#group_1")));
        String size = list.getFirstSelectedOption().getText();
        assertEquals("S" ,size);

        ArrayList<WebElement> options = new ArrayList<WebElement>(list.getOptions());
        ArrayList<String> sizes = new ArrayList<String>();

        Thread.sleep(1000);
        for(int i = 0; i < options.size(); i++){
            sizes.add(options.get(i).getAttribute("title"));
        }

        assertEquals(sizes.get(0), "S");
        assertEquals(sizes.get(1), "M");
        assertEquals(sizes.get(2), "L");

    }

    @Test(priority = 3)
    public void addCart() throws InterruptedException {
        driver.findElement(By.xpath("(//span[.='Add to cart'])[2]")).click();

        Thread.sleep(1000);
        String  succesfullAdd = driver.findElement(By.xpath("//span[@class='cross']//..//h2")).getText();

        assertEquals("Product successfully added to your shopping cart", succesfullAdd);
    }

    @Test(priority = 2)
    public void checkCartQuantity() throws InterruptedException {
        //click on the add to cart button on main page
        driver.findElement(By.xpath("(//span[.='Add to cart'])[2]")).click();
        Thread.sleep(1000);

        //click on the cross sign on the pop up
        driver.findElement(By.xpath("//span[@class='cross']")).click();
        //click cart

        driver.findElement(By.xpath("//b[.='Cart']")).click();
        // check the value
        String amount = driver.findElement(By.id("summary_products_quantity")).getText();

        assertEquals(amount, "1 Product");// verify quantity
    }

//TODO make the size and color dynamic
    @Test(priority = 4)
    public void checkCartSize() throws InterruptedException {

        // get the price info from main page
        String itemName =driver.findElement(By.xpath("(//a[@class='product-name'])[2]")).getText();

       // call the add cart class to reach the pop up
        addCart();

       // get the size
       String size = driver.findElement(By.cssSelector("#layer_cart_product_attributes")).getText();
       assertEquals(size, "Black, S","Chosen product size is not matching!" );

       //verify selected product info matches
        String verifyProductName = driver.findElement(By.cssSelector("#layer_cart_product_title")).getText();

        assertEquals(itemName, verifyProductName, "Chosen product name is not matching!");


    }

    @Test(priority = 6)
    public void login() throws InterruptedException {
        login(Config.getProperty("username"), "password");
        assertEquals(driver.getTitle(), "My account - My Store");
    }

    @Test ( priority = 5)
    public void MyPersonalInfo() throws InterruptedException {
        login();
        waitImp();
        // click on my information button
        driver.findElement(By.xpath("//span[.='My personal information']")).click();  // a[title=Information] < --- CSS

        assertTrue(driver.getTitle().contains("Identity"));

        String firstname = driver.findElement(By.id("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.id("lastname")).getAttribute("value");
        String name = firstname + " " + lastname;

        //get the name informantion next to sign out button
        String verifyname = driver.findElement(By.xpath("//a[@class='account']/span")).getText();

        // vefify name and account info is same
        assertEquals(name, verifyname);

        //click save
        driver.findElement(By.name("submitIdentity")).click();

        //verify the error message
        String eror = driver.findElement(By.xpath("//li[.='The password you entered is incorrect.']/..")).getText();
        assertEquals(eror, "The password you entered is incorrect." );

        // click on back to your account
        driver.findElement(By.xpath("(//a[@class= 'btn btn-default button button-small'])[2]")).click();

        // verify title contains my account
        assertTrue(driver.getTitle().contains("My account"));
        logOut();
    }
    //TODO unit test doesn't work
    @Test(priority = 7)
    public void myAddress() throws InterruptedException {


        driver.findElement(By.className("account")).click();
        waitImp();
       //click on the address
       driver.findElement(By.xpath("//a[@title= 'Addresses']")).click();

       //click on new address
        driver.findElement(By.xpath("(//a[@class= 'btn btn-default button button-medium'])[2]")).click();

        String firstname = driver.findElement(By.id("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.id("lastname")).getAttribute("value");
        String name = firstname + " " + lastname;

        //get the name informantion next to sign out button
        String verifyname = driver.findElement(By.xpath("//a[@class='account']/span")).getText();

        // vefify name and account info is same
        assertEquals(name, verifyname);

        //erase the name
        driver.findElement(By.id("firstname")).clear();

        //click save
        driver.findElement(By.name("submitAddress")).click();

        //verify the error message
        String eror = driver.findElement(By.xpath("//b[.='firstname']/../../..")).getText();

        assertTrue(eror.contains("firstname is required."));

        logOut();
    }
}
