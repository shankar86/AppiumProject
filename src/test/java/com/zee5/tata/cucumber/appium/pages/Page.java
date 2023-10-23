package com.zee5.tata.cucumber.appium.pages;

import com.jayway.restassured.response.Response;
import com.zee5.tata.cucumber.appium.action.AndroidAction;
import com.zee5.tata.cucumber.appium.action.AppiumAction;
import com.zee5.tata.cucumber.appium.action.SeleniumAction;
import com.zee5.tata.cucumber.appium.stepdefs.TestContext;

import io.appium.java_client.android.nativekey.KeyEvent;

public class Page {
	public static Response response=null;
	public static Response authObjectresp = null;
	
	public static TestContext getContext() {
        return TestContext.getContext();
    }
	
	public static SeleniumAction getAction() {
        return getContext().getAction();
    }
    
    public static AppiumAction getAppiumAction() {
        return getContext().getAppiumAction();
    }
    
    public static AndroidAction getAndroidAction() {
        return getContext().getAndroidAction();
    }
}
