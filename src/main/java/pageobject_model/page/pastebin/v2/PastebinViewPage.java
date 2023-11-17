package pageobject_model.page.pastebin.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static waits.Waiter.waitForElementLocatedBy;

public class PastebinViewPage {

    private WebDriver driver;

    public PastebinViewPage(WebDriver driver) {
        this.driver = driver;
    }

    By nameTitleHeader = By.xpath("//div[@class='info-top']/h1");
    By expiration = By.className("expire");
    By viewPastedCodeArea = By.className("de1");

    public String getHeader() {
        waitForElementLocatedBy(driver, nameTitleHeader);
        return driver.findElement(nameTitleHeader).getText();
    }

    public String getExpiration() {
        return driver.findElement(expiration).getText();
    }

    public String getPasteCode() {
        return driver.findElement(viewPastedCodeArea).getText();
    }
}
