package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RPoolPage {

	WebDriver driver;

	public RPoolPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "cars")
	public WebElement rpool_btn;

	@FindBy(how = How.XPATH, using = "//*[@id='howItWorks']/div/div/div[2]/div[1]/div[2]")
	public WebElement requestride;

	@FindBy(how = How.XPATH, using = "//*[@id='howItWorks']/div/div/div[2]/div[1]/div[3]")
	public WebElement chooseride;

	@FindBy(how = How.XPATH, using = "//*[@id='howItWorks']/div/div/div[2]/div[1]/div[4]")
	public WebElement pay_detail;

	@FindBy(how = How.XPATH, using = "//*[@id='howItWorks']/div/div/div[1]/span[2]")
	public WebElement offerride_tab;

	@FindBy(how = How.XPATH, using = "//*[@id='howItWorks']/div/div/div[2]/div[1]/div[2]")
	public WebElement offerride_Btn;

	@FindBy(how = How.XPATH, using = "//*[@id='howItWorks']/div/div/div[2]/div[1]/div[3]")
	public WebElement chooseride_btn;

	@FindBy(how = How.XPATH, using = "//*[@id='howItWorks']/div/div/div[2]/div[1]/div[4]")
	public WebElement getpoints;

	public void clickRpoolBtn() throws InterruptedException {
		rpool_btn.click();
		Thread.sleep(2000);
	}

	public void findRide() throws InterruptedException {
		requestride.click();
		Thread.sleep(1000);
		chooseride.click();
		Thread.sleep(1000);
		pay_detail.click();
		Thread.sleep(1000);
	}

	public void offerRide() throws InterruptedException {
		offerride_tab.click();
		Thread.sleep(1000);
		offerride_Btn.click();
		Thread.sleep(1000);
		chooseride_btn.click();
		getpoints.click();
		Thread.sleep(2000);
	}

}
