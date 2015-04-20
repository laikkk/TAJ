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

import PageObjects.AllOrdersPage;
import PageObjects.CreateNewOrderPage;
import PageObjects.DashboardPage;
import PageObjects.LoginPage;
import PageObjects.ShowOrderPage;

public class OrderTest {
	Random generator = new Random();
	static String appUrl = "https://asi-rails-i.herokuapp.com/";
	DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss SS");

	static ChromeDriver driver;
	static WebDriverWait wait;

	LoginPage loginPage;
	DashboardPage dashboardPage;
	CreateNewOrderPage createNewOrderPage;
	ShowOrderPage showOrderPage;
	AllOrdersPage allOrdersPage;

	String goodUserEmail = "testmail3@gmail.com";
	String goodUserPassword = "123456";

	@Rule
	public TestName testName = new TestName();

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		@Override
		protected void starting(final Description description) {
			String methodName = description.getMethodName();
			String className = description.getClassName();
			className = className.substring(className.lastIndexOf('.') + 1);
			System.err.println("Starting JUnit-test: " + className + " "
					+ methodName);
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
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		dashboardPage = loginPage.LogiInAs(goodUserEmail, goodUserPassword);
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}

	@Test
	public void WhenCreateOrder_ShouldBeDispalyedInDetailsOrderView() {
		String description = "testDesc";
		String maxBudget = "123";
		String contactNumber = "12345678";
		String contactEmail = "testmail3@gmail.com";
		createNewOrderPage = dashboardPage.createNewOrder();
		showOrderPage = createNewOrderPage.createOrder(description, maxBudget, contactNumber,
				contactEmail);
		Assert.assertTrue(showOrderPage.isDetailsArePresent(description, maxBudget,
				contactNumber, contactEmail));
		TakeScreenShot();
	}
	
	@Test
	public void WhenCreateOrder_ShouldBeDispalyedInAllOrdersView() {
		String description = "testDesc";
		String maxBudget = "123";
		String contactNumber = "12345678";
		String contactEmail = "testmail3@gmail.com";
		createNewOrderPage = dashboardPage.createNewOrder();
		showOrderPage = createNewOrderPage.createOrder(description, maxBudget, contactNumber,
				contactEmail);
		Assert.assertTrue(showOrderPage.isDetailsArePresent(description, maxBudget,
				contactNumber, contactEmail));
		TakeScreenShot();
		allOrdersPage = dashboardPage.goToAllOrders();
		Assert.assertTrue(allOrdersPage.isRowExists(description, maxBudget,
				contactNumber, contactEmail));
		TakeScreenShot();
	}

	private void TakeScreenShot() {
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileName = this.getClass().getSimpleName() + "_"
				+ testName.getMethodName() + "_"
				+ dateFormat.format(new Date()) + ".png";
		try {
			FileUtils.copyFile(scr, new File("c:\\tmp\\selenium\\screenshot\\"
					+ fileName));
		} catch (IOException e) {
			System.out.println("Nieudalo siê zrobic screen shota");
		}
	}
}
