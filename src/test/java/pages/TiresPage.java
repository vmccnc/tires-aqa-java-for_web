package pages;

import dto.TiresInput;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import wrappers.TiresInputWrapper;

@Log4j2
public class TiresPage extends BasePage {

    private final By SEARCH_BUTTON = By.xpath("//button[text()='Search tires']");
    private final By TIRES_LIST = By.xpath("//div[@class='tires-list']");

    public TiresPage(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public TiresPage fillingTheTiresTable(TiresInput tiresInput) {
        TiresInputWrapper tiresInputWrapper = new TiresInputWrapper(driver);
        tiresInputWrapper.selectWidth(tiresInput.getSelectWidth());
        tiresInputWrapper.selectProfile(tiresInput.getSelectProfile());
        tiresInputWrapper.selectDiameter(tiresInput.getSelectDiameter());
        tiresInputWrapper.selectProtector(tiresInput.getSelectProtector());
        tiresInputWrapper.enterFirstPrice(tiresInput.getEnterFirstPrice());
        tiresInputWrapper.enterSecondPrice(tiresInput.getEnterSecondPrice());
        return this;
    }

    public TiresPage clickSearchTiers() {
        log.info("Search Tiers");
        click(SEARCH_BUTTON);
        return this;
    }

    public boolean isTiresListOpened() {
        log.info("Checking if Tires list is opened");
        try {
            waitUntilElementBeVisible(TIRES_LIST);
            log.info("Tires list is opened");
            return true;
        } catch (TimeoutException e) {
            log.error("Tires list is not opened: {}", e.getMessage());
            return false;
        }
    }
}
