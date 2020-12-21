package pages;

import helper.ExplicitWaitMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainPage {
    private final WebDriver driver;
    private final ExplicitWaitMethods wait;

    @FindBy(id = "login-email")
    private WebElement email;
    @FindBy(id = "login-password-input")
    private WebElement password;
    @FindBy(className = "navigation-icon-user")
    private WebElement login;
    @FindBy(className = "submit")
    private WebElement submitButton;
    @FindBy(id = "error-box-wrapper")
    private WebElement error;
    @FindBy(className = "component-item")
    private List<WebElement> btqs;

    public MainPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new ExplicitWaitMethods(driver);
    }

    public void at() {
        driver.get("http://www.trendyol.com/Butik/Liste/Erkek");
    }

    public List<String> getBtqLinks() {
        return btqs.stream().map(webElement -> webElement.findElement(By.tagName("a")).getAttribute("href")
                ).collect(Collectors.toList());
    }

    public void loginPopup() {
        login.click();
        wait.forElementToBeDisplayed(5, email, "Email");
        wait.forElementToBeDisplayed(5, password, "Password");
        wait.forElementToBeDisplayed(5, submitButton, "Login button");
    }

    public void login(String strEmail, String strPassword) {
        email.sendKeys(strEmail);
        password.sendKeys(strPassword);
        submitButton.click();
    }

    public void clearInputs() {
        email.clear();
        password.clear();
    }

    public Boolean checkErrorMessageVisible(String errorMessage) {
        wait.forElementToBeDisplayed(5, error, "message");
        return error.getText().contains(errorMessage);
    }

    public void scrollDownToBottom() {
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        IntStream.range(0, 10).forEach(o -> {
            jsx.executeScript("window.scrollBy(0,550)", "");
            wait.explicitlyFor(2);
        });
    }
}
