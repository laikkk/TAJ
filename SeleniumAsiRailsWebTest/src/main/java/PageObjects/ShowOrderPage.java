package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShowOrderPage {
	WebDriver driver;
	WebDriverWait wait;
	
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
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
		 wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(descriptionParagraph));
	 }
}
