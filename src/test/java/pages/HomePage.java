package pages;


import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;


@Log4j2
public class HomePage extends BasePage {

    private final By SEARCH_BUTTON_PL = By.xpath("//button[contains(.,'Szukaj opon')]");
    private final By SIGN_IN_HEADER_BUTTON = By.xpath("//button[@class='header__button']");
    private final By EN_LANGUAGE = By.xpath("//span[contains(.,'EN')]");
    private final By SEARCH_BUTTON = By.xpath("//button[text()='Search tires']");
    private final By SIGN_IN_BUTTON_PL = By.xpath("//button[contains(.,'Zaloguj się ')]");
    private final By SIGN_IN_BUTTON_RU = By.xpath("//button[contains(.,'Войти ')]");
    private final By SIGN_IN_BUTTON_EN = By.xpath("//button[contains(.,'Sign in ')]");
    private final By SIGN_UP_BUTTON_PL = By.xpath("//button[contains(.,'Zarejestruj się ')]");
    private final By SIGN_UP_BUTTON_EN = By.xpath("//button[contains(.,'Sign up ')]");
    private final By SIGN_UP_BUTTON_RU = By.xpath("//button[contains(.,'Зарегистрироваться ')]");

    public HomePage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public HomePage isPageOpened() {
        try {
            log.info("Checking if Home page is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON_PL));
        } catch (TimeoutException e) {
            log.error("Home page did not open: {}", e.getMessage());
            throw e;
        }
        return this;
    }

    public HomePage open() {
        log.info("Opening Home page");
        driver.get(baseURL);
        return this;
    }

    public HomePage changeLanguageToEN() {
        log.info("Changing language to");
        click(EN_LANGUAGE);
        return this;
    }

    public HomePage clickSignInButton() {
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

    public HomePage clickSignUpButton() {
        log.info("Clicking 'Sign Up' button");
        click(SIGN_IN_HEADER_BUTTON);

        if (isElementPresent(SIGN_UP_BUTTON_EN)) {
            click(SIGN_UP_BUTTON_EN);
        } else if (isElementPresent(SIGN_UP_BUTTON_PL)) {
            click(SIGN_UP_BUTTON_PL);
        } else if (isElementPresent(SIGN_UP_BUTTON_RU)) {
            click(SIGN_UP_BUTTON_RU);
        } else {
            throw new NoSuchElementException("Sign Up button not found for any language");
        }
        return this;
    }
}