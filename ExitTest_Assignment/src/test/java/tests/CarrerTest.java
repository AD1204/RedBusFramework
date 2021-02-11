package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.CarrerPage;
import pages.HomePage;

public class CarrerTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(CarrerTest.class);

	@Test
	public void showCarrerInformation() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "showCarrerInformation").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			CarrerPage obj = new CarrerPage(driver);
			extentTest = extent.startTest("CAREER INFO");
			obj.clickCarrerOption();
			obj.switchWindow();
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='onBoardContainer']/h1")).isDisplayed());
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("CAREER INFO");
			throw new SkipException("CAREER INFO, Test Case has been Skipped");
		}
		logger.info("Carrer Info. found successfully");
	}
}
