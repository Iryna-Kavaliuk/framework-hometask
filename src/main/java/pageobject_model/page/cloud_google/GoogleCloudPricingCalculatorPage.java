package pageobject_model.page.cloud_google;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import waits.Waiter;
import java.util.List;

public class GoogleCloudPricingCalculatorPage {
    private WebDriver driver;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    By cookiesAlert = By.className("devsite-snackbar-action");
    By productTypeTabs = By.xpath("//md-tab-item[@role='tab']/div/div/div[@class='name']/span");
    By cloudSiteFrame= By.xpath("//article[@id='cloud-site']/devsite-iframe/iframe");
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

    By estimationResultContent = By.xpath("//md-card-content[@id='resultBlock']//h2[@class='md-flex ng-binding ng-scope']");

    String operatingSystemsSelectContainer = "//div[@id='select_container_115']";
    String provisioningModelSelectContainer = "//div[@id='select_container_119']";
    String seriesSelectContainer = "//div[@id='select_container_127']";
    String machineTypeSelectContainer = "//div[@id='select_container_129']";
    String gpuTypeSelectContainer = "//div[@id='select_container_510']";
    String gpuNumberSelectContainer = "//div[@id='select_container_512']";
    String localSSDSelectContainer = "//div[@id='select_container_469']";
    String dataCenterLocationSelectContainer = "//div[@id='select_container_135']";
    String committedUsageSelectContainer = "//div[@id='select_container_142']";

    public void closeCookiesAlert() {
        driver.findElement(cookiesAlert).click();
    }

    public void activateProductType(String productType) {
        List<WebElement> productTypeList = driver.findElements(productTypeTabs);
        for(WebElement pt: productTypeList) {
            String text = pt.getText();
            if(text.contains(productType)) {
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
        String locator = buildLocatorByText(operatingSystemsSelectContainer, operatingSystem);
        WebElement osChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, osChoice);
        driver.switchTo().activeElement();
        osChoice.click();
    }

    public void setProvisioningModel(String provisioningModel) {
        Waiter.waitForElementLocatedBy(driver, provisioningModelInput);
        driver.findElement(provisioningModelInput).click();
        String locator = buildLocatorByText(provisioningModelSelectContainer, provisioningModel);
        WebElement provisioningModelChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, provisioningModelChoice);
        provisioningModelChoice.click();
    }

    public void setSeries(String series) {
        scrollToElement(operatingSystemsInput);
        driver.findElement(seriesInput).click();
        String locator = buildLocatorByText(seriesSelectContainer, series);
        WebElement seriesChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, seriesChoice);
        driver.switchTo().activeElement();
        seriesChoice.click();
    }

    public void setMachineType(String machineType) {
        driver.findElement(machineTypeInput).click();
        String locator = buildLocatorByText(machineTypeSelectContainer, machineType);
        WebElement machineTypeChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, machineTypeChoice);
        machineTypeChoice.click();
    }

    public void checkAddGPUsCheckbox() {
        driver.findElement(addGPUsCheckbox).click();
    }

    public void setGpuType(String gpuType) {
        driver.switchTo().activeElement();
        driver.findElement(gpuTypeInput).click();
        String locator = buildLocatorByText(gpuTypeSelectContainer, gpuType);
        WebElement gpuTypeChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, gpuTypeChoice);
        gpuTypeChoice.click();
    }

    public void setGpuNumber(String gpuNumber) {
        Waiter.waitForElementLocatedBy(driver, gpuNumberInput);
        driver.findElement(gpuNumberInput).click();
        String locator = buildLocatorByText(gpuNumberSelectContainer, gpuNumber);
        WebElement gpuNumberChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, gpuNumberChoice);
        gpuNumberChoice.click();
    }

    public void setLocalSSD(String LocalSSD) {
        Waiter.waitForElementLocatedBy(driver, hoursInput);
        driver.switchTo().activeElement();
        driver.findElement(localSSDInput).click();
        String locator = buildLocatorByText(localSSDSelectContainer, LocalSSD);
        WebElement localSSDChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, localSSDChoice);
        driver.switchTo().activeElement();
        localSSDChoice.click();
    }

    public void setDataCenterLocation(String dcLocation) {
        Waiter.waitForElementLocatedBy(driver, dataCenterLocationInput);
        driver.findElement(dataCenterLocationInput).click();
        String locator = buildLocatorByText(dataCenterLocationSelectContainer, dcLocation);
        WebElement dcLocationChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, dcLocationChoice);
        dcLocationChoice.click();
    }

    public void setCommittedUsage(String committedUsage) {
        Waiter.waitForElementLocatedBy(driver, committedUsageInput);
        driver.findElement(committedUsageInput).click();
        String locator = buildLocatorByText(committedUsageSelectContainer, committedUsage);
        WebElement usageChoice = driver.findElement(By.xpath(locator));
        Waiter.waitForElementLocated(driver, usageChoice);
        usageChoice.click();
    }

    public void clickAddToEstimateButton() {
        driver.findElement(addToEstimateButton).click();
    }

    public String getEstimationResult() {
        return driver.findElement(estimationResultContent).getText();
    }

//    public void setOperatingSystems(String operatingSystem) {
//        Waiter.waitForElementLocatedBy(driver, operatingSystemsInput);
//        driver.findElement(operatingSystemsInput).click();
//        List<WebElement> operatingSystemList = driver.findElements(operatingSystemsItem);
//        for(WebElement os: operatingSystemList) {
//            String text = os.getText();
//            if(text.contains(operatingSystem)) {
//                os.click;
//                break;
//            }
//        }
//    }

//    public void setProvisioningModel(String provisioningModel) {
//        Waiter.waitForElementLocatedBy(driver, provisioningModelInput);
//        driver.findElement(provisioningModelInput).click();
//        List<WebElement> provisioningModelList = driver.findElements(provisioningModelItem);
//        for(WebElement pm: provisioningModelList) {
//            if(pm.getText().contains(provisioningModel)) {
//                pm.click();
//                break;
//            }
//        }
//    }

    private void scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    private String buildLocatorByText(String defaultPart, String valuePart) {
        return (defaultPart + "//div[text()[contains(.,'" + valuePart + "')]]");
    }

}
