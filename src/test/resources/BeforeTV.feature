#Author: Shankar
Feature: Title of your feature
  I want to use this template for my feature file
@test
  Scenario Outline: TestCase-1 Verify Before TV Pop Up on Clicking BeforeTV Episode for Unsubscribed User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Login button
    And Validate Authentication Code
    And Launch Browser and Authenticate the Device "<UserName>" "<Password>"
    And Click On Continue button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    And Verify Searched Content "<TVShow>"
    And Click On Search Content
    And Click on Before TV Content
    And Verify Before TV Pop Up for "<User>"
    And Exit the App

    Examples: 
      | UserName          | Password | TVShow          | Platform  | User         | Language |
      | swa10@yopmail.com |   123456 | jothe jotheyali | AndroidTV | UnSubscribed | English  |
     
  Scenario Outline: TestCase-2 Verify Before TV Pop Up on Clicking BeforeTV Episode for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    And Verify Searched Content "<TVShow>"
    And Click On Search Content
    And Click on Before TV Content
    And Verify Before TV Pop Up for "<User>"
    And Exit the App

    Examples: 
      | TVShow          | Platform  | User  | Language |
      | jothe jotheyali | AndroidTV | Guest | English  |

  Scenario Outline: TestCase-3 Verify Before TV Pop Up on Auto Play for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    And Verify Searched Content "<TVShow>"
    And Click On Search Content
    And Play the Previous Episode of Before TV Episode
    And Verify Content Title In Player
    And Scrub the Content Till End
    #And Verify Before TV Pop Up for "<User>"
    And Exit the App

    Examples: 
      | TVShow   	 | Platform  | User  | Language |
      | jodi hakki | AndroidTV | Guest | English  |
