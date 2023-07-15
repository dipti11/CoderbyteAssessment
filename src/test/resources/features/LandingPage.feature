@regression
Feature: Carousels on landing page
  As a user
  I want to verify the carousel on the landing page
  So that I can see the items in each carousel

  Background:
    Given I am on the landing page

  Scenario: Verify the presence of carousels on landing page
    When I scroll down to the carousel
    Then I should see the carousels
    And there should be 5 carousels

    #Example of a scenario outline. This will run 5 times, once for each row in the Examples table
  Scenario Outline: Verify the number of items in each carousel
    When I scroll down to the carousel
    Then I should see 15 items in the carousel <carousel No>
      Examples:
        | carousel No |
        | 1 |
        | 2 |
        | 3 |
        | 4 |
        | 5 |

      #Example of a scenario using soft assertions. This will run once and will not stop execution if any of the assertions fail
    Scenario: Verify the number of items in each carousel using soft assertions
      When I scroll down to the carousel
      Then I should see 15 items in each carousel