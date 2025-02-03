package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

@Log4j2
public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.xpath("//input[@type='email']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    private static final By SIGN_IN_HEADER_BUTTON = By.xpath("//button[@class='header__button']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");
    private static final By MY_ACCOUNT = By.xpath("//div[@class='my-account']");

    public LoginPage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public LoginPage isPageOpened() {
        try {
            log.info("Checking if LoginPage is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException e) {
            log.error("Login page did not open: {}", e.getMessage());
        }
        waitForPageLoaded(driver);
        return this;
    }

    public boolean isPageOpenedAfterRegistration() {
        try {
            log.info("Checking if LoginPage is opened after registration");
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
            waitForPageLoaded(driver);
            log.info("LoginPage is successfully opened.");
            return true;
        } catch (TimeoutException e) {
            log.error("Login page did not open after registration: {}", e.getMessage());
            return false;
        }
    }

    public LoginPage open() {
        log.info("Opening LoginPage");
        driver.get(baseURL + "signIn");
        return this;
    }

    public LoginPage login(String userName, String password) {
        log.info("Logging in using credentials");
        enter(USERNAME_INPUT, userName);
        enter(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
        waitUntilElementBeVisible(MY_ACCOUNT);
        return this;
    }

    public LoginPage enterEmail(String userName) {
        log.info("Entering email");
        enter(USERNAME_INPUT, userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        log.info("Entering password");
        enter(PASSWORD_INPUT, password);
        return this;
    }

    public LoginPage submitLogin() {
        log.info("Logging in using credentials");
        click(LOGIN_BUTTON);
        return this;
    }
}
