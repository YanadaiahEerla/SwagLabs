Feature: SwagLabs Application Product Page Functionality

  Background: 
    Given User navigate to the login page

  @productPage @regression
  Scenario Outline: Validate user is able to see the Product List and details
    When User enter username "<username>" and password "<password>"
    And User click the login button
    Then I should see a list of products with their names, images, prices, and descriptions

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  @productPage @regression
  Scenario Outline: Validate user is able to add a Product to the Cart
    When User enter username "<username>" and password "<password>"
    And User click the login button
    When User adds 1 product to the cart
    Then the cart icon should display "1" item

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  @productPage @regression
  Scenario Outline: Footer Links Verification
    When User enter username "<username>" and password "<password>"
    And User click the login button
    When User scrolls to the footer
    Then User should see links to Twitter, Facebook, and LinkedIn
    And each link should open the correct page

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  @productPage @regression
  Scenario Outline: Sort products by different criteria
    When User enter username "<username>" and password "<password>"
    And User click the login button
    When User selects "<sortOption>" from the sorting dropdown
    Then Products should be sorted "<expectedOutcome>"

    Examples: 
      | username      | password     | sortOption          | expectedOutcome              |
      | standard_user | secret_sauce | Name (A to Z)       | alphabetically from A to Z   |
      | standard_user | secret_sauce | Name (Z to A)       | alphabetically from Z to A   |
      | standard_user | secret_sauce | Price (low to high) | by price in ascending order  |
      | standard_user | secret_sauce | Price (high to low) | by price in descending order |
