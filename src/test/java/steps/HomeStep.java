package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

@Log4j2
public class HomeStep {

    private final HomePage homePage;

    public HomeStep(WebDriver driver, String baseURL) {
        this.homePage = new HomePage(driver, baseURL);
    }

    @Step("Open the Home page")
    public HomeStep openHomePage() {
        homePage.openHomePage()
                .isPageOpened();
        return this;
    }

    @Step("Change language to English")
    public HomeStep changeLanguageToEn() {
        homePage.changeLanguageToEN();
        return this;
    }

    @Step("Click 'Sign In' button")
    public HomeStep clickOnSignInButton() {
        homePage.clickSignInButton();
        return this;
    }

    @Step("Click 'Sign Up' button")
    public HomeStep clickOnSignUpButton() {
        homePage.clickSignUpButton();
        return this;
    }

    @Step("Click on 'Cart' button")
    public HomeStep clickOnCartButton() {
        homePage.clickOnCartButton();
        return this;
    }
}