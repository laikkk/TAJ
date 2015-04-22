package selenium.clear;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.DashboardPage;
import PageObjects.RegisterPage;
import PageObjects.UserPage;
import common.BaseTest;

public class RegisterTest extends BaseTest {
	
	static String singUpUrl = appUrl+"signup";

	RegisterPage registerPage;
	DashboardPage dashboardPage;
	UserPage userPage;
    
	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"./src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		driver.get(singUpUrl);
	}
	
	@Before
	public void isLoginThenLogoutAndGoToSignInPage(){
		if(dashboardPage!=null)
			dashboardPage.LogOut();
		driver.get(singUpUrl);
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
	
	@Test
	public void WhenLeaveRegisterFields_AllertShouldBeDisplayed(){
		registerPage = PageFactory.initElements(driver, RegisterPage.class);
		registerPage.Register("", "", "", "");
		Assert.assertTrue(registerPage.isAlertWithErrorsDisplayed(
				"Name can't be blank",
				"Email can't be blank"));
		TakeScreenShot();
	}
	
	@Test
	public void WhenRegisterCorrectly_ShouldDashboardBeDisplayed(){
		registerPage = PageFactory.initElements(driver, RegisterPage.class);
		DateFormat compactDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String unique = compactDateFormat.format(new Date());
		String userName = "testUs"+unique;
		String userEmail = "testmail"+unique+"@testmail.com";
		dashboardPage = registerPage.Register(userName, userEmail, "123456", "123456");
		Assert.assertTrue(dashboardPage.isDashboardLoaded());
		TakeScreenShot();
	}
	
	@Test
	public void WhenRegisterCorrectly_ShouldBeLogged(){
		registerPage = PageFactory.initElements(driver, RegisterPage.class);
		
		DateFormat compactDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String unique = compactDateFormat.format(new Date());
		String userName = "testUs"+unique;
		String userEmail = "testmail"+unique+"@testmail.com";
		
		dashboardPage = registerPage.Register(userName, userEmail, "123456", "123456");
		userPage = dashboardPage.goToUserPage();
		Assert.assertTrue(userPage.isEmailIsDisplayed(userEmail));
		TakeScreenShot();
	}	
}
