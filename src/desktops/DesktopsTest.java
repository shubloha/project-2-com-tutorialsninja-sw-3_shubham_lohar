package desktops;

import com.google.common.base.Verify;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Text;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        this.openBrowser(baseUrl);
    }
    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        //        1.1 Mouse hover on Desktops Tab.and click
        mouseHoverToElement(By.xpath("//a[normalize-space()='Desktops']"));
        //        1.2 Click on “Show All Desktops”
            clickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));
        //        1.3 Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"),"Name (A - Z)");
        //        1.4 Verify the Product will arrange in Descending order.
        List<WebElement> beforeFilterProductNames = getMultipleElements(By.xpath("//div[@class='caption']//h4"));
        //Create arraylist
        List<String> beforeFilterProductNamesList = new ArrayList<>();
        //Store elements text to array list
        for (WebElement p : beforeFilterProductNames) {
            beforeFilterProductNamesList.add(p.getText().toUpperCase());
        }
        //Sort arraylist to ascending oreder
        Collections.sort(beforeFilterProductNamesList);
        //Reverse the list
        Collections.reverse(beforeFilterProductNamesList);
        //Select Sort By position "Name: Z to A"
        selectByValueFromDropDown(By.id("input-sort"), "https://tutorialsninja.com/demo/index.php?route=product/category&path=20&sort=pd.name&order=DESC");
        Thread.sleep(2000);
        //Store elements after filtering
        List<WebElement> afterFilterProductNames = getMultipleElements(By.xpath("//div[@class='caption']//h4"));
        //Create anothor list to store text of elements after clicking on filter Z to A
        List<String> afterFilterProductNamesList = new ArrayList<>();
        for (WebElement s : afterFilterProductNames) {
            afterFilterProductNamesList.add(s.getText().toUpperCase());
        }
        //Verify the Product will arrange in Descending order.
        //Compare both list
        Assert.assertEquals("Products are not sorted in descending order", afterFilterProductNamesList, beforeFilterProductNamesList);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //        2.1 Mouse hover on Currency Dropdown and click

        mouseHoverToElement(By.xpath("//span[normalize-space()='Currency']"));
        //        2.2 Mouse hover on £Pound Sterling and click
        mouseHoverToElement(By.xpath("//button[normalize-space()='£Pound Sterling']"));
        //        2.3 Mouse hover on Desktops Tab.
        mouseHoverToElement(By.xpath("//a[normalize-space()='Desktops']"));
        //        2.4 Click on “Show All Desktops”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));
        //        2.5 Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (A - Z)");
        //        2.6 Select product “HP LP3065”
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[normalize-space()='HP LP3065']"));
        //        2.7 Verify the Text "HP LP3065"
        Assert.assertEquals("HP LP3065", getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']")));
        //        2.8 Select Delivery Date "2023-11-27"
        String year = "2023";
        String month = "November";
        String date = "27";
        clickOnElement(By.xpath("//div[@class='input-group date']//button[@type='button']")); // Open the calendar
        while (true) {
            String monthAndYear = getTextFromElement(By.xpath("//div[@class='datepicker-days']//thead//tr[1]"));
            String[] a = monthAndYear.split(" ");
            String mon = a[1];
            String yer = a[2];
            if (mon.equals(month) && yer.equals(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
            }
        }
        // Select the Date
        List<WebElement> allDates = driver.findElements(By.xpath("//td[contains(@class,'day')]"));
        for (WebElement dt : allDates) {
            if (dt.getText().equals(date)) {
                dt.click();
                break;
            }
        }
       //2.10 Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        Thread.sleep(3000);
        //2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
       // Assert.assertEquals("Success: You have added HP LP3065 to your shopping cart!",getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")));
        //2.12 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//span[normalize-space()='Shopping Cart']"));
        //2.13 Verify the text "Shopping Cart"
        Assert.assertEquals("Shopping Cart",getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).substring(0,13));
        //2.14 Verify the Product name "HP LP3065"
        Assert.assertEquals("HP LP3065",getTextFromElement(By.xpath("(//a[contains(text(),'HP LP3065')])[2]")));
        //2.15 Verify the Delivery Date "2023-11-27"
        Thread.sleep(2000);
        Assert.assertEquals("Delivery Date:2023-11-27",getTextFromElement(By.xpath("//small[normalize-space()='Delivery Date:2023-11-27']")));
        //2.16 Verify the Model "Product21"
        Assert.assertEquals("Product 21",getTextFromElement(By.xpath("//td[normalize-space()='Product 21']")));
        //2.17 Verify the Todat "£74.73"
        Assert.assertEquals("£74.73",getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[6]")));
    }
    @After
    public void tearDown() {
        closeBrowser();
    }

}
