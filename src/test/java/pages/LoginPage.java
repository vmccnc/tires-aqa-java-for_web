package pages;

import io.qameta.allure.Step;
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
    private static final By SIGN_IN_BUTTON = By.xpath("//button[contains(.,'Sign in ')]");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");
    private static final By MESSAGE = By.xpath("//h1[contains(.,'Your cart is empty')]");

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
        click(SIGN_IN_BUTTON);
        return this;
    }

    @Step("Log in to the application with valid credentials - username: {userName}, password: {password}.")
    public LoginPage login(String userName, String password) {
        log.info("Logging in using credentials '{}', '{}'", userName, password);
        enter(USERNAME_INPUT, userName);
        enter(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
        return this;
    }

    @Step("Get the Welcome text")
    public boolean successLogin() {
        log.info("Getting the Welcome text");
        String message = getElementText(MESSAGE);
        return message.contains("Your cart is empty");
    }
}
