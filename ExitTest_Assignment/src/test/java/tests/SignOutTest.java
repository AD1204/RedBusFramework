package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

public class SignOutTest extends BaseClass {

	public final static Logger logger = Logger.getLogger(SignOutTest.class);

	@Test
	public void signOutFromAllDevice() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "signOutFromAllDevice").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			LoginPage obj = new LoginPage(driver);
			HomePage obj2 = new HomePage(driver);
			extentTest = extent.startTest("SIGNOUT FROM ALL DEVICES");
			String user_id = reader.getRowTestData("Sheet1", "signOutFromAllDevice").get("email_id");
			String pass = reader.getRowTestData("Sheet1", "signOutFromAllDevice").get("password");
			obj.clickUser();
			obj.clickSignIn();
			obj.moveIframe();
			obj.clickFacebookOption();
			obj.SwitchWindow(user_id, pass);
			obj2.clickUserIcon();
			obj2.clickSignoutBtn();
			Assert.assertTrue(driver.getTitle().contains("Book Bus Travels"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("SIGNOUT FROM ALL DEVICES");
			throw new SkipException("SIGNOUT FROM ALL DEVICES");
		}
		logger.info("SignOut from all devices done successfully");
	}

	@Test
	public void signOut() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "signOut").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			LoginPage obj = new LoginPage(driver);
			HomePage obj2 = new HomePage(driver);
			extentTest = extent.startTest("SIGNOUT");
			String user_id = reader.getRowTestData("Sheet1", "signOut").get("email_id");
			String pass = reader.getRowTestData("Sheet1", "signOut").get("password");
			obj.clickUser();
			obj.clickSignIn();
			obj.moveIframe();
			obj.clickFacebookOption();
			obj.SwitchWindow(user_id, pass);
			obj2.clickUserIcon();
			obj2.clickSimpleSignOut();
			Assert.assertTrue(driver.getTitle().contains("Book Bus Travels"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("SIGNOUT");
			throw new SkipException("SIGNOUT");
		}
		logger.info("SignOut done successfully");
	}
}
