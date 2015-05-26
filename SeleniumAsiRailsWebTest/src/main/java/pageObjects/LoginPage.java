package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.PageBase;

public class LoginPage extends PageBase {
	@FindBy(id="session_email")
	WebElement emailInput;
	
	@FindBy(id="session_password")
	WebElement passwordInput;
	
	@FindBy(className="alert-danger")
	@CacheLookup
	WebElement alertDiv;
	
	@FindBy(className="row")
	@CacheLookup
	WebElement loginRow;
	
	String pleaseLogInMsg = "Please log in.";
	String invalidCredsMsg = "Invalid email/password combination";
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// Sometime we want to check if page is loaded before we will using it
		isLoadedCondition();
	}
	
	public void TryLogiInAs(String email, String password)
	{
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		passwordInput.submit();
	}
	
	public DashboardPage LogiInAs(String email, String password)
	{
		TryLogiInAs(email,password);
		return new DashboardPage(driver);
	}
	
	public boolean isAlertPleaseLogInDisplayed()
	{
		return alertDiv.getText().contains(pleaseLogInMsg);		
	}
	
	public boolean isAlertInvalidCredsDisplayed()
	{
		return alertDiv.getText().contains(invalidCredsMsg);		
	}

	public boolean isLoginPageDisplayed() {
		return loginRow.getText().contains("Log in");
	}

	@Override
	public Boolean isLoadedCondition() {
		 wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(emailInput));
		return emailInput.isDisplayed();
	}
}

