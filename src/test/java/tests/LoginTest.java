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
    @Test(testName = "Login into the application", description = "Check positive login")
    @Description("Check positive login")
    public void checkValidLogin() {
        homePage.open()
                .isPageOpened()
                .changeLanguage();
        loginPage
                .clickSignInButton()
                .isPageOpened()
                .login(user, password);

        boolean isLoginSuccessful = loginPage.successLogin();
        Assert.assertTrue(isLoginSuccessful, "Your cart is empty");
    }

    @Owner("Elizaveta Nikolaenya")
    @Test(testName = "Login into the application", description = "Check positive login")
    @Description("Check positive login")
    public void checkValidLogin1() {
        homeStep.openHomePage()
                .changeLanguage();
        loginStep.login(user, password);

        boolean isLoginSuccessful = loginPage.successLogin();
        Assert.assertTrue(isLoginSuccessful, "Your cart is empty");
    }
}
