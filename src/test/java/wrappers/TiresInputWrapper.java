package wrappers;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class TiresInputWrapper {

    private final WebDriver driver;
    protected WebDriverWait wait;

    private final String SELECT_WIDTH = "//div[@class='choose-width']//select/option[@value='%s']";
    private final String SELECT_PROFILE = "//div[@class='choose-profile']//select/option[@value='%s']";
    private final String SELECT_DIAMETER = "//div[@class='choose-diametr']//select/option[@value='%s']";
    private final String SELECT_PROTECTOR = "//div[@class='choose-protector']//select/option[@value='%s']";
    private final String ENTER_FIRST_PRICE = "//div[@class='choose-price__wrapper']/input[1]";
    private final String ENTER_SECOND_PRICE = "//div[@class='choose-price__wrapper']/input[2]";

    public TiresInputWrapper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Description("Выбор ширины шин")
    public void selectWidth(String value) {
        log.info("Selecting tire width '{}'", value);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(SELECT_WIDTH, value))));
        driver.findElement(By.xpath(String.format(SELECT_WIDTH, value))).click();
    }

    @Description("Выбор профиля шин")
    public void selectProfile(String value) {
        log.info("Selecting tire profile '{}'", value);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(SELECT_PROFILE, value))));
        driver.findElement(By.xpath(String.format(SELECT_PROFILE, value))).click();
    }

    @Description("Выбор диаметра шин")
    public void selectDiameter(String value) {
        log.info("Selecting tire diameter '{}'", value);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(SELECT_DIAMETER, value))));
        driver.findElement(By.xpath(String.format(SELECT_DIAMETER, value))).click();
    }

    @Description("Выбор протектора шин")
    public void selectProtector(String value) {
        log.info("Selecting tire protector '{}'", value);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(SELECT_PROTECTOR, value))));
        driver.findElement(By.xpath(String.format(SELECT_PROTECTOR, value))).click();
    }

    @Description("Ввод цены в первое поле")
    public void enterFirstPrice(String price) {
        log.info("Entering first price '{}'", price);
        WebElement firstInput = driver.findElement(By.xpath(ENTER_FIRST_PRICE));
        firstInput.clear();
        firstInput.sendKeys(price);
    }

    @Description("Ввод цены во второе поле")
    public void enterSecondPrice(String price) {
        log.info("Entering second price '{}'", price);
        WebElement secondInput = driver.findElement(By.xpath(ENTER_SECOND_PRICE));
        secondInput.clear();
        secondInput.sendKeys(price);
    }
}