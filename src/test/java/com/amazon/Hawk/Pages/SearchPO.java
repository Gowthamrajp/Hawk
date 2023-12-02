package com.amazon.Hawk.Pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.Hawk.Base.BasePage;
import com.amazon.Hawk.Constants.TestData;

public class SearchPO extends BasePage{
	private WebDriver driver;
	private Actions actions;
	private static SearchPO instance;
	
	public SearchPO(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchBox;
	
	@FindBy(id="nav-search-submit-button")
	private WebElement searchSubmitButton;
	
	public static SearchPO getInstance(WebDriver driver) {
		instance = new SearchPO(driver);
		return instance;
	}
	@SuppressWarnings("deprecation")
	public void getSearchPage() {
		driver.get(TestData.getSearchPageURL());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
	}
	public void searchProduct(String searchString) {
	    try {
	        searchBox.sendKeys(searchString);
	        searchSubmitButton.click();
	    } catch (NoSuchElementException e) {
	        logger.info("Element not found: " + e.getMessage());
	        // Handle or log the exception as needed
	    } catch (Exception e) {
	        logger.info("An unexpected error occurred: " + e.getMessage());
	        // Handle or log the exception as needed
	    }
	}

	public boolean isProductListed(String productName) {
	    try {
	        return driver.findElement(By.partialLinkText(productName)).isDisplayed();
	    } catch (NoSuchElementException e) {
	        logger.info("Element not found: " + e.getMessage());
	        // Handle or log the exception as needed
	        return false;
	    } catch (Exception e) {
	        logger.info("An unexpected error occurred: " + e.getMessage());
	        // Handle or log the exception as needed
	        return false;
	    }
	}

	public void clickProduct(String productName) {
	    try {
	        driver.findElement(By.partialLinkText(productName)).click();
	    } catch (NoSuchElementException e) {
	        logger.info("Element not found: " + e.getMessage());
	        // Handle or log the exception as needed
	    } catch (Exception e) {
	        logger.info("An unexpected error occurred: " + e.getMessage());
	        // Handle or log the exception as needed
	    }

	}
	public void quit() {
		driver.quit();
		
	}
	
}
