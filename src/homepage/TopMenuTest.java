package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    //This method should click on the menu whatever name is passed as parameter.
    public void setUp() {
        this.openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        List<WebElement> topMenu = driver.findElements(By.xpath("//a[@class='see-all']"));


        for (WebElement e : topMenu) {
            if (e.getText().equalsIgnoreCase(menu)) {
                e.click();
                break;
            }
        }
    }


    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {

        //1.1 Mouse hover on “Desktops” Tab and click
        mouseHoverToElement(By.xpath("//a[normalize-space()='Desktops']"));

        //1.2 call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("Show AllDesktops");
        //1.3 Verify the text ‘Desktops’
        Assert.assertEquals("Desktops",getTextFromElement(By.xpath("//h2[normalize-space()='Desktops']")));

    }


    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
       // 2.1 Mouse hover on “Laptops & Notebooks” Tab and click

      mouseHoverToElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
        //2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");
        //2.3 Verify the text ‘Laptops & Notebooks’
        Assert.assertEquals("Laptops & Notebooks",getTextFromElement(By.xpath("//h2[normalize-space()='Laptops & Notebooks']")));
    }
    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        //3.1 Mouse hover on “Components” Tab and click
        mouseHoverToElement(By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Components']"));
        //3.2 call selectMenu method and pass the menu = “Show All Components”
        selectMenu("Show AllComponents");
        //3.3 Verify the text ‘Components’
        Assert.assertEquals("Components",getTextFromElement(By.xpath("//h2[normalize-space()='Components']")));

    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}

