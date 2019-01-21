package com.prestashop.tests.Pages;

import com.prestashop.tests.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartIconPage {


    WebDriver driver;

    public CartIconPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath= "//a[@class='cart_block_product_name']")
    public WebElement proName;

    @FindBy(className= "ajax_cart_block_remove_link")
    public WebElement delete;

    @FindBy(xpath= "//span[@class='ajax_cart_no_product']")
    public WebElement title;
}
