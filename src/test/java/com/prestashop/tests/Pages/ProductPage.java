package com.prestashop.tests.Pages;

import com.prestashop.tests.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    WebDriver driver;

    public ProductPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }


}
