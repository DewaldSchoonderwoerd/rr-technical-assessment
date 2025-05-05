package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.PlaceOrderPage;
import pages.ProductPage;

import java.util.List;

public class DemoblazePurchaseTest extends BaseTest {

    @Epic("E-Commerce")
    @Feature("Cart Management")
    @Story("Add items and checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Verify adding items to cart and completing purchase")
    public void testCompletePurchaseFlow() {
        HomePage home = new HomePage(driver);
        ProductPage product = new ProductPage(driver);
        CartPage cart = new CartPage(driver);
        PlaceOrderPage order = new PlaceOrderPage(driver);

        // Add products
        home.goHome();
        home.selectProduct("Samsung galaxy s6");
        product.addToCart();

        home.goHome();
        home.selectProduct("Sony vaio i5");
        product.addToCart();

        // Verify cart total
        cart.openCart();
        List<Integer> prices = cart.getItemPrices();
        int expectedTotal = prices.stream().mapToInt(Integer::intValue).sum();
        int actualTotal = cart.getTotalPrice();
        Assert.assertEquals(actualTotal, expectedTotal, "Total price does not match sum of items");

        // Complete order
        cart.clickPlaceOrder();
        order.fillFormAndPurchase("John Doe", "USA", "NY", "4111111111111111", "12", "2025");

        Assert.assertEquals(order.getConfirmationHeader(), "Thank you for your purchase!", "Confirmation header mismatch");
        System.out.println("Order Details:\n" + order.getConfirmationDetails());
        order.clickOk();
    }

}
