package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import dto.TiresInput;
import pages.HomePage;

@Log4j2
public class HomeStep {

    private final HomePage homePage;

    public HomeStep(WebDriver driver, String baseURL) {
        this.homePage = new HomePage(driver, baseURL);
        log.info("HomeStep initialized with driver: {}", driver);
    }

    @Step("Open the Home page")
    public HomeStep openHomePage() {
        log.info("Opening Home page");
        try {
            homePage.open()
                    .isPageOpened();
            log.info("Home page opened");
        } catch (Exception e) {
            log.error("Failed to open Home page: {}", e.getMessage());
            throw e;
        }
        return this;
    }

    @Step("Change language to English")
    public HomeStep changeLanguage() {
        log.info("Changing language");
        try {
            homePage.changeLanguage();
            log.info("Language changed");
        } catch (Exception e) {
            log.error("Failed to change language: {}", e.getMessage());
            throw e;
        }
        return this;
    }

}