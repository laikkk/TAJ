package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Home extends WebDriverPage {

	@FindBy(how = How.ID, using = "loginregisterToGarage")
	WebElement signIn;
	
	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	public void open() {
		get("https://my-garage.azurewebsites.net/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void openLoginPage(){
		signIn = findElement(By.id("loginregisterToGarage"));
		signIn.click();
	}
}
