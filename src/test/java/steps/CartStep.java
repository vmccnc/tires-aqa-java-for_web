package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.CartPage;

@Log4j2
public class CartStep {

    private final CartPage cartPage;

    public CartStep(WebDriver driver, String baseURL) {
        this.cartPage = new CartPage(driver, baseURL);
    }

    @Step("Deleting all tires from cart")
    public CartStep deleteAllItemsFromCart() {
        cartPage.isPageOpened()
                .deleteAllItemsFromCart();
        return this;
    }

    @Step("Ensuring the cart is empty")
    public CartStep ensureCartIsEmpty() {
        cartPage.isPageOpened()
                .ensureCartIsEmpty();
        return this;
    }

    @Step("Clicking on 'Order now' button")
    public CartStep clickOnOrderButton() {
        cartPage.clickOnOrderButton();
        return this;
    }

    @Step("Getting cart empty message")
    public String getCartEmptyMessage() {
        return cartPage.cartIsEmpty();
    }
}
