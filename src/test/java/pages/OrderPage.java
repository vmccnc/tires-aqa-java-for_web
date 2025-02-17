package pages;

import dto.OrderInput;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;
import wrappers.OrderInputWrapper;

import java.time.Duration;
import java.util.Map;


@Log4j2
public class OrderPage extends BasePage {

    private static final By SUBMIT_BUTTON = By.xpath("//button[@class='payment-orders__button--confirm']");
    private static final String RADIO_BUTTON = "//input[@value='%s']";
    private static final String CHECKBOX = "//input[@name='%s']";

    public OrderPage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public OrderPage isPageOpened() {
        try {
            log.info("Checking if OrderPage is opened");
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BUTTON));
            return this;
        } catch (TimeoutException e) {
            log.error("Order page did not open: {}", e.getMessage());
            throw new RuntimeException("Order page did not open", e);
        }
    }

    public void checkEmailFieldIsNotEmpty() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

        wait.until(driver -> {
            String value = emailField.getAttribute("value");
            return value != null && !value.isEmpty();
        });

        String value = emailField.getAttribute("value");

        if (value == null || value.isEmpty()) {
            throw new AssertionError("Email field is empty, but it is required.");
        }

        if (!value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new AssertionError("Email field does not contain a valid email address.");
        }
    }

    public OrderPage setDetailsAnOrder(OrderInput orderInformation) {
        log.info("Creating a new order.");

        Map<String, String> fields = Map.of(
                "fullname", orderInformation.getFullname(),
                "email", orderInformation.getEmail(),
                "phone", orderInformation.getPhone(),
                "address", orderInformation.getAddress(),
                "city", orderInformation.getCity(),
                "zipcode", orderInformation.getZipcode(),
                "country", orderInformation.getCountry()
        );

        fields.forEach((key, value) -> {
            if (value != null) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(key)));
                inputField.sendKeys(value);
            }
        });

        return this;
    }

    public OrderPage selectRadioDelivery(String delivery) {
        log.info("Choosing delivery method: '{}'", delivery);
        String formattedXPath = String.format(RADIO_BUTTON, delivery);
        selectRadioButton(By.xpath(formattedXPath));
        return this;
    }

    public OrderPage selectRadioPayment(String payment) {
        log.info("Choosing payment method: '{}'", payment);
        String formattedXPath = String.format(RADIO_BUTTON, payment);
        selectRadioButton(By.xpath(formattedXPath));
        return this;
    }

    public OrderPage selectCheckboxes(String checkboxPrivacy, String checkboxCommercial) {
        log.info("Selecting checkboxes: Privacy Consent - '{}', Commercial Consent - '{}'", checkboxPrivacy, checkboxCommercial);
        String formattedXPathFirst = String.format(CHECKBOX, checkboxPrivacy);
        click(By.xpath(formattedXPathFirst));
        String formattedXPathSecond = String.format(CHECKBOX, checkboxCommercial);
        click(By.xpath(formattedXPathSecond));
        return this;
    }

    public OrderPage clickOnSubmitButton() {
        log.info("Submitting a new order");
        waitUntilElementBeVisible(SUBMIT_BUTTON);
        click(SUBMIT_BUTTON);
        return this;
    }

    public String getAlertText() {
        try {
            log.info("Attempting to switch to alert to get its text.");
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            log.info("Alert text retrieved: {}", alertMessage);
            return alertMessage;
        } catch (NoAlertPresentException e) {
            log.warn("No alert is present when trying to get alert text.");
            return null;
        } catch (WebDriverException e) {
            log.error("WebDriverException occurred while checking for alert: {}", e.getMessage());
            return null;
        }
    }
}
