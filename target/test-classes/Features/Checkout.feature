Feature: SwagLabs Application Checkout Functionality

  Background: 
    Given User navigate to the login page

  @checkout @regression
  Scenario Outline: Validate user is able to place an order
    When User enter username "<username>" and password "<password>"
    And User click the login button
    When User adds 2 product to the cart
    And User clicks on the cart icon
    Then The Products count should be 2 on the checkout page
    When the user clicks the Remove 1 products
    Then the cart icon should display "1" item
    And User navigates back to the shopping page
    When User adds 2 product to the cart
    And User clicks on the cart icon
    Then The Products count should be 3 on the checkout page
    And User clicks on the checkout button
    And User enters the user information "<firstName>" "<lastName>" "<zipCode>"
    And User clicks on the continue button on the information page
    Then The Products count should be 3 on the overview page
    And The total price should be validated
    And User clicks on the finish button on the overview page
    Then User should navigate to the confirmation page
    And User clicks on the home button and should navigate to the product page

    Examples: 
      | username      | password     | firstName | lastName | zipCode |
      | standard_user | secret_sauce | Yanadaiah | Eerla    |  546765 |
