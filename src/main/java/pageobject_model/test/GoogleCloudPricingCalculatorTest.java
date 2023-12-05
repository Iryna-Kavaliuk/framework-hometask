package pageobject_model.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.cloud_google.GoogleCloudHomePage;
import pageobject_model.page.cloud_google.GoogleCloudPricingCalculatorPage;

public class GoogleCloudPricingCalculatorTest {
    private WebDriver driver;
    private GoogleCloudHomePage homePage;
    private GoogleCloudPricingCalculatorPage calculatorPage;

    private static final String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";
    private static final String RESULTING_TERM = "Google Cloud Pricing Calculator";
    private static final String COMPUTE_ENGINE_AREA = "COMPUTE ENGINE";
    private static final String NUMBER_OF_INSTANCES = "4";
    private static final String OPERATING_SYSTEM = "Free: Debian, CentOS";
    private static final String PROVISIONING_MODEL = "Regular";                                 //VM_CLASS
    private static final String SERIES = "N1";
    private static final String MACHINE_TYPE = "n1-standard-8";
    private static final String GPU_TYPE = "NVIDIA Tesla T4";
    private static final String NUMBER_OF_GPUS = "1";
    private static final String LOCAL_SSD = "2x375 GB";
//    private static final String DATACENTER_LOCATION = "Frankfurt (europe-west3)";
    private static final String DATACENTER_LOCATION = "Northern Virginia";
    private static final String COMMITTED_USAGE = "1 Year";
    private static final String MANUALLY_GOT_ESTIMATION = "1,840.40 / mo";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new GoogleCloudHomePage(driver);
    }

    @Test(description = "Hurt Me Plenty. Testing Scenario for GCP Pricing Calculator")
    public void GCPCalculatorPriceEstimatingTest() throws InterruptedException {
        homePage.openPage();
        homePage.enterSearchTerm(SEARCH_TERM);
        calculatorPage = homePage.selectTargetingLinkFromSearchResults(RESULTING_TERM);
        calculatorPage.closeCookiesAlert();
        calculatorPage.activateProductType(COMPUTE_ENGINE_AREA);
        calculatorPage.setNumberOfInstances(NUMBER_OF_INSTANCES);
        calculatorPage.setOperatingSystems(OPERATING_SYSTEM);
        calculatorPage.setProvisioningModel(PROVISIONING_MODEL);
        calculatorPage.setSeries(SERIES);
        calculatorPage.setMachineType(MACHINE_TYPE);
        calculatorPage.checkAddGPUsCheckbox();
        calculatorPage.setGpuType(GPU_TYPE);
        calculatorPage.setGpuNumber(NUMBER_OF_GPUS);
        calculatorPage.setLocalSSD(LOCAL_SSD);
        calculatorPage.setDataCenterLocation(DATACENTER_LOCATION);
        calculatorPage.setCommittedUsage(COMMITTED_USAGE);
        calculatorPage.clickAddToEstimateButton();
        calculatorPage.highlightResultingContent();

        Assert.assertEquals(MANUALLY_GOT_ESTIMATION, calculatorPage.getEstimationResult(),
                "Estimations got manually and automatically are different!");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
