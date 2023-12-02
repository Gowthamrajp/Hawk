package com.amazon.Hawk.TestSuites;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import com.amazon.Hawk.Base.BaseTest;
import com.amazon.Hawk.Constants.TestData;
import com.amazon.Hawk.Pages.LoginPO;
import com.amazon.Hawk.Utils.CaptureScreenshot;
import com.amazon.Hawk.Utils.JiraHelper;

public class LoginTestSuite extends BaseTest{
	
	public void burnTestEnvironment() {
		logger.info("Environment is burnt for "+ getClass().getName());
	}
	@Test(dataProvider = "getValidCredentials", dataProviderClass = TestData.class)
	@Parameters({"browserName"})
	public void login_with_correct_credentials(String email, String passWord, String userName, String browser) throws IOException {
		WebDriver driver = driverFactory.getDriver(browser);
		LoginPO loginPO = new LoginPO(driver);
		try {
			loginPO.getLoginPage();
			loginPO.enterEmail(email);
			loginPO.enterPassword(passWord);
			Assert.assertTrue(loginPO.isLoggedInSuccessfully(userName),"Login Failed in "+getMethodName());
			loginPO.logout();
		} catch (Exception e) {
			System.out.println("Test case failed: " + e.getMessage());
			e.printStackTrace();
			File screenshotFile = CaptureScreenshot.captureScreenshot(driver, LoginTestSuite.getMethodName());
			JiraHelper.createIssue(LoginTestSuite.getMethodName(), e.getMessage(), screenshotFile);
		} finally {
			loginPO.quit();
		}
	}	
	@Test(dataProvider = "getInvalidPasswordCredentials", dataProviderClass = TestData.class)
	public void login_with_incorrect_password(String email, String passWord, String userName, String browser) throws IOException {
		WebDriver driver = driverFactory.getDriver(browser);
		LoginPO loginPO = new LoginPO(driver);
		try {
			loginPO.getLoginPage();
			loginPO.enterEmail(email);
			loginPO.enterPassword(passWord);
			Assert.assertTrue(loginPO.isBadPasswordAlertSeen(),"Bad password alert not seen in "+getMethodName());
		} catch (Exception e) {
			System.out.println("Test case failed: " + e.getMessage());
			e.printStackTrace();
			CaptureScreenshot.captureScreenshot(driver, LoginTestSuite.getMethodName());
		} finally {
			loginPO.quit();
		}
	}
	@Test(dataProvider = "getInvalidEmailCredentials", dataProviderClass = TestData.class)
	public void login_with_incorrect_email(String email, String passWord, String userName, String browser) throws IOException {
		WebDriver driver = driverFactory.getDriver(browser);
		LoginPO loginPO = new LoginPO(driver);
		try {
			loginPO.getLoginPage();
			loginPO.enterEmail(email);
			Assert.assertTrue(loginPO.isBadEmaildAlertSeen(),"Bad email alert not seen in "+getMethodName());
		} catch (Exception e) {
			System.out.println("Test case failed: " + e.getMessage());
			e.printStackTrace();
			CaptureScreenshot.captureScreenshot(driver, LoginTestSuite.getMethodName());
		} finally {
			loginPO.quit();
		}
	}
}
