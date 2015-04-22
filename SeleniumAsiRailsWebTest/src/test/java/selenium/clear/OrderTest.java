package selenium.clear;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import PageObjects.AllOrdersPage;
import PageObjects.CreateNewOrderPage;
import PageObjects.DashboardPage;
import PageObjects.LoginPage;
import PageObjects.ShowOrderPage;

import common.BaseTest;

public class OrderTest extends BaseTest {
	LoginPage loginPage;
	DashboardPage dashboardPage;
	CreateNewOrderPage createNewOrderPage;
	ShowOrderPage showOrderPage;
	AllOrdersPage allOrdersPage;

	String goodUserEmail = "testmail3@gmail.com";
	String goodUserPassword = "123456";

	@BeforeClass
	public static void openBrowserAndLogin() {
		System.setProperty("webdriver.chrome.driver",
				"./src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(appUrl);
	}
	
	@Before
	public void isLoginThenLogoutAndLoginAgain(){
		try{
			dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
			dashboardPage.LogOut();
		}
		catch(NoSuchElementException |TimeoutException e){
			 System.err.println("User is logged out");
		}
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		dashboardPage = loginPage.LogiInAs(goodUserEmail, goodUserPassword);
	}

	@AfterClass
	public static void closeBrowser() {
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
}
