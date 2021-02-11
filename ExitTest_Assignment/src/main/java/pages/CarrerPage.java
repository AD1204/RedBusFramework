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

public class CarrerPage {

	WebDriver driver;

	public CarrerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//*[@id='rh_footer']/div[3]/div/div/div[1]/div[1]/a[7]")
	public WebElement career_option;

	@FindBy(how = How.ID, using = "scroll-aHref-1")
	public WebElement our_crew;

	@FindBy(how = How.ID, using = "scroll-aHref-2")
	public WebElement life_at_redbus;

	@FindBy(how = How.XPATH, using = "//*[@id='scroll-aHref-3']")
	public WebElement jobs;

	public void clickCarrerOption() throws InterruptedException {
		Thread.sleep(10000);
		career_option.click();
	}

	public void switchWindow() throws InterruptedException {
		String parentwindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		int count = allWindows.size();
		for (String child : allWindows) {
			if (!parentwindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				WebDriverWait wait = new WebDriverWait(driver, 30);
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("scroll-aHref-1")));
				our_crew.click();
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				life_at_redbus.click();
				Thread.sleep(2000);
				jobs.click();
			}
		}
	}
}
