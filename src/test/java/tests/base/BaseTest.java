package tests.base;

import driver.DriverCreation;
import driver.DriverTypes;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import pages.*;
import steps.*;
import utils.allure.AllureUtils;
import utils.propertyUtils.PropertyReader;
import utils.testngUtils.TestListener;

import static driver.DriverCreation.quitWebDriver;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected RegistrationStep registrationStep;
    protected HomePage homePage;
    protected LoginStep loginStep;
    protected HomeStep homeStep;
    protected TiresPage tiresPage;
    protected TiresStep tiresStep;
    protected CartPage cartPage;
    protected CartStep cartStep;
    protected OrderPage orderPage;
    protected OrderStep orderStep;

    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));
    protected String baseURL = System.getProperty("baseURL", PropertyReader.getProperty("baseURL"));

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    @Description("Opening browser")
    protected void setUp(@Optional("chrome") String browser) {
        log.info("Setting up browser: {}", browser);

        DriverTypes driverType = DriverTypes.valueOf(browser.toUpperCase());
        driver = DriverCreation.startWebDriver(driverType);

        loginPage = new LoginPage(driver, baseURL);
        registrationPage = new RegistrationPage(driver, baseURL);
        registrationStep = new RegistrationStep(driver, baseURL);
        homePage = new HomePage(driver, baseURL);
        loginStep = new LoginStep(driver, baseURL);
        homeStep = new HomeStep(driver, baseURL);
        tiresPage = new TiresPage(driver, baseURL);
        tiresStep = new TiresStep(driver, baseURL);
        cartPage = new CartPage(driver, baseURL);
        cartStep = new CartStep(driver, baseURL);
        orderPage = new OrderPage(driver, baseURL);
        orderStep = new OrderStep(driver, baseURL);
        log.info("Browser started successfully");
    }

    @AfterMethod(alwaysRun = true)
    @Description("Close browser after each test and take screenshot if failed")
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            AllureUtils.takeScreenshot(driver);
        }
        if (driver != null) {
            log.info("Closing browser");
            quitWebDriver();
            log.info("Browser closed successfully");
        }
    }
}