package com.prestashop.tests.utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegisterInfo extends TestBase{

    protected String email = faker.internet().emailAddress();
    public String firstname =faker.name().firstName();
    protected String lastname = faker.name().lastName();
    protected String name = firstname + " " + lastname;
    public String pass = faker.internet().password();
    protected int day = makeRandom(1,31);
    protected int month = makeRandom(1,12);
    protected int year = makeRandom(1901,2019);
    protected String zipcode = faker.address().zipCode().substring(0,5);
    protected String company =faker.company().name();
    protected String address = faker.address().streetAddress();
    protected String cityName = faker.address().cityName();
    protected int state = makeRandom(0,50);
    protected String statechosen;


    public void registrationPage(){

        // go to login page
        driver.findElement(By.className("login")).click();


        //enter the email addres
        driver.findElement(By.id("email_create")).sendKeys(email);

        //click create account
        driver.findElement(By.id("SubmitCreate")).click();
    }

    public void fillForm(){
        registrationPage();
        driver.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);


        driver.findElement(By.id("customer_firstname")).sendKeys(firstname);
        driver.findElement(By.id("customer_lastname")).sendKeys(lastname);


        driver.findElement(By.id("passwd")).sendKeys(pass);

        Select selectD = new Select(driver.findElement(By.id("days")));
        selectD.selectByIndex(day);

        Select selectM = new Select(driver.findElement(By.id("months")));
        selectM.selectByIndex(month);

        Select selectY = new Select(driver.findElement(By.id("years")));
        selectY.selectByValue(""+year);

        //your Address company name
        driver.findElement(By.id("company")).sendKeys(company);

        //your Address address
        driver.findElement(By.id("address1")).sendKeys(address);

        //your Address city
        driver.findElement(By.id("city")).sendKeys(cityName);

        //your addreess country
        Select selectCountry = new Select(driver.findElement(By.id("id_country")));
        selectCountry.selectByIndex(1);


        //your Address zipcode
        driver.findElement(By.id("postcode")).sendKeys(zipcode);

        // your address state
        Select selectState = new Select(driver.findElement(By.id("id_state")));
        List<WebElement> states = new ArrayList<WebElement>(selectState.getOptions());
        selectState.selectByVisibleText(states.get(state).getText());
        statechosen = states.get(state).getText();

        //your Address phone
        driver.findElement(By.id("phone_mobile")).sendKeys(faker.phoneNumber().cellPhone());



    }

    public void fillForm(String name , String pass){
        registrationPage();
        driver.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);


        driver.findElement(By.id("customer_firstname")).sendKeys(name);
        driver.findElement(By.id("customer_lastname")).sendKeys(lastname);


        driver.findElement(By.id("passwd")).sendKeys(pass);

        Select selectD = new Select(driver.findElement(By.id("days")));
        selectD.selectByIndex(day);

        Select selectM = new Select(driver.findElement(By.id("months")));
        selectM.selectByIndex(month);

        Select selectY = new Select(driver.findElement(By.id("years")));
        selectY.selectByValue(""+year);

        //your Address company name
        driver.findElement(By.id("company")).sendKeys(company);

        //your Address address
        driver.findElement(By.id("address1")).sendKeys(address);

        //your Address city
        driver.findElement(By.id("city")).sendKeys(cityName);

        //your addreess country
        Select selectCountry = new Select(driver.findElement(By.id("id_country")));
        selectCountry.selectByIndex(1);


        //your Address zipcode
        driver.findElement(By.id("postcode")).sendKeys(zipcode);

        // your address state
        Select selectState = new Select(driver.findElement(By.id("id_state")));
        List<WebElement> states = new ArrayList<WebElement>(selectState.getOptions());
        selectState.selectByVisibleText(states.get(state).getText());
        statechosen = states.get(state).getText();

        //your Address phone
        driver.findElement(By.id("phone_mobile")).sendKeys(faker.phoneNumber().cellPhone());

        driver.findElement(By.id("submitAccount")).click();

    }

    public String monthsConvert(int month){
        String monthString= "";
        switch (month) {
            case 1:  monthString = "January";
                break;
            case 2:  monthString = "February";
                break;
            case 3:  monthString = "March";
                break;
            case 4:  monthString = "April";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "June";
                break;
            case 7:  monthString = "July";
                break;
            case 8:  monthString = "August";
                break;
            case 9:  monthString = "September";
                break;
            case 10: monthString = "October";
                break;
            case 11: monthString = "November";
                break;
            case 12: monthString = "December";
                break;
        }
        return monthString;
    }
    public void selectRandomSize(){
        Select list = new Select(driver.findElement(By.cssSelector("select#group_1")));
         list.getOptions().get(makeRandom(0,3)).click();

    }



}
