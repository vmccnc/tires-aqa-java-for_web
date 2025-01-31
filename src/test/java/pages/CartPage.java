package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

@Log4j2
public class CartPage extends BasePage {

    private static final By MY_ACCOUNT_BUTTON = By.xpath("//div[@class='my-account']");
    private static final By CART_BUTTON = By.xpath("//button[@class='info__cart']");

    public CartPage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public boolean successLogin() {
        log.info("Success login");
        return isElementPresent(CART_BUTTON);
    }
}
