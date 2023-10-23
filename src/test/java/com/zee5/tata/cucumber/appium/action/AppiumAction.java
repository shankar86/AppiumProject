package com.zee5.tata.cucumber.appium.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zee5.tata.cucumber.appium.stepdefs.BaseStep;
import com.zee5.tata.cucumber.appium.stepdefs.MainPage;
import com.zee5.tata.cucumber.appium.stepdefs.TestContext;

import io.appium.java_client.AppiumDriver;

public class AppiumAction {

	public AppiumDriver<WebElement> appiumDriver;

	public AppiumAction(AppiumDriver<WebElement> appiumDriver) {

		this.appiumDriver = appiumDriver;
	}

	public void clickByAccessibilityId(String locatorValue) throws InterruptedException {
		try {

			appiumDriver.findElementByAccessibilityId(locatorValue).click();
			Thread.sleep(2000);

		} catch (NoSuchElementException e) {

			e.printStackTrace();
		}

	}

	public void type(By locatorValue, String value) throws InterruptedException {
		try {

			WebElement ele = appiumDriver.findElement(locatorValue);
			// highLightElement(appiumDriver, ele);
			appiumDriver.findElement(locatorValue).sendKeys(value);
			Thread.sleep(5000);

		} catch (NoSuchElementException e) {

			e.printStackTrace();
		}

	}

	public void isElementIsDisplayed(By by) {

		WebElement ele = appiumDriver.findElement(by);
		// highLightElement(appiumDriver, ele);

		int count = appiumDriver.findElements(by).size();

		if (count != 0) {

			System.out.println(">>>>>>>>>>>visible");

		} else {

			System.out.println(">>>>>>>>>>>not visible");

		}

	}

	public void click(By locatorValue, String methodName) throws InterruptedException {
		try {

			WebElement ele = appiumDriver.findElement(locatorValue);
			highLightElement(methodName, ele);
			MainPage.wait.until(ExpectedConditions.visibilityOf(ele)).click();
			// appiumDriver.findElement(locatorValue).click();
			// Thread.sleep(5000);

		} catch (NoSuchElementException e) {

			Assert.fail("Web Element is not visible -->" + locatorValue);
			e.printStackTrace();
		}

	}

	public void verifyText(By by, String expectedText, String methodName) {

		WebElement ele = appiumDriver.findElement(by);
		highLightElement(methodName, ele);
		String actualText = MainPage.wait.until(ExpectedConditions.visibilityOf(ele)).getText();
		// String actualText = appiumDriver.findElement(by).getText();
		System.out.println("Actual Text Is: " + actualText);
		highLightElement(methodName, ele);
		assertEquals(expectedText, actualText);

	}

	public void verifyTextWithoutAssertion(By by, String expectedText) {

		WebElement ele = appiumDriver.findElement(by);
		String actualText = appiumDriver.findElement(by).getText();
		// highLightElement(methodName, ele);
		System.out.println("Actual Text Is: " + actualText);
		assertEquals(expectedText, actualText);
		//highLightElement(methodName, ele);

	}

	public void highLightElement(String methodName, WebElement element) {

		String folderName = BaseStep.ScenarioName;
		File file = new File("target/" + folderName);

		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}

		// long time = System.currentTimeMillis();

		File screenshotFile = appiumDriver.getScreenshotAs(OutputType.FILE);

		File newName = new File("target/" + folderName + "/" + methodName + ".png");
		try {
			FileUtils.copyFile(screenshotFile, newName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// System.out.println("Screenshot File Name Is: " + newName);

		/*
		 * try {
		 * 
		 * FileUtils.copyFileToDirectory(screenshotFile, file); } catch (IOException e)
		 * {
		 * 
		 * e.printStackTrace(); }
		 */

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}
	}

}
