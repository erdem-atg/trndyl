package helper;

public class LoginScenario {
    public LoginScenario(String email, String password, String fieldValidationErrorMessage, String scenarioName) {
        this.email = email;
        this.password = password;
        this.fieldValidationErrorMessage = fieldValidationErrorMessage;
        this.scenarioName = scenarioName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFieldValidationErrorMessage() {
        return fieldValidationErrorMessage;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    private final String email;
    private final String password;
    private final String fieldValidationErrorMessage;
    private final String scenarioName;
}
