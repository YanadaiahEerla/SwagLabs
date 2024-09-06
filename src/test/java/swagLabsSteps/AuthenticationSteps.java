package swagLabsSteps;

import java.util.Properties;

import io.cucumber.java.en.*;
import swagLabs.Pages.AuthenticationPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class AuthenticationSteps {

	private AuthenticationPage auth = new AuthenticationPage(DriverFactory.getDriver());
	private ConfigReader cofigReader = new ConfigReader();
	Properties prop = cofigReader.init_prop();

	@Given("User navigate to the login page")
	public void user_navigate_to_the_login_page() {
		DriverFactory.getDriver().get(prop.getProperty("url"));

	}

	@When("User enter username {string} and password {string}")
	public void user_enter_username_and_password(String username, String password) {
		auth.enterUserName(username);
		auth.enterPassword(password);

	}

	@When("User click the login button")
	public void user_click_the_login_button() {
		auth.clickOnLogin();

	}

	@Then("validate the outcome for {string} with message {string}")
	public void validate_the_outcome_for(String userType, String message) {

		switch (userType) {
		case "standard":
			auth.validateSuccessfulLogin(message);
			break;
		case "locked_out":
			auth.validateLockedOutUser(message);
			break;
		case "problem":
			auth.validateProblemUser(message);
			break;
		case "performance_glitch":
			auth.validatePerformanceGlitchUser(message);
			break;
		case "error_user":
			auth.validatee_eror_user(message);
			;
			break;
		case "visual_user":
			auth.validatevisual_user(message);
			break;
		case "empty_Uname_Pass":
			auth.validateErrorMessage(message);
			break;
		case "empty_pass":
			auth.validateErrorMessage(message);
			break;
		case "invalidCrds":
			auth.validateErrorMessage(message);
			break;
		default:
			throw new IllegalArgumentException("Unknown user type: " + userType);
		}

	}

	@Then("User should be navigated to the login page")
	public void User_should_be_navigated_to_the_login_page() {
		auth.validateUserOnLoginPage();
	}

}
