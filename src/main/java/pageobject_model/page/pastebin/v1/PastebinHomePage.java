package pageobject_model.page.pastebin.v1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static waits.Waiter.waitForElementLocated;

public class PastebinHomePage {

    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement textAreaCodeExpression;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement expirationInput;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement expirationOption;

    @FindBy(id = "postform-name")
    private WebElement pasteNameTitleInput;

    @FindBy(xpath = "//button[text() = 'Create New Paste']")
    private WebElement createNewPasteButton;

    @FindBy(xpath = "//button[@mode='primary']")
    private WebElement termsAndConditionsAgreeButton;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        waitForElementLocated(driver, createNewPasteButton);
        return this;
    }

    public PastebinHomePage enterCode(String codeExpression) {
        textAreaCodeExpression.sendKeys(codeExpression);
        return this;
    }

    public PastebinHomePage enterExpiration(String expiration) {
        waitForElementLocated(driver, expirationInput);
        expirationInput.click();
        expirationOption.click();
        return this;
    }

    public PastebinHomePage enterNameTitle(String nameTitle) {
        pasteNameTitleInput.sendKeys(nameTitle);
        return this;
    }

    public PastebinViewPage createNewPaste() {
        createNewPasteButton.click();
        return new PastebinViewPage(driver);
    }

    public PastebinHomePage agreeTermsAndConditions() {
        waitForElementLocated(driver, termsAndConditionsAgreeButton);
        termsAndConditionsAgreeButton.click();
        return this;
    }
}
