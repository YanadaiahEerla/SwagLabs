Feature: SwagLabs Application Burger Menu Functionality

  Background: 
    Given User navigate to the login page

  @burgerMenu @regression
  Scenario Outline: Validate Burger Menu About link Functionality
    When User enter username "<username>" and password "<password>"
    And User click the login button
    When User adds 4 product to the cart
    And User clicks on the Burger Menu
    And User clicks on the "About" CTA
    Then Validate user navigate to the "saucelabs.com"

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  @burgerMenu @regression
  Scenario Outline: Validate Burger Menu Logout functionality
    When User enter username "<username>" and password "<password>"
    And User click the login button
    And User clicks on the Burger Menu
    And User clicks on the "Logout" CTA
    Then User should be navigated to the login page

    Examples: 
      | username      | password     | userType |
      | standard_user | secret_sauce | standard |

  @burgerMenu @regression
  Scenario Outline: Validate Burger Menu Reset link Functionality
    When User enter username "<username>" and password "<password>"
    And User click the login button
    When User adds 4 product to the cart
    And User clicks on the Burger Menu
    And User clicks on the "Reset App State" CTA
    Then Validate Product cart should be empty

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |
