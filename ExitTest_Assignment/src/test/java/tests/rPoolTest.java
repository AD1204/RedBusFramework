package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.RPoolPage;

public class rPoolTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(rPoolTest.class);

	@Test
	public void showRpoolInfo() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "showRpoolInfo").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			RPoolPage obj = new RPoolPage(driver);
			extentTest = extent.startTest("rPOOL TEST");
			obj.clickRpoolBtn();
			obj.findRide();
			obj.offerRide();
			Assert.assertTrue(driver
					.findElement(
							By.xpath("//*[@id='howItWorks']/div/div/div[2]/div[5]/div/div/div[1]/ul/li[1]/span[2]"))
					.isDisplayed());
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("rPOOL TEST");
			throw new SkipException("rPOOL TEST, Test Case has been Skipped");
		}
		logger.info("rPool checked successfully ");
	}
}
