package steps;

import dto.ContactRegistration;
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
        log.info("LoginStep initialized with driver: {}", driver);
    }

    @Step("Creation a new account")
    public void create(ContactRegistration contactRegistration) {
        log.info("Starting account creation for {}", contactRegistration.getEmail());
        homePage.open()
                .isPageOpened()
                .changeLanguageToEN()
                .clickSignUpButton();
        registrationPage.isPageOpened()
                .signup(contactRegistration.getName(), contactRegistration.getSurname(),
                        contactRegistration.getEmail(), contactRegistration.getPassword(),
                        contactRegistration.getPhone())
                .clickSignUpButton();
    }
}
