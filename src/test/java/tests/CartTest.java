package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class CartTest extends BaseTest {

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "check Delete All Items From Cart", description = "Deleting all tires from cart.")
    @Description("Deleting all tires from cart.")
    public void checkDeleteAllItemsFromCart() {
        homeStep.openHomePage();
        loginStep.login(user, password)
                .changeLanguageToEn();
        cartStep.deleteAllTiresFromCart();

        String cartIsEmpty = cartPage.cartIsEmpty();
        Assert.assertEquals(cartIsEmpty, "Your cart is empty", "Expected error message was not " +
                "displayed.");
    }
}
