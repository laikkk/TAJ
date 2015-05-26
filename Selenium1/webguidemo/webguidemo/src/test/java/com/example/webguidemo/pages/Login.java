package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Login extends WebDriverPage {

	@FindBy(how = How.ID, using = "Email")
	WebElement emailInput;

	@FindBy(how = How.ID, using = "Password")
	WebElement passwordInput;

	@FindBy(how = How.CLASS_NAME, using = "validation-summary-errors")
	WebElement errorDiv;

	public Login(WebDriverProvider driverProvider) {
		super(driverProvider);
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void typeEmailAndPassword(String email, String password) {
		emailInput =findElement(By.id("Email"));
		passwordInput =findElement(By.id("Password"));
		
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		passwordInput.submit();
	}

	public boolean errorDivContains(String message) {
		errorDiv = findElement(By.className("validation-summary-errors"));
		return errorDiv.getText().contains(message);
	}
	
	public boolean visible() {
		emailInput =findElement(By.id("Email"));
		return emailInput.isDisplayed();
	}
	
	public void goToRegisterPage() {
		findElement(By.partialLinkText("Register")).click();
	}

}
