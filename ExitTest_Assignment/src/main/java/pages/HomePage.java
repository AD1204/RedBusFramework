package pages;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//li[@id='signOutAllLink']")
	public WebElement signout_btn;

	@FindBy(how = How.ID, using = "i-icon-profile")
	public WebElement user_icon;

	@FindBy(how = How.ID, using = "signOutLink")
	public WebElement simple_signout;

	@FindBy(how = How.XPATH, using = "//a[text()='T & C']")
	public WebElement terms_conditions;

	@FindBy(how = How.XPATH, using = "//*[@id='mBWrapper']/p/a/b")
	public WebElement passenger_guidelines;

	public void clickSignoutBtn() {
		signout_btn.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='manageHeaderLbl']")));
	}

	public void clickUserIcon() {
		user_icon.click();
	}

	public void clickSimpleSignOut() {
		simple_signout.click();
	}

	public void clickTermsAndConditions() throws InterruptedException {
		Thread.sleep(10000);
		terms_conditions.click();
	}

	public void switchWindow() {
		String parentwindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		int count = allWindows.size();
		for (String child : allWindows) {
			if (!parentwindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				WebDriverWait wait = new WebDriverWait(driver, 30);
				WebElement element = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mBWrapper']/p/a/b")));
				passenger_guidelines.click();
			}
		}
	}
}
