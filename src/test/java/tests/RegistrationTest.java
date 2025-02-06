package tests;

import com.github.javafaker.Faker;
import dto.ContactRegistration;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class RegistrationTest extends BaseTest {

    Faker faker = new Faker();
    ContactRegistration contactRegistration = ContactRegistration.builder()
            .name("testAQA" + faker.name().firstName())
            .surname(faker.name().lastName())
            .email(faker.internet().emailAddress())
            .password(faker.internet().password())
            .phone("+375221112211")
            .build();

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Create a new account", description = "Verify that a new account can be created with valid data")
    @Description("Creating a new account with valid data")
    public void checkCreateNewAccount() {
        registrationStep.create(contactRegistration);

        boolean isLoginPageOpened = loginPage.isPageOpenedAfterRegistration();

        Assert.assertTrue(isLoginPageOpened, "Login page was not opened after account creation.");
    }
}
