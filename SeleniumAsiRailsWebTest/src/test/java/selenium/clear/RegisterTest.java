package selenium.clear;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.DashboardPage;
import PageObjects.RegisterPage;
import PageObjects.UserPage;

public class RegisterTest {
	Random generator = new Random();
	static String appUrl = "https://asi-rails-i.herokuapp.com/signup";
	DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss SS");
	
	static ChromeDriver driver;
	static WebDriverWait wait;

	RegisterPage registerPage;
	DashboardPage dashboardPage;
	UserPage userPage;
	
    @Rule
    public TestName testName = new TestName();

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(final Description description) {
            String methodName = description.getMethodName();
            String className = description.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            System.err.println("Starting JUnit-test: " + className + " " + methodName);
        }
    };
    
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
	
	private void TakeScreenShot() {
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileName = this.getClass().getSimpleName() + "_"
				+ testName.getMethodName() + "_"
				+ dateFormat.format(new Date()) + ".png";
		try {
			FileUtils.copyFile(scr,
					new File("c:\\tmp\\selenium\\screenshot\\"
							+ fileName));
		} catch (IOException e) {
			System.out.println("Nieudalo siê zrobic screen shota");
		}
	}
	
}
