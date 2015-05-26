package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Register extends WebDriverPage {

	WebElement userNameInput;
	WebElement emailInput;
	WebElement passwordInput;
	WebElement confirmpasswordInput;
	WebElement errorDiv;

	public Register(WebDriverProvider driverProvider) {
		super(driverProvider);
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void fillInForm(String username, String email, String password,
			String confirmPassword) {
		userNameInput = findElement(By.id("Username"));
		emailInput = findElement(By.id("Email"));
		passwordInput = findElement(By.id("Password"));
		confirmpasswordInput = findElement(By.id("ConfirmPassword"));

		userNameInput.sendKeys(username);
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		confirmpasswordInput.sendKeys(confirmPassword);
		confirmpasswordInput.submit();
	}

	public boolean errorDivContains(String message) {
		errorDiv = findElement(By.className("validation-summary-errors"));
		return errorDiv.getText().contains(message);
	}

	public boolean visible() {
		emailInput = findElement(By.id("Email"));
		return emailInput.isDisplayed();
	}
}
