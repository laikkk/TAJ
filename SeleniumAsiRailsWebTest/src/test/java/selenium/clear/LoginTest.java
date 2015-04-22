package selenium.clear;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.DashboardPage;
import PageObjects.LoginPage;
import PageObjects.UserPage;

import common.BaseTest;

public class LoginTest extends BaseTest {
	String wrongUserName = "testuser1";
	String wrongUserEmail = "kamilamd@gmail.com";
	String wrongUserPassword = "zaq1@WSX";

	String goodUserEmail = "testmail3@gmail.com";
	String goodUserPassword = "123456";

	LoginPage loginPage;
	DashboardPage dashboardPage;
	UserPage userPage;

	@Before
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"./src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		driver.get(appUrl);
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}

	@Test
	public void WhenGoToMainPageShoulBeRedirectToLoginPage() {
		TakeScreenShot();
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		Assert.assertTrue(loginPage.isAlertPleaseLogInDisplayed());
		TakeScreenShot();
	}

	@Test
	public void WhenTryToLogInUsingNonExitingUserCredensial_ShouldShowErrorInvalidCreds() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.TryLogiInAs(wrongUserEmail, wrongUserPassword);
		Assert.assertTrue(loginPage.isAlertInvalidCredsDisplayed());
		TakeScreenShot();
	}

	@Test
	public void WhenLogIn_ShouldRedirectToDashbordPage() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		dashboardPage = loginPage.LogiInAs(goodUserEmail, goodUserPassword);
		Assert.assertTrue(dashboardPage.isDashboardLoaded());
		TakeScreenShot();
	}

	@Test
	public void WhenLogInAndGoToUserPanel_ShouldAppropriateValuesWereDisplayed() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		dashboardPage = loginPage.LogiInAs(goodUserEmail, goodUserPassword);
		userPage = dashboardPage.goToUserPage();
		Assert.assertTrue(userPage.isEmailIsDisplayed(goodUserEmail));
		TakeScreenShot();
	}

	@Test
	public void WhenLoginThenLogOut_ShouldRedirectToLoginPage() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		dashboardPage = loginPage.LogiInAs(goodUserEmail, goodUserPassword);
		loginPage = dashboardPage.LogOut();
		Assert.assertTrue(loginPage.isLoginPageDisplayed());
		TakeScreenShot();
	}
}
