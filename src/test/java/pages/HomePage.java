package pages;


import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;


@Log4j2
public class HomePage extends BasePage {

    private final By SIGN_IN_HEADER_BUTTON = By.xpath("//button[contains(.,'Szukaj opon')]");
    private final By EN_LANGUAGE = By.xpath("//span[contains(.,'EN')]");
    private final By SEARCH_BUTTON = By.xpath("//button[text()='Search tires']");

    public HomePage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public HomePage isPageOpened() {
        try {
            log.info("Checking if Home page is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN_HEADER_BUTTON));
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

    public HomePage changeLanguage() {
        log.info("Changing language to");
        click(EN_LANGUAGE);
        return this;
    }
}