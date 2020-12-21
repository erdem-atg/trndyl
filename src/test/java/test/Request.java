package test;

import helper.ResponseStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.MainPage;
import report.StatusReport;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class Request extends Base {

    private MainPage mainPage;

    @BeforeTest
    @Parameters(value = "browser")
    public void setUp(String browser) throws MalformedURLException {
        super.setUp(browser);
        mainPage = new MainPage(driver);
    }

    @Test
    public void sendRequestToBtqLinksAndGetResponses() throws Exception {
        mainPage.at();

        List<ResponseStatus> responseStatusList = mainPage.getBtqLinks()
                .stream().map(this::getStatus).collect(Collectors.toList());

        new StatusReport().write(responseStatusList);
    }

    private ResponseStatus getStatus(String s) {
        try {
            return new ResponseStatus(getResponseCode(s), s);
        } catch (IOException e) {
            return new ResponseStatus(408, s);
        }
    }

    private Integer getResponseCode(String link) throws IOException {
        return ((HttpURLConnection) new URL(link).openConnection()).getResponseCode();
    }

    @AfterTest
    public void tearDown() {
        super.tearDown();
    }

}
