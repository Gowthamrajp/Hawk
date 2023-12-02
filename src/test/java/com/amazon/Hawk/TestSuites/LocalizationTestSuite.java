package com.amazon.Hawk.TestSuites;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.amazon.Hawk.Base.BaseTest;
import com.amazon.Hawk.Pages.LoginPO;
import com.amazon.Hawk.Pages.SearchPO;
import com.amazon.Hawk.Utils.CaptureScreenshot;
import com.amazon.Hawk.Utils.DriverFactory;

public class LocalizationTestSuite extends BaseTest{
	WebDriver driver = DriverFactory.getDriver("chrome");
	LoginPO loginPO = new LoginPO(driver);
	SearchPO searchPO = new SearchPO(driver);
	@Test
	public void checkINRCurrencySymbol() throws IOException {
		try {
			String productLink = "mhttps://www.amazon.in/SG-Bouncer-Leather-Ball/dp/B00ID8BJSS/ref=sr_1_1?crid=2EHGP6DBFALI7&keywords=SG+Bouncer+Leather+Ball+%28Red%29+%2C+Standard+Size+1Pc&nsdOptOutParam=true&qid=1700989155&sprefix=sg+bouncer+leather+ball+red+%2C+standard+size+1pc%2Caps%2C202&sr=8-1";
			loginPO.login(".in");
			loginPO.loadProductPage(productLink);
			assertEquals(loginPO.getCurrencySymbol(),"₹");
		} catch (Exception e) {
			System.out.println("Test case failed: " + e.getMessage());
            e.printStackTrace();
            CaptureScreenshot.captureScreenshot(driver, BuyTestSuite.getMethodName());
		}
	}
	@Test
	public void checkUSDCurrencySymbol() throws IOException {
		try {
			String productLink = "https://www.amazon.com/Bouncer-Cricket-Leather-Piece-Stock/dp/B09DGM5Z24/ref=sr_1_1?crid=LAHMUEKIPNYP&keywords=SG+Bouncer+Leather+Ball+%28Red%29+%2C+Standard+Size+1Pc&qid=1700989050&sprefix=sg+bouncer+leather+ball+red+%2C+standard+size+1pc%2Caps%2C532&sr=8-1";
			loginPO.login();
			loginPO.loadProductPage(productLink);
			assertEquals(loginPO.getCurrencySymbol(),"$");
		} catch (Exception e) {
			System.out.println("Test case failed: " + e.getMessage());
            e.printStackTrace();
            CaptureScreenshot.captureScreenshot(driver, BuyTestSuite.getMethodName());
		}
	}
	
	@AfterClass
    public void tearDown() {
        loginPO.quit();
    }
}
