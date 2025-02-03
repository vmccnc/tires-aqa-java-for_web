package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class LoginTest extends BaseTest {

    @Owner("Elizaveta Nikolaenya")
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
                {user, "", "Firebase: Error (auth/missing-password)."}, // Отсутствует пароль
                {"", password, "Firebase: Error (auth/invalid-email)."}, // Отсутствует email
                {"test@gmail.com", password, "Firebase: Error (auth/invalid-credential)."}, // Неверный email
                {user, "wrongPassword", "Firebase: Error (auth/invalid-credential)."}, // Неверный пароль
                {"test@gmail.com", "wrongPassword", "Firebase: Error (auth/invalid-credential)."} // Неверные учетные данные
        };
    }

    @Owner("Elizaveta Nikolaenya")
    @Test(dataProvider = "LoginData", testName = "Invalid login data", description = "Check that user cannot login" +
            " with invalid data")
    @Description("Negative login check")
    public void checkInvalidLogin(String user, String password) {
        loginStep.openLoginPage()
                .enterEmail(user)
                .enterPassword(password)
                .submitLogin();
    }
}