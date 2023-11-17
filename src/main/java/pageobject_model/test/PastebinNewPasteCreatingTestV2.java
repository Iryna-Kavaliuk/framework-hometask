package pageobject_model.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.pastebin.v2.PastebinHomePage;
import pageobject_model.page.pastebin.v2.PastebinViewPage;

public class PastebinNewPasteCreatingTestV2 {

    private WebDriver driver;
    private PastebinHomePage homePage;
    private PastebinViewPage viewPage;
    private static final String PASTE_CODE = "Hello from WebDriver";
    private static final String SYNTAX_HIGHLIGHTING = "Bash";
    private static final String EXPIRATION = "10 Minutes";
    private static final String EXPIRATION_SHORT = "10 MIN";
    private static final String NAME_TITLE = "helloweb";
    private static final String NAME_BASH_TITLE = "how to gain dominance among developers";

    private static final String BASH_CODE_SOURCE = "pastebin-bash-code.txt";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new PastebinHomePage(driver);
    }

    @Test(description = "I can win. Creating a new paste in Pastebin w/o PageFactory approach")
    public void pastebinNewPasteIsCreated() {
        homePage.openPage();
        homePage.agreeTermsAndConditions();
        homePage.enterCode(PASTE_CODE);
        homePage.enterNameTitle(NAME_TITLE);
        homePage.enterExpiration();
        viewPage = homePage.createNewPaste();
        Assert.assertEquals(NAME_TITLE, viewPage.getHeader(), "Wrong Name/title!");
        Assert.assertEquals(EXPIRATION_SHORT, viewPage.getExpiration(), "Wrong expiration value!");
        Assert.assertEquals(PASTE_CODE, viewPage.getPasteCode(), "Wrong pasted code!");
    }

//    @Test(description = "Bring It On. Creating a new Bash paste in Pastebin w/o PageFactory approach")
//    public void pastebinNewBashPasteIsCreated() {
//        homePage.openPage();
////        homePage.agreeTermsAndConditions();
//        homePage.enterCodeFromFile(BASH_CODE_SOURCE);
//        homePage.enterNameTitle(NAME_BASH_TITLE);
//        homePage.enterSyntaxHighlighting();
//        homePage.enterExpiration();
//        viewPage = homePage.createNewPaste();
//        Assert.assertEquals(NAME_TITLE, viewPage.getHeader(), "Wrong Name/title!");
//        Assert.assertEquals(EXPIRATION_SHORT, viewPage.getExpiration(), "Wrong expiration value!");
//        Assert.assertEquals(PASTE_CODE, viewPage.getPasteCode(), "Wrong pasted code!");
//    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
