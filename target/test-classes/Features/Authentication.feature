Feature: SwagLabs Application Authentication Functionality

  Background: 
    Given User navigate to the login page

  @loginFeature @regression
  Scenario Outline: Login with different types of users
    When User enter username "<username>" and password "<password>"
    And User click the login button
    Then validate the outcome for "<userType>" with message "<verificationMessage>"

    Examples: 
      | username                | password      | userType           | verificationMessage                |
      | standard_user           | secret_sauce  | standard           | inventory                          |
      | locked_out_user         | secret_sauce  | locked_out         | user has been locked out           |
      | problem_user            | secret_sauce  | problem            | inventory                          |
      | performance_glitch_user | secret_sauce  | performance_glitch | inventory                          |
      | error_user              | secret_sauce  | error_user         | inventory                          |
      | visual_user             | secret_sauce  | visual_user        | inventory                          |
      |                         |               | empty_Uname_Pass   | Username is required               |
      | standard_user           |               | empty_pass         | Password is required               |
      | standard_user           | secret_sauce1 | invalidCrds        | Username and password do not match |

  @loginFeature @regression
  Scenario Outline: Validate Logout functionality
    When User enter username "<username>" and password "<password>"
    And User click the login button
    And User clicks on the Burger Menu
    And User clicks on the "Logout" CTA
    Then User should be navigated to the login page

    Examples: 
      | username      | password     | userType |
      | standard_user | secret_sauce | standard |
