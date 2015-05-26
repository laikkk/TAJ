package com.example.webguidemo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class MyGarageSteps {

	private final Pages pages;

	public MyGarageSteps(Pages pages) {
		this.pages = pages;
	}

	@Given("user is on Home page")
	public void userIsOnHomePage() {
		pages.home().open();
	}

	@When("click on Enter to Garage button")
	public void userClicksOnEnterToGarageButton() {
		pages.home().openLoginPage();
	}

	@Then("Login page appear")
	public void loginPageAppear() {
		Assert.assertTrue(pages.login().visible());
	}

	@When("login as $email $password")
	public void loginAs(String email, String password) {
		pages.login().typeEmailAndPassword(email, password);
	}

	@Then("alertbox contains $message")
	public void loginAlertBoxContains(String message) {
		Assert.assertTrue(pages.login().errorDivContains(message));
	}

	@When("click on register link")
	public void goToRegisterPage() {
		pages.login().goToRegisterPage();
	}

	@Then("Register page appear")
	public void registerPageAppear() {
		Assert.assertTrue(pages.register().visible());
	}

	@When("register as $username $email $password $confirmPassword")
	public void goToRegisterPage(String username, String email,
			String password, String confirmPassword) {
		pages.register().fillInForm(username, email, password, confirmPassword);
	}

	@Then("register alertbox contains $message")
	public void registerAlertBoxContains(String message) {
		Assert.assertTrue(pages.register().errorDivContains(message));
	}
}
