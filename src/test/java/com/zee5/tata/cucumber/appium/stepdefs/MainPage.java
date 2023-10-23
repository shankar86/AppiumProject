package com.zee5.tata.cucumber.appium.stepdefs;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.response.Response;
import com.sun.istack.Nullable;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.zee5.tata.cucumber.appium.pages.Page;
import com.zee5.tata.utils.FrameworkProperties;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.imagecomparison.FeatureDetector;
import io.appium.java_client.imagecomparison.FeaturesMatchingOptions;
import io.appium.java_client.imagecomparison.FeaturesMatchingResult;
import io.appium.java_client.imagecomparison.MatchingFunction;
import io.appium.java_client.imagecomparison.OccurrenceMatchingOptions;
import io.appium.java_client.imagecomparison.OccurrenceMatchingResult;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;

public class MainPage extends Page {
	static ClientResponse response;
	static Response response1;
	static String entity;
	public static String authenticationCode = null;
	static Logger log = LoggerFactory.getLogger(MainPage.class);
	public static HashMap<String, String> headerMap = new HashMap<String, String>();
	private static final Logger LOGGER = LoggerFactory.getLogger(MainPage.class);
	public static String nameofCurrMethod;
	public static WebDriverWait wait = new WebDriverWait(getAppiumAction().appiumDriver, 100);
	static String title[] = new String[30];
	static int count = 0;
	static int activePlansCount = 0;
	static int expiredPlansCount = 0;
	static int renewBtnCount = 0;
	static String displayLang;
	static int matchedCount = 0;
	static String guestToken = null;
	static Client client = ClientProvider.REST_CLIENT.getClient();
	private static Map<String, String> headers = new HashMap<String, String>();
	static String elapsedTime = null;
	static String appVersion = null;

	public static void LaunchTheApp() throws InterruptedException {
		getAppiumAction().appiumDriver.launchApp();
		System.out.println("App Relaunched for Next TC Testing");
		Thread.sleep(2000);
	}

	public static void LaunchTheApp1() throws InterruptedException {
		getAppiumAction().appiumDriver.launchApp();
		System.out.println("App Relaunched for Next TC Testing");
	}

	public static byte[] convertImageToByte(String Image) throws IOException {
		BufferedImage bImage = ImageIO.read(new File("Images/" + Image));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "png", bos);
		byte[] data = bos.toByteArray();
		return data;
	}

	public static byte[] convertImageToByte1(String Image) throws IOException {
		BufferedImage bImage = ImageIO.read(new File("target/" + Image));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "png", bos);
		byte[] data = bos.toByteArray();
		return data;
	}

	public static void VerifySpashScreen() throws InterruptedException, IOException {
		System.out.println("Image Comparision Started");
		byte[] partialImage = Base64.encodeBase64(convertImageToByte("Splash_Screen.png"));
		byte[] screenshot = Base64.encodeBase64(getAppiumAction().appiumDriver.getScreenshotAs(OutputType.BYTES));
		OccurrenceMatchingResult imageOccurence = getAppiumAction().appiumDriver.findImageOccurrence(screenshot,
				partialImage, new OccurrenceMatchingOptions().withEnabledVisualization());
		System.out.println("Length of Image Is: " + imageOccurence.getVisualization().length);
		org.junit.Assert.assertTrue("Partial image is not present!", imageOccurence.getVisualization().length > 0);
		assertNotNull(imageOccurence.getRect());
		System.out.println("Image Comparision Sucessful");
	}

	public static void VerifyThumbnailImage() throws IOException, InterruptedException {
		Thread.sleep(10000);
		matchedCount = 0;
		String screen = "AllChannels";
		// String imageUrl =
		// "https://akamaividz.zee5.com/resources/0-9-259/list/386x386/09259tv9kannada.png";
		System.out.println("Image Comparision Started");
		// URL url = new URL(imageUrl);
		// InputStream is = url.openStream();
		// Files.copy(is, Paths.get("target/OriginalImage.png"));
		// resize("target/OriginalImage.png", "target/ResizedImage.png", 254, 144);
		// byte[] urlImage = org.apache.commons.io.IOUtils.toByteArray(is);
		// byte[] urlImage1 = Base64.encodeBase64(urlImage);
		File image = null;
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			image = getAppiumAction().appiumDriver.findElement(By.id("com.graymatrix.did:id/nestedscroll_ID"))
					.getScreenshotAs(OutputType.FILE);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			image = getAppiumAction().appiumDriver.findElement(By.id("com.zee5.amazon:id/nestedscroll_ID"))
					.getScreenshotAs(OutputType.FILE);
		}
		File newName = new File("target/" + screen + ".png");
		try {
			FileUtils.copyFile(image, newName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		byte[] fullScreenImage = Base64.encodeBase64(convertImageToByte1("AllChannels.png"));
		/*
		 * for (int i = 1; i < 16; i++) { File channels =
		 * getAppiumAction().appiumDriver.findElement(By.xpath(
		 * "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.ScrollView/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout["
		 * + i + "]/android.widget.RelativeLayout/android.widget.ImageView"))
		 * .getScreenshotAs(OutputType.FILE); File newName1 = new
		 * File("target/channel"+i+".png"); try { FileUtils.copyFile(channels,
		 * newName1); } catch (IOException e1) { e1.printStackTrace(); } }
		 */
		File path = new File("LiveChannels");
		File[] files = path.listFiles();
		for (int i = 0; i < 15; i++) {
			String ChannelName = title[i];
			System.out.println("Channel Name from API: " + ChannelName);
			for (int j = 0; j < 15; j++) {
				String[] Str = files[j].getName().split(".png");
				// System.out.println("Stored Channel Name is: " + Str[0]);
				if (ChannelName.contentEquals(Str[0])) {
					System.out.println("Matched Image from Folder: " + files[j].getName());
					WebElement appImage = getAppiumAction().appiumDriver.findElement(By.xpath(
							"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.ScrollView/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout["
									+ (i + 1) + "]/android.widget.RelativeLayout/android.widget.ImageView"));
					byte[] appImageByte = Base64.encodeBase64(appImage.getScreenshotAs(OutputType.BYTES));
					OccurrenceMatchingResult imageOccurence = getAppiumAction().appiumDriver.findImageOccurrence(
							fullScreenImage, appImageByte, new OccurrenceMatchingOptions().withEnabledVisualization());
					matchedCount++;
				}
			}
		}
		assertTrue(matchedCount > 0);
		System.out.println("Total Number of Images Matched in All Channels Screen: " + matchedCount);
		System.out.println("Image Comparision Sucessful");

	}

	public static String getBase64FormatOfImageFromURL(String imageURL) throws IOException {
		URL url = new URL(imageURL);
		try {
			InputStream is = url.openStream();
			byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(is);
			return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
		} catch (Exception e) {
			throw new RuntimeException("Please check the network on your server! It seems disconnected.");
		}
	}

	public static void isImageAppearOnApplication(String base64FormatImage) throws IOException {
		// waitUtils.staticWait(5000);
		try {
			Assert.assertTrue("Expected Image did not appear on dashboard Screen.",
					getAppiumAction().appiumDriver.findElementByImage(base64FormatImage).isDisplayed());
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Expected image didn't display on Application!");
		}
	}

	public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
			throws IOException {
		// reads input image
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);

		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();

		// extracts extension of output file
		String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

		// writes to output file
		ImageIO.write(outputImage, formatName, new File(outputImagePath));
	}

	@SuppressWarnings("deprecation")
	public static void PlayWatchTrailerContent() throws InterruptedException {
		// TestContext.getContext().getAppiumDriver().findElement(SEARCH_ICON).click();
		// getAppiumAction().click(SEARCH_ICON);
		// appiumDriver.findElement(SEARCH_ICON).click();
		/*
		 * Actions actions = new Actions(getAppiumAction().appiumDriver); WebElement
		 * elementLocator = getAppiumAction().appiumDriver.findElement(By.
		 * xpath("//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Top10 on ZEE5 | Kannada\"]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageView"
		 * )); actions.doubleClick(elementLocator).perform();
		 */
		getAppiumAction().appiumDriver.findElement(By.xpath(
				"//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Top10 on ZEE5 | Kannada\"]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageView"))
				.click();
		System.out.println("Movie Clicked 1st Time");
		getAppiumAction().appiumDriver.findElement(By.xpath(
				"//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Top10 on ZEE5 | Kannada\"]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageView"))
				.click();
		System.out.println("Movie Clicked 2nd Time");
		Thread.sleep(10000);
		getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("TRAILER_BUTTON"))).click();
		System.out.println("Details Screen");
		Thread.sleep(10000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		System.out.println("Press Down RCU Key");
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		System.out.println("Press Down RCU Key");
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		System.out.println("Press OK RCU Key");
		// getAppiumAction().appiumDriver.findElement(By.id("com.zee5.xiaomiTV:id/player_settings")).click();
		System.out.println("Click on Settings Button");
		Thread.sleep(20000);
	}

	public static void ValidateWelcomeScreen() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.id(TestContext.getLocatorValue("AFS_WELCOME_MESSAGE"))));
			String expected = "Hi there,";
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_WELCOME_MESSAGE")), expected,
					nameofCurrMethod);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_LOGIN_BUTTON")), "LOGIN or REGISTER",
					nameofCurrMethod);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_SKIP_BUTTON")), "Skip",
					nameofCurrMethod);
			System.out.println("WelCome Screen Validated Succesfully");
			// Thread.sleep(5000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String expected = "Watch latest blockbusters, ZEE5 originals, exclusive TV shows & live TV channels \r\n"
					+ " across 18 different languages";
			//getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_WELCOME_MESSAGE")), expected,
			//		nameofCurrMethod);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_LOGIN_BUTTON")), "SIGN IN",
					nameofCurrMethod);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_SKIP_BUTTON")), "REGISTER WITH GOOGLE",
					nameofCurrMethod);
			System.out.println("WelCome Screen Validated Succesfully");
			// Thread.sleep(5000);
		}
	}

	public static void ClickOnLoginButton() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			WebElement test = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_LOGIN_BUTTON")));
			new Actions(getAppiumAction().appiumDriver).moveToElement(test).click().perform();
			System.out.println("Clicked On Login Button");
			Thread.sleep(5000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			WebElement test = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_LOGIN_BUTTON")));
			new Actions(getAppiumAction().appiumDriver).moveToElement(test).click().perform();
			System.out.println("Clicked On Login Button");
			Thread.sleep(5000);
		}
	}

	public static void ClickOnSkipButton() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_SKIP_BUTTON"))).click();
			System.out.println("Clicked on Skip Button");
			// Thread.sleep(5000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_SKIP_BUTTON")), nameofCurrMethod);
			System.out.println("Clicked on Skip Button");
		}
	}

	public static void ClickRCUBackButton() throws InterruptedException {
		TestContext.getContext().getAndroidDriver().pressKey(new KeyEvent(AndroidKey.BACK));
		System.out.println("RCU Back Button Pressed");
		Thread.sleep(5000);
	}

	public static void ValidateAuthenticationCode() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			authenticationCode = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_AUTHENTICATION_CODE"))).getText();
			System.out.println("Authentication Code is: " + authenticationCode);
			Thread.sleep(1000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			authenticationCode = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_AUTHENTICATION_CODE"))).getText();
			System.out.println("Authentication Code is: " + authenticationCode);
			Thread.sleep(1000);
		}
	}

	public static void VerifyAuthenticationScreen() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_AUTHENTICATION_SCREEN")),
					"Authenticate this device", nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_AUTHENTICATION_SCREEN")),
					"Authenticate this device", nameofCurrMethod);
		}
	}

	public static void ClickOnContinueButton() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_CONTINUE_BUTTON")))
					.click();
			System.out.println("Clicked On Continue Button in Authentication Screen");
			Thread.sleep(10000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AND_CONTINUE_BUTTON")))
					.click();
			System.out.println("Clicked On Continue Button in Authentication Screen");
			Thread.sleep(10000);
		}
	}

	public static void ValidateHomeScreen(String lang) {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			if (lang.equals("Tamil")) {
				getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_HOME_SCREEN")), "முகப்பு",
						nameofCurrMethod);
			} else if (lang.equals("Hindi")) {
				getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_HOME_SCREEN")), "होम",
						nameofCurrMethod);
			} else if (lang.equals("Kannada")) {
				getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_HOME_SCREEN")), "ಮುಖಪುಟ",
						nameofCurrMethod);
			} else {
				getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_HOME_SCREEN")), "Home",
						nameofCurrMethod);
			}
			System.out.println("Home Screen Validated Succesfully");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			if (lang.equals("Tamil")) {
				getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_HOME_SCREEN")), "முகப்பு",
						nameofCurrMethod);
			} else if (lang.equals("Hindi")) {
				getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_HOME_SCREEN")), "होम",
						nameofCurrMethod);
			} else if (lang.equals("Kannada")) {
				getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_HOME_SCREEN")), "ಮುಖಪುಟ",
						nameofCurrMethod);
			} else {
				getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_HOME_SCREEN")), "Home",
						nameofCurrMethod);
			}
			System.out.println("Home Screen Validated Succesfully");
		}
	}

	public static void LaunchBrowserAndAuthenticateDevice(String userName, String password)
			throws InterruptedException {
		// System.setProperty("webdriver.chrome.driver",
		// "E:\\AutomationProject\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		// **********************************************************************************************//
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		options.addArguments("lang=en");
		options.addArguments("no-sandbox");
		options.addArguments("disable-dev-shm-usage");
		desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
		desiredCapabilities.setPlatform(org.openqa.selenium.Platform.ANY);
		WebDriver driver = new ChromeDriver(desiredCapabilities);

		driver.manage().deleteAllCookies();
		// **********************************************************************************************//
		String URL = "https://" + userName + ":" + password + "www.zee5.com/signin";
		driver.get("https://www.zee5.com/signin");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Launched URL");
		// driver.findElement(By.xpath("//*[text()=\"Continue\"]//ancestor::div[1]")).click();
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//*[text()=\"Continue\"]//ancestor::div[1]")).click();
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//*[contains(@href,'/signin')]")).click();
		// System.out.println("Clicked on Browser Login Button");
		// Thread.sleep(2000);
		driver.findElement(By.name("userName")).sendKeys(userName);
		System.out.println("Entered UserName");
		Thread.sleep(2000);
		driver.findElement(By.name("password")).sendKeys(password);
		System.out.println("Entered Password");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[7]/div[1]/div/button")).click();
		System.out.println("Clicked on Browser Login Button");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/header/div[1]/div/div/div[3]/div/button")).click();
		System.out.println("Clicked on More Option");
		driver.findElement(
				By.xpath("//*[@id=\"root\"]/div/div/header/div[1]/div/div[1]/div[2]/div[1]/nav/div[2]/div[4]/a[2]"))
				.click();
		System.out.println("Clicked on Authenticate Device Option");
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("javascript:window.scrollBy(0, 500)", "");
		driver.findElement(By.name("code")).sendKeys(authenticationCode);
		System.out.println("Entered Authentication Code: " + authenticationCode);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[3]/div[2]/div/div[2]/div/button")).click();
		System.out.println("Clicked On Authenticate Button");
		Thread.sleep(3000);
		driver.close();
	}

	public static void ClickOnSearchScreen() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_SEARCH_ICON"))).click();
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_SEARCH_ICON"))).click();
			System.out.println("Clicked on Search Icon");
			// Thread.sleep(3000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_SEARCH_ICON")), nameofCurrMethod);
			getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_SEARCH_ICON")), nameofCurrMethod);
			System.out.println("Clicked on Search Icon");
			// Thread.sleep(3000);
		}
	}

	public static void singleClickOnSearchScreen() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_SEARCH_ICON"))).click();
			System.out.println("Clicked on Search Icon");
			// Thread.sleep(3000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_SEARCH_ICON")), nameofCurrMethod);
			System.out.println("Clicked on Search Icon");
			// Thread.sleep(3000);
		}
	}

	public static void VerifySearchScreen() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			String SearchScreenText = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_SEARCH_SCREEN_TEXT"))).getText();
			System.out.println(
					"Search Screen Text Is: " + SearchScreenText + " and Search Screen Validated Successfully");
			assertEquals(SearchScreenText, "a");
			Thread.sleep(2000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			// String SearchScreenText = getAppiumAction().appiumDriver
			// .findElement(By.id(TestContext.getLocatorValue("AND_SEARCH_SCREEN_TEXT"))).getText();
			// System.out.println(
			// "Search Screen Text Is: " + SearchScreenText + " and Search Screen Validated
			// Successfully");
			// assertEquals(SearchScreenText, "a");
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_SEARCH_SCREEN_TEXT")), "a",
					nameofCurrMethod);
			System.out.println("Search Screen Validated Succesfully");
			// Thread.sleep(2000);
		}
	}

	public static void SearchContent(String Content, String Platform) throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		char[] arr = Content.toCharArray();
		if (Platform.contentEquals("AndroidTV")) {
			Platform = "com.graymatrix.did";
		} else if (Platform.contentEquals("Amazon")) {
			Platform = "com.zee5.amazon";
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 'a') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_a")).click();
			} else if (arr[i] == 'b') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_b")).click();
			} else if (arr[i] == 'c') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_c")).click();
			} else if (arr[i] == 'd') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_d")).click();
			} else if (arr[i] == 'e') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_e")).click();
			} else if (arr[i] == 'f') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_f")).click();
			} else if (arr[i] == 'g') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_g")).click();
			} else if (arr[i] == 'h') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_h")).click();
			} else if (arr[i] == 'i') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_i")).click();
			} else if (arr[i] == 'j') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_j")).click();
			} else if (arr[i] == 'k') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_k")).click();
			} else if (arr[i] == 'l') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_l")).click();
			} else if (arr[i] == 'm') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_m")).click();
			} else if (arr[i] == 'n') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_n")).click();
			} else if (arr[i] == 'o') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_o")).click();
			} else if (arr[i] == 'p') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_p")).click();
			} else if (arr[i] == 'q') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_q")).click();
			} else if (arr[i] == 'r') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_r")).click();
			} else if (arr[i] == 's') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_s")).click();
			} else if (arr[i] == 't') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_t")).click();
			} else if (arr[i] == 'u') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_u")).click();
			} else if (arr[i] == 'v') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_v")).click();
			} else if (arr[i] == 'w') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_w")).click();
			} else if (arr[i] == 'x') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_x")).click();
			} else if (arr[i] == 'y') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_y")).click();
			} else if (arr[i] == 'z') {
				getAppiumAction().appiumDriver.findElement(By.id(Platform + ":id/button_z")).click();
			} else if (arr[i] == ' ') {
				getAppiumAction().appiumDriver.findElement(By.xpath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView"))
						.click();
			}
			// Thread.sleep(2000);
		}
	}

	public static void VerifySearchedContent(String Content) throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String searchResult = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_SEARCH_RESULT"))).getText();
			System.out.println("Search Result is: " + searchResult);
			assertEquals(searchResult.toLowerCase(), Content);
			Thread.sleep(1000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			String searchResult = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_SEARCH_RESULT"))).getText();
			System.out.println("Search Result is: " + searchResult);
			assertEquals(searchResult.toLowerCase(), Content);
			Thread.sleep(1000);
		}
	}

	public static void ClickOnSerachContent() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			// getAppiumAction().appiumDriver.findElement(By.xpath(TestContext.getLocatorValue("AND_SEARCHED_CONTENT"))).click();
			getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AND_SEARCHED_CONTENT")), nameofCurrMethod);
			System.out.println("Clicked On Searched Content");
			Thread.sleep(10000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			// getAppiumAction().appiumDriver.findElement(By.xpath(TestContext.getLocatorValue("AND_SEARCHED_CONTENT"))).click();
			getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AFS_SEARCHED_CONTENT")), nameofCurrMethod);
			System.out.println("Clicked On Searched Content");
			Thread.sleep(10000);
		}
	}

	public static void ExitApp() {
		// TestContext.getContext().getAppiumDriver().quit();
		getAppiumAction().appiumDriver.closeApp();
	}

	public static void NavigateToScreen(String screen) throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_SEARCH_ICON")), nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().click(By.id(TestContext.getLocatorValue("AFS_SEARCH_ICON")), nameofCurrMethod);
		}
		if (screen.contentEquals("Shows")) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			// getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_HOME_SCREEN")),
			// "Home",
			// nameofCurrMethod);
		} else if (screen.contentEquals("Movies")) {
			for (int i = 0; i < 3; i++) {
				getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			}
		} else if (screen.contentEquals("News")) {
			for (int i = 0; i < 4; i++) {
				getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			}
		} else if (screen.contentEquals("Premium")) {
			for (int i = 0; i < 5; i++) {
				getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			}
		} else if (screen.contentEquals("Videos")) {
			for (int i = 0; i < 6; i++) {
				getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			}
		} else if (screen.contentEquals("All Channels")) {
			for (int i = 0; i < 7; i++) {
				getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			}
		} else if (screen.contentEquals("Settings")) {
			for (int i = 0; i < 8; i++) {
				getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			}
		}
	}

	public static void NewCollectionAPI(String screen) {
		String collUrl = null;
		if (screen.contentEquals("Home")) {
			collUrl = "https://gwapi.zee5.com/content/collection/0-8-manualcol_2081341971?page=1&limit=20&item_limit=20&version=8&translation=en&languages=en,kn&country=IN";
		}
		String guestToken = getGuestToken();
		String accessToken = getAccessToken();
		headerMap.put("X-Z5-AppPlatform", "Connected Device");
		headerMap.put("X-User-Type", "guest");
		headerMap.put("X-ACCESS-TOKEN", accessToken);
		headerMap.put("X-Z5-Guest-Token", guestToken);
		headerMap.put("X-Z5-Appversion", "19.0.8.1");
		response1 = given().headers(headerMap).when().get(collUrl);
		System.out.println("Response Code Is:" + response1.getStatusCode());
		System.out.println("Response Time Is:" + response1.getTime());
	}

	public static void CollectionAPI(String screen) throws ClientHandlerException, UniformInterfaceException {
		// ClientResponse response;
		String collUrl = null;
		count=0;
		// Client client = ClientProvider.REST_CLIENT.getClient();
		if (screen.contentEquals("Home")) {
			collUrl = "https://gwapi.zee5.com/content/collection/0-8-manualcol_2081341971?page=1&limit=20&item_limit=20&version=8&translation=en&languages=en,kn&country=IN";
		} else if (screen.contentEquals("Shows")) {
			collUrl = "https://gwapi.zee5.com/content/collection/0-8-manualcol_1274139062?page=1&limit=20&item_limit=20&version=8&translation=en&languages=en,kn&country=IN";
		} else if (screen.contentEquals("Movies")) {
			collUrl = "https://gwapi.zee5.com/content/collection/0-8-manualcoll_160025695?page=1&limit=20&item_limit=20&version=8&translation=en&languages=en,kn&country=IN";
		} else if (screen.contentEquals("News")) {
			collUrl = "https://gwapi.zee5.com/content/collection/0-8-626?page=1&limit=20&item_limit=20&version=8&translation=en&languages=en,kn&country=IN";
		} else if (screen.contentEquals("Premium")) {
			collUrl = "https://gwapi.zee5.com/content/collection/0-8-manualcoll_317983822?page=1&limit=20&item_limit=20&version=8&translation=en&languages=en,kn&country=IN";
		} else if (screen.contentEquals("Videos")) {
			collUrl = "https://gwapi.zee5.com/content/collection/0-8-manualcol_1852704674?page=1&limit=20&item_limit=20&version=8&translation=en&languages=en,kn&country=IN";
		} else if (screen.contentEquals("All Channels")) {
			collUrl = "https://catalogapi.zee5.com/v1/channel?sort_by_field=channel_number&page=1&page_size=20&genres=FREE%2BChannels%2CNews%2CHindi%2BEntertainment%2CKids%2CMusic%2CElectro%2BDance%2BMusic%2CHindi%2BMovies%2CEnglish%2BEntertainment%2CHindi%2BNews%2CEnglish%2BNews%2CMarathi%2CTamil%2CTelugu%2CKannada%2CMalayalam%2CBengali%2CPunjabi%2CGujarati%2COdiya%2CEntertainment%2CMovie%2CLifestyle%2CDevotional%2CComedy%2CDrama%2CSports%2CInfotainment%2CMythology%2CEducation%2CTrap%2CCrime%2B%2526%2BMystery%2CFREE%2BNews%2BChannels%2CSunburn%2CIndie%2CFitness%2CLive%2BEvent%2CMusical%2CSpiritual&languages=en%2Ckn&country=IN&translation=en";
		}
		String guestToken = getGuestToken();
		String accessToken = getAccessToken();
		headers.put("X-Z5-AppPlatform", "Connected Device");
		headers.put("X-User-Type", "guest");
		headers.put("X-ACCESS-TOKEN", accessToken);
		headers.put("X-Z5-Guest-Token", guestToken);
		headers.put("X-Z5-Appversion", appVersion);
		loop: for (int i = 0; i < 5; i++) {
			WebResource resource = client.resource(collUrl);
			Builder requestBuilder = resource.getRequestBuilder();
			if (headers != null) {
				for (String key : headers.keySet()) {
					requestBuilder.header(key, headers.get(key));
				}
			}
			response = requestBuilder.get(ClientResponse.class);
			entity = response.getEntity(String.class);
			System.out.println("Response Is: " + entity);
			// LOGGER.debug("Response is '{}'", entity);
			// LOGGER.debug("Response Headers are '{}'", response.getHeaders());
			if (response.getStatus() == 200) {
				break loop;
			}
		}
		org.junit.Assert.assertEquals(200, response.getStatus());
		// LOGGER.debug("Response Status Code: '{}'", 200);
		if (screen.contentEquals("All Channels")) {
			getValueForPathFromEntity("items");
			count = 0;
		} else {
			getValueForPathFromEntity("buckets");
			//count = 0;
		}
	}

	public static void getValueForPathFromEntity(String path) {
		// LOGGER.debug("Getting value from path '{}'", path);
		// TODO: can be improve for better type checking by not casting value
		// into string.
		String post_id;
		JSONObject obj = new JSONObject(entity);
		JSONArray arr = obj.getJSONArray(path);
		for (int i = 0; i < arr.length(); i++) {
			post_id = arr.getJSONObject(i).getString("title");
			title[i] = post_id;
			count++;
			System.out.println("Tray Titles are: " + title[i]);
		}
		System.out.println("Total Number of Trays in Landing Screen are: " + (count - 1));
	}

	public static String getGuestToken() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			headers.put("X-Z5-AppPlatform", "Android TV");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			headers.put("X-Z5-AppPlatform", "Amazon Fire TV");
		}
		headers.put("X-Z5-Appversion", appVersion);
		String bodyJsonStr = "{\"user\": {\"apikey\": \"6BAE650FFC9A3CAA61CE54D\", \"aid\":\"45b1e21c-96c5-4542-aaa1-7ffb072a3025\"}}";
		String guestTokenUrl = "https://useraction.zee5.com/user/";
		WebResource resource = client.resource(guestTokenUrl);
		Builder requestBuilder = resource.getRequestBuilder();
		if (headers != null) {
			for (String key : headers.keySet()) {
				requestBuilder.header(key, headers.get(key));
			}
		}
		response = requestBuilder.post(ClientResponse.class, bodyJsonStr);
		entity = response.getEntity(String.class);
		System.out.println("Guest Token Response Is: " + entity);
		String[] guesttoken = entity.split("[:\"}]");
		System.out.println("Guest Token Is: " + guesttoken[4]);
		return guesttoken[4];
	}

	public static String getAccessToken() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			headers.put("X-Z5-AppPlatform", "Android TV");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			headers.put("X-Z5-AppPlatform", "Amazon Fire TV");
		}
		headers.put("X-Z5-Appversion", appVersion);
		String accessTokenUrl = "https://useraction.zee5.com/token/platform_tokens.php?platform_name=android_tv_app";
		WebResource resource = client.resource(accessTokenUrl);
		Builder requestBuilder = resource.getRequestBuilder();
		if (headers != null) {
			for (String key : headers.keySet()) {
				requestBuilder.header(key, headers.get(key));
			}
		}
		response = requestBuilder.get(ClientResponse.class);
		entity = response.getEntity(String.class);
		System.out.println("Access Token Response Is: " + entity);
		String[] accesstoken = entity.split("[:\"}]");
		System.out.println("Access Token Is: " + accesstoken[4]);
		return accesstoken[4];
	}

	@SuppressWarnings("deprecation")
	public static void LaunchPMRContent() throws InterruptedException {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		System.out.println("Pressed Down Key");
		Thread.sleep(20000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		System.out.println("Pressed Down Key");
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		System.out.println("Pressed Down Key");
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		System.out.println("Pressed Down Key");
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		System.out.println("Pressed Down Key");
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		System.out.println("Pressed Down Key");
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		System.out.println("Pressed Down Key");
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		System.out.println("Pressed Down Key");
		Thread.sleep(1000);
		// boolean ZeeTitle =
		// getAppiumAction().appiumDriver.getPageSource().contains("ZEE5 Recommends");
		// String ZeeTitle =
		// getAppiumAction().appiumDriver.findElement(By.xpath("//android.widget.LinearLayout[3]/android.widget.TextView")).getAttribute("RECOMMENDS");
		MobileElement element = (MobileElement) getAppiumAction().appiumDriver
				.findElementByClassName("android.widget.TextView");
		String elText = element.getText();
		System.out.println(elText);
	}

	@SuppressWarnings("deprecation")
	public static void NavigateToSettingsScreen(String user) throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AND_SEARCH_ICON"))).click();
			Thread.sleep(1000);
			for (int i = 0; i < 8; i++) {
				getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
			// **********************************Sprint-17************************************************//
			String Actual = getAppiumAction().appiumDriver
					.findElement(By.id("com.graymatrix.did:id/settings_title_new")).getText();
			// **********************************End******************************************************//
			/*
			 * if (user.contentEquals("Guest")) { String Actual =
			 * getAppiumAction().appiumDriver
			 * .findElement(By.id("com.graymatrix.did:id/guest_settings_title_new_text")).
			 * getText(); assertEquals(Actual, "SETTINGS"); } else { String Actual =
			 * getAppiumAction().appiumDriver
			 * .findElement(By.id("com.graymatrix.did:id/settings_title_new")).getText();
			 * assertEquals(Actual, "SETTINGS"); }
			 */
			assertEquals(Actual, "SETTINGS");
			System.out.println("Navigated to Settings Screen");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_SEARCH_ICON"))).click();
			Thread.sleep(1000);
			for (int i = 0; i < 8; i++) {
				getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
			String Actual = getAppiumAction().appiumDriver.findElement(By.id("com.zee5.amazon:id/settings_title_new"))
					.getText();
			assertEquals(Actual, "SETTINGS");
			System.out.println("Navigated to Settings Screen");
		}
	}

	public static void getTheAppVersion() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String version = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_APP_VERSION"))).getText();
			// String version = getAppiumAction().appiumDriver
			// .findElement(By.id("com.graymatrix.did:id/app_version_guest_user_version")).getText();
			String[] splitVersion = version.split(" ");
			appVersion = splitVersion[2];
			System.out.println("App Version is: " + appVersion);
			appVersion = version;
			System.out.println("App Version is: " + appVersion);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			String version = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_APP_VERSION"))).getText();
			// String version = getAppiumAction().appiumDriver
			// .findElement(By.id("com.graymatrix.did:id/app_version_guest_user_version")).getText();
			String[] splitVersion = version.split(" ");
			appVersion = splitVersion[2];
			System.out.println("App Version is: " + appVersion);
			appVersion = version;
			System.out.println("App Version is: " + appVersion);
		}
	}

	@SuppressWarnings("deprecation")
	public static void ClickOnMyPlansTab() throws InterruptedException {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		System.out.println("Clicked on My Plan Screen");
	}

	public static void VerifyAllPlansScreen() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_PLANS_SCREEN")), "ALL ACCESS PACKS",
					nameofCurrMethod);
			System.out.println("All Plans Screen Verified Successfully");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_PLANS_SCREEN")), "ALL ACCESS PACKS",
					nameofCurrMethod);
			System.out.println("All Plans Screen Verified Successfully");
		}
	}

	public static void ClickOnBeforeTvContent() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			// getAppiumAction().appiumDriver.findElement(By.xpath(TestContext.getLocatorValue("AND_BEFORETV_CONTENT"))).click();
			getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AFS_BEFORETV_CONTENT")), nameofCurrMethod);
			System.out.println("Clicked on Before TV Content");
			Thread.sleep(6000);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			// getAppiumAction().appiumDriver.findElement(By.xpath(TestContext.getLocatorValue("AND_BEFORETV_CONTENT"))).click();
			getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AFS_BEFORETV_CONTENT")), nameofCurrMethod);
			System.out.println("Clicked on Before TV Content");
			Thread.sleep(6000);
		}
	}

	@SuppressWarnings("deprecation")
	public static void verifyContentTitleInPlayer() throws InterruptedException {
		// getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		// System.out.println("Pressed RCU Down Key");
		// getAndroidAction().androidDriver.longPressKey(new
		// KeyEvent().withKey(AndroidKey.DPAD_CENTER));
		// System.out.println("Pressed Pause Button");
		// String Actual = getAppiumAction().appiumDriver
		// .findElement(By.id(TestContext.getLocatorValue("AND_CONTENT_TITLE_PLAYER"))).getText();
		// System.out.println("Content Title Is: " + Actual);
		// assertNotNull(Actual);
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.id(TestContext.getLocatorValue("AND_CONTENT_TITLE_PLAYER"))));
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
			System.out.println("Pressed RCU Down Key");
			getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.DPAD_CENTER));
			System.out.println("Pressed Pause Button");
			String Actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_CONTENT_TITLE_PLAYER"))).getText();
			System.out.println("Content Title Is: " + Actual);
			assertNotNull(Actual);
			System.out.println("Content Title Verified");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.id(TestContext.getLocatorValue("AFS_CONTENT_TITLE_PLAYER"))));
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
			System.out.println("Pressed RCU Down Key");
			getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.DPAD_CENTER));
			System.out.println("Pressed Pause Button");
			String Actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_CONTENT_TITLE_PLAYER"))).getText();
			System.out.println("Content Title Is: " + Actual);
			assertNotNull(Actual);
			System.out.println("Content Title Verified");
		}
	}

	public static void verifyPlayerScreen() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String Actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_CONTENT_TITLE_PLAYER"))).getText();
			System.out.println("Content Title Is: " + Actual);
			assertNotNull(Actual);
			System.out.println("Content Title Verified");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			String Actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_CONTENT_TITLE_PLAYER"))).getText();
			System.out.println("Content Title Is: " + Actual);
			assertNotNull(Actual);
			System.out.println("Content Title Verified");
		}
	}

	public static void verifyContentMetadataInPlayer() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String metaData = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_CONTENT_GENER_PLAYER"))).getText();
			System.out.println("Content Metadata Is: " + metaData);
			assertNotNull(metaData);
			System.out.println("Metada Verified Sucessfully");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			String metaData = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_CONTENT_GENER_PLAYER"))).getText();
			System.out.println("Content Metadata Is: " + metaData);
			assertNotNull(metaData);
			System.out.println("Metada Verified Sucessfully");
		}
	}

	public static void verifyPlayerControlsInPlayer() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			boolean RWButton = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_REWIND_10S"))).isDisplayed();
			System.out.println("-10S is Displayed: " + RWButton);
			assertTrue(RWButton);
			boolean FFButton = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_FORWARD_10S"))).isDisplayed();
			System.out.println("+10S is Displayed: " + FFButton);
			assertTrue(FFButton);
			boolean PlayPauseButton = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_PLAY_PAUSE"))).isDisplayed();
			System.out.println("+10S is Displayed: " + PlayPauseButton);
			assertTrue(PlayPauseButton);
			System.out.println("Player Controls are Validated Sucessfully");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			boolean RWButton = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_PLAYER_REWIND_10S"))).isDisplayed();
			System.out.println("-10S is Displayed: " + RWButton);
			assertTrue(RWButton);
			boolean FFButton = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_PLAYER_FORWARD_10S"))).isDisplayed();
			System.out.println("+10S is Displayed: " + FFButton);
			assertTrue(FFButton);
			boolean PlayPauseButton = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_PLAYER_PLAY_PAUSE"))).isDisplayed();
			System.out.println("+10S is Displayed: " + PlayPauseButton);
			assertTrue(PlayPauseButton);
			System.out.println("Player Controls are Validated Sucessfully");
		}
	}

	public static void verifyseekbarInPlayer() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String timeElapTime = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_ELAPSED_TIME"))).getText();
			System.out.println("Elapsed Time Is: " + timeElapTime);
			assertNotNull(timeElapTime);
			boolean seekBar = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_SEEK_BAR"))).isDisplayed();
			System.out.println("Seekbar Is Displayed: " + seekBar);
			assertTrue(seekBar);
			String totalTime = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_TOTAL_TIME"))).getText();
			System.out.println("Total Time Is: " + totalTime);
			assertNotNull(totalTime);
			System.out.println("Player SeekBar Validated Sucessfully");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			String timeElapTime = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_PLAYER_ELAPSED_TIME"))).getText();
			System.out.println("Elapsed Time Is: " + timeElapTime);
			assertNotNull(timeElapTime);
			boolean seekBar = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_PLAYER_SEEK_BAR"))).isDisplayed();
			System.out.println("Seekbar Is Displayed: " + seekBar);
			assertTrue(seekBar);
			String totalTime = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_PLAYER_TOTAL_TIME"))).getText();
			System.out.println("Total Time Is: " + totalTime);
			assertNotNull(totalTime);
			System.out.println("Player SeekBar Validated Sucessfully");
		}
	}

	public static void verifySettingsButtonInPlayer() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			boolean settingsButton = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_SETTINGS_BUTTON"))).isDisplayed();
			System.out.println("Settings Button Is Displayed: " + settingsButton);
			assertTrue(settingsButton);
			System.out.println("Verified Settings Button in Player");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			boolean settingsButton = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_PLAYER_SETTINGS_BUTTON"))).isDisplayed();
			System.out.println("Settings Button Is Displayed: " + settingsButton);
			assertTrue(settingsButton);
			System.out.println("Verified Settings Button in Player");
		}
	}

	public static void verifyRecoButtonInPlayer() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			boolean recoButton = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_RECO_BUTTON"))).isDisplayed();
			System.out.println("Reco Button Is Displayed: " + recoButton);
			assertTrue(recoButton);
			System.out.println("Verified Reco Button in Player");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			boolean recoButton = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_PLAYER_RECO_BUTTON"))).isDisplayed();
			System.out.println("Reco Button Is Displayed: " + recoButton);
			assertTrue(recoButton);
			System.out.println("Verified Reco Button in Player");
		}
	}

	public static void VerifyBeforeTvPopup(String User) throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			if (User.equalsIgnoreCase("UnSubscribed")) {
				getAppiumAction().verifyTextWithoutAssertion(
						By.id(TestContext.getLocatorValue("AND_BEFORETV_POPUP_UNSUB")), "GET PREMIUM");
				System.out.println("Before TV Pop Up Verified Succesfully");
				Thread.sleep(2000);
			} else if (User.equalsIgnoreCase("Guest")) {
				getAppiumAction().verifyTextWithoutAssertion(
						By.id(TestContext.getLocatorValue("AND_BEFORETV_POPUP_GUEST")), "GET PREMIUM");
				System.out.println("Before TV Pop Up Verified Succesfully");
			}
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			if (User.equalsIgnoreCase("UnSubscribed")) {
				getAppiumAction().verifyTextWithoutAssertion(
						By.id(TestContext.getLocatorValue("AFS_BEFORETV_POPUP_UNSUB")), "GET PREMIUM");
				System.out.println("Before TV Pop Up Verified Succesfully");
				Thread.sleep(2000);
			} else if (User.equalsIgnoreCase("Guest")) {
				getAppiumAction().verifyTextWithoutAssertion(
						By.id(TestContext.getLocatorValue("AFS_BEFORETV_POPUP_GUEST")), "GET PREMIUM");
				System.out.println("Before TV Pop Up Verified Succesfully");
			}
		}
	}

	public static void PlayPreviousEpisodeOfBeforeTvEpisode() throws InterruptedException {
		// nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		// *****************************Sprint-16Changes**************************************
		// WebElement btns = getAppiumAction().appiumDriver
		// .findElement(By.id("com.graymatrix.did:id/details_play_button"));
		// String btn = btns.getAttribute("focused");
		// ************************************************************************************
		// if (btn.contentEquals("true")) {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		// Thread.sleep(1000);
		// } else {
		// getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		// Thread.sleep(1000);
		// getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		// }
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			List<WebElement> premiumTag = getAppiumAction().appiumDriver
					.findElements(By.id("com.graymatrix.did:id/premium_tag"));
			for (int i = 0; i < premiumTag.size(); i++) {
				if (premiumTag.get(i).isDisplayed()) {
					getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
					Thread.sleep(1000);
				} else {
					// getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				}
			}
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			List<WebElement> premiumTag = getAppiumAction().appiumDriver
					.findElements(By.id("com.zee5.amazon:id/premium_tag"));
			for (int i = 0; i < premiumTag.size(); i++) {
				if (premiumTag.get(i).isDisplayed()) {
					getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
					Thread.sleep(1000);
				} else {
					// getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
					break;
				}
			}
		}
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		// getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AND_PREVIOUS_CONTENT_1")),
		// nameofCurrMethod);
		System.out.println("Played Previous Epiosde");
	}

	public static void PlayPreviousEpisode() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AND_PREVIOUS_CONTENT")), nameofCurrMethod);
			System.out.println("Played Previous Epiosde");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AFS_PREVIOUS_CONTENT")), nameofCurrMethod);
			System.out.println("Played Previous Epiosde");
		}
	}

	public static void ClickOnPlayButton() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AND_PLAY_BUTTON")), nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AFS_PLAY_BUTTON")), nameofCurrMethod);
		}
		// ************************DidChangesforSprint-16*******************************
		/*
		 * List<WebElement> btns = getAppiumAction().appiumDriver
		 * .findElements(By.id("com.graymatrix.did:id/detail_button_icon")); String btn
		 * = btns.get(0).getAttribute("focused"); if (btn.contentEquals("true")) { //
		 * getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		 * getAppiumAction().click(By.xpath(TestContext.getLocatorValue(
		 * "AND_PLAY_BUTTON")), nameofCurrMethod); } else {
		 * getAppiumAction().click(By.xpath(TestContext.getLocatorValue(
		 * "AND_PLAY_BUTTON")), nameofCurrMethod);
		 * getAppiumAction().click(By.xpath(TestContext.getLocatorValue(
		 * "AND_PLAY_BUTTON")), nameofCurrMethod); //
		 * getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER); //
		 * Thread.sleep(1000); //
		 * getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER); }
		 */
		System.out.println("Clicked on Play Button");
	}

	public static void VerifyLoginPopUp() {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_LOGIN_POP_UP_PREMIUM")), "LOGIN",
					nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_LOGIN_POP_UP_PREMIUM")), "LOGIN",
					nameofCurrMethod);
		}
		System.out.println("Verified Login Pop Up");
	}

	public static void ClickOnLoginPopUpButton() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		List<WebElement> btns = getAppiumAction().appiumDriver.findElements(By.className("android.widget.Button"));
		String btn = btns.get(0).getAttribute("focused");
		System.out.println("Button Focus is: " + btn);
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			if (Boolean.parseBoolean(btn)) {
				getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_LOGIN_POP_UP_PREMIUM")),
						nameofCurrMethod);
			} else {
				getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_LOGIN_POP_UP_PREMIUM")),
						nameofCurrMethod);
				getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_LOGIN_POP_UP_PREMIUM")),
						nameofCurrMethod);
			}
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			if (Boolean.parseBoolean(btn)) {
				getAppiumAction().click(By.id(TestContext.getLocatorValue("AFS_LOGIN_POP_UP_PREMIUM")),
						nameofCurrMethod);
			} else {
				getAppiumAction().click(By.id(TestContext.getLocatorValue("AFS_LOGIN_POP_UP_PREMIUM")),
						nameofCurrMethod);
				getAppiumAction().click(By.id(TestContext.getLocatorValue("AFS_LOGIN_POP_UP_PREMIUM")),
						nameofCurrMethod);
			}
		}
		System.out.println("Clicked on Login Pop Up Button");

	}

	public static void navigateToSettings() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		}
		System.out.println("Navigated Settings Screen");
		Thread.sleep(2000);
	}

	public static void clickOnLogoutButton() throws InterruptedException {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		for (int i = 0; i < 13; i++) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		}
		Thread.sleep(1000);
		getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.DPAD_CENTER));
		System.out.println("Clicked on Logout Button");
	}

	public static void clickOnAddWatchlistButton() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		/// *********************Sprint-16Changes***************************************
		// WebElement btns = getAppiumAction().appiumDriver
		// .findElement(By.id("com.graymatrix.did:id/play_or_resume_layout"));
		// String btn = btns.getAttribute("focused");
		// if (btn.contentEquals("true")) {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(1000);
		// } else {
		// getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		// getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		// getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		// Thread.sleep(1000);
		// }
		getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.DPAD_CENTER));
		Thread.sleep(2000);
		// getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AND_ADD_TO_MYWATCHLIST")),
		// nameofCurrMethod);
		// getAppiumAction().click(By.xpath(TestContext.getLocatorValue("AND_ADD_TO_MYWATCHLIST")),
		// nameofCurrMethod);
		System.out.println("Clicked on Add To My Watchlist Button");
		// Thread.sleep(2000);
	}

	public static void verifyAddWatchlistButton() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().verifyText(By.xpath(TestContext.getLocatorValue("AND_ADD_TO_MYWATCHLIST_TEXT")),
					"Remove From Watchlist", nameofCurrMethod);
			getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.DPAD_CENTER));
			Thread.sleep(1000);
			System.out.println("Clicked on Remove from Watchlist Button");
			getAppiumAction().verifyText(By.xpath(TestContext.getLocatorValue("AND_ADD_TO_MYWATCHLIST_TEXT")),
					"Add to Watchlist", nameofCurrMethod);
			System.out.println("Verified Add Watchlist");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().verifyText(By.xpath(TestContext.getLocatorValue("AFS_ADD_TO_MYWATCHLIST_TEXT")),
					"Remove From Watchlist", nameofCurrMethod);
			getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.DPAD_CENTER));
			Thread.sleep(1000);
			System.out.println("Clicked on Remove from Watchlist Button");
			getAppiumAction().verifyText(By.xpath(TestContext.getLocatorValue("AFS_ADD_TO_MYWATCHLIST_TEXT")),
					"Add to Watchlist", nameofCurrMethod);
			System.out.println("Verified Add Watchlist");
		}
	}

	public static void navigateToAllChannelsScreen() throws InterruptedException {
		for (int i = 0; i < 7; i++) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		}
		System.out.println("Navigate to All Channels Screen");
		Thread.sleep(5000);
	}

	public static void playLiveTvService() throws InterruptedException {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(2000);
		getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.DPAD_CENTER));
		System.out.println("Played Live TV Service");
		Thread.sleep(10000);
	}

	public static void verifyPlayedLiveTvService() {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.DPAD_CENTER));
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String Actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_CONTENT_TITLE_PLAYER"))).getText();
			System.out.println("Live TV Content Title Is: " + Actual);
			assertNotNull(Actual);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			String Actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_CONTENT_TITLE_PLAYER"))).getText();
			System.out.println("Live TV Content Title Is: " + Actual);
			assertNotNull(Actual);
		}
		System.out.println("Live TV Service Title Verified");
	}

	public static void ScrubTheContentTillEnd() throws InterruptedException, IOException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			String totalTime = getAndroidAction().androidDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_TOTAL_TIME"))).getText();
			String elapsedTime1 = getAndroidAction().androidDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_ELAPSED_TIME"))).getText();
			System.out.println("Total Time of the Content is: " + totalTime);
			System.out.println("Elapsed Time of the Content is: " + elapsedTime1);
			// String[] hrMn = totalTime.split(":");
			// int hour = Integer.parseInt(hrMn[0]);
			// System.out.println("Hour Is: " + hour);
			// int minute = Integer.parseInt(hrMn[1]);
			// System.out.println("Minute Is: " + minute);
			// int seconds = Integer.parseInt(hrMn[2]);
			// System.out.println("Seconds Is: " + seconds);
			// int hrToSecs = (hour * 3600) + (minute * 60) + seconds;
			// System.out.println("Hours to Seconds: " + hrToSecs);
			// int avg = (hrToSecs / 10);
			// System.out.println("Total Count to Click on FF Button: " + (avg + 1));
			System.out.println("FF of Content is In Process");
			for (int i = 0; i < 2; i++) {
				if (totalTime.equals(elapsedTime)) {
					i++;
				} else {
					// Thread.sleep(1000);
					// getAndroidAction().androidDriver.longPressKey(new
					// KeyEvent(AndroidKey.DPAD_RIGHT));
					String command = "adb shell input keyevent 22";
					Runtime.getRuntime().exec(command);
					// Thread.sleep(1000);
					String elapsedTime2 = getAndroidAction().androidDriver
							.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_ELAPSED_TIME"))).getText();
					// System.out.println("Elapsed Time of the Content is: " + elapsedTime2);
					elapsedTime = elapsedTime2;
					i--;
				}
			}
			elapsedTime = null;
			System.out.println("FF of Content Reached at End");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			String totalTime = getAndroidAction().androidDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_TOTAL_TIME"))).getText();
			String elapsedTime1 = getAndroidAction().androidDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_ELAPSED_TIME"))).getText();
			System.out.println("Total Time of the Content is: " + totalTime);
			System.out.println("Elapsed Time of the Content is: " + elapsedTime1);
			System.out.println("FF of Content is In Process");
			for (int i = 0; i < 2; i++) {
				if (totalTime.equals(elapsedTime)) {
					i++;
				} else {
					String command = "adb shell input keyevent 22";
					Runtime.getRuntime().exec(command);
					String elapsedTime2 = getAndroidAction().androidDriver
							.findElement(By.id(TestContext.getLocatorValue("AND_PLAYER_ELAPSED_TIME"))).getText();
					elapsedTime = elapsedTime2;
					i--;
				}
			}
			elapsedTime = null;
			System.out.println("FF of Content Reached at End");
		}
	}

	public static void validateAutoPlayScreen() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_AUTOPLAY_UPNEXT"))).getText();
			assertEquals("UP NEXT", actual);
			String actual1 = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_AUTOPLAY_UPNEXT"))).getText();
			assertNotNull(actual1);
			boolean progressBar = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_AUTOPLAY_PROGRESS"))).isDisplayed();
			System.out.println("Auto Play Progress Loader Displayed: " + progressBar);
			String actual3 = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_AUTOPLAY_CANCEL"))).getText();
			assertEquals("CANCEL", actual3);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			String actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_AUTOPLAY_UPNEXT"))).getText();
			assertEquals("UP NEXT", actual);
			String actual1 = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_AUTOPLAY_UPNEXT"))).getText();
			assertNotNull(actual1);
			boolean progressBar = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_AUTOPLAY_PROGRESS"))).isDisplayed();
			System.out.println("Auto Play Progress Loader Displayed: " + progressBar);
			String actual3 = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_AUTOPLAY_CANCEL"))).getText();
			assertEquals("CANCEL", actual3);
		}
	}

	public static void clickOnautoPlayProgressLoader() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.id(TestContext.getLocatorValue("AND_AUTOPLAY_PROGRESS"))));
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AND_AUTOPLAY_PROGRESS")))
					.click();
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.id(TestContext.getLocatorValue("AFS_AUTOPLAY_PROGRESS"))));
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_AUTOPLAY_PROGRESS")))
					.click();
		}
		System.out.println("Clicked on Auto Play Progress Loader");
		Thread.sleep(5000);
	}

	public static void clickOnCancelButtonInAutoPlayScreen() throws InterruptedException {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AND_AUTOPLAY_CANCEL")))
					.click();
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_AUTOPLAY_CANCEL")))
					.click();
		}
		System.out.println("Clicked on Cancel Button");
		Thread.sleep(2000);
	}

	public static void ValidateLandingScreenTraysTitles() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			Thread.sleep(10000);
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			getAppiumAction().verifyText(By.xpath(TestContext.getLocatorValue("AND_1ST_TRAY_TITLE")), title[1],
					nameofCurrMethod);
			int j = 1;
			for (int i = 2; i < count; i++) {
				if (getAppiumAction().appiumDriver
						.findElement(By.xpath(TestContext.getLocatorValue("AND_2ND_TRAY_TITLE"))).getText()
						.equals(title[i])) {
					System.out.println("Actual Content Title Is: " + title[i]);
					j++;
					getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
				} else {
					getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
					i--;
				}
			}
			assertTrue(j == (count - 1));
			System.out.println("No Of Rows Validated: " + j);
			System.out.println("Screen Validated Succesfully");
			count = 0;
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			Thread.sleep(10000);
			nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			getAppiumAction().verifyText(By.xpath(TestContext.getLocatorValue("AFS_1ST_TRAY_TITLE")), title[1],
					nameofCurrMethod);
			int j = 1;
			for (int i = 2; i < count; i++) {
				if (getAppiumAction().appiumDriver
						.findElement(By.xpath(TestContext.getLocatorValue("AFS_2ND_TRAY_TITLE"))).getText()
						.equals(title[i])) {
					System.out.println("Actual Content Title Is: " + title[i]);
					j++;
					getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
				} else {
					getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
					i--;
				}
			}
			assertTrue(j == (count - 1));
			System.out.println("No Of Rows Validated: " + j);
			System.out.println("Screen Validated Succesfully");
			count = 0;
		}
	}

	public static void verifyShowCallOutPopUp() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_MORE_CONTENT_TEXT"))).getText();
			assertEquals("Would you like to watch more Hindi content?", actual);
			boolean YesIWould = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_YES_I_WOULD"))).isDisplayed();
			assertTrue(YesIWould);
			boolean MoreLang = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_CHOOSE_MORE_LANG"))).isDisplayed();
			assertTrue(MoreLang);
			boolean NotNow = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_NOT_NOW"))).isDisplayed();
			assertTrue(NotNow);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			String actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_MORE_CONTENT_TEXT"))).getText();
			assertEquals("Would you like to watch more Hindi content?", actual);
			boolean YesIWould = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_YES_I_WOULD"))).isDisplayed();
			assertTrue(YesIWould);
			boolean MoreLang = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_CHOOSE_MORE_LANG"))).isDisplayed();
			assertTrue(MoreLang);
			boolean NotNow = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_NOT_NOW"))).isDisplayed();
			assertTrue(NotNow);
		}
		System.out.println("Show Call Out Pop Up Verified Succesfully");
	}

	public static void clickOnYesNowButton() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AND_YES_I_WOULD"))).click();
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_YES_I_WOULD"))).click();
		}
		Thread.sleep(1000);
		getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
		getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
		getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
		for (int i = 0; i < 8; i++) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			Thread.sleep(1000);
		}
		Thread.sleep(1000);
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String Actual = getAppiumAction().appiumDriver
					.findElement(By.id("com.graymatrix.did:id/settings_title_new")).getText();
			assertEquals(Actual, "SETTINGS");
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			String Actual = getAppiumAction().appiumDriver.findElement(By.id("com.zee5.amazon:id/settings_title_new"))
					.getText();
			assertEquals(Actual, "SETTINGS");
		}
		System.out.println("Navigated to Settings Screen");
	}

	public static void runSearchApi(String tvShow) {
		String searchUrl = null;
		Client client = ClientProvider.REST_CLIENT.getClient();
		searchUrl = "https://gwapi.zee5.com/content/getContent/search?q=" + URLEncoder.encode(tvShow)
				+ "&page=1&start=0&limit=24&asset_type=0,6,1&country=IN&languages=en,kn&translation=en&version=3";
		WebResource resource = client.resource(searchUrl);
		Builder requestBuilder = resource.getRequestBuilder();
		response = requestBuilder.get(ClientResponse.class);
		entity = response.getEntity(String.class);
		System.out.println("Response Is: " + entity);
		org.junit.Assert.assertEquals(200, response.getStatus());
		getValueForSearchPathFromEntity("tvshows");
	}

	public static void getValueForSearchPathFromEntity(String path) {
		String post_id;
		JSONObject obj = new JSONObject(entity);
		JSONArray arr = obj.getJSONArray(path);
		for (int i = 0; i < arr.length(); i++) {
			post_id = arr.getJSONObject(i).getString("title");
			title[i] = post_id;
			count++;
			System.out.println("Search Content Titles are: " + title[i]);
		}
		System.out.println("Total Number of Trays in Landing Screen are: " + (count - 1));
	}

	public static void navigateToSearchedContent(String tvShow) throws InterruptedException {
		if (tvShow.contentEquals("pavitra rishta")) {
			getAppiumAction().appiumDriver.findElement(By.xpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.TextView"))
					.click();
		} else if (tvShow.contentEquals("bhinna")) {
			getAppiumAction().appiumDriver.findElement(By.xpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.TextView"))
					.click();
		}
		System.out.println("Clicked on Genre");
	}

	public static void navigateAndClickLangSettings() throws InterruptedException {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		for (int i = 0; i < 8; i++) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			Thread.sleep(1000);
		}
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		System.out.println("Clicked on Language Settings Screen");
	}

	public static void verifyDisplayLanguageScreen() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			String actual = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_DISPLAY_LANG_SCREEN_TXT"))).getText();
			// assert("Choose the Display language or Content language of your choice",
			// actual);
			boolean displayBtn = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_DISPLAY_LANG_BTN"))).isDisplayed();
			assertTrue(displayBtn);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			boolean displayBtn = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_DISPLAY_LANG_BTN"))).isDisplayed();
			assertTrue(displayBtn);
		}
		System.out.println("Display Language Screen Verified Successfully");
	}

	public static void verifyContentLanguageScreen() throws InterruptedException {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		List<WebElement> chkBx = getAppiumAction().appiumDriver.findElements(By.className("android.widget.CheckBox"));
		String hindi = chkBx.get(0).getAttribute("checked");
		System.out.println("Check box is: " + hindi);
		assertTrue(Boolean.parseBoolean(hindi));
	}

	public static void selectDisplayLanguage(String lang) throws InterruptedException {
		displayLang = lang;
		if (lang.equals("Tamil")) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_LEFT);
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		} else if (lang.equals("Hindi")) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_LEFT);
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		} else if (lang.equals("Kannada")) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_LEFT);
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		}
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AND_LANG_SAVE_BUTTON")))
					.click();
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AND_LANG_SAVE_BUTTON")))
					.click();
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_LANG_SAVE_BUTTON")))
					.click();
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_LANG_SAVE_BUTTON")))
					.click();
		}
		Thread.sleep(5000);
		pressBackButton(displayLang);
	}

	public static void pressBackButton(String lang) throws InterruptedException {
		if (lang.equals("Tamil") || (lang.equals("Hindi"))) {
			for (int i = 0; i < 4; i++) {
				getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.BACK));
				Thread.sleep(1000);
			}
		} else {
			for (int i = 0; i < 3; i++) {
				getAndroidAction().androidDriver.longPressKey(new KeyEvent().withKey(AndroidKey.BACK));
				Thread.sleep(1000);
			}
		}
	}

	public static void verifyActivePlans() throws ParseException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			List<WebElement> expiryDate = getAppiumAction().appiumDriver
					.findElements(By.id("com.graymatrix.did:id/date_text"));
			// System.out.println("Active Plan is: " + expiryDate.get(0).getText());
			for (int i = 0; i < expiryDate.size(); i++) {
				extractDate(expiryDate.get(i).getText());
			}
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			List<WebElement> expiryDate = getAppiumAction().appiumDriver
					.findElements(By.id("com.zee5.amazon:id/date_text"));
			// System.out.println("Active Plan is: " + expiryDate.get(0).getText());
			for (int i = 0; i < expiryDate.size(); i++) {
				extractDate(expiryDate.get(i).getText());
			}
		}
		System.out.println("Total Active Plans are: " + activePlansCount);
		assertTrue(activePlansCount > 0);
	}

	public static void verifyExpiredPlans() throws ParseException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			List<WebElement> expiryDate = getAppiumAction().appiumDriver
					.findElements(By.id("com.graymatrix.did:id/date_text"));
			for (int i = 0; i < expiryDate.size(); i++) {
				extractDate(expiryDate.get(i).getText());
			}
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			List<WebElement> expiryDate = getAppiumAction().appiumDriver
					.findElements(By.id("com.zee5.amazon:id/date_text"));
			for (int i = 0; i < expiryDate.size(); i++) {
				extractDate(expiryDate.get(i).getText());
			}
		}
		System.out.println("Total Expired Plans are: " + expiredPlansCount);
		assertTrue(expiredPlansCount > 0);
	}

	public static void extractDate(String date) throws ParseException {
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String[] input = date.split("\\r?\\n");
		// String at = "On ";
		// if (!(input[1].contains(at))) {
		// System.out.println("ERROR - unexpected input");
		// return;
		// }
		// int indexAt = input[1].indexOf(0);
		// String extracted = input[1].substring(indexAt + at.length());
		String extracted = input[1];
		String[] splitDate = extracted.split(" ");
		String month = splitDate[1].substring(0, 3);
		System.out.println("Month Is: " + month);
		String packExpiryDate = splitDate[0] + "-" + month + "-" + splitDate[2];
		System.out.println("Pack Expiry Date: " + packExpiryDate);
		Date todayDate = new Date();
		System.out.println("Today's Date is: " + todayDate);
		Date ped = formatter1.parse(packExpiryDate);
		System.out.println("Pack Expiry Date: " + ped);
		System.out.println("date1 : " + formatter1.format(ped));
		System.out.println("date2 : " + formatter2.format(todayDate));
		if (ped.compareTo(todayDate) > 0) {
			System.out.println("Pack Expiry Date is after Today's Date");
			activePlansCount++;
		} else if (ped.compareTo(todayDate) < 0) {
			System.out.println("Pack Expiry Date is before Today's Date");
			expiredPlansCount++;
		} else if (ped.compareTo(todayDate) == 0) {
			System.out.println("Pack Expiry Date is equal to Today's Date");
			activePlansCount++;
		} else {
			System.out.println("How to get here?");
		}
	}

	public static void verifyRenewPlan() throws InterruptedException {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			for (int i = 0; i < 5; i++) {
				if (getAppiumAction().appiumDriver
						.findElements(By.id(TestContext.getLocatorValue("AND_RENEW_PLAN_BTN"))).isEmpty()) {
					getAndroidAction().androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_RIGHT));
				} else {
					renewBtnCount++;
					getAndroidAction().androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_RIGHT));
				}
			}
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			for (int i = 0; i < 5; i++) {
				if (getAppiumAction().appiumDriver
						.findElements(By.id(TestContext.getLocatorValue("AFS_RENEW_PLAN_BTN"))).isEmpty()) {
					getAndroidAction().androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_RIGHT));
				} else {
					renewBtnCount++;
					getAndroidAction().androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_RIGHT));
				}
			}
		}
		System.out.println("Total Renew Plan Button Counts: " + renewBtnCount);
		assertTrue(renewBtnCount > 0);
	}

	public static void clickOnContactUs() throws InterruptedException {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		for (int i = 0; i < 7; i++) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			Thread.sleep(1000);
		}
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		System.out.println("Clicked on Contact Us Button");
	}

	public static void verifyContactusScreen() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_CONTACT_US_TXT")), "Contact Us",
					nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_CONTACT_US_TXT")), "Contact Us",
					nameofCurrMethod);
		}
		Thread.sleep(1000);
	}

	public static void typeDescriptionAndSubmit() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AND_EDIT_TEXT_MSG")))
					.sendKeys("Test");
			Thread.sleep(1000);
			WebElement btns = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AND_CONT_US_SUBT_BTN")));
			String btn = btns.getAttribute("focused");
			System.out.println("Button Focus: " + btn);
			if (btn.contentEquals("true")) {
				getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_CONT_US_SUBT_BTN")), nameofCurrMethod);
				System.out.println("Clicked Submitt Button Once");
			} else {
				getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_CONT_US_SUBT_BTN")), nameofCurrMethod);
				System.out.println("Clicked Submitt Button Once");
			}
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().appiumDriver.findElement(By.id(TestContext.getLocatorValue("AFS_EDIT_TEXT_MSG")))
					.sendKeys("Test");
			Thread.sleep(1000);
			WebElement btns = getAppiumAction().appiumDriver
					.findElement(By.id(TestContext.getLocatorValue("AFS_CONT_US_SUBT_BTN")));
			String btn = btns.getAttribute("focused");
			System.out.println("Button Focus: " + btn);
			if (btn.contentEquals("true")) {
				getAppiumAction().click(By.id(TestContext.getLocatorValue("AFS_CONT_US_SUBT_BTN")), nameofCurrMethod);
				System.out.println("Clicked Submitt Button Once");
			} else {
				getAppiumAction().click(By.id(TestContext.getLocatorValue("AFS_CONT_US_SUBT_BTN")), nameofCurrMethod);
				System.out.println("Clicked Submitt Button Once");
			}
		}
	}

	public static void verifySuccessPopUp() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_TEXT_VIEW_SUCCESS")),
					"Successfully Submitted", nameofCurrMethod);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_TEXT_VIEW_THANKS")),
					"Thank you for writing to us. We will get in touch with you soon.", nameofCurrMethod);
			getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_BTN_OK")), nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_TEXT_VIEW_SUCCESS")),
					"Successfully Submitted", nameofCurrMethod);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_TEXT_VIEW_THANKS")),
					"Thank you for writing to us. We will get in touch with you soon.", nameofCurrMethod);
			getAppiumAction().click(By.id(TestContext.getLocatorValue("AFS_BTN_OK")), nameofCurrMethod);
		}
		Thread.sleep(1000);
	}

	public static void verifyFaqScreen() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		for (int i = 0; i < 6; i++) {
			getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			Thread.sleep(1000);
		}
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		System.out.println("Clicked on FAQ's Button");
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_FAQ_TITLE")), "FAQ's",
					nameofCurrMethod);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_FAQ_SUB_TITLE")), "GENERAL",
					nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_FAQ_TITLE")), "FAQ's",
					nameofCurrMethod);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_FAQ_SUB_TITLE")), "GENERAL",
					nameofCurrMethod);
		}
		System.out.println("FAQ's Screen Validated Succesfully");
	}

	public static void verifyAboutUsScreen() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		TestContext.getContext().getAndroidDriver().pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		System.out.println("Clicked on About Us Button");
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_FAQ_TITLE")), "About Us",
					nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_FAQ_TITLE")), "About Us",
					nameofCurrMethod);
		}
		System.out.println("About Us Screen Validated Succesfully");
	}

	public static void verifyTermsOfUseScreen() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		TestContext.getContext().getAndroidDriver().pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		System.out.println("Clicked on Terms Of Use Button");
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().verifyText(By.xpath(TestContext.getLocatorValue("AND_TERMS_OF_USE_TXT")), "Terms of Use",
					nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().verifyText(By.xpath(TestContext.getLocatorValue("AFS_TERMS_OF_USE_TXT")), "Terms of Use",
					nameofCurrMethod);
		}
		System.out.println("Terms Of Use Screen Validated Succesfully");
	}

	public static void verifyPrivacyPolicyScreen() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		TestContext.getContext().getAndroidDriver().pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		System.out.println("Clicked on Privacy Policy Button");
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().verifyText(By.xpath(TestContext.getLocatorValue("AND_PRIVACY_POLICY_TXT")),
					"Privacy Policy", nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().verifyText(By.xpath(TestContext.getLocatorValue("AFS_PRIVACY_POLICY_TXT")),
					"Privacy Policy", nameofCurrMethod);
		}
		System.out.println("Privacy Policy Screen Validated Succesfully");
	}

	public static void verifyGetPremiumButtonInCarousel() throws InterruptedException {
		Thread.sleep(2000);
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.id(TestContext.getLocatorValue("AND_GET_PREMIUM_BTN"))));
			for (int i = 0; i < 7; i++) {
				boolean subBtn = getAppiumAction().appiumDriver
						.findElement(By.id(TestContext.getLocatorValue("AND_GET_PREMIUM_BTN"))).isDisplayed();
				if (subBtn) {
					assertTrue(subBtn);
					String subText = getAppiumAction().appiumDriver
							.findElement(By.id(TestContext.getLocatorValue("AND_GET_PREMIUM_BTN"))).getText();
					System.out.println("Button Text Is: " + subText);
					System.out.println("Get Premium Button Verified on Carousel");
					i = 8;
				} else {
					getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
					Thread.sleep(1000);
				}
			}
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.id(TestContext.getLocatorValue("AFS_GET_PREMIUM_BTN"))));
			for (int i = 0; i < 7; i++) {
				boolean subBtn = getAppiumAction().appiumDriver
						.findElement(By.id(TestContext.getLocatorValue("AFS_GET_PREMIUM_BTN"))).isDisplayed();
				if (subBtn) {
					assertTrue(subBtn);
					String subText = getAppiumAction().appiumDriver
							.findElement(By.id(TestContext.getLocatorValue("AFS_GET_PREMIUM_BTN"))).getText();
					System.out.println("Button Text Is: " + subText);
					System.out.println("Get Premium Button Verified on Carousel");
					i = 8;
				} else {
					getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
					Thread.sleep(1000);
				}
			}
		}
	}

	public static void verifyPlanScreenOnClickGetPremiumBtn() throws InterruptedException {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().click(By.id(TestContext.getLocatorValue("AND_GET_PREMIUM_BTN")), nameofCurrMethod);
			Thread.sleep(2000);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_PLANS_SCREEN")), "ALL ACCESS PACKS",
					nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().click(By.id(TestContext.getLocatorValue("AFS_GET_PREMIUM_BTN")), nameofCurrMethod);
			Thread.sleep(2000);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_PLANS_SCREEN")), "ALL ACCESS PACKS",
					nameofCurrMethod);
		}
		System.out.println("All Plans Screen Verified Successfully");
	}

	public static void exitTheAppByPressingBackButton() throws InterruptedException {
		TestContext.getContext().getAndroidDriver().pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(1000);
		TestContext.getContext().getAndroidDriver().pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(1000);
		TestContext.getContext().getAndroidDriver().pressKey(new KeyEvent(AndroidKey.BACK));
		System.out.println("Exited the App By Pressing Back Button on Home Screen");
	}

	public static void clickOnSeasonsAndEpisodeButton() throws InterruptedException {
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(1000);
		getAppiumAction().appiumDriver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(1000);
		getAndroidAction().androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_CENTER));
		Thread.sleep(3000);
		System.out.println("Clicked on Seasons and Episode Button");
	}

	public static void verifySeasonsAndEpisodeScreen() {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_SEASONS_TXT")), "SELECT SEASONS",
					nameofCurrMethod);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_SEASONS_TITLE")), "SEASON 17",
					nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_SEASONS_TXT")), "SELECT SEASONS",
					nameofCurrMethod);
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_SEASONS_TITLE")), "SEASON 17",
					nameofCurrMethod);
		}
		System.out.println("Seasons and Episode Screen Verified Succesfully");
	}

	public static void verifySearchScreenForDisplayLanguage(String lang) {
		nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AND_SEARCH_RESULT")),
					"ನಿಮ್ಮ ನೆಚ್ಚಿನ ಕಾರ್ಯಕ್ರಮಗಳು ಹಾಗೂ ಚಲನಚಿತ್ರಗಳನ್ನು ಇಲ್ಲಿ ಹುಡುಕಿ ", nameofCurrMethod);
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			getAppiumAction().verifyText(By.id(TestContext.getLocatorValue("AFS_SEARCH_RESULT")),
					"ನಿಮ್ಮ ನೆಚ್ಚಿನ ಕಾರ್ಯಕ್ರಮಗಳು ಹಾಗೂ ಚಲನಚಿತ್ರಗಳನ್ನು ಇಲ್ಲಿ ಹುಡುಕಿ ", nameofCurrMethod);
		}
	}

	public static void verifyAllChannelsScreen() {
		if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("AndroidTV")) {
			List<WebElement> allChannels = getAppiumAction().appiumDriver
					.findElements(By.id("com.graymatrix.did:id/channel_listing_verticalView"));
			System.out.println("Element is: " + allChannels.size());
		} else if (FrameworkProperties.PLATFORM_NAME.equalsIgnoreCase("Amazon")) {
			List<WebElement> allChannels = getAppiumAction().appiumDriver
					.findElements(By.id("com.zee5.amazon:id/channel_listing_verticalView"));
			System.out.println("Element is: " + allChannels.size());
		}
	}
}
