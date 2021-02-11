package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchBusPage {

	WebDriver driver;

	public SearchBusPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "src")
	public WebElement source;

	@FindBy(how = How.XPATH, using = "//*[@id='search']/div/div[1]/div/ul/li")
	public WebElement select_src;

	@FindBy(how = How.ID, using = "dest")
	public WebElement destination;

	@FindBy(how = How.XPATH, using = "//*[@id='search']/div/div[2]/div/ul/li")
	public WebElement select_dest;

	@FindBy(how = How.XPATH, using = "//span[@class='fl icon-calendar_icon-new icon-onward-calendar icon']")
	public WebElement calender;

	@FindBy(how = How.XPATH, using = "//*[@id='rb-calendar_onward_cal']/table/tbody/tr[6]/td[7]")
	public WebElement date;

	@FindBy(how = How.XPATH, using = "//*[@id='search_btn']")
	public WebElement search_btn;

	@FindBy(how = How.XPATH, using = "//*[@id='fixer']/div/div/div[2]")
	public WebElement modify_btn;

	@FindBy(how = How.XPATH, using = "//button[@class=' button ms-btn']")
	public WebElement modify_search_btn;

	@FindBy(how = How.XPATH, using = "//input[@id='onward_cal']")
	public WebElement new_calender;

	@FindBy(how = How.XPATH, using = "//*[@id='rb-calmiddle']/ul[2]/li[26]/span")
	public WebElement new_date;

	@FindBy(how = How.XPATH, using = "//*[@id='filter-block']/div/div[2]/ul[1]/li[1]/span")
	public WebElement livetracker_filter;

	@FindBy(how = How.XPATH, using = "//*[@id='filter-block']/div/div[2]/ul[1]/li[3]/span")
	public WebElement resheduable_filter;

	@FindBy(how = How.XPATH, using = "//label[@for='dtAfter 6 pm']")
	public WebElement depart_time_filter;

	@FindBy(how = How.XPATH, using = "//span[@title='Track My Bus']")
	public WebElement amenities_filter;

	@FindBy(xpath = "//div[@class='close']")
	public WebElement icon_close;

	@FindBy(xpath = "//span[@class='g-button d-color fr']")
	public WebElement ok_btn;

	public void sourceLocation(String location) throws InterruptedException {
		Thread.sleep(3000);
		source.sendKeys(location);
		select_src.click();
		Thread.sleep(1000);
	}

	public void destinationLocation(String location) throws InterruptedException {
		destination.sendKeys(location);
		select_dest.click();
		Thread.sleep(1000);
	}

	public void clickCalender() throws InterruptedException {
		calender.click();
		Thread.sleep(1000);
	}

	public void chooseDate() throws InterruptedException {
		date.click();
		Thread.sleep(1000);
	}

	public void clickSearchBtn() throws InterruptedException {
		search_btn.click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='onward-modify-btn g-button clearfix fl']")));
	}

	public void clickModifyBtn() throws InterruptedException {
		modify_btn.click();
	}

	public void editDate() throws InterruptedException {
		new_calender.click();
		new_date.click();
		Thread.sleep(1000);
	}

	public void clickModifySearchBtn() throws InterruptedException {
		modify_search_btn.click();
	}

	public void applyFilters() throws InterruptedException {
		Thread.sleep(2000);
		icon_close.click();
		livetracker_filter.click();
		resheduable_filter.click();
		depart_time_filter.click();
		Thread.sleep(1000);
		amenities_filter.click();
		ok_btn.click();
	}

}
