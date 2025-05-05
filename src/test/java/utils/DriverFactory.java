package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static utils.ConfigReader.getInt;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static WebDriver createDriver(String browser) throws MalformedURLException {
        WebDriver driverInstance;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driverInstance = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            driverInstance = new FirefoxDriver(options);

        } else if (browser.equalsIgnoreCase("chrome_remote")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless=new"); // Optional: add only if needed
            driverInstance = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

        } else if (browser.equalsIgnoreCase("firefox_remote")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless"); // Optional: add only if needed
            driverInstance = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        int implicitWait = getInt("implicit.wait");
        driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        setDriver(driverInstance);
        return driverInstance;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
