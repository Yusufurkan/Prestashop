package com.prestashop.tests.functional_tests.login;

import com.prestashop.tests.utilities.*;
import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class PositiveLoginTests extends RegisterInfo {


    @Test(priority = 0)
    public void enterInfo(){
        fillForm();

        //click register
        driver.findElement(By.id("submitAccount")).click();
        // verify name and account info is same
        assertEquals(name, driver.findElement(By.xpath("//a[@class='account']/span")).getText());
    }


    private void goToVerifyPersonalInfoPage(){
        driver.findElement(By.className("account")).click();

        //click on personal info
        driver.findElement(By.xpath("//a[@title='Information']")).click();
    }

    @Test(dependsOnMethods = "enterInfo",priority = 1)
    public void verifyInfo() {
       RegisterInfo aaa = new PositiveLoginTests();
        goToVerifyPersonalInfoPage();

        assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstname);

        assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastname);

        assertEquals(driver.findElement(By.id("email")).getAttribute("value"), email);

        assertEquals(driver.findElement(By.xpath("//div[@id='uniform-days']//span")).getText().trim(), "" + day);

        assertEquals(driver.findElement(By.xpath("//div[@id='uniform-months']//span")).getText().trim(), monthsConvert(month));

        assertEquals(driver.findElement(By.xpath("//div[@id='uniform-years']//span")).getText().trim(), "" + year);

        //go back to page
        driver.findElement(By.partialLinkText("Back to")).click();
        // click on the address
        driver.findElement(By.className("icon-building")).click();

        assertEquals(driver.findElement(By.className("address_address1")).getText(), address);

        assertEquals(driver.findElement(By.xpath("(//div[@class='bloc_adresses row']//div/ul/li)[5]")).getText(), cityName + ", " + statechosen + " " + zipcode);

    }

    @Test(dependsOnMethods = "enterInfo" ,priority = 3)
    public void loginWithSameCredentials() throws InterruptedException {
        driver.findElement(By.className("logout")).click();
        login(email,pass);
        verifyInfo();
        logOut();
    }

    //TODO its not working because when switches to different class due to method driver becomes null
    @Test(priority = 4)
    public void firstNameErrorMessage() throws InterruptedException {
       RegisterInfo noName = new PositiveLoginTests();

        noName.fillForm("", noName.pass);
        Thread.sleep(1000);
        String eror = driver.findElement(By.xpath("//b[.='firstname']/../../..")).getText();

        assertTrue(eror.contains("firstname is required."));
    }

    @Test(priority = 5)
    public void cartQuantityCheck() throws InterruptedException {

        //trying to get the items that is NOT on sale
        do {
            List<WebElement> products = new ArrayList<WebElement>(driver.findElements(By.xpath("//a[@class='product-name']")));
            products.get(makeRandom(0,6)).click();
        }while (isOnSale());

        WebElement quantity = driver.findElement(By.id("quantity_wanted"));
        quantity.clear();
        int purchaseThisMany = makeRandom(2, 5);
        quantity.sendKeys("" +purchaseThisMany);
        waitImp();
        double priceTag = Double.parseDouble(driver.findElement(By.xpath("//span[@id='our_price_display']")).getText().substring(1));
        double total = priceTag* purchaseThisMany + 2;
        driver.findElement(By.id("add_to_cart")).click();

        Thread.sleep(1000);
        String  succesfullAdd = driver.findElement(By.xpath("//span[@class='cross']//..//h2")).getText();

        assertEquals("Product successfully added to your shopping cart", succesfullAdd);
        driver.findElement(By.className("cross")).click();

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//a[@title='View my shopping cart']"))).perform();

        Thread.sleep(1000);
        assertEquals(driver.findElement(By.xpath("//span[@class='price cart_block_total ajax_block_cart_total']")).getText().substring(1), "" + total);
        System.out.println(driver.findElement(By.xpath("//span[@class='price cart_block_total ajax_block_cart_total']")).getText().substring(1));
        System.out.println("" + total);





    }



}
