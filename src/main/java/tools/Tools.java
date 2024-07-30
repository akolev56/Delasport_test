package tools;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static tools.WebDriverManager.*;
import static org.junit.jupiter.api.Assertions.*;

public class Tools {
    /**
     * Build the web driver and set implicitlyWait and pageLoadTimeout
     */
    static synchronized public void buildDriver() {
        WebDriverManager.webDriver();
    }

    /**
     * Click on Element
     *
     * @param element the element you will click
     */
    static public void click(WebElement element) {
        doAction(element, "click", "");
    }

    /**
     * Add data field on Element and sent keys as String
     *
     * @param element the element you will add data
     */
    static public void sendKeys(WebElement element, String keys) {
        doAction(element, "sendKeys", keys);
    }

    /**
     * For more control over the Actions and for more visibility
     *
     * @param element The element you interact with
     * @param action  The action you going to do on the element
     * @param text    The text you want to input in the element
     */
    static public void doAction(WebElement element, String action, String text) {

        int br = 0;
        while (br <= 5) {
            br++;
            try {
                switch (action) {
                    case "click":
                        element.click();
                        break;
                    case "sendKeys":
                        element.sendKeys(text);
                        break;
                    default:
                        System.out.println("There is no such Action");
                        fail();
                }
                br = 6;
            } catch (Exception e) {
                if (br == 5) {
                    throw e;
                }
                try {
                    Thread.sleep(1500);
                } catch (Exception ignored) {
                }
            }
        }
    }

    /**
     * Find web Elements and add some wait if it's needed
     *
     * @param by use all type of possible locators
     * @return WebElement
     */
    static synchronized public WebElement findElementBy(By by) {
        WebElement element = null;
        try {
            int br = 1;
            do {
                if (!getWebDriver().findElements(by).isEmpty() && getWebDriver().findElement(by).isDisplayed()) {
                    element = getWebDriver().findElement(by);
                } else {
                    Thread.sleep(1000);
                }
                br++;
            } while (br <= 10 && element == null);
        } catch (Exception NoSuchElementException) {
            fail("\nElement not found with Exception: " + NoSuchElementException);
        }
        return element;
    }

    /**
     * Get the Actual Text and compare it to Expected Text
     *
     * @param actualText   Actual text value
     * @param expectedText Expected text value
     */
    public void assertTextsAreEqual(String actualText, String expectedText) {
        boolean isPresent = false;
        int br = 0;
        while (!isPresent && br <= 10) {
            try {
                br++;
                Assert.isTrue(actualText.equals(expectedText), "The WebElement contains: '" + actualText +
                        "' The text '" + expectedText + "' is not present on this Web Element, please check!");
                isPresent = true;
            } catch (Exception e) {
                Assert.isTrue(actualText.equals(expectedText), "The WebElement contains: '" + actualText +
                        "' The text '" + expectedText + "' is not present on this Web Element, please check!");
            }
            System.out.println("Тhe web element value: '" + actualText + "' Equals the text: '" + expectedText + "'");

        }
    }

    /**
     * This method will verify that the web element is present in the page
     *
     * @param element = web element
     */
    public static void verifyWebElementPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


}
