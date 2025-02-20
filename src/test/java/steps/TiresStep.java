package steps;

import dto.TiresInput;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.TiresPage;

@Log4j2
public class TiresStep {

    private final TiresPage tiresPage;
    private HomePage homePage;
    private LoginStep loginStep;

    public TiresStep(WebDriver driver, String baseURL) {
        this.tiresPage = new TiresPage(driver, baseURL);
        this.homePage = new HomePage(driver, baseURL);
        this.loginStep = new LoginStep(driver, baseURL);
    }

    @Step("Filling the tires table with input: {tiresInput}")
    public TiresStep fillingTheTiresTable(TiresInput tiresInput) {
        tiresPage.fillingTheTiresTable(tiresInput)
                .clickSearchTiers();
        return this;
    }

    @Step("Adding tires to cart")
    public TiresStep addingToCart() {
        tiresPage.clickAddToCart();
        return this;
    }

    @Step("Login and add tires to cart")
    public TiresStep loginAndAddTiresToCart(String user, String password, TiresInput tiresInput) {
        homePage.openHomePage();
        loginStep.login(user, password)
                .openHomePage()
                .changeLanguageToEn();
        fillingTheTiresTable(tiresInput)
                .addingToCart();
        return this;
    }

    public String getSuccessMessage() {
        return tiresPage.successMessageIsVisible();
    }
}