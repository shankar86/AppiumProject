package com.zee5.tata.cucumber.appium.stepdefs;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;

public class BaseStep {

	static Logger log = Logger.getLogger(BaseStep.class.getName());
	public static String ScenarioName;
	private AppiumDriver<WebElement> appiumDriver;

	@Before
	public void beforeScenario(Scenario scenario) {

		log.info("-------------NEW SCENARIO STARTED-----------------------");
		ScenarioName = scenario.getName();
		log.info("SCENARIO NAME : " + ScenarioName);
	}

}
