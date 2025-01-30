package tests;

import dto.TiresInput;
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

    @Test
    public void testFillingTiresTable() {
        homeStep.openHomePage()
                .changeLanguage();
        tiresStep.fillingTheTiresTable(tiresInput);

        boolean isTiresListOpened = tiresPage.isTiresListOpened();
        Assert.assertTrue(isTiresListOpened, "Tires list is not opened after filling the table.");
    }
}
