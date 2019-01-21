package com.prestashop.tests.functional_tests.cart;

import com.prestashop.tests.utilities.Config;
import com.prestashop.tests.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.prestashop.tests.Pages.*;

import java.util.ArrayList;
import java.util.List;

public class CartLoginTest {
    private static List<Integer> numbers =new ArrayList<Integer>();


    @Test(priority = 0)
    public void loginTest() {
        MainPage mp = new MainPage();
        mp.goTOPage();
        //chose a random product
        randomProChose();

        hoverCart();
        Driver.nap(1000);

        // get the name of the product
        String proName = mp.popUpProName.getText();
        //close tge popup
        mp.popUpCross.click();

        //hover over cart
        hoverCart();
        Driver.nap(1000);

        //verify cart contains product
        Assert.assertTrue(proName.contains(mp.cartProName.getText().replace(".", "")));

        //sign in
        mp.login.click();
        AcctLoginPage crtLog = new AcctLoginPage();
        crtLog.login(Config.getProperty("username"),Config.getProperty("password"));

        hoverCart();

        //verify cart contains product
        Assert.assertTrue(proName.contains(mp.cartProName.getText().replace(".", "")));

        mp.singOut.click();
    }

    @Test(priority = 1)
    public void logout() {
        goTOPage();
        AcctLoginPage acct = new AcctLoginPage();

        acct.goSignin.click();
        acct.login();
        MainPage mp = new MainPage();
        mp.mainPage.click();

        //chose a random product
        randomProChose();

        Driver.nap(2000);
        // get the name of the product
        String proName = mp.popUpProName.getText();
        mp.popUpCross.click();

        //hover over cart
        hoverCart();
        Driver.nap(1000);

        //verify cart contains product
        Assert.assertTrue(proName.contains(mp.cartProName.getText().replace(".", "")));

        mp.singOut.click();
    }


    @Test(priority = 2)
    public void DeleteTest() {
        goTOPage();
        randomProChose();
        MainPage mp = new MainPage();
        mp.popUpCross.click();
        hoverCart();
        Driver.nap(1000);

        CartIconPage cp = new CartIconPage();

        //click on delete
        cp.delete.click();
        //verify is empty displayed
        Driver.nap(1000);
        Assert.assertEquals(cp.title.getText(), "(empty)");

    }

    @Test(priority = 3)
    public void CheckOutDelete() {
        goTOPage();

        MainPage mp = new MainPage();
        randomProChoseUniq();
        mp.popUpContinue.click();
        randomProChoseUniq();
        mp.popUpCheckout.click();
        CartPage cp = new CartPage();

        //verify 2 products are chosen
        Assert.assertEquals(cp.ProContains.getText(), "2 Products");

        //delete item
        cp.deleteItems.click();
        Driver.nap(2000);

        //verify 1 product is chosen
        Assert.assertEquals(cp.ProContains.getText(), "1 Product");

        cp.deleteItems.click();
        Driver.nap(2000);

        //verify 1 product is chosen
        Assert.assertEquals(cp.cartEmptySign.getText(), "Your shopping cart is empty.");


    }


    protected int makeRandom(int start, int end) {
        int dif = end - start + 1;
        return (int) (Math.random() * dif + start);
    }

    private int makeRandomUniq(int start, int end) {
        int result = 0;
        do {
            int dif = end - start + 1;
            result = (int) (Math.random() * dif + start);
            if (numbers.size() == 0) {
                break;
            }

        }
        while (numbers.contains(result));
        numbers.add(result);
        return result;
    }

    public void hoverCart() {
        MainPage mp = new MainPage();
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(mp.cartIcon).perform();


    }

    public void goTOPage() {
        Driver.getDriver().get("http://automationpractice.com/index.php");
    }

    public void randomProChose() {
        MainPage mp = new MainPage();
        WebElement randomAddCart = mp.addCartButtons.get(makeRandom(0, mp.products.size() - 1));
        randomAddCart.click();
    }

    public void randomProChoseUniq() {
        MainPage mp = new MainPage();
        int a = makeRandomUniq(0, mp.products.size() - 1);
        mp.addCartButtons.get(a).click();
    }

}
