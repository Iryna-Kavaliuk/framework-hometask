package pageobject_model.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.pastebin.v1.PastebinHomePage;
import pageobject_model.page.pastebin.v1.PastebinViewPage;

public class PastebinNewPasteCreatingTestV1 {

    private WebDriver driver;
    private static final String PASTE_CODE = "Hello from WebDriver";
    private static final String EXPIRATION = "10 Minutes";
    private static final String EXPIRATION_SHORT = "10 MIN";
    private static final String NAME_TITLE = "helloweb";

    @BeforeMethod (alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "I can win. Creating a new paste in Pastebin with PageFactory approach")
    public void pastebinNewPasteIsCreated() {
        PastebinViewPage viewPage = new PastebinHomePage(driver)
                .openPage()
                .agreeTermsAndConditions()
                .enterCode(PASTE_CODE)
                .enterNameTitle(NAME_TITLE)
                .enterExpiration(EXPIRATION)
                .createNewPaste();
        Assert.assertEquals(NAME_TITLE, viewPage.getHeader(), "Wrong Name/title!");
        Assert.assertEquals(EXPIRATION_SHORT, viewPage.getExpiration(), "Wrong expiration value!");
        Assert.assertEquals(PASTE_CODE, viewPage.getPasteCode(), "Wrong pasted code!");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
