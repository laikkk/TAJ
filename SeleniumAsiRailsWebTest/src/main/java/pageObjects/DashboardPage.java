package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.PageBase;

public class DashboardPage extends PageBase {
	
	@FindBy(className = "glyphicon-user")
	WebElement userLink;

	@FindBy(className = "glyphicon-off")
	WebElement logoutLink;

	@FindBy(className = "fa-bar-chart")
	WebElement allOrdersLink;

	@FindBy(className = "fa-plus-circle")
	WebElement addNewOrderLink;

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	public boolean isDashboardLoaded() {
		return userLink.isDisplayed() && logoutLink.isDisplayed();
	}

	public UserPage goToUserPage() {
		userLink.click();
		return PageFactory.initElements(driver, UserPage.class);
	}

	public LoginPage LogOut() {
		wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
		logoutLink.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}

	public CreateNewOrderPage createNewOrder() {
		wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.elementToBeClickable(addNewOrderLink));
		addNewOrderLink.click();
		return PageFactory.initElements(driver, CreateNewOrderPage.class);
	}
	
	public AllOrdersPage goToAllOrders() {
		wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.elementToBeClickable(allOrdersLink));
		allOrdersLink.click();
		return PageFactory.initElements(driver, AllOrdersPage.class);
	}

	@Override
	public Boolean isLoadedCondition() {
		return userLink.isDisplayed();
	}
}
