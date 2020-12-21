package test;


import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Base {

    private static final String FIREFOX = "firefox";
    public WebDriver driver;
;

    public void setUp(String browser) throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();

        if (browser.equals(FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.merge(dc);
            driver = new FirefoxDriver(firefoxOptions);

        } else {
            System.setProperty("webdriver.chrome.helper", "chromedriver.exe");
            dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
            dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
            driver = new ChromeDriver(dc);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
