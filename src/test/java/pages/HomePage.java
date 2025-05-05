package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goHome() {
        waitForClickability(By.cssSelector(".navbar-brand")).click();
        waitForVisibility(By.id("tbodyid"));
    }

    public void selectProduct(String productName) {
        waitForClickability(By.linkText(productName)).click();
    }
}
