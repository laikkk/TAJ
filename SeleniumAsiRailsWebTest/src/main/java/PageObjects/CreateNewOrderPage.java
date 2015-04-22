package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewOrderPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="order_description")
	WebElement descriptionInput;
	
	@FindBy(id="order_max_budget")
	WebElement maxBudgetInput;
	
	@FindBy(id="order_contact_number")
	WebElement contactNumberInput;
	
	@FindBy(id="order_contact_emial")
	WebElement contactEmailInput;
	
 public CreateNewOrderPage(WebDriver driver) {
	 this.driver = driver;
	 PageFactory.initElements(driver, this);
	 isLoadedConditions();
  }
 
 public void tryCreateOrder(String description,String maxBudget,String contactNumber, String contactEmail)
 {
	 descriptionInput.sendKeys(description);
	 maxBudgetInput.sendKeys(maxBudget);
	 contactNumberInput.sendKeys(contactNumber);
	 contactEmailInput.clear();
	 contactEmailInput.sendKeys(contactEmail);
	 contactEmailInput.submit();
 }
 
 public ShowOrderPage createOrder(String description,String maxBudget,String contactNumber, String contactEmail)
 {
	 tryCreateOrder(description,maxBudget,contactNumber,contactEmail);
	 return PageFactory.initElements(driver, ShowOrderPage.class);
 }
 
 private void isLoadedConditions(){
	 wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(descriptionInput));
 }
}
