package base;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import factory.DriverFactory;
import utils.ScreenshotUtil;

import java.net.MalformedURLException;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class BaseTest {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) throws MalformedURLException {
        DriverFactory.createDriver(browser);
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(result.getName());
        }
        DriverFactory.quitDriver();
    }
}
