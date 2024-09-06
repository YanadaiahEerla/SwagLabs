package swagLabs.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import utilities.DriverFactory;
import utilities.Utilities;

public class AuthenticationPage {

	private WebDriver driver;

	private Utilities utility = new Utilities(DriverFactory.getDriver());

	public AuthenticationPage(WebDriver driver) {
		this.driver = driver;
	}

	private By userName = By.xpath("//input[@id='user-name']");
	private By password = By.xpath("//input[@id='password']");
	private By loginBtn = By.xpath("//input[@id='login-button']");
	private By errorMgs = By.xpath("//h3[@data-test='error']");
	private By problamImg = By.xpath("//img[@class='inventory_item_img']");
	private By shopCart = By.xpath("//a[@data-test='shopping-cart-link']");

	public void enterUserName(String username) {
		driver.findElement(userName).sendKeys(username);
	}

	public void enterPassword(String Password) {
		driver.findElement(password).sendKeys(Password);
	}

	public void clickOnLogin() {
		driver.findElement(loginBtn).click();
	}

	public void validateSuccessfulLogin(String title) {
		utility.waitForUrlContains(title, 10);
		Assert.assertTrue((driver.getCurrentUrl()).contains(title));

	}

	public void validateLockedOutUser(String error) {

		utility.waitForElementToBeVisible(errorMgs, 10);
		Assert.assertTrue(driver.findElement(errorMgs).getText().contains(error));

	}

	public void validateProblemUser(String title) {
		utility.waitForUrlContains(title, 10);
		List<String> imageSources = new ArrayList<>();
		for (WebElement img : driver.findElements(problamImg)) {
			imageSources.add(img.getAttribute("src"));
		}

		String firstImageSrc = imageSources.get(0);
		boolean allSame = imageSources.stream().allMatch(src -> src.equals(firstImageSrc));

		Assert.assertTrue("All product images are the same for problem_user", allSame);

	}

	public void validatePerformanceGlitchUser(String title) {

		long startTime = System.currentTimeMillis();
		utility.waitForUrlContains(title, 10);
		long endTime = System.currentTimeMillis();
		long loadTime = endTime - startTime;
		System.out.println("Total load time: " + loadTime + " milliseconds");
		Assert.assertTrue("Login time was less than expected", loadTime > 5);
	}

	public void validatee_eror_user(String title) {
		utility.waitForUrlContains(title, 10);
	}

	public void validatevisual_user(String title) {
		utility.waitForUrlContains(title, 10);
		WebElement misalignedElement = driver.findElement(shopCart);
		Assert.assertFalse("UI element is misaligned", misalignedElement.getCssValue("position").equals("absolute"));
	}

	public void validateErrorMessage(String error) {
		utility.waitForElementToBeVisible(errorMgs, 10);
		String actualErrorMessage = driver.findElement(errorMgs).getText();
		Assert.assertTrue("Error message does not contain expected text", actualErrorMessage.contains(error));
	}

	public void validateUserOnLoginPage() {
		utility.waitForElementToBeVisible(loginBtn, 10);
		Assert.assertTrue("Element is not present", driver.findElement(loginBtn).isDisplayed());
	}

}
