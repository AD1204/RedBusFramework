package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.LoginPage;

public class RedBusLoginTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(RedBusLoginTest.class);

	@Test
	public void validLogin() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "validLogin").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			LoginPage obj = new LoginPage(driver);
			extentTest = extent.startTest("VALID LOGIN");
			obj.clickUser();
			obj.clickSignIn();
			obj.moveIframe();
			obj.clickFacebookOption();
			String user_id = reader.getRowTestData("Sheet1", "validLogin").get("email_id");
			String pass = reader.getRowTestData("Sheet1", "validLogin").get("password");
			obj.SwitchWindow(user_id, pass);
			Assert.assertTrue(driver.getTitle().contains("Book Bus Travels"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("VALID LOGIN");
			throw new SkipException("VALID LOGIN, Test Case has been Skipped");
		}
		logger.info("ValidLogin done successfully ");
	}
	

	@Test
	public void invalidLogin() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "invalidLogin").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			LoginPage obj = new LoginPage(driver);
			extentTest = extent.startTest("INVALID LOGIN");
			obj.clickUser();
			obj.clickSignIn();
			obj.moveIframe();
			obj.clickFacebookOption();
			String user_id = reader.getRowTestData("Sheet1", "invalidLogin").get("email_id");
			String pass = reader.getRowTestData("Sheet1", "invalidLogin").get("password");
			obj.handleInvalidLogin(user_id, pass);
			Assert.assertFalse(driver.getTitle().contains("Facebook"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("INVALID LOGIN");
			throw new SkipException("INVALID LOGIN, Test Case has been Skipped");
		}
		logger.info("InvalidLogin successfully");
	}
}
