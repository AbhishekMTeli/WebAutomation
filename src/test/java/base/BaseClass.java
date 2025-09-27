package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import adminPages.AdminDashBoardPage;
import commonPages.BecomeUserPage;
import commonPages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class BaseClass {

	protected WebDriver driver;
	protected static ExtentReports extentReports;
	protected ExtentTest extentTest;
	protected AdminDashBoardPage adminDashBoardPage;
	protected BecomeUserPage becomeUserPage;
	protected LoginPage loginPage;

	@BeforeSuite
	public void setUpExtent() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
	}

	@Parameters({ "browser", "remote" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, @Optional("false") String remote)
			throws MalformedURLException {
		ConfigReader.loadProperties();

		String url = ConfigReader.get("baseUrl");

		if (Boolean.parseBoolean(remote)) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(browser);
			String gridUrl = ConfigReader.get("gridUrl");
			driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
		} else {
			driver = DriverFactory.initDriver(browser);
		}

		driver.get(url);

		extentTest = extentReports.createTest(getClass().getSimpleName() + " - " + Thread.currentThread().getId());
		extentTest.info("Started test on browser: " + browser);

		doStandardLoginAndUserSwitch();
	}

	/**
	 * Centralizes the login and Become User flow.
	 */
	protected void doStandardLoginAndUserSwitch() {
		// 1. Login Page
		loginPage = new LoginPage(driver);
		loginPage.clickTemporaryUserLink();
		String userId = ConfigReader.get("userName");
		String password = ConfigReader.get("password");
		loginPage.login(userId, password);

		// 2. Become User navigation (if required for scenario)
		adminDashBoardPage = new AdminDashBoardPage(driver);
		adminDashBoardPage.clickBecomeUserTab();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = utils.SeleniumUtils.captureScreenshot(driver, result.getName());
			extentTest.fail(result.getThrowable());
			extentTest.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.skip("Test skipped: " + result.getName());
		} else {
			extentTest.pass("Test passed: " + result.getName());
		}

		DriverFactory.quitDriver();
	}

	@AfterSuite
	public void tearDownExtent() {
		if (extentReports != null) {
			extentReports.flush();
		}
	}
}
