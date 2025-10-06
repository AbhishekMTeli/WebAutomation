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
import adminPages.BecomeUserPage;
import commonPages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class BaseClass {

	protected static ExtentReports extentReports;
	protected ExtentTest extentTest;
	protected AdminDashBoardPage adminDashBoardPage;
	protected BecomeUserPage becomeUserPage;
	protected LoginPage loginPage;

	// Get driver from DriverFactory for thread safety
	protected WebDriver getDriver() {
		return DriverFactory.getDriver();
	}

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
		
		
		String remoteConfig = ConfigReader.get("remote");
	    boolean isRemote = Boolean.parseBoolean(remoteConfig);

	    if (isRemote) {
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setBrowserName(browser);
	        String gridUrl = ConfigReader.get("gridUrl");
	        RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(gridUrl), capabilities);
	        remoteDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        remoteDriver.manage().window().maximize();
	        DriverFactory.setDriver(remoteDriver);
	    } else {
	        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
	        DriverFactory.initDriver(browser, headless);
	    }

		getDriver().get(url);

		extentTest = extentReports
				.createTest(getClass().getSimpleName() + " - ThreadId: " + Thread.currentThread().getId());
		extentTest.info("Started test on browser: " + browser);

		doStandardLoginAndUserSwitch();
	}

	protected void doStandardLoginAndUserSwitch() {
		loginPage = new LoginPage(getDriver());
		loginPage.clickTemporaryUserLink();
		String userId = ConfigReader.get("userName");
		String password = ConfigReader.get("password");
		loginPage.login(userId, password);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = utils.SeleniumUtils.captureScreenshot(getDriver(), result.getName());
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
