package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
@Epic("Cart Management")
@Feature("Cart Operations")
public class CartTest extends BaseTest {

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Check Delete All Items From Cart", description = "Deleting all tires from cart.")
    @Description("Deleting all tires from cart.")
    public void checkDeleteAllItemsFromCart() {
        homeStep.openHomePage();
        loginStep.login(user, password)
                .changeLanguageToEn();
        cartStep.ensureCartIsEmpty()
                .deleteAllItemsFromCart();

        String cartIsEmpty = cartPage.cartIsEmpty();
        Assert.assertEquals(cartIsEmpty, "Your cart is empty", "Expected error message was not " +
                "displayed.");
    }
}
