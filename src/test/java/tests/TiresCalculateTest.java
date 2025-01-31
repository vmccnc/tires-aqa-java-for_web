package tests;

import dto.TiresInput;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class TiresCalculateTest extends BaseTest {

    TiresInput tiresInput = TiresInput.builder()
            .selectWidth("235")
            .selectProfile("60")
            .selectDiameter("16")
            .selectProtector("RISKO")
            .enterFirstPrice("100")
            .enterSecondPrice("2000")
            .build();

    @Owner("Elizaveta Nikolaenya")
    @Test(testName = "Check filling tires table", description = "Verify that the tires table is filled correctly.")
    @Description("Verify that the tires table is filled correctly and the tires list is opened.")
    public void checkFillingTiresTable() {
        homeStep.openHomePage()
                .changeLanguage();
        tiresStep.fillingTheTiresTable(tiresInput);

        boolean isTiresListOpened = tiresPage.isTiresListOpened();
        Assert.assertTrue(isTiresListOpened, "Tires list is not opened after filling the table.");
    }

    @Owner("Elizaveta Nikolaenya")
    @Test(testName = "Check add to cart if user not logged", description = "Verify that an error message is displayed " +
            "when trying to add a tire to the cart without logging in.")
    @Description("Verify that an error message 'User  not authenticated' is displayed when a user tries to add a tire " +
            "to the cart without being logged in.")
    public void checkAddToCartIfUserNotLogged() {
        homeStep.openHomePage()
                .changeLanguage();
        tiresStep.fillingTheTiresTable(tiresInput)
                .addingToCart();

        String errorMessage = tiresPage.errorMessageIsVisible();
        Assert.assertEquals(errorMessage, "User  not authenticated", "Expected error message was not " +
                "displayed.");
    }

    @Owner("Elizaveta Nikolaenya")
    @Test(testName = "Check add to cart if user logged", description = "Verify that a tire can be added to the cart when " +
            "the user is logged in.")
    @Description("Verify that a success message is displayed when a logged-in user adds a tire to the cart.")
    public void checkAddToCartIfUserLogged() {
        homeStep.openHomePage();
        loginStep.login(user, password)
                .openHomePage()
                .changeLanguage();
        tiresStep.fillingTheTiresTable(tiresInput)
                .addingToCart();

        String successMessage = tiresPage.successMessageIsVisible();
        Assert.assertEquals(successMessage, "Product added to cart!", "Expected success message was not " +
                "displayed.");
    }
}