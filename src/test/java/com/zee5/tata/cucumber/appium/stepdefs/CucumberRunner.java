package com.zee5.tata.cucumber.appium.stepdefs;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources", "/src/test/resources/AndroidTV", "/src/test/resources/AFS" }, tags = { "@test", "@AFS" }, glue = {
		"com.zee5.tata.cucumber.api.stepdefs" })

public class CucumberRunner {

}
