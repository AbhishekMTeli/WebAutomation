package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {
	// Wait for element visibility
	public static void waitForVisibility(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
	}

	// Wait for element to be clickable
	public static void waitForClickability(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
	}

	// Wait for an element to be present (by)
	public static void waitForPresence(WebDriver driver, By locator, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// Click element with wait
	public static void click(WebDriver driver, WebElement element, int timeout) {
		waitForClickability(driver, element, timeout);
		element.click();
	}

	// Type text with wait
	public static void type(WebDriver driver, WebElement element, String text, int timeout) {
		waitForVisibility(driver, element, timeout);
		element.clear();
		element.sendKeys(text);
	}

	public static void scrollToTopOfPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
	}

	// Get text from a list of elements using Java Streams
	public static List<String> getTexts(List<WebElement> elements) {
		return elements.stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public static String getText(WebElement element) {
		return element.getText().replaceAll("\\s+", " ").trim();
	}

	// Click the first element matching a predicate using Java Streams
	public static void clickFirstMatching(List<WebElement> elements, Predicate<WebElement> predicate) {
		Optional<WebElement> match = elements.stream().filter(predicate).findFirst();
		match.ifPresent(WebElement::click);
	}

	// Safe find element by
	public static Optional<WebElement> safeFindElement(WebDriver driver, By locator) {
		try {
			return Optional.of(driver.findElement(locator));
		} catch (NoSuchElementException e) {
			return Optional.empty();
		}
	}

	// Take screenshot and return file path
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dest = "screenshots/" + screenshotName + "_" + System.currentTimeMillis() + ".png";
		try {
			Files.createDirectories(new File("screenshots").toPath());
			Files.copy(src.toPath(), new File(dest).toPath());
			return dest;
		} catch (IOException e) {
			throw new RuntimeException("Failed to save screenshot", e);
		}
	}

	// Handle JavaScript click (for stubborn elements)
	public static void jsClick(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	// Wait for alert and accept
	public static void acceptAlert(WebDriver driver, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	// Scroll to element by visible text
	public static void scrollToElementByVisibleText(WebDriver driver, String visibleText) {
		int maxScrolls = 10;
		int scrollCount = 0;
		String xpath = "//*[normalize-space()='" + visibleText + "']";

		while (scrollCount < maxScrolls) {
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			if (!elements.isEmpty()) {
				WebElement element = elements.get(0);
				((JavascriptExecutor) driver)
						.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", element);
				return;
			}
			// Incremental scroll (recommended for dynamic or lazy loading UIs)
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, window.innerHeight / 2);");
			scrollCount++;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException("Interrupted during scrolling", e);
			}
		}
		throw new RuntimeException(
				"Element with visible text '" + visibleText + "' not found after " + maxScrolls + " scrolls.");
	}

	// Scroll to end of page
	public static void scrollToEndOfPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	// Common dropdown utility methods

	// Select dropdown option by visible text
	public static void selectDropdownByVisibleText(WebDriver driver, WebElement dropdownElement, String visibleText,
			int timeout) throws InterruptedException {
		waitForVisibility(driver, dropdownElement, timeout);
		Select select = new Select(dropdownElement);
		Thread.sleep(500);
		// Optionally: wait for the option to be present as well
		By optionLocator = By.xpath(".//option[normalize-space(.)='" + visibleText + "']");
		new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.presenceOfNestedElementLocatedBy(dropdownElement, optionLocator));
		select.selectByVisibleText(visibleText);
	}

	// Select dropdown option by value attribute
	public static void selectDropdownByValue(WebDriver driver, WebElement dropdownElement, String value, int timeout) {
		waitForVisibility(driver, dropdownElement, timeout);
		Select select = new Select(dropdownElement);
		select.selectByValue(value);
	}

	// Select dropdown option by index
	public static void selectDropdownByIndex(WebDriver driver, WebElement dropdownElement, int index, int timeout) {
		waitForVisibility(driver, dropdownElement, timeout);
		Select select = new Select(dropdownElement);
		select.selectByIndex(index);
	}

	// Get selected option text from dropdown
	public static String getSelectedOptionText(WebDriver driver, WebElement dropdownElement, int timeout) {
		waitForVisibility(driver, dropdownElement, timeout);
		Select select = new Select(dropdownElement);
		return select.getFirstSelectedOption().getText();
	}

	// Calander Handeling
	public static void navigateToMonthYear(WebDriver driver, String targetMonthYear, int timeoutSeconds)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);

		YearMonth targetYearMonth;
		try {
			targetYearMonth = YearMonth.parse(targetMonthYear, formatter);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(
					"Invalid targetMonthYear format. Expected 'MMMM yyyy', e.g. 'August 2025'");
		}

		while (true) {
			WebElement displayedMonthYearElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[@class='datepicker-switch']")));
			String displayedMonthYearText = displayedMonthYearElement.getText();

			YearMonth displayedYearMonth;
			try {
				displayedYearMonth = YearMonth.parse(displayedMonthYearText, formatter);
			} catch (DateTimeParseException e) {
				throw new RuntimeException(
						"Unable to parse displayed month/year from calendar: " + displayedMonthYearText);
			}

			// If matched, break loop
			if (displayedYearMonth.equals(targetYearMonth)) {
				break;
			}

			if (displayedYearMonth.isBefore(targetYearMonth)) {
				// Click next arrow
				WebElement nextArrow = driver.findElement(By.xpath("//th[@class='next']"));
				nextArrow.click();
			} else {
				// Click prev arrow
				WebElement prevArrow = driver.findElement(By.xpath("//th[@class='prev']"));
				prevArrow.click();
			}
			// Small wait for calendar to update UI
			Thread.sleep(500);
		}
	}

	// Handling Browser alerts
	public static void acceptAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			System.out.println("✅ Alert accepted successfully.");
		} catch (NoAlertPresentException e) {
			System.out.println("⚠️ No alert present to accept.");
		}
	}

	public static void dismissAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			System.out.println("✅ Alert dismissed successfully.");
		} catch (NoAlertPresentException e) {
			System.out.println("⚠️ No alert present to dismiss.");
		}
	}

	public static String getAlertText(WebDriver driver) throws InterruptedException {
		try {
			Alert alert = driver.switchTo().alert();
			String text = alert.getText().trim();
			System.out.println("📌 Alert text: " + text);
			return text;
		} catch (NoAlertPresentException e) {
			System.out.println("⚠️ No alert present to get text from.");
			return null;
		}
	}

	public static void sendKeysToAlert(WebDriver driver, String input) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(input);
			System.out.println("✅ Sent input to alert: " + input);
		} catch (NoAlertPresentException e) {
			System.out.println("⚠️ No alert present to send input.");
		}
	}

}
