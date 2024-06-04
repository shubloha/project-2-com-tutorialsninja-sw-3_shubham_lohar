package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class MyAccountsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {                   //Open browser
        openBrowser(baseUrl);
    }

    public void selectMyAccountOptions(String option) {
        clickOnElement(By.xpath("//a[normalize-space()='" + option + "']"));
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        //Clickr on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        //Call the method “selectMyAccountOptions” method and pass the parameter Register
        selectMyAccountOptions("Register");
        //Verify the text “Register Account”.
        Assert.assertEquals("Register Account", getTextFromElement(By.xpath("//h1[normalize-space()='Register Account']")));
    }


    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        //Clickr on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        //Call the method “selectMyAccountOptions” method and pass the parameter Login
        selectMyAccountOptions("Login");
        //Verify the text “Returning Customer”.
        Assert.assertEquals("Returning Customer", getTextFromElement(By.xpath("//h2[normalize-space()='Returning Customer']")));
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
        //Clickr on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        selectMyAccountOptions("Register");
        //Enter First Name
        sendTextToElement(By.id("input-firstname"), "Shubham");
        //Enter Last Name
        sendTextToElement(By.id("input-lastname"), "Lohar");
        //Enter Email
        String userName = "" + (int) (Math.random() * Integer.MAX_VALUE);            //Create random username
        String emailID = "User" + userName + "@example.com";
        sendTextToElement(By.id("input-email"), emailID);
        //Enter Telephone
        sendTextToElement(By.id("input-telephone"), "09828127782");
        //Enter Password
        sendTextToElement(By.id("input-password"), "123Sigma123");
        //Enter Password Confirm
        sendTextToElement(By.id("input-confirm"), "123Sigma123");
        //Select Subscribe Yes radio button
        clickOnElement(By.xpath("//label[normalize-space()='Yes']"));
        //Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@name='agree']"));
        //Click on Continue button
        clickOnElement(By.xpath("//input[@value='Continue']"));
        //Verify the message “Your Account Has Been Created!”
        Assert.assertEquals("Your Account Has Been Created!", getTextFromElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")));
        //Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
        //Clickr on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        //Verify the text “Account Logout”
        selectMyAccountOptions("Logout");
        Assert.assertEquals("Account Logout", getTextFromElement(By.xpath("//div[@id='content']//h1")));
        //Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        //Clickr on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        selectMyAccountOptions("Login");
        //Enter Email address
        sendTextToElement(By.id("input-email"), "shubham1010@gmail.com");
        //Enter Password
        sendTextToElement(By.id("input-password"), "123Sigma123");
        //Click on Login button
        clickOnElement(By.xpath("//input[@value='Login']"));
        //Verify text “My Account”
        Assert.assertEquals("My Account", getTextFromElement(By.xpath("//h2[normalize-space()='My Account']")));
        //Clickr on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        selectMyAccountOptions("Logout");
        //Verify the text “Account Logout”
        Assert.assertEquals("Account Logout", getTextFromElement(By.xpath("//div[@id='content']//h1")));
        //Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

    }

    @After
    public void tearDown() {                //Close browser
        closeBrowser();
    }
}
