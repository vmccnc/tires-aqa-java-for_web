package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
@Epic("Tests on Login")
@Feature("User Authentication")
public class LoginTest extends BaseTest {

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Login into the application", description = "Verify that a user can log in with valid credentials.")
    @Description("Verify that a user can successfully log in to the application using valid credentials.")
    public void checkValidLogin() {

        homeStep.openHomePage()
                .changeLanguageToEn();
        loginStep.login(user, password);

        boolean isLoginSuccessful = cartPage.successLogin();
        Assert.assertTrue(isLoginSuccessful, "Login was not successful because the cart button is not visible.");
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {user, "", "Firebase: Error (auth/missing-password)."},
                {"", password, "Firebase: Error (auth/invalid-email)."},
                {"test@gmail.com", password, "Firebase: Error (auth/invalid-credential)."},
                {user, "wrongPassword", "Firebase: Error (auth/invalid-credential)."},
                {"test@gmail.com", "wrongPassword", "Firebase: Error (auth/invalid-credential)."}
        };
    }

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "LoginData", testName = "Invalid login data", description = "Check that user cannot log in with invalid data.")
    @Description("Negative login check")
    public void checkInvalidLogin(String user, String password, String expectedErrorMessage) {
        loginStep.openLoginPage()
                .enterEmail(user)
                .enterPassword(password)
                .submitLogin();

        loginStep.verifyErrorMessage(expectedErrorMessage);
    }
}
