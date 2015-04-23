package common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseTest {
	protected static WebDriver driver;
	public static String appUrl = "https://asi-rails-i.herokuapp.com/";
	String destinationOfScreenShots = "c:\\tmp\\selenium\\screenshot\\";
	DateFormat dateFormatWithSpace = new SimpleDateFormat("yyyy MM dd HH mm ss SS");

	// Used to show which test method is actually executing
	@Rule
	public TestName testName = new TestName();

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		@Override
		protected void starting(final Description description) {
			String methodName = description.getMethodName();
			String className = description.getClassName();
			className = className.substring(className.lastIndexOf('.') + 1);
			System.err.println("Starting JUnit-test: " + className + " "
					+ methodName);
		}
	};

	/**
	 * Taking screen shot and save it as {ClassName}_{TestMethodName}_{dateTime} in folder set in destinationOfScreenShots
	 */
	protected void TakeScreenShot() {
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileName = this.getClass().getSimpleName() + "_"
				+ testName.getMethodName() + "_"
				+ dateFormatWithSpace.format(new Date()) + ".png";
		try {
			FileUtils.copyFile(scr, new File(destinationOfScreenShots
					+ fileName));
		} catch (IOException e) {
			System.out.println("Error occurred during taking screen shots");
		}
	}
}
