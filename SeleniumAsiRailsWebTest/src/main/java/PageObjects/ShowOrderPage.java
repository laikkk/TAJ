package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.PageBase;

public class ShowOrderPage extends PageBase {
	@FindBy(className = "alert")
	WebElement alertDiv;

	@FindBy(xpath = ".//*[@id='Wraper']/div/div/form/div[3]/div/p")
	WebElement descriptionParagraph;

	@FindBy(xpath = ".//*[@id='Wraper']/div/div/form/div[4]/div/p")
	WebElement maxBudgetParagraph;

	@FindBy(xpath = ".//*[@id='Wraper']/div/div/form/div[5]/div/p")
	WebElement contactNumberParagraph;

	@FindBy(xpath = ".//*[@id='Wraper']/div/div/form/div[6]/div/p")
	WebElement contactEmailParagraph;

	public ShowOrderPage(WebDriver driver) {
		super(driver);
		isLoadedConditions();
	}
	
	public boolean isDetailsArePresent(String description,String maxBudget,String contactNumber, String contactEmail)
	{
		return descriptionParagraph.getText().contains(description)
				&& maxBudgetParagraph.getText().contains(maxBudget)
				&& contactNumberParagraph.getText().contains(contactNumber)
				&& contactEmailParagraph.getText().contains(contactEmail);
	}

	 private void isLoadedConditions(){
		 
	 }

	@Override
	public Boolean isLoadedCondition() {
		wait = new WebDriverWait(driver, 5);
		return wait.until(ExpectedConditions.elementToBeClickable(descriptionParagraph))
				.isDisplayed();
	}
}
