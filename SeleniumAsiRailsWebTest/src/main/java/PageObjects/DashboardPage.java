package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	WebDriver driver;

	@FindBy(className = "glyphicon-user")
	WebElement userLink;

	@FindBy(className = "glyphicon-off")
	WebElement logoutLink;

	@FindBy(className = "fa-bar-chart")
	WebElement allOrdersLink;

	@FindBy(className = "fa-plus-circle")
	WebElement addNewOrderLink;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isDashboardLoaded() {
		return userLink.isDisplayed() && logoutLink.isDisplayed();
	}

	public UserPage goToUserPage() {
		userLink.click();
		return PageFactory.initElements(driver, UserPage.class);
	}

	public LoginPage LogOut() {
		logoutLink.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}

	public CreateNewOrderPage createNewOrder() {
		addNewOrderLink.click();
		return PageFactory.initElements(driver, CreateNewOrderPage.class);
	}
	
	public AllOrdersPage goToAllOrders() {
		allOrdersLink.click();
		return PageFactory.initElements(driver, AllOrdersPage.class);
	}
}
