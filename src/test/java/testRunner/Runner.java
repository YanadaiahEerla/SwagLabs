package testRunner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/resources/Features/" }, glue = { "swagLabsSteps" }, plugin = { "pretty",
				"html:target/cucumber-reports.html", "json:target/cucumber-reports/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "timeline:test-output-thread/" },
		monochrome = true,
		dryRun = false
		//tags= "@regression"
	
)

public class Runner extends AbstractTestNGCucumberTests {

}
