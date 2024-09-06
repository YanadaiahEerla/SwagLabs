Feature: SwagLabs Application Add to cart Functionality

  Background: 
    Given User navigate to the login page

  @addToCart @regression
  Scenario Outline: Validate user is able to add a Product to the Cart
    When User enter username "<username>" and password "<password>"
    And User click the login button
    When User adds 4 product to the cart
    Then the cart icon should display "4" item
    When the user clicks the Remove 2 products
    Then the cart icon should display "2" item

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  @addToCart @regression
  Scenario Outline: Validate adding items to the cart after logout and login
    When User enter username "<username>" and password "<password>"
    And User click the login button
    When User adds 3 product to the cart
    And User clicks on the Burger Menu
    And User clicks on the "Logout" CTA
    Then User should be navigated to the login page
    When User enter username "<username>" and password "<password>"
    And User click the login button
    Then the cart icon should display "3" item

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |
