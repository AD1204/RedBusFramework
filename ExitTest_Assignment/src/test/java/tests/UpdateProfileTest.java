package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.UpdateProfilePage;

public class UpdateProfileTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(UpdateProfileTest.class);

	@Test
	public void validUpdateProfile() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "validUpdateProfile").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			LoginPage obj = new LoginPage(driver);
			UpdateProfilePage obj2 = new UpdateProfilePage(driver);
			extentTest = extent.startTest("VALID UPDATE");
			String user_id = reader.getRowTestData("Sheet1", "validUpdateProfile").get("email_id");
			String pass = reader.getRowTestData("Sheet1", "validUpdateProfile").get("password");
			String user_name = reader.getRowTestData("Sheet1", "validUpdateProfile").get("User_name");
			obj.clickUser();
			obj.clickSignIn();
			obj.moveIframe();
			obj.clickFacebookOption();
			obj.SwitchWindow(user_id, pass);
			obj2.clickUserIcon();
			obj2.clickMyProfileBtn();
			obj2.clickEditButton();
			obj2.editName(user_name);
			obj2.chooseGender();
			obj2.clickSaveBtn();
			Assert.assertTrue(driver.findElement(By.id("nf_success")).isDisplayed());
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("VALID UPDATE");
			throw new SkipException("VALID UPDATE, Test Case has been Skipped");
		}
		logger.info("Valid Update done successfully");
	}

	@Test
	public void invalidUpdateProfile() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "invalidUpdateProfile").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			LoginPage obj = new LoginPage(driver);
			UpdateProfilePage obj2 = new UpdateProfilePage(driver);
			extentTest = extent.startTest("INVALID UPDATE");
			String user_id = reader.getRowTestData("Sheet1", "invalidUpdateProfile").get("email_id");
			String pass = reader.getRowTestData("Sheet1", "invalidUpdateProfile").get("password");
			String user_name = reader.getRowTestData("Sheet1", "invalidUpdateProfile").get("User_name");
			obj.clickUser();
			obj.clickSignIn();
			obj.moveIframe();
			obj.clickFacebookOption();
			obj.SwitchWindow(user_id, pass);
			obj2.clickUserIcon();
			obj2.clickMyProfileBtn();
			obj2.clickEditButton();
			obj2.editName(user_name);
			obj2.chooseGender();
			obj2.clickSaveBtn();
			Assert.assertTrue(driver.findElement(By.id("nf_success")).isDisplayed());
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("INVALID UPDATE");
			throw new SkipException("INVALID UPDATE, Test Case has been Skipped");
		}
		logger.info("Invalid update done successfully");
	}
}
