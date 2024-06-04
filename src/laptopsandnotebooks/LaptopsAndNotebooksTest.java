package laptopsandnotebooks;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        this.openBrowser(baseUrl);
    }
    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException {
        //1.1 Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverToElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
        //1.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']"));
        //1.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"),"Price (High > Low)");
        //1.4 Verify the Product price will arrange in High to Low order.
        List<WebElement> beforeFilterProductPrice = getMultipleElements(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//div[1]//div[2]//div[1]//p//span[@class='price-tax']"));
        //Create arraylist
        List<Double> beforeFilterProductPriceList = new ArrayList<>();
        //Store elements text to array list
        for (WebElement p : beforeFilterProductPrice) {
            String beforeFilterPrice = p.getText().replaceAll("[E,x,T,a,x,£,:,$]", "").replace(",", "");
            Double priceValueBeforeFilter = Double.parseDouble(beforeFilterPrice);
            beforeFilterProductPriceList.add(priceValueBeforeFilter);
        }
        //Sort arraylist to ascending oreder
        Collections.sort(beforeFilterProductPriceList);
        //Reverse the list
        Collections.reverse(beforeFilterProductPriceList);
        //Select Sort By position "Price high to low"
        selectByValueFromDropDown(By.id("input-sort"), "https://tutorialsninja.com/demo/index.php?route=product/category&path=18&sort=p.price&order=DESC");
        Thread.sleep(2000);
        //Store elements after filtering
        List<WebElement> afterFilterProductPrice = getMultipleElements(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//div[1]//div[2]//div[1]//p//span[@class='price-tax']"));
        //Create anothor list to store text of elements after clicking on filter Price high to low
        List<Double> afterFilterProductPriceList = new ArrayList<>();
        for (WebElement s : afterFilterProductPrice) {
            String afterFilterPrice = s.getText().replaceAll("[E,x,T,a,x,£,:,$]", "").replace(",", "");
            Double afterFilterPriceValue = Double.parseDouble(afterFilterPrice);
            afterFilterProductPriceList.add(afterFilterPriceValue);
        }
        //Verify the Product will arrange in Descending order.
        Assert.assertEquals("Products are not sorted in descending order", afterFilterProductPriceList, beforeFilterProductPriceList);

    }
    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {

        //Mouse hover on Currency Dropdown and click
        mouseHoverToElement(By.xpath("//span[normalize-space()='Currency']"));
        //Mouse hover on £Pound Sterling and click
        mouseHoverToElement(By.xpath("//button[normalize-space()='£Pound Sterling']"));
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        mouseHoverToElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
        //2.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']"));
        //2.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"),"Price (High > Low)");
        //2.4 Select Product “MacBook”
        clickOnElement(By.xpath("//a[normalize-space()='Sony VAIO']"));
        //2.5 Verify the text “MacBook”
        Assert.assertEquals("Sony VAIO",getTextFromElement(By.xpath("//h1[normalize-space()='Sony VAIO']")));
        //2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        //2.7 Verify the message “Success: You have added Sony VAIO to your shopping cart!”
        Assert.assertEquals("Success: You have added Sony VAIO to your shopping cart!",getTextFromElement(By.xpath("//div[contains(text(),'Success')]")).substring(0, 56));
        //2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']"));
        //2.9 Verify the text "Shopping Cart"
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        //2.10 Verify the Product name "MacBook"

        Assert.assertEquals("Sony VAIO",getTextFromElement(By.xpath("(//a[contains(text(),'Sony VAIO')])[2]")));
        //2.11 Change Quantity "2"
        driver.findElement(By.xpath("//input[@class='form-control']")).clear();
        sendTextToElement(By.xpath("//input[@class='form-control']"), "2");
        //2.12 Click on “Update” Tab
        clickOnElement(By.xpath("//button[@type='submit']"));
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        Assert.assertEquals("Success: You have modified your shopping cart!", getTextFromElement(By.xpath("//div[contains(text(),'Success')]")).substring(0, 46));
        //2.14 Verify the Total £737.45
        Thread.sleep(3000);
        Assert.assertEquals("£1,472.45", getTextFromElement(By.xpath("//tbody//tr//td[6]")));
        //2.15 Click on “Checkout” button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
        //2.16 Verify the text “Checkout”
        Assert.assertEquals("Checkout", getTextFromElement(By.xpath("//h1[normalize-space()='Checkout']")));
        //2.17 Verify the Text “New Customer”
        Assert.assertEquals("New Customer", getTextFromElement(By.xpath("//h2[normalize-space()='New Customer']")));
        //2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@value='guest']"));
        //2.19 Click on “Continue” tab
        clickOnElement(By.id("button-account"));
        //2.20 Fill the mandatory fields
        sendTextToElement(By.id("input-payment-firstname"), "Shubham");
        sendTextToElement(By.id("input-payment-lastname"), "Lohar");
        sendTextToElement(By.id("input-payment-email"), "shubham123@gmail.com");
        sendTextToElement(By.id("input-payment-telephone"), "09828127782");
        sendTextToElement(By.id("input-payment-address-1"), "62 b422");
        sendTextToElement(By.id("input-payment-city"), "birmingham");
        sendTextToElement(By.id("input-payment-postcode"), "B88E11");
        //2.21 Click on “Continue” Button
        selectByValueFromDropDown(By.id("input-payment-zone"), "3524");
        //2.22 Add Comments About your order into text area
        clickOnElement(By.id("button-guest"));
        //2.23 Check the Terms & Conditions check box
        sendTextToElement(By.xpath("//textarea[@name='comment']"), "Macbook is out of order");
        //2.24 Click on “Continue” button
        clickOnElement(By.id("button-shipping-method"));
        Thread.sleep(1000);
        clickOnElement(By.xpath("//input[@name='agree']"));
        clickOnElement(By.id("button-payment-method"));
        //2.25 Verify the message “Warning: Payment method required!”
        clickOnElement(By.id("button-confirm"));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
