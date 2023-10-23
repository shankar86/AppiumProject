package com.zee5.tata.cucumber.appium.stepdefs;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.zee5.tata.cucumber.appium.action.AndroidAction;
import com.zee5.tata.cucumber.appium.action.AppiumAction;
import com.zee5.tata.cucumber.appium.action.SeleniumAction;
import com.zee5.tata.utils.FrameworkProperties;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestContext {

	private WebDriver driver;
	private AppiumDriver<WebElement> appiumDriver;
	private AndroidDriver<WebElement> androidDriver;
	public static ThreadLocal<TestContext> thread;
	public static ThreadLocal<TestContext> appiumThread;
	public static String currentDirectory = System.getProperty("user.dir");
	private SeleniumAction action = null;
	private AppiumAction appiumAction = null;
	private AndroidAction androidAction = null;
	static String d1;
	private HashMap<String, Object> data = new HashMap<String, Object>();
	String fileName = "E:\\APK\\app-androidtv-release.apk";
	BasicFileAttributes fatr;
	String fileCreationTime = null;
	static int fileCreatedTime;
	static String fileCreationOld;

	public TestContext() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		File apkFilePath = new File("E:\\APK\\app-androidtv-release.apk");
		File apkFilePath1 = new File("E:\\APK\\app-amazon-prodDebug.apk");
		int checkNewFile = JavaFileLastCreationTime(FrameworkProperties.PLATFORM_NAME);

		System.out.println("*******PlatformName Is*****" + FrameworkProperties.PLATFORM_NAME);
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			capabilities.setCapability("BROWSER_NAME", "Android");
			capabilities.setCapability("VERSION", "7.6.6.4");
			capabilities.setCapability("deviceName", "mahalaxmi's FireTVStick");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 0);
			capabilities.setCapability("unicodeKeyboard", true);
			capabilities.setCapability("resetKeyboard", true);
			//capabilities.setCapability(MobileCapabilityType.UDID, "192.168.1.52:5555");
			capabilities.setCapability("adbExecTimeout", 120000);
			capabilities.setCapability("automationName", "UiAutomator2");
			//capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			//capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
			// if (checkNewFile != 0) {
			// capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
			// capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
			// System.out.println("Uninstalled the Existing App");
			// capabilities.setCapability("app", apkFilePath1.getAbsolutePath());
			// System.out.println("Installed New App");
			// }
			capabilities.setCapability("appPackage", "com.zee5.amazon");
			capabilities.setCapability("appActivity", FrameworkProperties.SPLASHACTIVITY);
			try {
				appiumDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				androidDriver = (AndroidDriver<WebElement>) appiumDriver;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.appiumAction = new AppiumAction(this.appiumDriver);
			this.androidAction = new AndroidAction(this.androidDriver);
			appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Zee5 App Launched");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			capabilities.setCapability("BROWSER_NAME", "Android");
			capabilities.setCapability("VERSION", "9");
			capabilities.setCapability("deviceName", "emulator-5554");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("unicodeKeyboard", true);
			capabilities.setCapability("resetKeyboard", true);
			// capabilities.setCapability(MobileCapabilityType.UDID, "192.168.0.176:5555");
			// capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, "8200");
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 0);
			if (checkNewFile != 0) {
				capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
				capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
				System.out.println("Uninstalled the Existing App");
				capabilities.setCapability("app", apkFilePath.getAbsolutePath());
				System.out.println("Installed New App");
			}
			capabilities.setCapability("appPackage", "com.graymatrix.did");
			capabilities.setCapability("appActivity", FrameworkProperties.SPLASHACTIVITY);
			try {
				appiumDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				androidDriver = (AndroidDriver<WebElement>) appiumDriver;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.appiumAction = new AppiumAction(this.appiumDriver);
			this.androidAction = new AndroidAction(this.androidDriver);
			appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Zee5 App Launched");
		} else if (FrameworkProperties.ROW.equalsIgnoreCase("PMR")) {
			capabilities.setCapability("BROWSER_NAME", "Android");
			capabilities.setCapability("VERSION", "5.1.1");
			capabilities.setCapability("deviceName", "Grace's Fire TV Stick");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 0);
			capabilities.setCapability("appPackage", "com.amazon.tv.launcher");
			capabilities.setCapability("appActivity", "com.amazon.tv.launcher.ui.HomeActivity_vNext");
			try {
				appiumDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				androidDriver = (AndroidDriver<WebElement>) appiumDriver;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.appiumAction = new AppiumAction(this.appiumDriver);
			appiumDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			System.out.println("Amazon Home Screen Loaded");
		}
	}

	public WebDriver getWebDriver() {
		return this.driver;
	}

	public AppiumDriver<WebElement> getAppiumDriver() {
		return this.appiumDriver;
	}

	public AndroidDriver<WebElement> getAndroidDriver() {
		return this.androidDriver;
	}

	public static TestContext getContext() {
		return ((TestContext) thread.get());
	}

	public TestContext put(String key, Object value) {
		this.data.put(key, value);
		return this;

	}

	public Object get(String key) {
		return this.data.get(key);
	}

	public SeleniumAction getAction() {
		return this.action;
	}

	public AppiumAction getAppiumAction() {
		return this.appiumAction;
	}

	public AndroidAction getAndroidAction() {
		return this.androidAction;
	}

	public static String getLocatorValue(String Key) {
		File flowTypePropFile = new File(currentDirectory + "\\src\\main\\resources\\locators.properties");
		// System.out.println("File path: " + currentDirectory +
		// "\\src\\main\\resources\\locators.properties");
		if (flowTypePropFile.exists()) {
			Properties prop = new Properties();
			FileReader reader;
			try {
				reader = new FileReader(flowTypePropFile);
				prop.load(reader);
				reader.close();
				d1 = prop.getProperty(Key);
				// System.out.println("Locator Key Value" + d1);
				return prop.getProperty(Key);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return d1;
	}

	public int JavaFileLastCreationTime(String platformName) {
		System.out.println("Inside JavaFileLastCreationTime");
		if (platformName.contentEquals("AndroidTV")) {
			fileName = "E:\\APK\\app-androidtv-release.apk";
		} else if (platformName.contentEquals("Amazon")) {
			fileName = "E:\\APK\\app-amazon-prodDebug.apk";
		}
		File myfile = new File(fileName);
		Path path = myfile.toPath();

		try {
			fatr = Files.readAttributes(path, BasicFileAttributes.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File myObj = null;
		FileWriter myWriter = null;
		fileCreationTime = fatr.creationTime().toString();
		System.out.println("Latest Reading of FileCreationTime Is: " + fileCreationTime);
		try {
			myObj = new File("filename.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				fileCreationOld = myReader.nextLine();
				// System.out.println(fileCreationOld);
				// myReader.close();
			}
			System.out.println("Old Reading of FileCcreationTime Is: " + fileCreationOld);
			fileCreatedTime = fileCreationTime.compareTo(fileCreationOld);
			System.out.println("Compared Results is: " + fileCreatedTime);
			if (fileCreatedTime != 0) {
				myWriter = new FileWriter("filename.txt");
				myWriter.write(fileCreationTime);
				myWriter.close();
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return fileCreatedTime;
	}

	static {
		thread = new ThreadLocal<TestContext>() {
			protected TestContext initialValue() {
				return new TestContext();
			}
		};
	}
}
