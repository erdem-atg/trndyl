package test;

import helper.ScenarioDataReader;
import helper.LoginScenario;
import org.testng.annotations.*;
import pages.MainPage;
import report.TakeScreenshot;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

@Listeners(TakeScreenshot.class)
public class Login extends Base {

    private MainPage homepage;

    @BeforeTest
    @Parameters(value = "browser")
    public void setUp(String browser) throws MalformedURLException {
        super.setUp(browser);
        homepage = new MainPage(driver);
    }

    @Test
    public void runDataDrivenLoginScenarios() throws IOException {
        homepage.go();
        homepage.checkLogoDisplay();
        homepage.openLoginModal();

        getLoginScenarios().forEach(loginScenario -> {

                    homepage.clearInputs();
                    homepage.login(loginScenario.getEmail(), loginScenario.getPassword());

                    assertTrue(
                            loginScenario.getScenarioName(),
                            homepage.checkErrorMessageVisible(loginScenario.getFieldValidationErrorMessage())
                    );
                }
        );
    }

    private List<LoginScenario> getLoginScenarios() throws IOException {
        List<LoginScenario> loginScenarios = new ScenarioDataReader().readLoginScenarios();
        assertTrue(!loginScenarios.isEmpty());
        return loginScenarios;
    }

    @AfterTest
    public void tearDown() {
        super.tearDown();
    }
}
