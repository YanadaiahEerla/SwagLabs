package swagLabsSteps;

import org.junit.Assert;

import io.cucumber.java.en.*;
import swagLabs.Pages.ProductsPage;
import utilities.DriverFactory;
import utilities.Utilities;

public class ProductsPageSteps {

	private ProductsPage ProductPage = new ProductsPage(DriverFactory.getDriver());
	private Utilities utility = new Utilities(DriverFactory.getDriver());

	@And("User clicks on the Burger Menu")
	public void user_click_on_burger_menu() {
		ProductPage.ClicOnBurgerMenu();
	}

	@And("User clicks on the {string} CTA")
	public void user_click_the_login_button(String CTA) {
		switch (CTA) {
		case "About":
			ProductPage.ClicOnAboutCTA();
			break;
		case "Logout":
			ProductPage.ClicOnLogoutCTA();
			break;
		case "Reset App State":
			ProductPage.ClicOnResetCTA();
			break;
		default:
			throw new IllegalArgumentException("Unknown user type: " + CTA);

		}

	}

	@Then("I should see a list of products with their names, images, prices, and descriptions")
	public void i_should_see_a_list_of_products_with_their_names_images_prices_and_descriptions() {
		ProductPage.validateNamesImagesPricesAndDescriptions();
	}

	@When("User adds {int} product to the cart")
	public void user_adds_a_product_to_the_cart(int productCount) {
		ProductPage.addProductToCart(productCount);
	}

	@Then("the cart icon should display {string} item")
	public void the_cart_icon_should_display_item(String productsCount) {
		ProductPage.cartIconDisplayItemCount(productsCount);
	}

	@When("User scrolls to the footer")
	public void user_scrolls_to_the_footer() {
		ProductPage.scrollToFooter();
	}

	@Then("User should see links to Twitter, Facebook, and LinkedIn")
	public void i_should_see_links_to_twitter_facebook_and_linked_in() {
		ProductPage.validateSocialMediaLinks();
	}

	@Then("each link should open the correct page")
	public void each_link_should_open_the_correct_page() {
		ProductPage.validateLinksOpenCorrectPages();
	}

	@When("User selects {string} from the sorting dropdown")
	public void select_sorting_from_dropdown(String sortOpt) {
		ProductPage.selectFromDropdown(sortOpt);
	}

	@Then("Products should be sorted {string}")
	public void Products_should_be_sorted(String expectedOutcome) {
		ProductPage.ValidateProductsShouldBeSorted(expectedOutcome);
	}

	@When("the user clicks the Remove {int} products")
	public void the_user_clicks_the_remove_products(Integer removeCount) {
		ProductPage.removeProductsFromCart(removeCount);
	}

	@Then("Validate user navigate to the {string}")
	public void validate_user_navigate_to_the(String url) {
		utility.waitForUrlContains(url, 10);
		String actualUrl = DriverFactory.getDriver().getCurrentUrl();
		Assert.assertTrue("User is not navigated to the expected URL!", actualUrl.contains(url));

	}

	@Then("Validate Product cart should be empty")
	public void validate_product_cart_should_be_empty() {
		ProductPage.validateProductCartShouldBeEmpty();
	}

	@When("User clicks on the cart icon")
	public void user_clicks_on_the_cart_icon() {
		ProductPage.clickOnCart();
	}

}
