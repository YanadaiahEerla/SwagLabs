package utilities;

import java.time.Duration;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	private WebDriver driver;

	public Utilities(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForUrlContains(String partialUrl, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.urlContains(partialUrl));
	}

	public void waitForElementToBeVisible(By element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public void waitForElementToBeClickable(By element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForNumberOfWindowsToBe(final int expectedNumberOfWindows, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		try {
			wait.until(new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					Set<String> handles = driver.getWindowHandles();
					return handles.size() == expectedNumberOfWindows;
				}
			});
		} catch (WebDriverException e) {
			throw new RuntimeException("Timed out waiting for number of windows to be " + expectedNumberOfWindows, e);
		}
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void selectByVisibleText(WebDriver driver, By locator, String visibleText) {
		WebElement dropdownElement = driver.findElement(locator);
		Select select = new Select(dropdownElement);
		select.selectByVisibleText(visibleText);
	}

	public void selectByValue(WebDriver driver, By locator, String value) {
		WebElement dropdownElement = driver.findElement(locator);
		Select select = new Select(dropdownElement);
		select.selectByValue(value);
	}

	public void selectByIndex(WebDriver driver, By locator, int index) {
		WebElement dropdownElement = driver.findElement(locator);
		Select select = new Select(dropdownElement);
		select.selectByIndex(index);
	}

}
