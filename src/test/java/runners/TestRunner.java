package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
//        features = "src/test/resources/features/login.feature",
        features = "src/test/resources/features/bookStore.feature",
//        features = "src/test/resources/features/browserWindowsHandling.feature",


        glue = "stepDefinitions",  // Match exact package
        plugin = {"pretty","html:target/cucumber-report.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
