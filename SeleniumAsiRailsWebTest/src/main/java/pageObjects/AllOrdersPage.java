package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.PageBase;

public class AllOrdersPage extends PageBase {
	@FindBy(className = "alert")
	WebElement alertDiv;

	@FindBy(xpath = ".//*[@id='Wraper']/div/div/div/table/tbody/tr")
	List<WebElement> tableRows;

	public AllOrdersPage(WebDriver driver) {
		super(driver);
		isLoadedCondition();
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

	@Override
	public Boolean isLoadedCondition() {
		wait = new WebDriverWait(driver, 5);
		return wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
						.xpath(".//*[@id='Wraper']/div/div/div/table/tbody/tr")))
				.get(0).isDisplayed();
	}
}
