package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlaceOrderPage extends BasePage {
    public PlaceOrderPage(WebDriver driver) {
        super(driver);
    }

    public void fillFormAndPurchase(String name, String country, String city, String card, String month, String year) {
        waitForVisibility(By.id("name")).sendKeys(name);
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("card")).sendKeys(card);
        driver.findElement(By.id("month")).sendKeys(month);
        driver.findElement(By.id("year")).sendKeys(year);
        waitForClickability(By.xpath("//button[text()='Purchase']")).click();
    }

    public String getConfirmationHeader() {
        return waitForVisibility(By.xpath("//div[contains(@class,'sweet-alert')]//h2")).getText();
    }

    public String getConfirmationDetails() {
        return waitForVisibility(By.xpath("//div[contains(@class,'sweet-alert')]//p")).getText();
    }

    public void clickOk() {
        waitForClickability(By.xpath("//button[text()='OK']")).click();
    }
}

