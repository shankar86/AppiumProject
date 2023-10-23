package com.zee5.tata.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class FrameworkProperties {

	public static Properties prop;
	public static Properties prop1;

	public static final String PLATFORM_NAME = getProperty("platformName", "");
	public static final String SPLASHACTIVITY = getProperty("splashActivity", "");
	public static final String ROW = getProperty("Row", "");

	public static String getProperty(String key, String defaultValue) {

		if (null == prop) {
			prop = new Properties();

			InputStream is = null;

			try {

				System.out.println("******Loading Properties file********");
				// TODO: We don't have framework.properties
				is = (FrameworkProperties.class.getClassLoader().getResource("framework.properties") != null)
						? FrameworkProperties.class.getClassLoader().getResource("framework.properties").openStream()
						: null;
				// By default there is no framework.properties , should handled by null check
				if (is == null)
					return null;
				prop.load(is);
			} catch (IOException e) {
				e.printStackTrace();

			} finally {
				try {
					if (is != null)
						is.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			Enumeration<Object> keys = prop.keys();

			System.out.println("****** Configured Properties ********");
			while (keys.hasMoreElements()) {

				String element = (String) keys.nextElement();
				System.out.println(String.format("\t\tProperty: %s, value: '%s'",
						new Object[] { element, prop.getProperty(element) }));
			}

		}
		return prop.getProperty(key, defaultValue);
	}
	
	public static void getProperty1() throws IOException {
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/locators.properties");
		prop1.load(objfile);
	}
}
