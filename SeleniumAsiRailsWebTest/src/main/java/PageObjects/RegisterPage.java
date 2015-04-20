package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	@FindBy(id="user_name")
	WebElement userNameInput;
	
	@FindBy(id="user_email")
	WebElement userEmailInput;
	
	@FindBy(id="user_password")
	WebElement userPasswordInput;
	
	@FindBy(id="error_explanation")
	WebElement alertDiv;
	
	@FindBy(id="user_password_confirmation")
	WebElement userConfirmPasswordInput;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void tryRegister(String userName,String userEmail, String userPassword,  String confirmPassword){
		userNameInput.sendKeys(userName);
		userEmailInput.sendKeys(userEmail);
		userPasswordInput.sendKeys(userPassword);
		userConfirmPasswordInput.sendKeys(confirmPassword);
		userConfirmPasswordInput.submit();
	}
	
	public DashboardPage Register(String userName,String userEmail, String userPassword,  String confirmPassword){
		tryRegister(userName,userEmail,userPassword,confirmPassword);
		return PageFactory.initElements(driver, DashboardPage.class);
	}

	public boolean isAlertWithErrorsDisplayed(String messageOne, String messageTwo) {
		String alertText = alertDiv.getText();
		return alertText.contains(messageOne) && alertText.contains(messageTwo);
	}
}
