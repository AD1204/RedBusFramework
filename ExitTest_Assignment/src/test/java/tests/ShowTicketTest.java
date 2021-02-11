package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.TicketPage;

public class ShowTicketTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(ShowTicketTest.class);

	@Test
	public void showTicketWithCorrectInfo() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "showTicketWithCorrectInfo").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			TicketPage obj2 = new TicketPage(driver);
			extentTest = extent.startTest("VALID TICKET STATUS");
			String ticket_email = reader.getRowTestData("Sheet1", "showTicketWithCorrectInfo").get("email_id");
			String ticket_no = reader.getRowTestData("Sheet1", "showTicketWithCorrectInfo").get("Ticket_No");
			obj2.clickManageBookingBtn();
			obj2.clickShowTicket();
			obj2.enterTicketNo(ticket_no);
			obj2.enterEmail(ticket_email);
			obj2.clickSubmitBtn();
			Assert.assertTrue(driver
					.findElement(By.xpath("//*[@id='mBWrapper']/table/tbody/tr/td/table[1]/tbody/tr[2]/td[1]/div/img"))
					.isDisplayed());
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("VALID TICKET STATUS");
			throw new SkipException("VALID TICKET STATUS, Test Case has been Skipped");
		}
		logger.info("Valid Ticket viewed successfully");
	}

	@Test
	public void neglectTicketWithWrongInfo() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "neglectTicketWithWrongInfo").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			TicketPage obj = new TicketPage(driver);
			extentTest = extent.startTest("INVALID TICKET STATUS");
			String ticket_email = reader.getRowTestData("Sheet1", "neglectTicketWithWrongInfo").get("email_id");
			String ticket_no = reader.getRowTestData("Sheet1", "neglectTicketWithWrongInfo").get("Ticket_No");
			obj.clickManageBookingBtn();
			obj.clickShowTicket();
			obj.enterTicketNo(ticket_no);
			obj.enterEmail(ticket_email);
			obj.clickSubmitBtn();
			Assert.assertFalse(driver.findElement(By.id("nf_error")).isDisplayed());
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("INVALID TICKET STATUS");
			throw new SkipException("INVALID TICKET STATUS, Test Case has been Skipped");
		}
		logger.info("Invalid ticket checked successfully");
	}

}
