package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utils.Screenshot;
import utils.Xls_Reader;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class BaseClass {

	public static WebDriver driver = null;
	public static Xls_Reader reader = null;
	public static ExtentReports extent = null;
	public static ExtentTest extentTest = null;

	static File file = new File("./Resources/PropertyFile/config.properties");
	public static FileInputStream fi = null;
	public static Properties prop = new Properties();
	static {
		try {
			fi = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		try {
			prop.load(fi);
		} catch (IOException el) {
			el.printStackTrace();

		}
		try {
			reader = new Xls_Reader(prop.getProperty("excel_path"));//integrating excel file
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public final static Logger logger = Logger.getLogger(BaseClass.class);

	public static void driverInitialize(String browser, String browserType) {
		if (browser.toLowerCase().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome_driver_path"));
			ChromeOptions options = new ChromeOptions();
			if (browserType.toLowerCase().equals("headless")) {// chrome headless mode
				options.addArguments(prop.getProperty("window_size"));
				options.addArguments("headless");
				options.addArguments(prop.getProperty("user_agent"));
				driver = new ChromeDriver(options);
			} else {
				driver = new ChromeDriver();
			}
		} else if (browser.toLowerCase().equals("ie")) {
			System.setProperty("webdriver.ie.driver", prop.getProperty("ie_driver_path")); // IE doesn't have support
																							// for Headless Mode
			driver = new InternetExplorerDriver();

		} else if (browser.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefox_driver_path"));
			FirefoxOptions options = new FirefoxOptions();
			if (browserType.toLowerCase().equals("headless")) {// firefox headless mode
				options.setHeadless(true);
				driver = new FirefoxDriver(options);
			} else {
				driver = new FirefoxDriver();
			}

		} else {
			System.out.println("Incompatible Browser Selection");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void openBrowser() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.info("Browser opened successfully");
	}

	@BeforeTest
	public static void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Nagarro");
		extent.addSystemInfo("User Name", "Adarsh Garg");
		extent.addSystemInfo("Environment", "QA");

	}

	@AfterTest
	public static void endReport() {
		extent.flush();
		extent.close();
	}

	@AfterMethod
	public static void getReport(ITestResult result) throws IOException, InterruptedException {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in
			// extent report

			String screenshotPath = Screenshot.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to add screenshot in extent
			Thread.sleep(1500);
			driver.quit();
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
			Thread.sleep(1500);
			driver.quit();
		}

		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
		logger.info("Report Generated Successfully");
	}

}
