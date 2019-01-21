package com.prestashop.tests.Pages;

import com.prestashop.tests.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartPage {

    WebDriver driver;

    public CartPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "summary_products_quantity")
    public WebElement ProContains;

    @FindBy(xpath = "//a[@title='Subtract']")
    public WebElement substract;

    @FindBy(xpath = "//input[@class='cart_quantity_input form-control grey']")
    public WebElement quantityBox;

    @FindBy(xpath = "//td[@class='cart_quantity text-center']/input")
    public WebElement quantityValue;

    @FindBy(className= "cart_quantity_delete")
    public WebElement deleteItems;

    @FindBy(xpath= "//div[@id='center_column']/p")
    public WebElement cartEmptySign;





}
