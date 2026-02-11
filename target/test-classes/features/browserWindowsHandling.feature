Feature: Automate Browser Windows handling

  Background:
    Given user launches the application
    When user navigates to Book Store Application
    And user logs in with valid credentials
    Then user should be redirected to books page

  Scenario: Navigate to Alerts, Frames & Windows section
    Given user clicks on "Alerts, Frame & Windows" section
    Then user should see options:
      | Browser Windows |
      | Alerts          |
      | Frames          |
      | Nested Frames   |
      | Modal Dialogs   |

  Scenario: Handle New Tab
    Given user navigates to "Browser Windows" page
    Then user should see "Browser Windows" text displayed at center of the screen
    When user clicks on "New Tab" button
    Then a new tab should be opened
    And user switches to newly opened tab
    And user should see the text "This is a sample page"

  Scenario: Handle New Window
    Given user navigates to "Browser Windows" page
    When user clicks on "New Window" button
    Then a new window should be opened with URL "https://demoqa.com/sample"
    And user switches to newly opened window
    And user should see the text "This is a sample page"
    Then user closes the current window
    And user switches back to parent window

  Scenario: Handle New Window Message
    Given user navigates to "Browser Windows" page
    When user clicks on "New Window Message" button
    Then a new window should be opened
    And user switches to newly opened window
    And user should see the message text:
      """
      Knowledge increases by sharing but not by saving.
      Please share this website with your friends and in your organization.
      """
    Then user closes the current window
    And user switches back to parent window

