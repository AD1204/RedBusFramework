package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.BusOperatorsPage;

public class BusOperatorsTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(BusOperatorsTest.class);

	@Test
	public void searchBusesWithTopOperator() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "searchBusesWithTopOperator").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			BusOperatorsPage obj = new BusOperatorsPage(driver);
			extentTest = extent.startTest("SEARCH WITH OPERATOR");
			obj.clickOneTopOperator();
			obj.selectBusRoute();
			obj.clickCloseIcon();
			Assert.assertTrue(driver.getTitle().contains("Pune to Bangalore"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("SEARCH WITH OPERATOR");
			throw new SkipException("Search Buses With Top Operator, Test Case has been Skipped");
		}
		logger.info("Buses found successfully");
	}
	

	@Test
	public void findAllBusOperators() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "findAllBusOperators").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			BusOperatorsPage obj = new BusOperatorsPage(driver);
			extentTest = extent.startTest("FIND ALL BUS OPERATOR");
			obj.clickAllOperators();
			obj.switchWindow();
			Assert.assertTrue(driver.getTitle().contains("Abhimanyu"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("FIND ALL BUS OPERATOR");
			throw new SkipException("FIND ALL BUS OPERATOR, Test Case has been Skipped");
		}
		logger.info("Bus operators found successfully");
	}
}
