package tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.module.Network;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import java.util.Optional;

import static org.openqa.selenium.remote.http.Contents.utf8String;

public class WebDriverManager {
    private static WebDriver driver;
    private static DevTools devTools;


    public static void webDriver() {
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
    }


    public static void quitDriver() {
        driver.quit();
    }

    public static WebDriver getWebDriver() {
        return driver;
    }


}
