package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "definitions",   // step definitions package
                "config"         // cucumber spring config package
        },
        plugin = {
                "pretty",
                "summary"
        }
)
public class TestRunner {
}