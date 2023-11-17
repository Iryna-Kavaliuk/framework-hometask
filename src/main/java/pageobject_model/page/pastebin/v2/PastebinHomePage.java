package pageobject_model.page.pastebin.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.*;

import static waits.Waiter.waitForElementLocatedBy;

public class PastebinHomePage {

    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private WebDriver driver;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators for Code, Expiration, Name fields
    By codeTextArea = By.id("postform-text");
    By syntaxHighlightingInput = By.id("select2-postform-format-container");
    By syntaxHighlightingOption = By.xpath("//li[text()='Bash']");
    By expirationInput = By.id("select2-postform-expiration-container");
    By expirationOption = By.xpath("//li[text()='10 Minutes']");
    By nameTitleInput = By.id("postform-name");
    By createNewPasteButton = By.xpath("//button[text() = 'Create New Paste']");
    By termsAndConditionsAgreeButton = By.xpath("//button[@mode='primary']");

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        waitForElementLocatedBy(driver, expirationInput);
        return this;
    }

    public void enterCode(String codeExpression) {
        driver.findElement(codeTextArea).sendKeys(codeExpression);
    }

    public void enterCodeFromFile(String filePath) {
        driver.findElement(codeTextArea).sendKeys(bashCode(filePath));
    }

    private String bashCode(String inputFileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(inputFileName).getFile());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void enterSyntaxHighlighting(){
        waitForElementLocatedBy(driver, expirationInput);
        driver.findElement(syntaxHighlightingInput).click();
        driver.findElement(syntaxHighlightingOption).click();
    }

    public void enterExpiration(){
        driver.findElement(expirationInput).click();
        driver.findElement(expirationOption).click();
    }

    public void enterNameTitle(String nameTitle) {
        driver.findElement(nameTitleInput).sendKeys(nameTitle);
    }

    public PastebinViewPage createNewPaste() {
        driver.findElement(createNewPasteButton).click();
        return new PastebinViewPage(driver);
    }

    public void agreeTermsAndConditions() {
        waitForElementLocatedBy(driver, termsAndConditionsAgreeButton);
        driver.findElement(termsAndConditionsAgreeButton).click();
    }

}
