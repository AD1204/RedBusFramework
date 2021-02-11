package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.TicketPage;

public class RescheduleTicketTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(RescheduleTicketTest.class);

	@Test
	public void checkResheduleWithWrongInfo() throws InterruptedException {

		String status = reader.getRowTestData("Sheet1", "checkResheduleWithWrongInfo").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			TicketPage obj = new TicketPage(driver);
			extentTest = extent.startTest("INVALID RESCHEDULE");
			obj.clickManageBookingBtn();
			obj.clickResheduableBtn();
			String ticket_email = reader.getRowTestData("Sheet1", "checkResheduleWithWrongInfo").get("email_id");
			String ticket_no = reader.getRowTestData("Sheet1", "checkResheduleWithWrongInfo").get("Ticket_No");
			obj.enterReTicketNo(ticket_no);
			obj.enterReTicketEmail(ticket_email);
			obj.clickSubmitBtn();
			Thread.sleep(2000);
			Assert.assertFalse(driver.findElement(By.id("nf_error")).isDisplayed());
		} else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("INVALID RESCHEDULE");
			throw new SkipException("INVALID RESCHEDULE, Test Case has been Skipped");
		}
		logger.info("Ticket Reschedule checked successfully ");
	}
}
