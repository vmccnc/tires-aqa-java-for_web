package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    private static final By MY_ACCOUNT_BUTTON = By.xpath("//div[@class='my-account']");
    private static final By CART_BUTTON = By.xpath("//button[@class='info__cart']");
    private static final By DELETE_BUTTON = By.xpath("//button[text()='Delete from cart']");
    private static final By CART = By.xpath("//h1[contains(.,'Your')]");
    private static final By EMPTY_CART = By.xpath("//h1[contains(@class,'_cart_empty')]");
    private static final By ORDER_BUTTON = By.xpath("//*[contains(@class, '_cart_information_')]/button");

    public CartPage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public boolean successLogin() {
        log.info("Success login");
        return isElementPresent(CART_BUTTON);
    }

    public CartPage isPageOpened() {
        try {
            log.info("Checking if Cart page is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CART));
        } catch (TimeoutException e) {
            log.error("Cart page did not open CartPage: {}", e.getMessage());
            throw e;
        }
        return this;
    }

    public CartPage clickOnDeleteButton(int index) {
        By deleteButton = By.xpath(DELETE_BUTTON + "[" + index + "]");
        log.info("Click on 'delete button' " + index);
        click(deleteButton);
        return this;
    }

    public CartPage deleteAllItemsFromCart() {
        log.info("Starting to delete all items from the cart.");

        var deleteButtonsCount = driver.findElements(DELETE_BUTTON).size();

        while (deleteButtonsCount != 0) {
            log.info("Deleting item number " + deleteButtonsCount);

            wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_BUTTON));
            driver.findElement(DELETE_BUTTON).click();

            log.info("Items remaining: " + --deleteButtonsCount);
        }
        log.info("No more items to delete. Cart is empty.");
        return this;
    }


    public CartPage deleteItemByName(String itemName) {
        log.info("Attempting to delete item: " + itemName);

        By itemXPath = By.xpath(String.format("//p[text()='%s']", itemName));

        List<WebElement> items = driver.findElements(itemXPath);

        if (!items.isEmpty()) {
            WebElement deleteButton = items.get(0).findElement(By.xpath("following-sibling::button[text()='Delete from cart']"));

            log.info("Deleting item: " + itemName);
            deleteButton.click();
            log.info("Item deleted: " + itemName);
        } else {
            log.warn("Item not found in the cart: " + itemName);
        }

        return this;
    }

    public String cartIsEmpty() {
        log.info("Checking if all items was deleted from cart");
        waitUntilElementBeVisible(EMPTY_CART);
        return getElementText(EMPTY_CART);
    }

    public CartPage clickOnOrderButton() {
        log.info("Click on order button");
        click(ORDER_BUTTON);
        return this;
    }
}
