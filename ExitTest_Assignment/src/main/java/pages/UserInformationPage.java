package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserInformationPage {

	WebDriver driver;

	public UserInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "i-icon-profile")
	public WebElement user_icon;

	@FindBy(how = How.XPATH, using = "//li[@class='personalization-link-blocks config-options']")
	public WebElement my_trip_icon;

	@FindBy(how = How.XPATH, using = "//li[text()='Wallets/Cards']")
	public WebElement wallet_icon;

	public void clickUserIcon() {
		user_icon.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='signOutAllLink']")));
	}

	public void clickTripIcon() {
		my_trip_icon.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='Upcoming']")));
	}

	public void clickWalletIcon() {
		wallet_icon.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Wallet']")));
	}
}
