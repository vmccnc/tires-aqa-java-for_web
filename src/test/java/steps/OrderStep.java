package steps;

import dto.OrderInput;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.OrderPage;

@Log4j2
public class OrderStep {

    private final OrderPage orderPage;

    public OrderStep(WebDriver driver, String baseURL) {
        this.orderPage = new OrderPage(driver, baseURL);
    }

    @Step("Open the Order page")
    public OrderStep openOrderPage() {
        orderPage.isPageOpened()
                .checkEmailFieldIsNotEmpty();
        return this;
    }

    @Step("Set order details")
    public OrderStep setOrderDetails(OrderInput orderInformation) {
        orderPage.setDetailsAnOrder(orderInformation);
        return this;
    }

    @Step("Select delivery method: {delivery}")
    public OrderStep selectDeliveryMethod(String delivery) {
        orderPage.selectRadioDelivery(delivery);
        return this;
    }

    @Step("Select payment method: {payment}")
    public OrderStep selectPaymentMethod(String payment) {
        orderPage.selectRadioPayment(payment);
        return this;
    }

    @Step("Select checkboxes: Privacy Consent - {checkboxPrivacy}, Commercial Consent - {checkboxCommercial}")
    public OrderStep selectCheckboxes(String checkboxPrivacy, String checkboxCommercial) {
        orderPage.selectCheckboxes(checkboxPrivacy, checkboxCommercial);
        return this;
    }

    @Step("Submit the order")
    public OrderStep submitOrder() {
        orderPage.clickOnSubmitButton();
        return this;
    }
}