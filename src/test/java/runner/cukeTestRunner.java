package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        features = {".//src/test/resources/features"},
        tags = "@regression",
        glue = "steps"
)

public class cukeTestRunner {
}
