package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class RegistrationTest extends BaseTest {


//    @Test(testName = "Login into the application", description = "Check positive login")
//    @Description("Check positive login")
//    public void checkValidRegistration() {
//        registrationPage.open()
//                .isPageOpened()
//                .signup("Test", "Aqa", "testaqa26@gmail.com", "!TEaqaST26!", "+333223331122");
//        Assert.fail("Login page did not open");
//    }
//
//    @DataProvider(name = "RegistrationData")
//    public Object[][] loginData() {
//        return new Object[][]{
//                {"", password, "Email/Login is required."},
//                {user, "", "Password is required."},
//                {user, "qwerty", "Sorry, there was a problem.\nEmail/Login or Password is incorrect. Please try again."}
//        };
//    }
//
//    @Owner("Elizaveta Nikolaenya")
//    @Severity(SeverityLevel.MINOR)
//    @Feature("Negative Login")
//    @Test(dataProvider = "LoginData", testName = "Invalid login data", description = "Check that user cannot login with invalid data")
//    @Description("Negative login check")
//    public void invalidLogin(String user, String password, String expectedMessage) {
//        loginPage.openPage()
//                .enterEmail(user)
//                .enterPassword(password)
//                .submitLogin();
//        loginPage.verifyErrorMessage(expectedMessage);
//    }
}
