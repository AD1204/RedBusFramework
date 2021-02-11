package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TicketPage {

	WebDriver driver;

	public TicketPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='manageHeaderLbl']")
	public WebElement manage_booking_btn;

	@FindBy(how = How.CSS, using = "#hmb > div:nth-child(2) > ul > li:nth-child(4) > span > span")
	public WebElement show_my_ticket;

	@FindBy(how = How.CSS, using = "#hmb > div:nth-child(2) > ul > li:nth-child(3) > span > span")
	public WebElement resheduable_option;

	@FindBy(how = How.ID, using = "searchTicketTIN")
	public WebElement ticket_no_box;

	@FindBy(how = How.ID, using = "searchTicketEmail")
	public WebElement email_box;

	@FindBy(how = How.ID, using = "ticketSearch")
	public WebElement submit_btn;

	@FindBy(how = How.ID, using = "searchTicket")
	public WebElement re_ticket_no_box;

	@FindBy(how = How.ID, using = "searchEmail")
	public WebElement re_email_box;
	
	@FindBy(how = How.XPATH, using = "//*[@id='hmb']/div[2]/ul/li[2]/span")
	public WebElement cancel_icon;
	
	@FindBy(how = How.XPATH, using = "//*[@id='root']/div/div[2]/div[1]/div[1]/div/input")
    public WebElement c_ticket_box;

 

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    public WebElement c_email_box;

 

    @FindBy(how = How.XPATH, using = "//*[@id='root']/div/div[2]/div[2]/div")
    public WebElement Select_Passenger_Icon;

	public void clickManageBookingBtn() throws InterruptedException {
		manage_booking_btn.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='hmb']/div[2]/ul/li[5]/span/span")));
		// Thread.sleep(2000);
	}

	public void clickShowTicket() {
		show_my_ticket.click();
	}
	
	public void clickCanceIcon() {
		cancel_icon.click();
	}

	public void enterTicketNo(String value) {
		ticket_no_box.sendKeys(value);
	}

	public void enterEmail(String value) {
		email_box.sendKeys(value);
	}

	public void clickSubmitBtn() {
		submit_btn.click();
	}

	public void clickResheduableBtn() {
		resheduable_option.click();
	}

	public void enterReTicketNo(String value) {
		re_ticket_no_box.sendKeys(value);
	}

	public void enterReTicketEmail(String value) {
		re_email_box.sendKeys(value);
	}
	
	public void enterCancelTicketNo(String value) throws InterruptedException {
		c_ticket_box.sendKeys(value);
		Thread.sleep(1000);
	}

	public void enterCancelTicketEmail(String value) throws InterruptedException {
		c_email_box.sendKeys(value);
		Thread.sleep(1000);
	}
	
	public void clickSelectPassenger() throws InterruptedException {
		 Select_Passenger_Icon.click();
		 Thread.sleep(2000);
	}
	
}
