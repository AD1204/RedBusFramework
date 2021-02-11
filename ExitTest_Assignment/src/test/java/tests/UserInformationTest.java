package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.UserInformationPage;

public class UserInformationTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(UserInformationTest.class);

	@Test
	public void checkTrips() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "checkTrips").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			LoginPage obj = new LoginPage(driver);
			UserInformationPage obj2 = new UserInformationPage(driver);
			extentTest = extent.startTest("CHECK TRIPS");
			String user_id = reader.getRowTestData("Sheet1", "checkTrips").get("email_id");
			String pass = reader.getRowTestData("Sheet1", "checkTrips").get("password");
			obj.clickUser();
			obj.clickSignIn();
			obj.moveIframe();
			obj.clickFacebookOption();
			obj.SwitchWindow(user_id, pass);
			obj2.clickUserIcon();
			obj2.clickTripIcon();
			Assert.assertTrue(driver.getTitle().contains("Online Bus tickets"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("CHECK TRIPS");
			throw new SkipException("CHECK TRIPS, Test Case has been Skipped");
		}
		logger.info("Trips opened successfully");
	}

	@Test
	public void checkWalletBalance() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "checkWalletBalance").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			LoginPage obj = new LoginPage(driver);
			UserInformationPage obj2 = new UserInformationPage(driver);
			extentTest = extent.startTest("CHECK WALLET");
			String user_id = reader.getRowTestData("Sheet1", "checkWalletBalance").get("email_id");
			String pass = reader.getRowTestData("Sheet1", "checkWalletBalance").get("password");
			obj.clickUser();
			obj.clickSignIn();
			obj.moveIframe();
			obj.clickFacebookOption();
			obj.SwitchWindow(user_id, pass);
			obj2.clickUserIcon();
			obj2.clickWalletIcon();
			Assert.assertTrue(driver.getTitle().contains("Online Bus tickets"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("CHECK WALLET");
			throw new SkipException("CHECK WALLET, Test Case has been Skipped");
		}
		logger.info("Wallet opened successfully");
	}
}
