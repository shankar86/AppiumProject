#Author: Shankar

Feature: Verify Settings Screen Options

  Scenario: Verify My Plan Screen
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen
    And Navigate to Settings Screen
    And Click on All Plans Tab
    And Verify All Plans Screen
    And Exit the App


