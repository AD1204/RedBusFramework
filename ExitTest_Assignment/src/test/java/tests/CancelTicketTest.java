package tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.TicketPage;

public class CancelTicketTest extends BaseClass {
	public final static Logger logger = Logger.getLogger(CancelTicketTest.class);
	
	@Test
	public void checkTicketCancel() throws InterruptedException {
		
		String status = reader.getRowTestData("Sheet1", "checkTicketCancel").get("TestExecution(yes/no)");//getting test case status from excel file
		if (status.toLowerCase().trim().equals("yes")) {
			BaseClass.driverInitialize(prop.getProperty("browser"), prop.getProperty("browser_type"));
			BaseClass.openBrowser();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			TicketPage obj = new TicketPage(driver);
			extentTest = extent.startTest("CHECK CANCEL TICKET");
			String ticket_email = reader.getRowTestData("Sheet1", "checkTicketCancel").get("email_id");
			String ticket_no = reader.getRowTestData("Sheet1", "checkTicketCancel").get("Ticket_No");
			obj.clickManageBookingBtn();
			obj.clickCanceIcon();
			obj.enterCancelTicketEmail(ticket_email);
			obj.enterCancelTicketNo(ticket_no);
			obj.clickSelectPassenger();
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='root']/div/div[3]/div")).isDisplayed());
	}
		else {// having test status(no) from excel file , then skipping it 
			extentTest = extent.startTest("CHECK CANCEL TICKET");
			throw new SkipException("CHECK CANCEL TICKET, Test Case has been Skipped");
		}
		logger.info("Ticket Cancellation checked successfully ");
	}
}
