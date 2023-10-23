import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = false,
        features = {"E:/AutomationProject/src/test/resources/BeforeTV.feature"},
        plugin = {"json:E:/AutomationProject/target/cucumber-reports/1.json", "html:E:/AutomationProject/target/cucumber-reports/1", "rerun:E:/AutomationProject/target/cucumber-reports/1.txt"},
        monochrome = false,
        tags = {"@test", "~@Ignore"},
        glue = {"com.zee5.tata.cucumber.appium.stepdefs"})
public class Parallel01IT {
}
