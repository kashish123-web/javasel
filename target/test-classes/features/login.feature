Feature: User Login Functionality

  Scenario: Successful login to DemoQA application
    Given user launches the application
    When user navigates to Book Store Application
    And user logs in with valid credentials
    Then user should be redirected to books page
