package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

@Log4j2
public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    private final By NAME_INPUT = By.xpath("//input[@name='name']");
    private final By SURNAME_INPUT = By.xpath("//input[@name='surname']");
    private final By EMAIL_INPUT = By.xpath("//input[@name='email']");
    private final By PASSWORD_INPUT = By.xpath("//input[@name='password']");
    private final By PHONE_INPUT = By.xpath("//input[@name='phone']");
    private final By SIGN_UP_BUTTON = By.xpath("//button[contains(.,'Sign up')]");

    public RegistrationPage isPageOpened() {
        try {
            log.info("Checking if RegistrationPage is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(SIGN_UP_BUTTON));
        } catch (TimeoutException e) {
            log.error("Login page did not open: " + e.getMessage());
        }
        waitForPageLoaded(driver);
        return this;
    }

    public RegistrationPage open() {
        log.info("Opening Registration Page");
        driver.get(baseURL + "registration");
        return this;
    }

    public RegistrationPage signup(String userName, String surname, String email, String password, String phone) {
        log.info("Signing up with credentials '{}', '{}'", userName, password);
        enter(NAME_INPUT, userName);
        enter(SURNAME_INPUT, surname);
        enter(EMAIL_INPUT, email);
        enter(PASSWORD_INPUT, password);
        enter(PHONE_INPUT, phone);
        return this;
    }

    public RegistrationPage clickSignUpButton(){
        click(SIGN_UP_BUTTON);
        return this;
    }
}
