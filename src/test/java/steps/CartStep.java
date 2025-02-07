package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.CartPage;

@Log4j2
public class CartStep {

    private final CartPage cartPage;
    private final WebDriver driver;
    private final String baseURL;

    public CartStep(WebDriver driver, String baseURL) {
        this.driver = driver;
        this.cartPage = new CartPage(driver, baseURL);
        this.baseURL = baseURL;
    }

    @Step("Deleting all tires from cart")
    public void deleteAllTiresFromCart() {
        log.info("Starting deleting all items.");
        cartPage.isPageOpened()
                .deleteAllItemsFromCart();
    }
}
