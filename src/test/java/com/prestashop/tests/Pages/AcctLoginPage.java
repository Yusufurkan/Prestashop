package com.prestashop.tests.Pages;

import com.prestashop.tests.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AcctLoginPage {

    WebDriver driver;

    public AcctLoginPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="email")
    public WebElement email;

    @FindBy(id="passwd")
    public WebElement password;

    @FindBy(id="SubmitLogin")
    public WebElement signinBtn;

    @FindBy(xpath="//a[@title='My Store']")
    public WebElement mainPage;

    @FindBy(className= "login")
    public WebElement goSignin;


    public void login(String username , String password){
        email.sendKeys(username);
        this.password.sendKeys(password);
        signinBtn.click();
    }

    public void login(){
        login("hyilmaz@gmail.com", "F1234567");
    }

}
