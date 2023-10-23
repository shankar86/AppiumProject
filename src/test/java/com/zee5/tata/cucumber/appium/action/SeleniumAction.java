package com.zee5.tata.cucumber.appium.action;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumAction {
	public WebDriver webDriver;

	public SeleniumAction(WebDriver driver) {

		this.webDriver = driver;
	}

	public void click(By locatorValue) throws InterruptedException {
		try {

			WebElement ele = webDriver.findElement(locatorValue);
			highLightElement(webDriver, ele);
			webDriver.findElement(locatorValue).click();
			Thread.sleep(1000);

		} catch (NoSuchElementException e) {

			Assert.fail("Web Element is not visible -->" + locatorValue);
			e.printStackTrace();
		}

	}

	public void type(By locatorValue, String value) throws InterruptedException {
		try {

			WebElement ele = webDriver.findElement(locatorValue);
			highLightElement(webDriver, ele);
			webDriver.findElement(locatorValue).sendKeys(value);
			Thread.sleep(5000);

		} catch (NoSuchElementException e) {
			Assert.fail("Web Element is not visible -->" + locatorValue);
			e.printStackTrace();
		}

	}

	public void highLightElement(WebDriver driver, WebElement ele) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);

		
		  /*String folderName = ((RemoteWebDriver)
		  getContext().getWebDriver()).getSessionId().toString();
		  
		  File file = new File("target/" + folderName);
		  
		  if (!file.exists()) { if (file.mkdir()) {
		  System.out.println("Directory is created!"); } else {
		  System.out.println("Failed to create directory!"); } }
		  
		  File screenshotFolder = new File("target/" + "screenshot");
		  
		  if (!screenshotFolder.exists()) { if (screenshotFolder.mkdir()) {
		  System.out.println("Directory is created!"); } else {
		  System.out.println("Failed to create directory!"); } }
		  
		  long time = System.currentTimeMillis();
		  
		  File screenshotFile = ((TakesScreenshot)
		  getContext().getWebDriver()).getScreenshotAs(OutputType.FILE); File
		  screenshotFile1 = ((TakesScreenshot)
		  getAction().webDriver).getScreenshotAs(OutputType.FILE);
		  
		  File newName = new File("target/screenshot/" + time + ".png");
		  screenshotFile1.renameTo(newName);
		  
		  //System.out.println("Shankar" + screenshotFile.renameTo(newName));
		  
		  try {
		  
		  FileUtils.copyFileToDirectory(newName, file); } catch (IOException e) {
		  
		  e.printStackTrace(); }
		  
		  try { Thread.sleep(50); } catch (InterruptedException e) {
		  
		  System.out.println(e.getMessage()); }*/
		 
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px green');", ele);

	}

	public void selectByIndex(By by, int index) {

		try {
			WebElement ele = webDriver.findElement(by);
			highLightElement(webDriver, ele);

			Select select = new Select(ele);
			select.selectByIndex(index);
		} catch (NoSuchElementException e) {
			Assert.fail("Web Element is not visible -->" + by);
			e.printStackTrace();
		}

	}

	public void selectByValue(By by, String value) {

		try {
			WebElement ele = webDriver.findElement(by);
			highLightElement(webDriver, ele);

			Select select = new Select(ele);
			select.selectByValue(value);
		} catch (NoSuchElementException e) {

			e.printStackTrace();
		}

	}

	public void selectByVisibleText(By by, String text) {

		try {
			WebElement ele = webDriver.findElement(by);
			highLightElement(webDriver, ele);

			Select select = new Select(ele);
			select.selectByVisibleText(text);
		} catch (NoSuchElementException e) {

			Assert.fail("Web Element is not visible -->" + by);
			e.printStackTrace();
		}

	}

	public String getText(By by) {

		String text = null;

		try {
			WebElement ele = webDriver.findElement(by);
			highLightElement(webDriver, ele);

			text = webDriver.findElement(by).getText();

		} catch (NoSuchElementException e) {

			e.printStackTrace();
			Assert.fail("Text is not visible -->" + by);
		}
		return text;

	}

	public void scrollDownBy(By by) {

		WebElement ele = webDriver.findElement(by);
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		jse.executeScript("arguments[0].scrollIntoView(true);", ele);

	}

	public void scrollDown() {

		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("javascript:window.scrollBy(0, 350)", "");

	}
	
	public void scrollDownXY(int x, int y) {
		
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("javascript:window.scrollBy(" + x + "," + y + ")", "");

	}

	public void scrollUp() {

		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		jse.executeScript("window.scrollBy(0,-250)", "");

	}

	public void verifyActualAndExpectedData(String expected, String actual) {

		Assert.assertEquals(expected, actual);

	}

	public void verifyText(String expectedMessage, By by) {

		String actualMessage = webDriver.findElement(by).getText();

		System.out.println("actual message :" + actualMessage);

		if (!expectedMessage.contains(actualMessage)) {

			Assert.fail("Expected and Actual text is different :" + "Expected :" + expectedMessage + "Actual :"
					+ actualMessage);

		}
	}

	public String isElementVisibleVisible(final By by) throws InterruptedException {

		if (webDriver.findElements(by).size() > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Element is not visible " + by);
		}
		return null;

	}

	public void isElementNotVisible(final By by) throws InterruptedException {

		if (webDriver.findElements(by).size() <= 0) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Element is visible " + by);
		}

	}

	public void waitForElementToVisible(By by) {

		try {
			WebElement element = webDriver.findElement(by);
			WebDriverWait wait = new WebDriverWait(webDriver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (NoSuchElementException e) {

			e.printStackTrace();
		}

	}

	/*
	 * public void clickE(String propertyKey) {
	 * 
	 * WebElement element = findByXpath(propertyKey);
	 * 
	 * element.click();
	 * 
	 * }
	 */

	/*
	 * public WebElement findByXpath(String propertyKey) { String xpath =
	 * getXpathByPropertyKey(propertyKey); WebElement element =
	 * webDriver.findElement(By.xpath(xpath)); if (element == null) {
	 * System.out.println("Cannot find element with xpath: " + xpath); } else {
	 * System.out.println("Found element " + element + " by xpath: " + xpath); }
	 * return element; }
	 */

	/*
	 * public String getXpathByPropertyKey(String propertyKey) {
	 * 
	 * String xpath = MainPage.getProperty(propertyKey);
	 * 
	 * // String xpath = DemoPage.getContext().get(propertyKey);
	 * System.out.println("Value of xpath is===========" + xpath); if
	 * (StringUtils.isBlank(xpath)) { fail("Missing xpath property with key: " +
	 * propertyKey); } return xpath; }
	 */
}
