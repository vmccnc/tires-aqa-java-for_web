package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class LoginTest extends BaseTest {

    @Owner("Elizaveta Nikolaenya")
    @Test(testName = "Login into the application", description = "Verify that a user can log in with valid credentials.")
    @Description("Verify that a user can successfully log in to the application using valid credentials.")
    public void checkValidLogin() {

        homeStep.openHomePage()
                .changeLanguage();
        loginStep.login(user, password);

        boolean isLoginSuccessful = cartPage.successLogin();
        Assert.assertTrue(isLoginSuccessful, "Login was not successful because the cart button is not visible.");
    }
}