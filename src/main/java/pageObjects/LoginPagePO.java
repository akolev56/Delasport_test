package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tools.Tools;

import static tools.ThreadLocals.user;

public class LoginPagePO extends Tools {

    /**
     * Locate by Xpath
     *
     * @return Log In button
     */
    static synchronized public WebElement getTopNavigationLoginBtn() {
        return findElementBy(By.xpath("//button[contains(@class, 'cl-login')]"));
    }

    /**
     * Locate by id
     *
     * @return Enter Username Field
     */
    static synchronized public WebElement getEnterUsernameField() {
        return findElementBy(By.id("login_form[username]"));
    }

    /**
     * Locate by id
     *
     * @return Enter Password Field
     */
    static synchronized public WebElement getEnterPasswordField() {
        return findElementBy(By.id("login-modal-password-input"));
    }

    /**
     * Locate by id
     *
     * @return Account Login Popup Login Button
     */
    static synchronized public WebElement getAccountLoginPopupLoginBtn() {
        return findElementBy(By.xpath("//button[@type='submit'][contains(text(),'Log In')]"));
    }

    //Actions

    /**
     * This method will execute the whole login actions for the user
     */
    public static void loginWithUser() {
        click(getTopNavigationLoginBtn());
        sendKeys(getEnterUsernameField(), user.get().getUserName());
        sendKeys(getEnterPasswordField(), user.get().getUserPassword());
        click(getAccountLoginPopupLoginBtn());
    }
}
