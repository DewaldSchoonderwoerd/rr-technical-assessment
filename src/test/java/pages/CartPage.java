package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        waitForClickability(By.id("cartur")).click();
        waitForVisibility(By.id("totalp"));
    }

    public List<Integer> getItemPrices() {
        return driver.findElements(By.xpath("//tr/td[3]"))
                .stream()
                .map(e -> Integer.parseInt(e.getText()))
                .toList();
    }

    public int getTotalPrice() {
        return Integer.parseInt(waitForVisibility(By.id("totalp")).getText());
    }

    public void clickPlaceOrder() {
        waitForClickability(By.xpath("//button[text()='Place Order']")).click();
    }
}

