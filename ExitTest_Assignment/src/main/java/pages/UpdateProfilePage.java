package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateProfilePage {

	WebDriver driver;

	public UpdateProfilePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "i-icon-profile")
	public WebElement user_icon;

	@FindBy(how = How.XPATH, using = "//*[@id='hc']/ul/li[3]")
	public WebElement myprofile_btn;

	@FindBy(how = How.ID, using = "Editbtn")
	public WebElement edit_btn;

	@FindBy(how = How.ID, using = "profile-displayName")
	public WebElement edit_name;

	@FindBy(how = How.ID, using = "male")
	public WebElement gender_option;

	@FindBy(how = How.ID, using = "Savebtn")
	public WebElement save_btn;

	public void clickUserIcon() {
		user_icon.click();
	}

	public void clickMyProfileBtn() {
		myprofile_btn.click();
	}

	public void clickEditButton() {
		edit_btn.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Savebtn")));
	}

	public void editName(String value) {
		edit_name.clear();
		edit_name.sendKeys(value);
	}

	public void chooseGender() {
		gender_option.click();
	}

	public void clickSaveBtn() throws InterruptedException {
		save_btn.click();
		Thread.sleep(3000);
	}
}
