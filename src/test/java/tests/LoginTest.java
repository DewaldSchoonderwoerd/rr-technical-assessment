package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void testLoginPageLoads() {
        driver.get("https://example.com/login");
        System.out.println("Title is: " + driver.getTitle());
    }
}