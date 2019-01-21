package com.prestashop.tests.Pages;

import com.prestashop.tests.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class MainPage {

     WebDriver driver;

    public MainPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this );
    }

    @FindBy(xpath = "//ul[@id='homefeatured']/li//a[@class='product-name']")
    public List<WebElement> products;


    @FindBy(xpath = "//ul[@id='homefeatured']/li//span[.='Add to cart']")
    public List<WebElement> addCartButtons;

    @FindBy(className= "cross")
    public WebElement popUpCross;

    @FindBy(id= "layer_cart_product_title")
    public WebElement popUpProName;

    @FindBy(xpath= "//a[@title='View my shopping cart']")
    public WebElement cartIcon;

    @FindBy(xpath= "//a[@class='cart_block_product_name']")
    public WebElement cartProName;

    @FindBy(className= "login")
    public WebElement login;

    @FindBy(xpath="//a[@title='My Store']")
    public WebElement mainPage;

    @FindBy(linkText = "Sign out")
    public WebElement singOut;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    public WebElement popUpContinue;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    public WebElement popUpCheckout;





    public void goTOPage(){
        driver.get(" http://automationpractice.com/index.php");
    }



}
