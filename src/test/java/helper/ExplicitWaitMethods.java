package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitMethods {
    private final WebDriver driver;

    public ExplicitWaitMethods(WebDriver driver) {
        this.driver = driver;
    }

    private void waitUntilCondition(ExpectedCondition condition, String timeoutMessage, Integer timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(timeoutMessage);
        wait.until(condition);
    }

    public void forElementToBeDisplayed(Integer timeout, WebElement webElement, String webElementName) {
        ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(webElement);
        String timeoutMessage = webElementName + " wasn't displayed after " + Integer.toString(timeout) + " seconds.";
        waitUntilCondition(condition, timeoutMessage, timeout);
    }


    public void explicitlyFor(Integer second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
