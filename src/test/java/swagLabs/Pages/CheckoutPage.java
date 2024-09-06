package swagLabs.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.DriverFactory;
import utilities.Utilities;

public class CheckoutPage {

	private WebDriver driver;
	private Utilities utility = new Utilities(DriverFactory.getDriver());

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	private By checkoutItems = By.xpath("//div[@data-test='inventory-item']");
	private By continueShopping = By.xpath("//button[@id='continue-shopping']");
	private By checkoutbtn = By.xpath("//button[@id='checkout']");
	private By fNameInput = By.xpath("//input[@id='first-name']");
	private By lNameInput = By.xpath("//input[@id='last-name']");
	private By zipNameInput = By.xpath("//input[@id='postal-code']");
	private By continueBtn = By.xpath("//input[@id='continue']");
	private By prodPrice = By.xpath("//div[@class='inventory_item_price']");
	private By itemsTotal = By.xpath("//div[@data-test='subtotal-label']");
	private By textPrice = By.xpath("//div[@data-test='tax-label']");
	private By totalPrice = By.xpath("//div[@data-test='total-label']");
	private By finish = By.xpath("//button[@id='finish']");
	private By confirmationText = By.xpath("//h2[@data-test='complete-header']");
	private By backHomeBtn = By.xpath("//button[@id='back-to-products']");
	private By ProdsOnHomePage = By.xpath("//div[@class='inventory_item']");

	public void validateItemOnCheckoutPage(int items) {
		List<WebElement> cartItemCount = driver.findElements(checkoutItems);
		int actualCount = cartItemCount.size();
		Assert.assertEquals(items, actualCount);
	}

	public void NavigateBackToShoppingPageFromCheckoutPage() {
		utility.waitForElementToBeClickable(continueShopping, 10);
		driver.findElement(continueShopping).click();
	}

	public void clickOnCheckoutBtn() {
		utility.waitForElementToBeClickable(checkoutbtn, 10);
		driver.findElement(checkoutbtn).click();
	}

	public void userEntersInformation(String fname, String lName, String zipCode) {
		utility.waitForElementToBeClickable(fNameInput, 10);
		driver.findElement(fNameInput).sendKeys(fname);
		driver.findElement(lNameInput).sendKeys(lName);
		driver.findElement(zipNameInput).sendKeys(zipCode);
	}

	public void clickOnContinueBtn() {
		utility.waitForElementToBeClickable(continueBtn, 10);
		driver.findElement(continueBtn).click();
	}

	public void validatePriceOnOverViewPage() {
		List<Double> productPrices = new ArrayList<>();
		List<WebElement> priceElements = driver.findElements(prodPrice);

		for (WebElement priceElement : priceElements) {
			String priceText = extractNumericValue(priceElement.getText());
			productPrices.add(Double.parseDouble(priceText));
		}

		double expectedItemTotal = productPrices.stream().mapToDouble(Double::doubleValue).sum();

		WebElement itemTotalPriceElement = driver.findElement(itemsTotal);
		double displayedItemTotal = Double.parseDouble(extractNumericValue(itemTotalPriceElement.getText()));

		WebElement taxElement = driver.findElement(textPrice);
		double tax = Double.parseDouble(extractNumericValue(taxElement.getText()));

		WebElement finalTotalElement = driver.findElement(totalPrice);
		double displayedFinalTotal = Double.parseDouble(extractNumericValue(finalTotalElement.getText()));

		double delta = 0.01;
		Assert.assertEquals(displayedItemTotal, expectedItemTotal, delta);

		double expectedFinalTotal = displayedItemTotal + tax;
		Assert.assertEquals(displayedFinalTotal, expectedFinalTotal, delta);
	}

	private String extractNumericValue(String text) {
		Pattern pattern = Pattern.compile("[\\d.]+");
		Matcher matcher = pattern.matcher(text);
		return matcher.find() ? matcher.group() : "0";
	}

	public void clickOnFinishBtn() {
		utility.waitForElementToBeClickable(finish, 10);
		driver.findElement(finish).click();
	}

	public void validateUserIsOnConfirmationPage() {
		utility.waitForElementToBeVisible(confirmationText, 10);
		Assert.assertTrue(driver.findElement(backHomeBtn).isDisplayed());
	}

	public void validateUserNavigateToHomePage() {
		utility.waitForElementToBeClickable(backHomeBtn, 10);
		driver.findElement(backHomeBtn).click();
		Assert.assertTrue(driver.findElement(ProdsOnHomePage).isDisplayed());
	}

}
