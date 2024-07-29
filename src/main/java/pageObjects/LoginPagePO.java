package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tools.Tools;

public class LoginPagePO extends Tools {


    /**
     * Locate by Xpath
     * @return Log In button
     */
    static synchronized public WebElement getTopNavigationLoginBtn() {
        return findElementBy(By.xpath("//button[contains(@class, 'cl-login')]"));
    }

    static synchronized public WebElement getEnterUsernameField() {
        return findElementBy(By.id("login_form[username]"));
    }

    static synchronized public WebElement getEnterPasswordField() {
        return findElementBy(By.id("login-modal-password-input"));
    }

    static synchronized public WebElement getAccountLoginPopupLoginBtn() {
        return findElementBy(By.xpath("//button[@type='submit'][contains(text(),'Log In')]"));
    }

    //Actions

    public static void loginWithUser(String userName, String userPassword) {
        click(getTopNavigationLoginBtn());
        sendKeys(getEnterUsernameField(), userName);
        sendKeys(getEnterPasswordField(), userPassword);
        click(getAccountLoginPopupLoginBtn());
    }
}
