package com.zee5.tata.cucumber.appium.action;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidAction {
	
	public AndroidDriver<WebElement> androidDriver;
	
	public AndroidAction(AndroidDriver<WebElement> androidDriver) {

		this.androidDriver = androidDriver;
	}

}
