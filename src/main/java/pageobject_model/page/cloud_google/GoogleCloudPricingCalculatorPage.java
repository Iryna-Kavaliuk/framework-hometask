package pageobject_model.page.cloud_google;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import waits.Waiter;

import java.util.List;

public class GoogleCloudPricingCalculatorPage {
    private WebDriver driver;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    By cookiesAlert = By.className("devsite-snackbar-action");
    By productTypeTabs = By.xpath("//md-tab-item[@role='tab']/div/div/div[@class='name']/span");
    By cloudSiteFrame = By.xpath("//article[@id='cloud-site']/devsite-iframe/iframe");
    By myResourcesFrame = By.id("myFrame");
    By numberOfInstancesInput = By.xpath("//input[@type='number']");
    By operatingSystemsInput = By.xpath("//label[text() = 'Operating System / Software']/../descendant::span/div[@class='md-text']");
    By provisioningModelInput = By.xpath("//label[text() = 'Provisioning model']/../descendant::span/div[@class='md-text']");
    By seriesInput = By.xpath("//label[text() = 'Series']/../descendant::span/div[@class='md-text ng-binding']");
    By machineTypeInput = By.xpath("//label[text() = 'Machine type']/../descendant::span/div[@class='md-text ng-binding']");
    By addGPUsCheckbox = By.xpath("//*[@aria-label='Add GPUs']/div");
    By gpuTypeInput = By.xpath("//md-select[@aria-label = 'GPU type']");
    By gpuNumberInput = By.xpath("//md-select[@placeholder = 'Number of GPUs']/md-select-value/span");
    By localSSDInput = By.xpath("//label[text() = 'Local SSD']/../descendant::span/div[@class='md-text ng-binding']");
    By dataCenterLocationInput = By.xpath("//label[text() = 'Datacenter location']/../descendant::span/div[@class='md-text ng-binding']");
    By committedUsageInput = By.xpath("//label[text() = 'Committed usage']/../descendant::span/div[@class='md-text']");
    By hoursInput = By.xpath("//input[@name='hours']");
    By addToEstimateButton = By.xpath("//button[text()[contains(., 'Add to Estimate')]]");
    String gpuNumberSelectContainer = "//div[contains(@id, 'select_container')]//md-option[contains(@ng-repeat, 'gpuType') and @value='%s']/div[1]";

    By estimationResultContent = By.xpath("//md-card-content[@id='resultBlock']//h2[@class='md-flex ng-binding ng-scope']");
    By estimationResultFullContent = By.xpath("//*[@id=\"resultBlock\"]/md-card//b[@class='ng-binding']");

    public void closeCookiesAlert() {
        driver.findElement(cookiesAlert).click();
    }

    public void activateProductType(String productType) {
        List<WebElement> productTypeList = driver.findElements(productTypeTabs);
        for (WebElement pt : productTypeList) {
            String text = pt.getText();
            if (text.contains(productType)) {
                pt.click();
                break;
            }
        }
        driver.switchTo().frame(driver.findElement(cloudSiteFrame)).switchTo().frame(driver.findElement(myResourcesFrame));
    }

    public void setNumberOfInstances(String number) {
        Waiter.waitForElementLocatedBy(driver, numberOfInstancesInput);
        driver.findElement(numberOfInstancesInput).sendKeys(number);
    }

    public void setOperatingSystems(String operatingSystem) {
        Waiter.waitForElementLocatedBy(driver, operatingSystemsInput);
        driver.findElement(operatingSystemsInput).click();
        String locator = buildLocatorByText(operatingSystem);
        WebElement osChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, osChoice);
        driver.switchTo().activeElement();
        osChoice.click();
    }

    public void setProvisioningModel(String provisioningModel) {
        Waiter.waitForElementLocatedBy(driver, provisioningModelInput);
        driver.findElement(provisioningModelInput).click();
        String locator = buildLocatorByText(provisioningModel);
        WebElement provisioningModelChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, provisioningModelChoice);
        provisioningModelChoice.click();
    }

    public void setSeries(String series) {
        scrollToElement(operatingSystemsInput);
        driver.findElement(seriesInput).click();
        String locator = buildLocatorByText(series);
        WebElement seriesChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, seriesChoice);
        driver.switchTo().activeElement();
        seriesChoice.click();
    }

    public void setMachineType(String machineType) {
        driver.findElement(machineTypeInput).click();
        String locator = buildLocatorByText(machineType);
        WebElement machineTypeChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, machineTypeChoice);
        clickToElement(By.xpath(locator));
    }

    public void checkAddGPUsCheckbox() {
        driver.findElement(addGPUsCheckbox).click();
    }

    public void setGpuType(String gpuType) {
        driver.switchTo().activeElement();
        driver.findElement(gpuTypeInput).click();
        String locator = buildLocatorByText(gpuType);
        WebElement gpuTypeChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, gpuTypeChoice);
        gpuTypeChoice.click();
    }

    public void setGpuNumber(String gpuNumber) {
        Waiter.waitForElementLocatedBy(driver, gpuNumberInput);
        driver.findElement(gpuNumberInput).click();
        String locator = String.format(gpuNumberSelectContainer, gpuNumber);
        WebElement gpuNumberChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, gpuNumberChoice);
        gpuNumberChoice.click();
    }

    public void setLocalSSD(String LocalSSD) {
        Waiter.waitForElementLocatedBy(driver, hoursInput);
        driver.switchTo().activeElement();
        driver.findElement(localSSDInput).click();
        String locator = buildLocatorByText(LocalSSD);
        WebElement localSSDChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, localSSDChoice);
        driver.switchTo().activeElement();
        localSSDChoice.click();
    }

    public void setDataCenterLocation(String dcLocation) {
        Waiter.waitForElementLocatedBy(driver, dataCenterLocationInput);
        driver.findElement(dataCenterLocationInput).click();
        String locator = buildLocatorByText(dcLocation);
        WebElement dcLocationChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, dcLocationChoice);
        dcLocationChoice.click();
    }

    public void setCommittedUsage(String committedUsage) {
        Waiter.waitForElementLocatedBy(driver, committedUsageInput);
        driver.findElement(committedUsageInput).click();
        String locator = buildLocatorByText(committedUsage);
        WebElement usageChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, usageChoice);
        usageChoice.click();
    }

    public void clickAddToEstimateButton() throws InterruptedException {
        scrollToElement(dataCenterLocationInput);
        clickToElement(addToEstimateButton);
    }

    public String getEstimationResult() {
        return driver.findElement(estimationResultContent).getText();
    }

    public void  highlightResultingContent() {
        scrollToElement(estimationResultFullContent);
        Actions builder = new Actions(driver);
        WebElement content = driver.findElement(estimationResultContent);

        builder.moveToElement(content, 0, 0).build().perform();
        builder.clickAndHold().moveToElement(content, content.getSize().getWidth()/ 2, content.getSize().getHeight() / 2).release().build().perform();
    }

    private void scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    private void clickToElement(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private String buildLocatorByText(String valuePart) {
        return String.format("//div[contains(@id, 'select_container')]//div[text()[contains(.,'%s')]]", valuePart);
    }

}
