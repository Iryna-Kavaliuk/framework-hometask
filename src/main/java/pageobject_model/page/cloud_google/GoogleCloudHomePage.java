package pageobject_model.page.cloud_google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static waits.Waiter.waitForElementLocatedBy;

public class GoogleCloudHomePage {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/ ";
    private WebDriver driver;

    public GoogleCloudHomePage(WebDriver driver) {
        this.driver = driver;
    }

    By searchButton = By.xpath("//form[@action = \"https://cloud.google.com/s/results\"]");
    By searchInput = By.name("q");
    By searchResultLink = By.xpath("//div[@class='gs-title']/a");

    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        waitForElementLocatedBy(driver, searchInput);
        return this;
    }

    public void enterSearchTerm(String term) throws InterruptedException {
        driver.findElement(searchButton).click();
        Thread.sleep(3000);
        driver.findElement(searchInput).sendKeys(term);
        driver.findElement(searchInput).submit();
        Thread.sleep(3000);
    }

    public GoogleCloudPricingCalculatorPage selectTargetingLinkFromSearchResults(String term) {
        List<WebElement> searchResultList = driver.findElements(searchResultLink);
        for(WebElement result: searchResultList) {
            String text = result.getText();
            if(text.contains(term)) {
                result.click();
                break;
            }
        }
        return new GoogleCloudPricingCalculatorPage(driver);
    }

}
