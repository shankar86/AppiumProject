$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("E:/AutomationProject/src/test/resources/BeforeTV.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Author: Shankar"
    }
  ],
  "line": 2,
  "name": "Title of your feature",
  "description": "I want to use this template for my feature file",
  "id": "title-of-your-feature",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 5,
  "name": "TestCase-1 Verify Before TV Pop Up on Clicking BeforeTV Episode for Unsubscribed User",
  "description": "",
  "id": "title-of-your-feature;testcase-1-verify-before-tv-pop-up-on-clicking-beforetv-episode-for-unsubscribed-user",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@test"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Launch the App",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Validate WelCome Screen",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "Click on Login button",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "Validate Authentication Code",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Launch Browser and Authenticate the Device \"\u003cUserName\u003e\" \"\u003cPassword\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Click On Continue button",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "Validate Home Screen \"\u003cLanguage\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "Click On Search Screen",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "Verify Search Screen",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "Search \"\u003cTVShow\u003e\" Content for \"\u003cPlatform\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "Verify Searched Content \"\u003cTVShow\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "Click On Search Content",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "Click on Before TV Content",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "Verify Before TV Pop Up for \"\u003cUser\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "Exit the App",
  "keyword": "And "
});
formatter.examples({
  "line": 22,
  "name": "",
  "description": "",
  "id": "title-of-your-feature;testcase-1-verify-before-tv-pop-up-on-clicking-beforetv-episode-for-unsubscribed-user;",
  "rows": [
    {
      "cells": [
        "UserName",
        "Password",
        "TVShow",
        "Platform",
        "User",
        "Language"
      ],
      "line": 23,
      "id": "title-of-your-feature;testcase-1-verify-before-tv-pop-up-on-clicking-beforetv-episode-for-unsubscribed-user;;1"
    },
    {
      "cells": [
        "swa10@yopmail.com",
        "123456",
        "jothe jotheyali",
        "AndroidTV",
        "UnSubscribed",
        "English"
      ],
      "line": 24,
      "id": "title-of-your-feature;testcase-1-verify-before-tv-pop-up-on-clicking-beforetv-episode-for-unsubscribed-user;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 124741500,
  "status": "passed"
});
formatter.scenario({
  "line": 24,
  "name": "TestCase-1 Verify Before TV Pop Up on Clicking BeforeTV Episode for Unsubscribed User",
  "description": "",
  "id": "title-of-your-feature;testcase-1-verify-before-tv-pop-up-on-clicking-beforetv-episode-for-unsubscribed-user;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@test"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Launch the App",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Validate WelCome Screen",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "Click on Login button",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "Validate Authentication Code",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Launch Browser and Authenticate the Device \"swa10@yopmail.com\" \"123456\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Click On Continue button",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "Validate Home Screen \"English\"",
  "matchedColumns": [
    5
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "Click On Search Screen",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "Verify Search Screen",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "Search \"jothe jotheyali\" Content for \"AndroidTV\"",
  "matchedColumns": [
    2,
    3
  ],
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "Verify Searched Content \"jothe jotheyali\"",
  "matchedColumns": [
    2
  ],
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "Click On Search Content",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "Click on Before TV Content",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "Verify Before TV Pop Up for \"UnSubscribed\"",
  "matchedColumns": [
    4
  ],
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "Exit the App",
  "keyword": "And "
});
formatter.match({
  "location": "StepDefs.launch_the_app()"
});
formatter.result({
  "duration": 16907154800,
  "status": "passed"
});
formatter.match({
  "location": "StepDefs.validate_welcome_screen()"
});
formatter.result({
  "duration": 3041881900,
  "status": "passed"
});
formatter.match({
  "location": "StepDefs.click_on_login_button()"
});
formatter.result({
  "duration": 5203079200,
  "status": "passed"
});
formatter.match({
  "location": "StepDefs.validate_authenticate_code()"
});
formatter.result({
  "duration": 1061426100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "swa10@yopmail.com",
      "offset": 44
    },
    {
      "val": "123456",
      "offset": 64
    }
  ],
  "location": "StepDefs.launch_the_browser_authenticate_device(String,String)"
});
formatter.result({
  "duration": 3243532100,
  "error_message": "org.openqa.selenium.SessionNotCreatedException: session not created: This version of ChromeDriver only supports Chrome version 85\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027LAXMI\u0027, ip: \u0027192.168.1.105\u0027, os.name: \u0027Windows 11\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u002717.0.8\u0027\nDriver info: driver.version: ChromeDriver\nremote stacktrace: Backtrace:\n\tOrdinal0 [0x0116D383+3134339]\n\tOrdinal0 [0x0105A171+2007409]\n\tOrdinal0 [0x00EFAEE8+569064]\n\tOrdinal0 [0x00E8139F+70559]\n\tOrdinal0 [0x00E7CC63+52323]\n\tOrdinal0 [0x00EA0DFC+200188]\n\tOrdinal0 [0x00EA0C1D+199709]\n\tOrdinal0 [0x00E9EB0B+191243]\n\tOrdinal0 [0x00E82E77+77431]\n\tOrdinal0 [0x00E83E3E+81470]\n\tOrdinal0 [0x00E83DC9+81353]\n\tOrdinal0 [0x01070CD9+2100441]\n\tGetHandleVerifier [0x012DB75A+1396954]\n\tGetHandleVerifier [0x012DB3D9+1396057]\n\tGetHandleVerifier [0x012E7126+1444518]\n\tGetHandleVerifier [0x012DBCE8+1398376]\n\tOrdinal0 [0x01067F51+2064209]\n\tOrdinal0 [0x010722EB+2106091]\n\tOrdinal0 [0x01072411+2106385]\n\tOrdinal0 [0x010849C4+2181572]\n\tBaseThreadInitThunk [0x76627BA9+25]\n\tRtlInitializeExceptionChain [0x774CBD3B+107]\n\tRtlClearBits [0x774CBCBF+191]\n\r\n\tat java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77)\r\n\tat java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\r\n\tat java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:499)\r\n\tat java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:480)\r\n\tat org.openqa.selenium.remote.W3CHandshakeResponse.lambda$errorHandler$0(W3CHandshakeResponse.java:62)\r\n\tat org.openqa.selenium.remote.HandshakeResponse.lambda$getResponseFunction$0(HandshakeResponse.java:30)\r\n\tat org.openqa.selenium.remote.ProtocolHandshake.lambda$createSession$0(ProtocolHandshake.java:126)\r\n\tat java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)\r\n\tat java.base/java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)\r\n\tat java.base/java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)\r\n\tat java.base/java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)\r\n\tat java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)\r\n\tat java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)\r\n\tat java.base/java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)\r\n\tat java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)\r\n\tat java.base/java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)\r\n\tat org.openqa.selenium.remote.ProtocolHandshake.createSession(ProtocolHandshake.java:128)\r\n\tat org.openqa.selenium.remote.ProtocolHandshake.createSession(ProtocolHandshake.java:74)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:136)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.startSession(RemoteWebDriver.java:213)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.\u003cinit\u003e(RemoteWebDriver.java:131)\r\n\tat org.openqa.selenium.chrome.ChromeDriver.\u003cinit\u003e(ChromeDriver.java:181)\r\n\tat org.openqa.selenium.chrome.ChromeDriver.\u003cinit\u003e(ChromeDriver.java:147)\r\n\tat com.zee5.tata.cucumber.appium.stepdefs.MainPage.LaunchBrowserAndAuthenticateDevice(MainPage.java:435)\r\n\tat com.zee5.tata.cucumber.appium.stepdefs.StepDefs.launch_the_browser_authenticate_device(StepDefs.java:69)\r\n\tat ✽.And Launch Browser and Authenticate the Device \"swa10@yopmail.com\" \"123456\"(E:/AutomationProject/src/test/resources/BeforeTV.feature:10)\r\n",
  "status": "failed"
});
formatter.match({
  "location": "StepDefs.click_on_continue_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "English",
      "offset": 22
    }
  ],
  "location": "StepDefs.validate_home_screen(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "StepDefs.click_on_search_screen()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "StepDefs.verify_search_screen()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "jothe jotheyali",
      "offset": 8
    },
    {
      "val": "AndroidTV",
      "offset": 38
    }
  ],
  "location": "StepDefs.search_content(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "jothe jotheyali",
      "offset": 25
    }
  ],
  "location": "StepDefs.verify_searched_Content(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "StepDefs.click_on_search_content()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "StepDefs.click_on_beforetv_content()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "UnSubscribed",
      "offset": 29
    }
  ],
  "location": "StepDefs.verify_before_tv_pop_up(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "StepDefs.exit_the_App()"
});
formatter.result({
  "status": "skipped"
});
});