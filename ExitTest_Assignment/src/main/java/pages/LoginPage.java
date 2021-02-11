package pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "sign-in-icon-down")
	public WebElement user;

	@FindBy(how = How.ID, using = "hc")
	public WebElement signin_button;

	@FindBy(how = How.CLASS_NAME, using = "modalIframe")
	public WebElement iframe;

	@FindBy(how = How.ID, using = "newFbId")
	public WebElement facebookBtn;

	@FindBy(how = How.ID, using = "email")
	public WebElement facebookEmail;

	@FindBy(how = How.ID, using = "pass")
	public WebElement facebookPass;

	@FindBy(how = How.ID, using = "loginbutton")
	public WebElement LoginBtn;

	@FindBy(how = How.XPATH, using = "//i[@class='icon-close']")
	public WebElement close_icon;

	public void clickUser() {
		user.click();
	}

	public void clickSignIn() throws InterruptedException {
		signin_button.click();
		Thread.sleep(5000);
	}

	public void moveIframe() throws InterruptedException {
		driver.switchTo().frame(iframe);
	}

	public void clickFacebookOption() {
		facebookBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Terms & Conditions']")));
	}

	public void enterValidFacebookCredentials(String mail, String password) {
		facebookEmail.sendKeys(mail);
		facebookPass.sendKeys(password);
	}

	public void enterInvalidFacebookCredentials(String mail, String password) {
		facebookEmail.sendKeys(mail);
		facebookPass.sendKeys(password);
	}

	public void SwitchWindow(String mail, String pass) throws InterruptedException {

		String parentwindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		int count = allWindows.size();
		for (String child : allWindows) {
			if (!parentwindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				driver.manage().window().maximize();
				WebDriverWait wait = new WebDriverWait(driver, 30);
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
				enterValidFacebookCredentials(mail, pass);
				LoginBtn.click();
			}
		}
		Thread.sleep(5000);
		driver.switchTo().window(parentwindow);
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='manageHeaderLbl']")));
	}

	public void handleInvalidLogin(String mail, String pass) throws InterruptedException {
		String parentwindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		int count = allWindows.size();
		for (String child : allWindows) {
			if (!parentwindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				driver.manage().window().maximize();
				WebDriverWait wait = new WebDriverWait(driver, 30);
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
				enterInvalidFacebookCredentials(mail, pass);
				LoginBtn.click();
			}
		}
		Thread.sleep(5000);
	}

}
