package tools;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.interactions.Actions;


import static tools.WebDriverManager.*;
import static org.junit.jupiter.api.Assertions.*;

public class Tools {

    /**
     * Build the web driver and set implicitlyWait and pageLoadTimeout
     */
    static synchronized public void buildDriver() {
        webDriver();
    }

    /**
     * Clear Browser cookies
     */
    static public synchronized void clearWebDriveCookies() {
        getWebDriver().manage().deleteAllCookies();
    }

    /**
     * @return Selenium Web Driver Actions
     */
    static public Actions actions() {
        return new Actions(getWebDriver());
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
     * Clear field Element
     *
     * @param element the element you will clear
     */
    static public void clear(WebElement element) {
        doAction(element, "clear", "");
    }

    /**
     * Add data field on Element
     *
     * @param element the element you will add data
     */
    static public void sendKeys(WebElement element, String keys) {
        doAction(element, "sendKeys", keys);
    }

    /**
     * Get an attribute value from Element
     *
     * @param element the element you will get an attribute value
     */
    static public String getAttribute(WebElement element, String value) {
        return doAction(element, "getAttribute", value);
    }

    /**
     * For more control over the Actions and for more visibility
     *
     * @param element The element you interact with
     * @param action  The action you going to do on the element
     * @param text    The text you want to input in the element
     * @return the element Attribute value by Attribute name
     */
    static public String doAction(WebElement element, String action, String text) {

        int br = 0;
        String result = null;
        while (br <= 5) {
            br++;
            try {
                switch (action) {
                    case "click":
                        element.click();
                        break;
                    case "clear":
                        element.clear();
                        break;
                    case "sendKeys":
                        element.sendKeys(text);
                        break;
                    case "getAttribute":
                        result = element.getAttribute(text);
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
        return result;
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
     * Get the Element Text and verify that contains the right values
     *
     * @param element The web element with the text
     * @param text    The text value
     */
    public void assertWebElementText(WebElement element, String text) {
        boolean isPresent = false;
        int br = 0;
        while (!isPresent && br <= 10) {
            try {
                br++;
                Assert.isTrue(element.getText().contains(text), "The WebElement contains: '" + element.getText() +
                        "' The text '" + text + "' is not present on this Web Element, please check!");
                isPresent = true;
            } catch (Exception e) {
                Assert.isTrue(element.getText().contains(text), "The WebElement contains: '" + element.getText() +
                        "' The text '" + text + "' is not present on this Web Element, please check!");
            }

        }
    }




}
