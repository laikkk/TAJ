package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(id="session_email")
	WebElement emailInput;
	
	@FindBy(id="session_password")
	WebElement passwordInput;
	
	@FindBy(className="alert-danger")
	WebElement alertDiv;
	
	@FindBy(className="row")
	WebElement loginRow;
	
	String pleaseLogInMsg = "Please log in.";
	String invalidCredsMsg = "Invalid email/password combination";
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
}

