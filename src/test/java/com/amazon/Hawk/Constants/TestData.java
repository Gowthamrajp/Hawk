
package com.amazon.Hawk.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.DataProvider;

public class TestData {
	private static String loginPageURL = "https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0";
	private static String loginPageURLForIndia = "https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0";
	private static String accountPageURL = "https://www.amazon.com/gp/css/homepage.html?ref_=nav_AccountFlyout_ya";
	private static String searchPageURL = "https://www.amazon.com/";
	private static String ordersPageURL = "https://www.amazon.in/gp/css/order-history?ref_=nav_orders_first";
	private static String searchProductDataCSVPath = "C:\\Users\\gowthrj\\eclipse-workspace\\Hawk\\src\\test\\resources\\CSV\\SearchProductData.csv";
	private static String buyProductDataCSVPath = "C:\\Users\\gowthrj\\eclipse-workspace\\Hawk\\src\\test\\resources\\CSV\\BuyProductData.csv";
	@DataProvider
	public static Object[][] getValidCredentials() {
		return new Object[][] { { "gowthrj+gamma-sim@amazon.com", "qwerty", "Gowtham Vanilla", "chrome" },
				{ "gowthrj+gamma-sim@amazon.com", "qwerty", "Gowtham Vanilla", "firefox" } };
	}
	@DataProvider
	public static Object[][] getValidCredentialsWithDomain(String domain) {
		if (domain.equals(".in")) {
			return new Object[][] { { "gowthamrajp1996@gmail.com", "qwerty", "Tester" } };
		}
		return new Object[][] { { "gowthrj+gamma-sim@amazon.com", "qwerty", "Gowtham Vanilla", "chrome" },
				{ "gowthrj+gamma-sim@amazon.com", "qwerty", "Gowtham Vanilla", "firefox" } };
	}
	@DataProvider
	public static Object[][] getInvalidPasswordCredentials() {
		return new Object[][] { { "gowthrj+gamma-sim@amazon.com", "owerty", "Gowtham Vanilla", "chrome" },
				{ "gowthrj+gamma-sim@amazon.com", "owerty", "Gowtham Vanilla", "firefox" } };
	}
	@DataProvider
	public static Object[][] getInvalidEmailCredentials() {
		return new Object[][] { { "gowthrj+gamma-sim@amazon.co", "owerty", "Gowtham Vanilla", "chrome" },
				{ "gowthrj+gamma-sim@amazon.co", "owerty", "Gowtham Vanilla", "firefox" } };
	}
	@DataProvider
	public static Object[][] getSearchProductDetails() {
		return readDataFromCSV(searchProductDataCSVPath);
	}
	@DataProvider
	public static Object[][] getBuyProductDetails() {
		return new Object[][] { { "SG Bouncer Leather Ball (Red) , Standard Size 1Pc", "chrome" },
				{ "SG Bouncer Leather Ball (Red) , Standard Size 1Pc", "firefox" } };
		// return readDataFromCSV(buyProductDataCSVPath);
	}
	private static Object[][] readDataFromCSV(String filePath) {
		List<Object[]> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				records.add(values);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return records.toArray(new Object[0][]);
	}
	public static String getLoginPageURL() {
		return loginPageURL;
	}
	public static String getAccountPageURL() {
		return accountPageURL;
	}
	public static String getSearchPageURL() {
		return searchPageURL;
	}
	public static String getLoginPageURL(String domain) {
		if (domain.equals(".in")) {
			return loginPageURLForIndia;
		}
		return loginPageURL;
	}
	public static String getOrdersPageURL() {
		return ordersPageURL;
	}
}
