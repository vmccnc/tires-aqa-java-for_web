package tests;

import com.github.javafaker.Faker;
import dto.OrderInput;
import dto.TiresInput;
import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
@Epic("Order Management")
@Feature("Order Creation")
public class OrderTest extends BaseTest {

    TiresInput tiresInput = TiresInput.builder()
            .selectWidth("235")
            .selectProfile("60")
            .selectDiameter("16")
            .selectProtector("RISKO")
            .enterFirstPrice("100")
            .enterSecondPrice("2000")
            .build();

    Faker faker = new Faker();
    OrderInput orderInformation = OrderInput.builder()
            .fullname("testAQA" + faker.name().firstName())
            .phone(faker.phoneNumber().phoneNumber())
            .address(faker.address().fullAddress())
            .city(faker.address().city())
            .zipcode(faker.address().zipCode())
            .country(faker.address().country())
            .build();

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Create Order", description = "Verify that an order can be created with valid data.")
    @Description("Creating a new order with valid data and verifying the order is successful.")
    public void checkCreateOrder() {
        tiresStep.loginAndAddTiresToCart(user, password, tiresInput);
        homeStep.clickOnCartButton();
        cartStep.clickOnOrderButton();
        orderStep.openOrderPage()
                .setOrderDetails(orderInformation)
                .selectDeliveryMethod("shipping_to_customer")
                .selectPaymentMethod("payment_at_delivery")
                .selectCheckboxes("consentPrivacy","consentCommercial")
                .submitOrder();

        String alertText = orderPage.getAlertText();
        Assert.assertNotNull(alertText, "Alert should be present");

        String expectedPattern = "Your order has been successfully placed! Order ID: \\d+";
        Assert.assertTrue(alertText.matches(expectedPattern), "Alert message does not match the expected pattern");
    }
}

