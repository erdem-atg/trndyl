package helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScenarioDataReader {

    private static final String DELIMITER = ";";
    private static final String DATA_FILE = "DataDrivenLogin.csv";

    public List<LoginScenario> readLoginScenarios() throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(DATA_FILE), "ISO-8859-9"
                )
        )) {
            String firstLine = br.readLine();
            if (firstLine == null) throw new IOException("File is empty!");
            return br.lines()
                    .map(line -> {
                        List<String> columns = Arrays.asList(line.split(DELIMITER));
                        return new LoginScenario(
                                columns.get(0), //email
                                columns.get(1), //password
                                columns.get(2), //fieldValidationErrorMessage
                                columns.get(3)); //failureMessage
                    })
                    .collect(Collectors.toList());
        }

    }
}
