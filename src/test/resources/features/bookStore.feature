Feature: Book Store Application

  Background:
    Given user launches the application
    When user navigates to Book Store Application
    And user logs in with valid credentials
    Then user should be redirected to books page
    
  Scenario Outline: Verify search by different book attributes  
    When user searches book by "<searchType>" with value "<searchValue>"
    Then user should see correct search result for "<searchType>" with value "<searchValue>"

    Examples:
      | searchType | searchValue          |
      | Title      | Git Pocket Guide     |
      | Author     | Richard E. Silverman |
      | Publisher  | O'Reilly Media       |
      | Title      | InvalidBookName      |
      | Title      | Speaking JavaScript  |
