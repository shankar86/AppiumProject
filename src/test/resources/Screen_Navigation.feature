#Author: Shankar
Feature: Zee5 App Navigation

  Scenario: Test Case: 1 Validate WelCome Screen
    Given Launch the App
    And Validate WelCome Screen
    Then Exit the App

  Scenario Outline: Test Case: 2 Login as Subscribed User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Login button
    And Validate Authentication Code
    And Launch Browser and Authenticate the Device "<UserName>" "<Password>"
    And Click On Continue button
    Then Validate Home Screen
    And Exit the App

    Examples: 
      | UserName          | Password |
      | swa10@yopmail.com |   123456 |

  Scenario: Test Case: 3 Login as Guest User By Pressing Skip button in WelCome Screen
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen
    And Exit the App

  Scenario: Test Case: 4 Login as Guest User By Pressing RCU Back button in WelCome Screen
    Given Launch the App
    And Validate WelCome Screen
    When Click RCU back button
    Then Validate Home Screen
    And Exit the App

  Scenario: Test Case: 5 Play Watch Trailer Content as Guest User
    Given Validate WelCome Screen
    When Click on Skip button
    Then Play Watch Trailer Content
    And Exit the App

  Scenario: Test Case: 6 Verify Search Screen
    Given Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen
    And Click On Search Screen
    And Verify Search Screen
    And Exit the App

  Scenario Outline: Test Case: 6 Search Shows in Search Screen
    Given Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    And Verify Searched Content "<TVShow>"
    ##Jothe Jotheyali
    And Exit the App

    Examples: 
      | TVShow        | Platform        |
      | Kumkum Bhagya | com.zee5.amazon |

  Scenario Outline: Validate Home Screen Trays for Guest User
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen
    And Validate Landing Scree Trays Titles
    And Exit the App

    Examples: 
      | Screen |
      | Home   |

  Scenario Outline: Validate Shows Screen Trays for Guest User
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen
    And Navigate to Screen "<Screen>"
    And Validate Landing Scree Trays Titles
    And Exit the App

    Examples: 
      | Screen |
      | Show   |

  Scenario Outline: Validate Movies Screen Trays for Guest User
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen
    And Navigate to Screen "<Screen>"
    And Validate Landing Scree Trays Titles
    And Exit the App

    Examples: 
      | Screen |
      | Movies |

  Scenario Outline: Validate News Screen Trays for Guest User
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen
    And Navigate to Screen "<Screen>"
    And Validate Landing Scree Trays Titles
    And Exit the App

    Examples: 
      | Screen |
      | News   |

  Scenario Outline: Validate Premium Screen Trays for Guest User
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen
    And Navigate to Screen "<Screen>"
    And Validate Landing Scree Trays Titles
    And Exit the App

    Examples: 
      | Screen  |
      | Premium |

  Scenario Outline: Validate Videos Screen Trays for Guest User
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen
    And Navigate to Screen "<Screen>"
    And Validate Landing Scree Trays Titles
    And Exit the App

    Examples: 
      | Screen |
      | Videos |
