package utils;

import factory.DriverFactory;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public static void takeScreenshot(String testName) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver == null) {
            System.out.println("WebDriver is null. Cannot take screenshot.");
            return;
        }

        try {
            File srcFile = takeScreenshot(driver);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = "screenshots/" + testName + "_" + timestamp + ".png";

            File destFile = new File(filename);
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("Screenshot saved: " + filename);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public static File takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }
}
