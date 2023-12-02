package com.amazon.Hawk.Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.Hawk.Base.BasePage;
public class BuyPO extends BasePage{
	private WebDriver driver;
	private Actions actions;
	private WebDriverWait wait;
	private static BuyPO instance;
	private static LoginPO loginPO;
	public BuyPO(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		this.loginPO = new LoginPO(driver);
	}
	
	@FindBy(id="add-to-cart-button")
	private WebElement AddToCartButton;
	
	@FindBy(id = "nav-cart")
	private WebElement viewCartButton;
	
	//input[@value='Delete']
	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement deleteProductsButton;
	
	@FindBy(xpath = "//input[@value='Proceed to checkout']")
	private WebElement proceedToBuyButton;
	
	@FindBy(xpath = "//input[@name='placeYourOrder1' and @class='a-button-input' and @type='submit']")
	private WebElement placeOrderButton;
	
	public void quit() {
		driver.quit();
		
	}
	public void viewCart() {
		viewCartButton.click();
		
	}
	public void addToCart() {
		AddToCartButton.click();
		
	}
	
	public void deleteProductsFromCart() {
		deleteProductsButton.click();
		
	}
	public boolean isProductInCart(String productName) {
		return driver.findElement(By.partialLinkText(productName)).isDisplayed();
		
	}
	public void proceedToBuy() {
		proceedToBuyButton.click();
		
	}
	public void enterShippingDetails() {
		// TODO Auto-generated method stub
		
	}
	public void selectPaymentMethod() {
		// TODO Auto-generated method stub
		
	}
	public void PlaceOrder() {
		placeOrderButton.click();
		
	}
	public void cancelOrder() {
	    try {
	        logger.info("Clicking on 'View or edit order'");
	        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("View or edit order"))).click();

	        // Click cancelItems
	        logger.info("Clicking on 'Cancel items'");
	        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Cancel items"))).click();

	        // Click cancelButton
	        logger.info("Clicking on cancel button");
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='cq.submit' and @class='a-button-input' and @type='submit']"))).click();

	        // Add additional wait if needed for the next steps

	        // Handle the remaining steps in the cancellation process
	        // ...

	        logger.info("Cancellation process completed successfully");
	    } catch (NoSuchElementException e) {
	        logger.info("Element not found: " + e.getMessage());
	        // Handle or log the exception as needed
	    } catch (Exception e) {
	        logger.info("An unexpected error occurred: " + e.getMessage());
	        // Handle or log the exception as needed
	    }
	}
	public boolean isOrderPlaced() {
		return driver.findElement(By.xpath("//h4[text()='Order placed, thank you!']")).isDisplayed();
	}
	public void goToOrders() {
		loginPO.getOrdersPage();
		
	}
	public boolean isOrderCancelled() {
	    try {
	        return driver.findElement(By.xpath("//h4[text()='This order has been cancelled.']")).isDisplayed();
	    } catch (NoSuchElementException e) {
	        logger.info("Element not found: " + e.getMessage());
	        // Handle or log the exception as needed
	        return false; // Return false in case of failure
	    } catch (Exception e) {
	        logger.info("An unexpected error occurred: " + e.getMessage());
	        // Handle or log the exception as needed
	        return false; // Return false in case of failure
	    }
	}

}
