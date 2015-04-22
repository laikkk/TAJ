package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllOrdersPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(className = "alert")
	WebElement alertDiv;

	@FindBy(xpath = ".//*[@id='Wraper']/div/div/div/table/tbody/tr")
	List<WebElement> tableRows;

	public AllOrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		isLoadedConditions();
	}

	public boolean isRowExists(String description, String maxBudget,
			String contactNumber, String contactEmail) {

		for (WebElement row : tableRows) {
			String rowText = row.getText();
			if (rowText.contains(description) && rowText.contains(maxBudget)
					&& rowText.contains(contactNumber)
					&& rowText.contains(contactEmail))
				return true;
		}

		return false;
	}
	
	 private void isLoadedConditions(){
		 wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='Wraper']/div/div/div/table/tbody/tr")));
	 }
}
