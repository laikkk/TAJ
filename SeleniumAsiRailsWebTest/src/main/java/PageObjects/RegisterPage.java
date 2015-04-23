package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.PageBase;

public class RegisterPage extends PageBase {
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
		super(driver);
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

	@Override
	public Boolean isLoadedCondition() {
		// used implicitly Wait
		return userNameInput.isDisplayed();
	}
}
