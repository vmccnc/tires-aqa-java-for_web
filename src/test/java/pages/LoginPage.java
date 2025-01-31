package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;

@Log4j2
public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.xpath("//input[@type='email']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    private static final By SIGN_IN_HEADER_BUTTON = By.xpath("//button[@class='header__button']");
    private static final By SIGN_IN_BUTTON_PL = By.xpath("//button[contains(.,'Zaloguj się ')]");
    private static final By SIGN_IN_BUTTON_RU = By.xpath("//button[contains(.,'Войти ')]");
    private static final By SIGN_IN_BUTTON_EN = By.xpath("//button[contains(.,'Sign in ')]");
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

    public LoginPage clickSignInButton() {
        log.info("Clicking 'Sign In' button");
        click(SIGN_IN_HEADER_BUTTON);

        if (isElementPresent(SIGN_IN_BUTTON_EN)) {
            click(SIGN_IN_BUTTON_EN);
        } else if (isElementPresent(SIGN_IN_BUTTON_PL)) {
            click(SIGN_IN_BUTTON_PL);
        } else if (isElementPresent(SIGN_IN_BUTTON_RU)) {
            click(SIGN_IN_BUTTON_RU);
        } else {
            throw new NoSuchElementException("Sign In button not found for any language");
        }
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
}
