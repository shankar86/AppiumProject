#Author: your.email@your.domain.com

Feature: Performance Test Cases

  Scenario: Test Case-1 Validate Home Screen as Guest User By Clicking Skip Button and get App Version
    Given Launch the App
    And Validate WelCome Screen
@performance
  Scenario Outline: Test Case-2 Validate Home Screen Collection Trays for Guest User
    Given Launch the App
    Given Run New Collection API "<Screen>"
    #And Validate WelCome Screen
    #When Click on Skip button
    #Then Validate Home Screen "<Language>"
    #And Validate Landing Scree Trays Titles
    #And Exit the App

    Examples: 
      | Screen | Language |
      | Home   | English  |
