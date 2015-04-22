package common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
	Random generator = new Random();
	public static String appUrl = "https://asi-rails-i.herokuapp.com/";
	DateFormat dateFormatWithSpace = new SimpleDateFormat("yyyy MM dd HH mm ss SS");
	
	protected static ChromeDriver driver;
	protected static WebDriverWait wait;
	
	// used to show which test method is actually executing
    @Rule
    public TestName testName = new TestName();

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(final Description description) {
            String methodName = description.getMethodName();
            String className = description.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            System.err.println("Starting JUnit-test: " + className + " " + methodName);
        }
    };
    
	protected void TakeScreenShot() {
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileName = this.getClass().getSimpleName() + "_"
				+ testName.getMethodName() + "_"
				+ dateFormatWithSpace.format(new Date()) + ".png";
		try {
			FileUtils.copyFile(scr,
					new File("c:\\tmp\\selenium\\screenshot\\"
							+ fileName));
		} catch (IOException e) {
			System.out.println("Nieudalo siê zrobic screen shota");
		}
	}
}
