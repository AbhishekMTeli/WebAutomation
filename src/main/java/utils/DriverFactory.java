package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	// Enhance browser initialization with options support and extensibility
	public static WebDriver initDriver(String browser) {
		WebDriver webDriver;
		switch (browser != null ? browser.toLowerCase() : "") {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
				chromeOptions.addArguments("--headless=new");
			}
			webDriver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
				firefoxOptions.addArguments("-headless");
			}
			webDriver = new FirefoxDriver(firefoxOptions);
			break;
		default:
			throw new IllegalArgumentException("Invalid browser type: " + browser);
		}
		webDriver.manage().window().maximize();
		driver.set(webDriver);
		return webDriver;
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		WebDriver webDriver = getDriver();
		if (webDriver != null) {
			webDriver.quit();
			driver.remove();
		}
	}
}
