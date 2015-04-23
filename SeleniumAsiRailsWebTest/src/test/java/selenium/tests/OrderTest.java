package selenium.tests;

import org.apache.commons.lang3.SystemUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import pageObjects.AllOrdersPage;
import pageObjects.CreateNewOrderPage;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import pageObjects.ShowOrderPage;
import common.BaseTest;

/**
 * @author Kamil Zielinski
 * Example of tests that use WebDriverWait and one driver session
 */
public class OrderTest extends BaseTest {
	LoginPage loginPage;
	DashboardPage dashboardPage;
	CreateNewOrderPage createNewOrderPage;
	ShowOrderPage showOrderPage;
	AllOrdersPage allOrdersPage;

	String goodUserEmail = "testmail3@gmail.com";
	String goodUserPassword = "123456";
	
	String description = "testDesc";
	String maxBudget = "123";
	String contactNumber = "12345678";
	String contactEmail = "testmail3@gmail.com";

	@BeforeClass
	public static void openBrowserAndLogin() {
		if(SystemUtils.IS_OS_WINDOWS)
		System.setProperty("webdriver.chrome.driver",
				"./src/main/resources/chromedriver.exe");
		else 
			System.setProperty("webdriver.chrome.driver",
					"./src/main/resources/chromedriver");
		driver = new ChromeDriver();
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
		createNewOrderPage = dashboardPage.createNewOrder();
		showOrderPage = createNewOrderPage.createOrder(description, maxBudget, contactNumber,
				contactEmail);
		Assert.assertTrue(showOrderPage.isDetailsArePresent(description, maxBudget,
				contactNumber, contactEmail));
		TakeScreenShot();
	}
	
	@Test
	public void WhenCreateOrder_ShouldBeDispalyedInAllOrdersView() {
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
