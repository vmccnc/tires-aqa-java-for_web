package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class OrderInputWrapper {

    WebDriver driver;
    String label;

    public OrderInputWrapper(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        log.info("Writing '{}' into '{}'", text, label);
        WebElement inputElement = driver.findElement(By.xpath(String.format("//input[@id='%s']", label)));
        inputElement.clear();
        inputElement.sendKeys(text);
    }
}