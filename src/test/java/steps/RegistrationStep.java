package steps;

import dto.AccountRegistration;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.RegistrationPage;

@Log4j2
public class RegistrationStep {

    private final HomePage homePage;
    @Getter
    private final RegistrationPage registrationPage;
    private final WebDriver driver;
    private final String baseURL;

    public RegistrationStep(WebDriver driver, String baseURL) {
        this.driver = driver;
        this.homePage = new HomePage(driver, baseURL);
        this.registrationPage = new RegistrationPage(driver, baseURL);
        this.baseURL = baseURL;
    }

    @Step("Open home page")
    public void openHomePage() {
        homePage.openHomePage();
        homePage.isPageOpened();
    }

    @Step("Change language to English")
    public void changeLanguageToEnglish() {
        homePage.changeLanguageToEN();
    }

    @Step("Click sign up button")
    public void clickSignUpButton() {
        homePage.clickSignUpButton();
    }

    @Step("Sign up with contact registration details")
    public void signUp(AccountRegistration accountRegistration) {
        registrationPage.isPageOpened();
        registrationPage.signup(
                accountRegistration.getName(),
                accountRegistration.getSurname(),
                accountRegistration.getEmail(),
                accountRegistration.getPassword(),
                accountRegistration.getPhone());
    }

    @Step("Submit sign up form")
    public void submitSignUp() {
        registrationPage.clickSignUpButton();
    }

    @Step("Create a new account")
    public void create(AccountRegistration accountRegistration) {
        openHomePage();
        changeLanguageToEnglish();
        clickSignUpButton();
        signUp(accountRegistration);
        submitSignUp();
    }
}
