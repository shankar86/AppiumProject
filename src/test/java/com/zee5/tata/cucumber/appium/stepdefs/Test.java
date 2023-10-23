package com.zee5.tata.cucumber.appium.stepdefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Currency;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class Test {
	static String fileCreationOld;
	static int fileCreatedTime;

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		/*
		 * WebDriver driver1 = new ChromeDriver();
		 * System.setProperty("webdriver.chrome.driver", "chromedriver");
		 * driver1.get("https://www.zee5.com");
		 * driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 * driver1.manage().window().maximize(); System.out.println("Launched URL");
		 * driver1.findElement(By.xpath(
		 * "//*[@id=\"root\"]/div/div/header/div[1]/div/div[5]")).click();
		 * Thread.sleep(5000);
		 * driver1.findElement(By.name("userName")).sendKeys("bala111@yopmail.com");
		 * Thread.sleep(5000);
		 * driver1.findElement(By.name("password")).sendKeys("123456");
		 * Thread.sleep(5000); driver1.findElement(By.xpath(
		 * "//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[4]/div[1]/div/button")).
		 * click(); Thread.sleep(10000); driver1.findElement(By.xpath(
		 * "//*[@id=\"root\"]/div/div/header/div[1]/div/div[2]/div[3]/div/button")).
		 * click(); Thread.sleep(5000); driver1.findElement(By.xpath(
		 * "//*[@id=\"root\"]/div/div/header/div[1]/div/div[2]/div[2]/div[1]/nav/div[2]/div[4]/div[4]"
		 * )).click(); Thread.sleep(5000); JavascriptExecutor jse = (JavascriptExecutor)
		 * driver1; jse.executeScript("javascript:window.scrollBy(0, 500)", "");
		 * Thread.sleep(5000); driver1.findElement(By.name("code")).sendKeys("123456");;
		 * Thread.sleep(5000); //driver1.findElement(By.xpath(
		 * "//*[@id=\"root\"]/div/div/div[1]/div[3]/div[2]/div/div[1]/span")).sendKeys(
		 * "123456"); //Thread.sleep(2000); driver1.findElement(By.xpath(
		 * "//*[@id=\"root\"]/div/div/div[1]/div[3]/div[2]/div/div[2]/div/button")).
		 * click(); Thread.sleep(2000);
		 */

		/*
		 * String fileName = "E:\\APK\\app-androidtv-prodDebug.apk"; String fileName1 =
		 * "E:\\APK\\app-androidtv-prodDebug1.apk";
		 * 
		 * File myfile = new File(fileName); Path path = myfile.toPath();
		 * 
		 * File myfile1 = new File(fileName1); Path path1 = myfile1.toPath();
		 * 
		 * 
		 * BasicFileAttributes fatr = Files.readAttributes(path,
		 * BasicFileAttributes.class); BasicFileAttributes fatr1 =
		 * Files.readAttributes(path1, BasicFileAttributes.class);
		 * 
		 * FileTime fileCreationTime = fatr.creationTime(); FileTime fileCreationTime1 =
		 * fatr1.creationTime();
		 * 
		 * String test = fileCreationTime.toString();
		 * 
		 * 
		 * FileWriter myWriter = new FileWriter("filename.txt"); myWriter.write(test);
		 * myWriter.close();
		 * 
		 * 
		 * try { File myObj = new File("filename.txt"); Scanner myReader = new
		 * Scanner(myObj); while (myReader.hasNextLine()) { fileCreationOld =
		 * myReader.nextLine(); System.out.println(fileCreationOld); } myReader.close();
		 * } catch (FileNotFoundException e) { System.out.println("An error occurred.");
		 * e.printStackTrace(); }
		 * 
		 * System.out.println("File creation time: " + fatr.creationTime());
		 * fileCreatedTime = test.compareTo(fileCreationOld);
		 * //fileCreationTime.compareTo(fileCreationTime1);
		 * 
		 * System.out.println("Compared Result: " +
		 * fileCreationTime.compareTo(fileCreationTime1));
		 * System.out.println("Result : " + fileCreatedTime);
		 * 
		 * long time = System.currentTimeMillis();
		 * 
		 * System.out.println("File creation time: " + fatr.creationTime());
		 * System.out.println("File creation time: " + fatr1.creationTime());
		 * System.out.println("File creation time is: " + fileCreationTime.toMillis());
		 * System.out.println("File creation time is: " + fileCreationTime1.toMillis());
		 * System.out.println("Current Time Is: " + time);
		 */
		

		Client client = ClientProvider.REST_CLIENT.getClient();

		String apixURL = "https://gwapi.zee5.com/content/collection/0-8-manualcol_2081341971?page=1&limit=20&item_limit=20&version=3&translation=en&languages=en,kn&country=IN";
		WebResource resource = client.resource(apixURL);
		Builder requestBuilder = resource.getRequestBuilder();
		ClientResponse response = requestBuilder.get(ClientResponse.class);
		String entity = response.getEntity(String.class);
		//LOGGER.debug("Response is '{}'", entity);
		//LOGGER.debug("Response Headers are '{}'", response.getHeaders());
		//org.junit.Assert.assertEquals(200, response.getStatus());
		//LOGGER.debug("Response Status Code: '{}'", 200);
		//JSONObject resObj1 = (JSONObject) new JSONParser().parse(entity);
		//String data_obj1 = (String) resObj1.get("buckets.title").toString();
		//System.out.println(data_obj1);
		//String title[] = new String[25];
		String post_id;
		String title[] = new String[25];
		JSONObject obj = new JSONObject(entity);
		JSONArray arr = obj.getJSONArray("buckets");
        for (int i = 0; i < arr.length(); i++) {
            post_id = arr.getJSONObject(i).getString("title");
            title[i] = post_id;
            //System.out.println(title[1]);
        }
        System.out.println(title[0]);
	}

}
