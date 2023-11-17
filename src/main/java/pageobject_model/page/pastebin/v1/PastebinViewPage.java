package pageobject_model.page.pastebin.v1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static waits.Waiter.waitForElementLocated;

public class PastebinViewPage {

    private WebDriver driver;

    public PastebinViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='info-top']/h1")
    private WebElement nameTitleHeader;

    @FindBy(xpath = "//div[@class='expire']")
    private WebElement expirationValue;

    @FindBy(className = "de1")
    private WebElement viewPastedCodeArea;

    public String getHeader() {
        waitForElementLocated(driver, nameTitleHeader);
        return nameTitleHeader.getText();
    }

    public String getExpiration() {
        return expirationValue.getText();
    }

    public String getPasteCode() {
        return viewPastedCodeArea.getText();
    }
}