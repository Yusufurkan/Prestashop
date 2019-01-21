package com.prestashop.tests.Pages;

import com.prestashop.tests.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyPage {

    WebDriver driver;

    public MyPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this );
    }

    @FindBy(linkText = "Sign out")
    public WebElement singOut;

    @FindBy(className= "login")
    public WebElement login;


}
