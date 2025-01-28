package tests.base;

import driver.DriverTypes;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import pages.LoginPage;
import pages.RegistrationPage;
import propertyUtils.PropertyReader;
import testngUtils.TestListener;

import java.lang.reflect.InvocationTargetException;

import static driver.DriverCreation.quitWebDriver;
import static driver.DriverCreation.startWebDriver;
import static driver.DriverTypes.CHROME;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;

    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));
    protected String baseURL = System.getProperty("baseURL", PropertyReader.getProperty("baseURL"));

    @Parameters("browser")
    @BeforeTest(alwaysRun = true)
    @Description("Opening browser")
    protected void setUp() {
        driver = startWebDriver(System.getProperties().containsKey("config")
                ? DriverTypes.valueOf(PropertyReader.getProperty("browser").toUpperCase())
                : CHROME
        );
        loginPage = new LoginPage(driver, baseURL);
        registrationPage = new RegistrationPage(driver, baseURL);
    }

    protected <T> T get(Class<T> page) {
        T instance;
        try {
            instance = page.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @AfterTest(alwaysRun = true)
    @Description("Closing browser")
    public void tearDown() {
        quitWebDriver();
    }
}
