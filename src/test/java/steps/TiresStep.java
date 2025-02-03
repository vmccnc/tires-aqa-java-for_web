package steps;

import dto.TiresInput;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.TiresPage;

@Log4j2
public class TiresStep {

    private final TiresPage tiresPage;

    public TiresStep(WebDriver driver, String baseURL) {
        this.tiresPage = new TiresPage(driver, baseURL);
        log.info("HomeStep initialized with driver: {}", driver);
    }

    @Step("Filling the tires table with input: {tiresInput}")
    public TiresStep fillingTheTiresTable(TiresInput tiresInput) {
        log.info("Filling the tires table with input: {}", tiresInput);
        try {
            tiresPage.fillingTheTiresTable(tiresInput);
            log.info("Tires table filled successfully");
        } catch (Exception e) {
            log.error("Failed to fill the tires table: {}", e.getMessage());
            throw e;
        }
        tiresPage.clickSearchTiers();
        return this;
    }

    @Step("Adding tires to cart")
    public TiresStep addingToCart() {
        tiresPage.clickAddToCart();
        return this;
    }
}
