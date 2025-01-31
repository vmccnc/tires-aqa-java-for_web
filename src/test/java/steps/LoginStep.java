package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

@Log4j2
public class LoginStep {

    private final LoginPage loginPage;
    private final HomePage homePage;
    private final WebDriver driver;
    private final String baseURL;

    public LoginStep(WebDriver driver, String baseURL) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver, baseURL);
        this.homePage = new HomePage(driver, baseURL);
        this.baseURL = baseURL;
        log.info("LoginStep initialized with driver: {}", driver);
    }

    @Step("Login with username and password")
    public HomeStep login(String userName, String password) {
        log.info("Starting login process for user: {}", userName);
        try {
            loginPage.clickSignInButton()
                    .isPageOpened()
                    .login(userName, password);
            log.info("Login process completed for user: {}", userName);
        } catch (Exception e) {
            log.error("Login process failed for user: {}. Error: {}", userName, e.getMessage());
            throw e;
        }
        return new HomeStep(driver, baseURL);
    }
}
