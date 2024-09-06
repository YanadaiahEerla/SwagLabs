package swagLabsSteps;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class Hooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader cofigReader;
	Properties prop;

	@Before(order = 0)
	public void getProperty() {
		cofigReader = new ConfigReader();
		prop = cofigReader.init_prop();
	}

	@Before(order = 1)
	public void LaunchBrowser() {
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
	}

	@After(order = 0)
	public void quiteBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			String ScreenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", ScreenshotName);
		}
	}

}
