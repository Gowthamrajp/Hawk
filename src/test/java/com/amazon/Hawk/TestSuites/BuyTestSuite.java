package com.amazon.Hawk.TestSuites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazon.Hawk.Base.BaseTest;
import com.amazon.Hawk.Constants.TestData;
import com.amazon.Hawk.Pages.BuyPO;
import com.amazon.Hawk.Pages.LoginPO;
import com.amazon.Hawk.Pages.SearchPO;
import com.amazon.Hawk.Utils.CaptureScreenshot;
import com.amazon.Hawk.Utils.DriverFactory;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
public class BuyTestSuite extends BaseTest{
	@Test(dataProvider = "getBuyProductDetails", dataProviderClass = TestData.class)
	void addToCartAndVerify(String productName, String browserType) throws IOException {
//		String browserType = "chrome";
//		String productName = "SG Bouncer Leather Ball (Red) , Standard Size 1Pc";
		WebDriver driver = driverFactory.getDriver(browserType);
		LoginPO loginPO = new LoginPO(driver);
		SearchPO searchPO = new SearchPO(driver);
		BuyPO buyPO = new BuyPO(driver);
		
		try {
			loginPO.login(".in");
			searchPO.searchProduct(productName);
			//assertTrue(searchPO.isProductListed(productName),"Product listing for "+productName+"is not seen");
			searchPO.clickProduct(productName);
			//String originalHandle = driver.getWindowHandle();
			//Set<String> allHandles = driver.getWindowHandles();
			driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
			buyPO.addToCart();
			buyPO.viewCart();
			assertTrue(buyPO.isProductInCart(productName),productName+"is not found in cart");
			
		} catch (Exception e) {
            System.out.println("Test case failed: " + e.getMessage());
            e.printStackTrace();
            CaptureScreenshot.captureScreenshot(driver, BuyTestSuite.getMethodName());
		} finally {
			buyPO.deleteProductsFromCart();
			buyPO.quit();
		}
	}
	
	@Test(groups = "pre-req")
	@Parameters({"chrome","SG Bouncer Leather Ball (Red) , Standard Size 1Pc"})
	void buyProductAndVerify(String browserType,String productName ) throws IOException {
		WebDriver driver = driverFactory.getDriver(browserType);
		LoginPO loginPO = new LoginPO(driver);
		SearchPO searchPO = new SearchPO(driver);
		BuyPO buyPO = new BuyPO(driver);
		
		try {
			loginPO.login(".in");
			searchPO.searchProduct(productName);
			//assertTrue(searchPO.isProductListed(productName),"Product listing for "+productName+"is not seen");
			searchPO.clickProduct(productName);
			//String originalHandle = driver.getWindowHandle();
			//Set<String> allHandles = driver.getWindowHandles();
			driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
			buyPO.addToCart();
			buyPO.viewCart();
			assertTrue(buyPO.isProductInCart(productName),productName+"is not found in cart");
			buyPO.proceedToBuy();
			//Thread.sleep(10000);
			buyPO.PlaceOrder();
			//buyPO.enterShippingDetails();
			//buyPO.selectPaymentMethod();
			assertTrue(buyPO.isOrderPlaced(),"Order is not placed");
			
		} catch (Exception e) {
            System.out.println("Test case failed: " + e.getMessage());
            e.printStackTrace();
            CaptureScreenshot.captureScreenshot(driver, BuyTestSuite.getMethodName());
		} finally {
			//buyPO.cancelOrder();
			buyPO.quit();
		}
	}
	
	@Test(dependsOnGroups = "pre-req" )
	@Parameters({"chrome","SG Bouncer Leather Ball (Red) , Standard Size 1Pc"})
	void cancelOrderAndVerify(String browserType,String productName) throws IOException {
		WebDriver driver = DriverFactory.getDriver(browserType);
		LoginPO loginPO = new LoginPO(driver);
		BuyPO buyPO = new BuyPO(driver);
		
		try {
			loginPO.login(".in");
			buyPO.goToOrders();
			buyPO.cancelOrder();
			assertTrue(buyPO.isOrderCancelled(),"Order is not cancelled");
			
		} catch (Exception e) {
            System.out.println("Test case failed: " + e.getMessage());
            e.printStackTrace();
            CaptureScreenshot.captureScreenshot(driver, BuyTestSuite.getMethodName());
		} finally {
			buyPO.quit();
		}
	}
}