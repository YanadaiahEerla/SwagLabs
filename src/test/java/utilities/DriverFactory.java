package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;

	public static ThreadLocal<WebDriver> localDriver = new ThreadLocal<>();

	// this method is used to initialize the threadlocal driver on the basis of the
	// given browser

	public WebDriver init_driver(String browser) {

		System.out.println("browser value is : " + browser);

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			localDriver.set(new ChromeDriver());
		} else if (browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			localDriver.set(new FirefoxDriver());
		} else if (browser.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			localDriver.set(new EdgeDriver());
		} else {
			System.out.println("please pass correct browser value: " + browser);
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return getDriver();

	}

	// this is used to get the driver with threadloacl
	public static synchronized WebDriver getDriver() {
		return localDriver.get();
	}

}
