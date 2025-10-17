package eu.eurotechstudy.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/html-reports.html"
        },
        features = "src/test/resources/features",
        glue = "eu/eurotechstudy/step_definitions",
        dryRun = false,
        tags = "@wip",
        publish = false
)
public class Eurotech_Runner {
}
