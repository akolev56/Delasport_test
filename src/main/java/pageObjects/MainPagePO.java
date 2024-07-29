package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static tools.Tools.findElementBy;

public class MainPagePO {

    static synchronized public WebElement getUserBalanceValue() {
        return findElementBy(By.xpath("//li[1]/span[contains(@class,'balance-item-amount')]"));
    }
}
