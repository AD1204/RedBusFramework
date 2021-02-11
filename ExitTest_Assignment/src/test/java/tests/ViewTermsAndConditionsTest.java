package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.HomePage;

public class ViewTermsAndConditionsTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(ViewTermsAndConditionsTest.class);

	@Test
	public void showTermsAndConditions() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "showTermsAndConditions").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			HomePage obj = new HomePage(driver);
			extentTest = extent.startTest("SHOW TERMS&CONDITIONS");
			obj.clickTermsAndConditions();
			obj.switchWindow();
			Assert.assertTrue(driver.getTitle().contains("Passenger"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("SHOW TERMS&CONDITIONS");
			throw new SkipException("SHOW TERMS&CONDITIONS, Test Case has been Skipped");
		}
		logger.info("Terms&Conditions opened successfully");
	}
}
