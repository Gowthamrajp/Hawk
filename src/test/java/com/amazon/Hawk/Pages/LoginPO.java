package com.amazon.Hawk.Pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.Hawk.Base.BasePage;
import com.amazon.Hawk.Constants.TestData;
public class LoginPO extends BasePage{
	
	private WebDriver driver;
	private Actions actions;
	private WebDriverWait wait;
	private static LoginPO instance;
	
	public LoginPO(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="email")
	private WebElement emailField;
	
	@FindBy(id="continue")
	private WebElement continueButton;
	
	@FindBy(name="password")
	private WebElement passwordField;
	
	@FindBy(id="signInSubmit")
	private WebElement signInButton;
	
	@FindBy(xpath = "//span[text()='Sign Out']")
	private WebElement logOutButton;
	//= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sign Out']")));
	
	@FindBy(xpath = "//span[@class='nav-title' and text()='Your Account']")
	private WebElement accountButton;
	
	@FindBy(xpath = "//h2[contains(text(), 'Login & security')]")
	private WebElement logInAndSecurityTab;
	
	@FindBy(id="nav-link-accountList-nav-line-1")
	private WebElement accountNavBox;
	
	@FindBy(xpath = "//div[@tabindex='0']//span")
	private WebElement nameTitle;
	
	@FindBy(xpath = "//span[normalize-space(text())='Your password is incorrect']")
	private WebElement badPasswordAlert;
	
	@FindBy(xpath = "//span[normalize-space(text())='We cannot find an account with that email address']")
	private WebElement badEmailAlert;
	
	@FindBy(className = "a-price-symbol" )
	private WebElement currencySymbol;
	
	
	public static LoginPO getInstance(WebDriver driver) {
		instance = new LoginPO(driver);
		return instance;
	}
	public void enterEmail(String str) {
		emailField.sendKeys(str);
		continueButton.click();
	}
	public void enterPassword(String str) {
		passwordField.sendKeys(str);
		signInButton.click();
	}
	public void getLoginPage() {
		driver.get(TestData.getLoginPageURL());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
	}
	@SuppressWarnings("deprecation")
	public void getLoginPage(String domain) {
		driver.get(TestData.getLoginPageURL(domain));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
	}
	@SuppressWarnings("deprecation")
	public void getAccountPage() {
		driver.get(TestData.getAccountPageURL());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
	}
	
	@SuppressWarnings("deprecation")
	public void getOrdersPage() {
		logger.info("Opening Orders page");
		driver.get(TestData.getOrdersPageURL());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
	}
	
	public void quit() {
		driver.quit();
		
	}
	public void logout() throws InterruptedException {
		actions.moveToElement(accountNavBox).perform();
		Thread.sleep(3000);
		logOutButton.click();	
	}
	public String getLoggedInUserName() {
		if(accountNavBox.isDisplayed()) {
//		actions.moveToElement(accountNavBox).perform();
//		actions.moveToElement(accountButton).perform();
		//accountButton.click();
		getAccountPage();
		logInAndSecurityTab.click();
		return nameTitle.getText();
		}
		return "";
	}
	public void login() {
		String email = (String) TestData.getValidCredentials()[0][0];
		String passWord = (String) TestData.getValidCredentials()[0][1];
		logger.info(email+passWord+"gowti");
		getLoginPage();
		enterEmail(email);
		enterPassword(passWord);
	}
	public void login(String domain) {
		String email = (String) TestData.getValidCredentialsWithDomain(domain)[0][0];
		String passWord = (String) TestData.getValidCredentialsWithDomain(domain)[0][1];
		getLoginPage(domain);
		enterEmail(email);
		enterPassword(passWord);
	}
	
	public boolean isLoggedInSuccessfully(String userName) {
		return getLoggedInUserName().equals(userName);
	}
	public boolean isBadPasswordAlertSeen() {
		return badPasswordAlert.isDisplayed();
	}
	public boolean isBadEmaildAlertSeen() {
		return badEmailAlert.isDisplayed();
	}
	public String getCurrencySymbol() {
		return currencySymbol.getText();
		
	}
	public void loadProductPage(String productLink) {
		driver.get(productLink);
		
	}
	
}
