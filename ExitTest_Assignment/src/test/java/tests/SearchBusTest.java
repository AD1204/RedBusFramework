package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.SearchBusPage;

public class SearchBusTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(SearchBusTest.class);

	@Test
	public void searchBuses() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "searchBuses").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			SearchBusPage obj = new SearchBusPage(driver);
			extentTest = extent.startTest("SEARCH BUS TEST");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String source = reader.getRowTestData("Sheet1", "searchBuses").get("Source_Location");
			String destination = reader.getRowTestData("Sheet1", "searchBuses").get("Dest_Location");
			obj.sourceLocation(source);
			obj.destinationLocation(destination);
			obj.clickCalender();
			obj.chooseDate();
			obj.clickSearchBtn();
			Assert.assertTrue(driver.getTitle().contains("Search Bus Tickets"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("SEARCH BUS TEST");
			throw new SkipException("SEARCH BUS TEST, Test Case has been Skipped");
		}
		logger.info("Search Bus done successfully ");
	}

	@Test
	public void modifySearchBuses() throws InterruptedException {//getting test case status from excel file

		String status = reader.getRowTestData("Sheet1", "modifySearchBuses").get("TestExecution(yes/no)");
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			SearchBusPage obj = new SearchBusPage(driver);
			extentTest = extent.startTest("MODIFY SEARCH BUS TEST");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String source = reader.getRowTestData("Sheet1", "modifySearchBuses").get("Source_Location");
			String destination = reader.getRowTestData("Sheet1", "modifySearchBuses").get("Dest_Location");
			obj.sourceLocation(source);
			obj.destinationLocation(destination);
			obj.clickCalender();
			obj.chooseDate();
			obj.clickSearchBtn();
			obj.clickModifyBtn();
			obj.editDate();
			obj.clickModifySearchBtn();
			Assert.assertTrue(driver.getTitle().contains("Search Bus Tickets"));
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("MODIFY SEARCH BUS TEST");
			throw new SkipException("MODIFY SEARCH BUS TEST, Test Case has been Skipped");
		}
		logger.info("Search Bus modified successfully ");
	}

	@Test
	public void searchBusesWithFilters() throws InterruptedException {//getting test case status from excel file

		String status = reader.getRowTestData("Sheet1", "searchBusesWithFilters").get("TestExecution(yes/no)");
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			SearchBusPage obj = new SearchBusPage(driver);
			extentTest = extent.startTest("SEARCH BUS WITH FILTERS");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String source = reader.getRowTestData("Sheet1", "searchBusesWithFilters").get("Source_Location");
			String destination = reader.getRowTestData("Sheet1", "searchBusesWithFilters").get("Dest_Location");
			obj.sourceLocation(source);
			obj.destinationLocation(destination);
			obj.clickCalender();
			obj.chooseDate();
			obj.clickSearchBtn();
			obj.applyFilters();
			Assert.assertTrue(driver.findElement(By.xpath("//li[@title='Track My Bus']")).isDisplayed());
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("SEARCH BUS WITH FILTERS");
			throw new SkipException("SEARCH BUS WITH FILTERS, Test Case has been Skipped");
		}
		logger.info("Search bus with filters done successfully ");
	}
}
