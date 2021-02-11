package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.SafetyProgramPage;

public class SafetyProgramTest extends BaseClass {

	public final static Logger logger = Logger.getLogger(SafetyProgramTest.class);

	@Test
	public void checkSafetyProgram() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "checkSafetyProgram").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			SafetyProgramPage obj = new SafetyProgramPage(driver);
			extentTest = extent.startTest("SAFETY PROGRAM TEST");
			obj.clickKnowMoreBtn();
			Assert.assertTrue(driver.getTitle().contains("Online Bus tickets"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("SAFETY PROGRAM TEST");
			throw new SkipException("SAFETY PROGRAM TEST, Test Case has been Skipped");
		}
		logger.info("Safety Program checked successfully");
	}
}
