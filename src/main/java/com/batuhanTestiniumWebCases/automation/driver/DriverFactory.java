package com.batuhanTestiniumWebCases.automation.driver;


import com.batuhanTestiniumWebCases.automation.config.Settings;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public DriverFactory() {
    }

    public static WebDriver InitializeBrowser() {
        WebDriver driver = null;
        if (driver == null) {

            switch (Settings.BrowserType) {
                case "Chrome":
                    ChromeOptions co = new ChromeOptions();
                    co.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(co);
                    break;

                case "FireFox":
                    FirefoxOptions fo = new FirefoxOptions();
                    driver = new FirefoxDriver(fo);
                    break;

                default:
                    Assert.fail("Desteklenmeyen browser: " + Settings.BrowserType);
            }

        }
        LocalDriverContext.setDriver(driver);
        return driver;
    }
}
