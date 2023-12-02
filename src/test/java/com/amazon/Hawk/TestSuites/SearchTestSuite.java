package com.amazon.Hawk.TestSuites;

import org.testng.annotations.Test;

import com.amazon.Hawk.Base.BaseTest;
import com.amazon.Hawk.Constants.TestData;
import com.amazon.Hawk.Pages.LoginPO;
import com.amazon.Hawk.Pages.SearchPO;
import com.amazon.Hawk.Utils.CaptureScreenshot;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
public class SearchTestSuite extends BaseTest{
	
	@Test(dataProvider = "getSearchProductDetails", dataProviderClass = TestData.class)
	public void search_products_without_login(String searchString,String browserType) throws IOException {
		WebDriver driver = driverFactory.getDriver(browserType);
		LoginPO loginPO = new LoginPO(driver);
		SearchPO searchPO = new SearchPO(driver);
		try {
			searchPO.getSearchPage();
			searchPO.searchProduct(searchString);
			assertTrue(searchPO.isProductListed(searchString),"Product listing for "+searchString+"is not seen");
		} catch (Exception e) {
            System.out.println("Test case failed: " + e.getMessage());
            e.printStackTrace();
            CaptureScreenshot.captureScreenshot(driver, SearchTestSuite.getMethodName());
		} finally {
			logger.info("Testcase executed with");
			searchPO.quit();
		}
	}
	@Test(dataProvider = "getSearchProductDetails", dataProviderClass = TestData.class)
	public void search_products_with_login(String searchString,String browserType) throws IOException {		
		WebDriver driver = driverFactory.getDriver(browserType);
		LoginPO loginPO = new LoginPO(driver);
		SearchPO searchPO = new SearchPO(driver);
		try {
			loginPO.login();
			searchPO.searchProduct(searchString);
			assertTrue(searchPO.isProductListed(searchString),"Product listing for "+searchString+"is not seen");
			
		} catch (Exception e) {
            System.out.println("Test case failed: " + e.getMessage());
            e.printStackTrace();
            CaptureScreenshot.captureScreenshot(driver, LoginTestSuite.getMethodName());
		} finally {
			logger.info("Testcase executed with");
			searchPO.quit();
		}
	}
}
