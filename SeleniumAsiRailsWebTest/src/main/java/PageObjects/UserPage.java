package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.PageBase;

public class UserPage extends PageBase {
	@FindBy(xpath=".//*[@id='Wraper']/div/div/form/div[2]/div/p")
	WebElement emailParagraph;
	
 public UserPage(WebDriver driver) {
	 super(driver);
  }
	
	public boolean isEmailIsDisplayed(String goodUserEmail) {
		return emailParagraph.getText().contains(goodUserEmail);
	}

	@Override
	public Boolean isLoadedCondition() {
		return emailParagraph.isDisplayed();
	}

}
