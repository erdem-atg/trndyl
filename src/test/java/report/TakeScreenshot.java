package report;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import test.Base;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TakeScreenshot extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult test) {
        WebDriver driver = ((Base) test.getInstance()).driver;
        if (!test.isSuccess()) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                File destFile = new File(getPath());
                FileUtils.copyFile(scrFile, destFile);
                addScreenshot(destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getPath() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm_ss");
        String currentDateTime = dateTimeFormatter.format(LocalDateTime.now());

        return new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/screenshots" + "/SS_" + currentDateTime + ".png";
    }

    private void addScreenshot(File destFile) {
        Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
    }


}