package swagLabsSteps;

import io.cucumber.java.en.*;
import swagLabs.Pages.CheckoutPage;
import utilities.DriverFactory;

public class CheckoutSteps {

	private CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());

	@Then("The Products count should be {int} on the checkout page")
	public void the_products_count_should_be_on_the_checkout_page(Integer items) {
		checkoutPage.validateItemOnCheckoutPage(items);
	}

	@Then("User navigates back to the shopping page")
	public void user_navigates_back_to_the_shopping_page() {
		checkoutPage.NavigateBackToShoppingPageFromCheckoutPage();
	}

	@Then("User clicks on the checkout button")
	public void user_clicks_on_the_checkout_button() {
		checkoutPage.clickOnCheckoutBtn();
	}

	@Then("User enters the user information {string} {string} {string}")
	public void user_enters_the_user_information(String fname, String lName, String zipCode) {
		checkoutPage.userEntersInformation(fname, lName, zipCode);
	}

	@Then("User clicks on the continue button on the information page")
	public void user_clicks_on_the_continue_button_on_the_information_page() {
		checkoutPage.clickOnContinueBtn();
	}

	@Then("The Products count should be {int} on the overview page")
	public void the_products_count_should_be_on_the_overview_page(Integer items) {
		checkoutPage.validateItemOnCheckoutPage(items);
	}

	@Then("The total price should be validated")
	public void the_total_price_should_be_validated() {
		checkoutPage.validatePriceOnOverViewPage();
	}

	@Then("User clicks on the finish button on the overview page")
	public void user_clicks_on_the_finish_button_on_the_overview_page() {
		checkoutPage.clickOnFinishBtn();
	}

	@Then("User should navigate to the confirmation page")
	public void user_should_navigate_to_the_confirmation_page() {
		checkoutPage.validateUserIsOnConfirmationPage();
	}

	@Then("User clicks on the home button and should navigate to the product page")
	public void user_clicks_on_the_home_button_and_should_navigate_to_the_product_page() {
		checkoutPage.validateUserNavigateToHomePage();
	}

}
