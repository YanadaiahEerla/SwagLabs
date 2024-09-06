package swagLabs.Pages;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.DriverFactory;
import utilities.Utilities;

public class ProductsPage {

	private WebDriver driver;
	private Utilities utility = new Utilities(DriverFactory.getDriver());

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	private By burgerMenu = By.xpath("//button[@id='react-burger-menu-btn']");
	private By logoutLink = By.xpath("//a[@id='logout_sidebar_link']");
	private By aboutLink = By.xpath("//a[@id='about_sidebar_link']");
	private By resetLink = By.xpath("//a[@id='reset_sidebar_link']");
	private By products = By.xpath("//div[@class='inventory_item']");
	private By productNameLocator = By.xpath("//div[@class='inventory_item_name ']");
	private By productImgLocator = By.xpath("//div[@class='inventory_item_img']");
	private By productDescLocator = By.xpath("//div[@class='inventory_item_desc']");
	private By productPriceLocator = By.xpath("//div[@class='inventory_item_price']");
	private By addToCart = By.xpath("//button[contains(@name,'add-to-cart')]");
	private By cartItemCount = By.xpath("//span[@class='shopping_cart_badge']");
	private By twitterLink = By.xpath("//a[@data-test='social-twitter']");
	private By facebookLink = By.xpath("//a[@data-test='social-facebook']");
	private By linkedInLink = By.xpath("//a[@data-test='social-linkedin']");
	private By footer = By.xpath("//footer[@data-test='footer']");
	private By sortOpt = By.xpath("//select[@class='product_sort_container']");
	private By removeBtn = By.xpath("//button[contains(@name,'remove')]");
	private By cart = By.xpath("//a[@data-test='shopping-cart-link']");

	public void ClicOnBurgerMenu() {
		utility.waitForElementToBeClickable(burgerMenu, 10);
		driver.findElement(burgerMenu).click();
	}

	public void ClicOnLogoutCTA() {
		utility.waitForElementToBeClickable(logoutLink, 10);
		driver.findElement(logoutLink).click();
	}

	public void ClicOnAboutCTA() {
		utility.waitForElementToBeClickable(aboutLink, 10);
		driver.findElement(aboutLink).click();
	}

	public void ClicOnResetCTA() {
		utility.waitForElementToBeClickable(resetLink, 10);
		driver.findElement(resetLink).click();
	}

	public void validateNamesImagesPricesAndDescriptions() {
		List<WebElement> productElements = driver.findElements(products);
		Assert.assertTrue("No products found on the page!", productElements.size() > 0);

		for (WebElement product : productElements) {
			WebElement productName = product.findElement(productNameLocator);
			Assert.assertTrue("Product name is missing!", productName.isDisplayed());
			Assert.assertFalse("Product name is empty!", productName.getText().isEmpty());

			WebElement productImage = product.findElement(productImgLocator);
			Assert.assertTrue("Product image is missing!", productImage.isDisplayed());

			WebElement productPrice = product.findElement(productPriceLocator);
			Assert.assertTrue("Product price is missing!", productPrice.isDisplayed());
			Assert.assertFalse("Product price is empty!", productPrice.getText().isEmpty());

			WebElement productDescription = product.findElement(productDescLocator);
			Assert.assertTrue("Product description is missing!", productDescription.isDisplayed());
			Assert.assertFalse("Product description is empty!", productDescription.getText().isEmpty());
		}
	}

	public void addProductToCart(int productCount) {
		utility.waitForElementToBeClickable(addToCart, 10);
		List<WebElement> addToCartButtonList = driver.findElements(addToCart);
		Assert.assertTrue("Not enough products available to add to the cart!",
				addToCartButtonList.size() >= productCount);
		for (int i = 0; i < productCount; i++) {
			addToCartButtonList.get(i).click();
		}
	}

	public void cartIconDisplayItemCount(String count) {
		utility.waitForElementToBeVisible(cartItemCount, 10);
		String itemCount = driver.findElement(cartItemCount).getText();
		Assert.assertEquals("Cart does not display the correct number of items!", count, itemCount);
	}

	public void scrollToFooter() {
		utility.scrollToElement(driver.findElement(footer));
		utility.waitForElementToBeVisible(footer, 10);
	}

	public void validateSocialMediaLinks() {
		utility.waitForElementToBeVisible(twitterLink, 10);
		utility.waitForElementToBeVisible(facebookLink, 10);
		utility.waitForElementToBeVisible(linkedInLink, 10);

		Assert.assertTrue("Twitter link is not displayed!", driver.findElement(twitterLink).isDisplayed());
		Assert.assertTrue("Facebook link is not displayed!", driver.findElement(facebookLink).isDisplayed());
		Assert.assertTrue("LinkedIn link is not displayed!", driver.findElement(linkedInLink).isDisplayed());
	}

	public void validateLinksOpenCorrectPages() {
		String originalWindow = driver.getWindowHandle();

		driver.findElement(twitterLink).click();
		switchToNewWindowAndValidateURL("x.com");
		driver.switchTo().window(originalWindow);

		driver.findElement(facebookLink).click();
		switchToNewWindowAndValidateURL("www.facebook.com");
		driver.switchTo().window(originalWindow);

		driver.findElement(linkedInLink).click();
		switchToNewWindowAndValidateURL("www.linkedin.com");
		driver.switchTo().window(originalWindow);
	}

	private void switchToNewWindowAndValidateURL(String expectedUrl) {
		utility.waitForNumberOfWindowsToBe(2, 10);

		for (String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
		}
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue("Expected URL was not opened! Expected: " + expectedUrl + ", but got: " + currentUrl,
				currentUrl.contains(expectedUrl));
		driver.close();
	}

	public void selectFromDropdown(String sort) {
		utility.selectByVisibleText(driver, sortOpt, sort);
	}

	public void ValidateProductsShouldBeSorted(String expectedOutcome) {
		List<WebElement> productNames = driver.findElements(productNameLocator);
		List<WebElement> productPrices = driver.findElements(productPriceLocator);

		if (expectedOutcome.contains("alphabetically")) {
			validateProductNamesSorting(productNames, expectedOutcome.contains("A to Z"));
		} else if (expectedOutcome.contains("price")) {
			validateProductPricesSorting(productPrices, expectedOutcome.contains("ascending"));
		}
	}

	private void validateProductNamesSorting(List<WebElement> productNames, boolean ascending) {
		List<String> actualNames = productNames.stream().map(WebElement::getText).collect(Collectors.toList());
		List<String> sortedNames = actualNames.stream().sorted().collect(Collectors.toList());
		if (!ascending) {
			sortedNames.sort((a, b) -> b.compareTo(a));
		}
		Assert.assertEquals("Product names are not sorted correctly!", sortedNames, actualNames);
	}

	private void validateProductPricesSorting(List<WebElement> productPrices, boolean ascending) {
		List<Double> actualPrices = productPrices.stream()
				.map(price -> Double.parseDouble(price.getText().replace("$", ""))).collect(Collectors.toList());
		List<Double> sortedPrices = actualPrices.stream().sorted().collect(Collectors.toList());
		if (!ascending) {
			sortedPrices.sort((a, b) -> b.compareTo(a));
		}
		Assert.assertEquals("Product prices are not sorted correctly!", sortedPrices, actualPrices);
	}

	public void removeProductsFromCart(int removeCount) {
		utility.waitForElementToBeClickable(removeBtn, 10);
		List<WebElement> addToCartButtonList = driver.findElements(removeBtn);
		Assert.assertTrue("Not enough products available to add to the cart!",
				addToCartButtonList.size() >= removeCount);
		for (int i = 0; i < removeCount; i++) {
			addToCartButtonList.get(i).click();
		}
	}

	public void validateProductCartShouldBeEmpty() {
		List<WebElement> elements = driver.findElements(cartItemCount);
		Assert.assertTrue("Cart did not reset", elements.isEmpty());
	}

	public void clickOnCart() {
		utility.waitForElementToBeVisible(cart, 0);
		driver.findElement(cart).click();
	}
}
