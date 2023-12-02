package com.amazon.Hawk.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
private static DriverFactory instance;
	
	// Private constructor to prevent instantiation from outside the class
	private DriverFactory(){
		
	}
	// Method to get the singleton instance
	public static DriverFactory getInstance() {
		if(instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}
	
	// Method to create WebDriver instances based on the browser type
	public static WebDriver getDriver(String browserType) {
		if ("chrome".equalsIgnoreCase(browserType)) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\gowthrj\\eclipse-workspace\\Hawk\\src\\test\\resources\\drivers\\chromedriver.exe");
            return new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browserType)) {
        	System.setProperty("webdriver.gecko.driver", "C:\\Users\\gowthrj\\eclipse-workspace\\Hawk\\src\\test\\resources\\drivers\\geckodriver.exe");
            return new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser type");
        }
	}
}
