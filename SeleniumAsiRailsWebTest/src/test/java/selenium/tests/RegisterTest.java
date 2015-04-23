package selenium.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import pageObjects.DashboardPage;
import pageObjects.RegisterPage;
import pageObjects.UserPage;
import common.BaseTest;

/**
 * @author Kamil Zielinski
 * Examples of test that use implicit wait and one driver session 
 * */
public class RegisterTest extends BaseTest {
	static String singUpUrl = appUrl + "signup";
	DateFormat compactDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");

	RegisterPage registerPage;
	DashboardPage dashboardPage;
	UserPage userPage;

	// values are prepared by prepareTestData method
	String userName;
	String userEmail;
	String userPassword = "123456";

	@BeforeClass
	public static void openBrowser() {
		if(SystemUtils.IS_OS_WINDOWS)
		System.setProperty("webdriver.chrome.driver",
				"./src/main/resources/chromedriver.exe");
		else 
			System.setProperty("webdriver.chrome.driver",
					"./src/main/resources/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(singUpUrl);
	}

	@Before
	public void isLoginThenLogoutAndGoToSignInPage() {
		if (dashboardPage != null)
			dashboardPage.LogOut();
		driver.get(singUpUrl);
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}

	@Test
	public void WhenLeaveRegisterFields_AllertShouldBeDisplayed() {
		registerPage = PageFactory.initElements(driver, RegisterPage.class);
		registerPage.Register("", "", "", "");
		Assert.assertTrue(registerPage.isAlertWithErrorsDisplayed(
				"Name can't be blank", "Email can't be blank"));
		TakeScreenShot();
	}

	@Test
	public void WhenRegisterCorrectly_ShouldDashboardBeDisplayed() {
		registerPage = PageFactory.initElements(driver, RegisterPage.class);
		prepareTestData();
		dashboardPage = registerPage.Register(userName, userEmail, userPassword,
				userPassword);
		Assert.assertTrue(dashboardPage.isDashboardLoaded());
		TakeScreenShot();
	}

	@Test
	public void WhenRegisterCorrectly_ShouldBeLogged() {
		registerPage = PageFactory.initElements(driver, RegisterPage.class);
		prepareTestData();
		dashboardPage = registerPage.Register(userName, userEmail, userPassword,
				userPassword);
		userPage = dashboardPage.goToUserPage();
		Assert.assertTrue(userPage.isEmailIsDisplayed(userEmail));
		TakeScreenShot();
	}

	/**
	 * Create unique userName and userEmail based on datetime
	 */
	private void prepareTestData() {
		String unique = compactDateFormat.format(new Date());
		userName = "testUs" + unique;
		userEmail = "testmail" + unique + "@testmail.com";
	}
}
