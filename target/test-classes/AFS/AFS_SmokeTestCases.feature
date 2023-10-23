#Author: Shankar P

Feature: AFS Smoke Test Cases
@AFS
  Scenario: Test Case-1 Verify Spalsh Screen
    Given Launch the App1
    Then Verify Splash Screen
    And Exit the App

  Scenario: Test Case-2 Validate WelCome Screen
    Given Launch the App
    And Validate WelCome Screen
    Then Exit the App

  Scenario Outline: Test Case-3 Validate Home Screen as Guest User By Clicking Skip Button and get App Version
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Navigate to Settings Screen "<User>"
    And Get the App Version
    And Exit the App

    Examples: 
      | Language | User  |
      | English  | Guest |

  Scenario Outline: Test Case-4 Validate Home Screen as Guest User By Pressing RCU Back Button
    Given Launch the App
    And Validate WelCome Screen
    When Click RCU back button
    Then Validate Home Screen "<Language>"
    And Exit the App

    Examples: 
      | Language |
      | English  |

  Scenario Outline: Test Case-5 Validate Search Screen
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Exit the App

    Examples: 
      | Language |
      | English  |

  Scenario Outline: Test Case-6 Verify Search Shows in Search Screen as Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    And Verify Searched Content "<TVShow>"
    And Exit the App

    Examples: 
      | TVShow        | Platform | Language |
      | kumkum bhagya | Amazon   | English  |

  Scenario Outline: Test Case-7 Validate Home Screen Collection Trays for Guest User
    Given Launch the App
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Validate Landing Screen Trays Titles
    And Exit the App

    Examples: 
      | Screen | Language |
      | Home   | English  |

  Scenario Outline: Test Case-8 Validate Shows Screen Collection Trays for Guest User
    Given Launch the App
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Navigate to Screen "<Screen>"
    And Validate Landing Screen Trays Titles
    And Exit the App

    Examples: 
      | Screen | Language |
      | Shows  | English  |

  Scenario Outline: Test Case-9 Validate Movies Screen Collection Trays for Guest User
    Given Launch the App
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Navigate to Screen "<Screen>"
    And Validate Landing Screen Trays Titles
    And Exit the App

    Examples: 
      | Screen | Language |
      | Movies | English  |

  Scenario Outline: Test Case-10 Validate News Screen Collection Trays for Guest User
    Given Launch the App
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Navigate to Screen "<Screen>"
    And Validate Landing Screen Trays Titles
    And Exit the App

    Examples: 
      | Screen | Language |
      | News   | English  |

  Scenario Outline: Test Case-11 Validate Premium Screen Collection Trays for Guest User
    Given Launch the App
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Navigate to Screen "<Screen>"
    And Validate Landing Screen Trays Titles
    And Exit the App

    Examples: 
      | Screen  | Language |
      | Premium | English  |

  Scenario Outline: Test Case-12 Validate Videos Screen Collection Trays for Guest User
    Given Launch the App
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Navigate to Screen "<Screen>"
    And Validate Landing Screen Trays Titles
    And Exit the App

    Examples: 
      | Screen | Language |
      | Videos | English  |

  Scenario Outline: Test Case-13 Verify My Plan Screen for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Navigate to Settings Screen "<User>"
    And Click on My Plans Tab
    And Verify All Plans Screen
    And Exit the App

    Examples: 
      | Language | User  |
      | English  | Guest |

  Scenario Outline: TestCase-14 Verify Before TV Pop Up on Clicking BeforeTV Episode for Guest User
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
      | TVShow          | Platform | User  | Language |
      | jothe jotheyali | Amazon   | Guest | English  |

  Scenario Outline: TestCase-15 Verify Before TV Pop Up on Clicking BeforeTV Episode for Unsubscribed User
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
      | UserName          | Password | TVShow          | Platform | User         | Language |
      | swa10@yopmail.com |   123456 | jothe jotheyali | Amazon   | UnSubscribed | English  |

  Scenario Outline: Test Case-16 Login as Subscribed User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Login button
    And Validate Authentication Code
    And Launch Browser and Authenticate the Device "<UserName>" "<Password>"
    And Click On Continue button
    Then Validate Home Screen "<Language>"
    And Exit the App

    Examples: 
      | UserName        | Password | Language |
      | p10@yopmail.com |   123456 | English  |

  Scenario Outline: Test Case-17 Verify Login Pop Up on Click of Premium Content for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    And Verify Searched Content "<TVShow>"
    And Navigate to Searched Content "<TVShow>"
    And Click On Search Content
    And Click on Play button
    And Verify Login Pop Up
    And Exit the App

    Examples: 
      | TVShow | Platform | Language |
      | bhinna | Amazon   | English  |

  Scenario Outline: Test Case-18 Verify Authentication Screen On Click of Login Pop Up of Premium Content for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    And Verify Searched Content "<TVShow>"
    And Click On Search Content
    And Click on Play button
    And Verify Login Pop Up
    And Click on Login Pop Up button
    And Verify Authentication Screen
    And Validate Authentication Code
    And Exit the App

    Examples: 
      | TVShow | Platform | Language |
      | bhinna | Amazon   | English  |

  Scenario Outline: TestCase-19 Verify Before TV Pop Up on Auto Play for Guest User
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
    And Click On Auto Play Progress Loader
    And Verify Before TV Pop Up for "<User>"
    And Exit the App

    Examples: 
      | TVShow    | Platform | User  | Language |
      | gattimela | Amazon   | Guest | English  |

  Scenario Outline: TestCase-20 Verify Player Controls,Metadata,Seekbar,Settings and Reco button in Player for Guest User
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
    And Verify Content Metadata In Player
    And Verify Player Controls in Player
    And Verify Seekbar In Player
    And Verify Settings button in Player
    And Verify Reco button in Player
    And Exit the App

    Examples: 
      | TVShow     | Platform | User  | Language |
      | jodi hakki | Amazon   | Guest | English  |

  Scenario Outline: Test Case-21 Login as Subscribed User and Logout
    Given Launch the App
    And Validate WelCome Screen
    When Click on Login button
    And Validate Authentication Code
    And Launch Browser and Authenticate the Device "<UserName>" "<Password>"
    And Click On Continue button
    Then Validate Home Screen "<Language>"
    And Single Click On Search Screen
    And Navigate to Settings
    And Click On Logout button
    Then Validate Home Screen "<Language>"
    And Exit the App

    Examples: 
      | UserName        | Password | Language |
      | p10@yopmail.com |   123456 | English  |

  Scenario Outline: TestCase-22 Verify Add Watchlist for Unsubscribed User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Login button
    And Validate Authentication Code
    And Launch Browser and Authenticate the Device "<UserName>" "<Password>"
    And Click On Continue button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<Movie>" Content for "<Platform>"
    And Verify Searched Content "<Movie>"
    And Navigate to Searched Content "<Movie>"
    And Click On Search Content
    And Click on Add Watchlist button
    And Verify Add Watchlist button
    And Exit the App

    Examples: 
      | UserName          | Password | Movie  | Platform | User         | Language |
      | swa10@yopmail.com |   123456 | bhinna | Amazon   | UnSubscribed | English  |

  Scenario: TestCase-23 Verify Live TV Channel Playback for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Single Click On Search Screen
    And Navigate to All Channels Screen
    And Play Live TV Service
    And Verify Played Live TV Service
    And Exit the App

  Scenario Outline: TestCase-24 Verify Auto Play Screen and Auto Play Content for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    And Verify Searched Content "<TVShow>"
    And Click On Search Content
    And Play the Previous Episode
    And Verify Content Title In Player
    And Scrub the Content Till End
    And Validate Auto Play Screen
    And Click On Auto Play Progress Loader
    And Verify Auto Played Content Title In Player
    And Exit the App

    Examples: 
      | TVShow               | Platform | User  | Language |
      | subbalakshmi samsara | Amazon   | Guest | English  |

  Scenario Outline: TestCase-25 Verify Cancel in Auto Play Screen for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    And Verify Searched Content "<TVShow>"
    And Click On Search Content
    And Play the Previous Episode
    And Verify Content Title In Player
    And Scrub the Content Till End
    And Validate Auto Play Screen
    And Click On Cancel button in Auto Play Screen
    And Verify Player Screen
    And Exit the App

    Examples: 
      | TVShow               | Platform | User  | Language |
      | subbalakshmi samsara | Amazon   | Guest | English  |

  @Ignore
  Scenario Outline: Test Case-26 Verify Thumbnail Image in Landing Screen
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Verify Thumbnail Image
    And Exit the App

    Examples: 
      | Language |
      | English  |

  Scenario Outline: TestCase-27 Verify Show Call out Pop up on Back Press during content Playback and Verify Content language gets added
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    #And Run Search API for "<TVShow>"
    And Verify Searched Content "<TVShow>"
    And Navigate to Searched Content "<TVShow>"
    And Click On Search Content
    And Play the Previous Episode of Before TV Episode
    And Verify Content Title In Player
    And Click RCU back button
    And Verify Show Call Out Pop Up
    And Click on Yes Now button
    And Navigate and Click on Language Settings Screen
    And Verify Content Language Screen
    And Exit the App

    Examples: 
      | TVShow         | Platform | Language |
      | pavitra rishta | Amazon   | English  |

  Scenario Outline: Test Case-28 Verify Home Screen after changing Display language as Tamil for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language1>"
    And Navigate to Settings Screen "<User>"
    And Navigate and Click on Language Settings Screen
    And Verify Display Language Screen
    And Select "<Language2>" Display Language
    And Validate Home Screen "<Language2>"
    And Exit the App

    Examples: 
      | Language1 | Language2 | User  |
      | English   | Tamil     | Guest |

  Scenario Outline: Test Case-29 Verify Home Screen after changing Display language as Hindi for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language1>"
    And Navigate to Settings Screen "<User>"
    And Navigate and Click on Language Settings Screen
    And Verify Display Language Screen
    And Select "<Language2>" Display Language
    And Validate Home Screen "<Language2>"
    And Exit the App

    Examples: 
      | Language1 | Language2 | User  |
      | English   | Hindi     | Guest |

  Scenario Outline: Test Case-30 Verify Active Plans for Subscribed Users
    Given Launch the App
    And Validate WelCome Screen
    When Click on Login button
    And Validate Authentication Code
    And Launch Browser and Authenticate the Device "<UserName>" "<Password>"
    And Click On Continue button
    Then Validate Home Screen "<Language>"
    And Navigate to Settings Screen "<User>"
    And Click on My Plans Tab
    And Verify Active Plans
    And Exit the App

    Examples: 
      | UserName        | Password | Language | User       |
      | p10@yopmail.com |   123456 | English  | Subscribed |

  Scenario Outline: Test Case-31 Verify Renew Plans for Unsubscribed Users
    Given Launch the App
    And Validate WelCome Screen
    When Click on Login button
    And Validate Authentication Code
    And Launch Browser and Authenticate the Device "<UserName>" "<Password>"
    And Click On Continue button
    Then Validate Home Screen "<Language>"
    And Navigate to Settings Screen "<User>"
    And Click on My Plans Tab
    And Verify Renew Plan and button
    And Exit the App

    Examples: 
      | UserName            | Password | Language | User         |
      | bala111@yopmail.com |   123456 | English  | Unsubscribed |

  Scenario Outline: Test Case-32 Verify Expired Plans for UnSubscribed Users
    Given Launch the App
    And Validate WelCome Screen
    When Click on Login button
    And Validate Authentication Code
    And Launch Browser and Authenticate the Device "<UserName>" "<Password>"
    And Click On Continue button
    Then Validate Home Screen "<Language>"
    And Navigate to Settings Screen "<User>"
    And Click on My Plans Tab
    And Verify Expired Plans
    And Exit the App

    Examples: 
      | UserName            | Password | Language | User         |
      | bala111@yopmail.com |   123456 | English  | Unsubscribed |

  Scenario Outline: Test Case-33 Verify Contact Us Screen and Submitt for Subscribed Users
    Given Launch the App
    And Validate WelCome Screen
    When Click on Login button
    And Validate Authentication Code
    And Launch Browser and Authenticate the Device "<UserName>" "<Password>"
    And Click On Continue button
    Then Validate Home Screen "<Language>"
    And Navigate to Settings Screen "<User>"
    And Click on Contact Us
    And Verify Contact Us Screen
    And Type Some Description and Submit
    And Verify Success Pop Up
    And Exit the App

    Examples: 
      | UserName        | Password | Language | User       |
      | p10@yopmail.com |   123456 | English  | Subscribed |

  Scenario Outline: Test Case-34 Verify FAQ's, About Us, Terms of Use, Privacy Policy Screen for Subscribed Users
    Given Launch the App
    And Validate WelCome Screen
    When Click on Login button
    And Validate Authentication Code
    And Launch Browser and Authenticate the Device "<UserName>" "<Password>"
    And Click On Continue button
    Then Validate Home Screen "<Language>"
    And Navigate to Settings Screen "<User>"
    And Verify FAQ's Screen
    And Verify About Us Screen
    And Verify Terms of Use Screen
    And Verify Privacy Policy Screen
    And Exit the App

    Examples: 
      | UserName        | Password | Language | User       |
      | p10@yopmail.com |   123456 | English  | Subscribed |

  Scenario Outline: Test Case-35 Validate Get Premium button in Carousel Banner for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Verify Get Premium button in Carousel
    And Verify Plan Screen on Click of Get Premium button
    And Exit the App

    Examples: 
      | Language |
      | English  |

  Scenario Outline: Test Case-36 Exit the App By Pressing Back button
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Verify Get Premium button in Carousel
    And Verify Plan Screen on Click of Get Premium button
    And Exit the App By Pressing Back button

    Examples: 
      | Language |
      | English  |

  Scenario Outline: TestCase-37 Verify Seasons Displaying for Premium Shows
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Click On Search Screen
    And Verify Search Screen
    And Search "<TVShow>" Content for "<Platform>"
    #And Run Search API for "<TVShow>"
    And Verify Searched Content "<TVShow>"
    And Navigate to Searched Content "<TVShow>"
    And Click On Search Content
    And Click on Seasons and Episode button
    And Verify Seasons and Episode Screen
    And Exit the App

    Examples: 
      | TVShow        | Platform | Language |
      | american idol | Amazon   | English  |

  Scenario Outline: Test Case-38 Verify Search Page Translation after changing Display language as Kannada for Guest User
    Given Launch the App
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language1>"
    And Navigate to Settings Screen "<User>"
    And Navigate and Click on Language Settings Screen
    And Verify Display Language Screen
    And Select "<Language2>" Display Language
    And Validate Home Screen "<Language2>"
    And Click On Search Screen
    And Verify Search Screen for "<Language2>" Display Language
    And Exit the App

    Examples: 
      | Language1 | Language2 | User  |
      | English   | Kannada   | Guest |

  Scenario Outline: Test Case-39 Validate All Channels Screen for Guest User
    Given Launch the App
    Given Run Collection API "<Screen>"
    And Validate WelCome Screen
    When Click on Skip button
    Then Validate Home Screen "<Language>"
    And Navigate to Screen "<Screen>"
    #And Verify All Channels Scree`	n
    And Verify Thumbnail Image
    And Exit the App

    Examples: 
      | Screen       | Language |
      | All Channels | English  |
