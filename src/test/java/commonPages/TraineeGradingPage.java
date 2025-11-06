package commonPages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class TraineeGradingPage {
	private WebDriver driver;
	private int timeout;

	public TraineeGradingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
		initStaticTextValidationMap();
	}

	@FindBy(xpath = "//th[normalize-space(text())='Scheduled Date & Time']")
	private WebElement scheduledDateAndTimeStaticText;

	@FindBy(xpath = "//th[normalize-space(text())='Device Id']")
	private WebElement deviceIdStaticText;

	@FindBy(xpath = "(//th[text()='Curriculum'])[1]")
	private WebElement curriculumStaticText;

	@FindBy(xpath = "(//th[text()='Lesson'])[1]")
	private WebElement lessonStaticText;

	@FindBy(xpath = "(//th[text()='Trainees'])[1]")
	private WebElement traineesStaticText;

	@FindBy(xpath = "(//th[text()='Sectors'])[1]")
	private WebElement sectorsStaticText;

	@FindBy(xpath = "//a[@id='AimsDatatable_>']")
	private WebElement nextArrowButton;

	// ========== Static Text Validation ==========
	private Map<String, WebElement> staticTextElements = new HashMap<>();
	private Map<String, String> expectedStaticTexts = new HashMap<>();

	private void initStaticTextValidationMap() {
		staticTextElements.put("scheduledDateAndTime", scheduledDateAndTimeStaticText);
		staticTextElements.put("deviceId", deviceIdStaticText);
		staticTextElements.put("curriculum", curriculumStaticText);
		staticTextElements.put("lesson", lessonStaticText);
		staticTextElements.put("trainees", traineesStaticText);
		staticTextElements.put("sectors", sectorsStaticText);

		expectedStaticTexts.put("scheduledDateAndTime", "Scheduled Date & Time");
		expectedStaticTexts.put("deviceId", "Device Id");
		expectedStaticTexts.put("curriculum", "Curriculum");
		expectedStaticTexts.put("lesson", "Lesson");
		expectedStaticTexts.put("trainees", "Trainees");
		expectedStaticTexts.put("sectors", "Sectors");
	}

	public void validateAllStaticTexts() throws InterruptedException {
		staticTextElements.forEach((key, element) -> {
			SeleniumUtils.waitForVisibility(driver, element, 60);
			String actual = element.getText().trim();
			String expected = expectedStaticTexts.get(key);
			if (expected != null && !actual.contains(expected)) {
				throw new AssertionError("Static text mismatch for '" + key + "': expected to contain '" + expected
						+ "', but actual was '" + actual + "'");
			}
		});
	}

	public List<WebElement> getGradeButtons() {
		String lessonNames = ConfigReader.get("lessonName");
		List<WebElement> elements = new ArrayList<>();
		String[] arrayOfLessonNames = lessonNames.split(",");
		for (String lessonName : arrayOfLessonNames) {
			String xpath = "//td[text()='" + lessonName.trim() + "']";
			try {
				elements.add(driver.findElement(By.xpath(xpath)));
			} catch (NoSuchElementException e) {
				// Log and continue if an element is not found for a lesson name
				System.out.println("Element not found for lesson: " + lessonName);
			}
		}
		return elements;
	}

	public void clickOnGradeButtonWithRetries(int maxRetries) {
		PopupPage popupPage = new PopupPage(driver);
		int retries = 0;
		while (retries < maxRetries) {
			SeleniumUtils.scrollToTopOfPage(driver);
			try {
				List<WebElement> gradeButtons = getGradeButtons();
				boolean clicked = false;
				// Iterate over all matched buttons, click the first clickable one then exit
				for (WebElement gradeBtn : gradeButtons) {
					try {
						SeleniumUtils.waitForClickability(driver, gradeBtn, timeout);
						SeleniumUtils.click(driver, gradeBtn, timeout);
						System.out.println("Clicked GradeButton on attempt " + (retries + 1));
						clicked = true;
						break; // Exit the for loop on successful click
					} catch (Exception e) {
						System.out.println("Failed to click this GradeButton: " + e.getMessage());
						// Try next in list
					}
				}
				if (clicked) {
					return; // Success: exit method immediately
				}
				// If none clicked, will throw below to enter catch block
				throw new Exception("No clickable GradeButton found on attempt " + (retries + 1));
			} catch (Exception e) {
				System.out.println("Attempt " + (retries + 1) + " failed: " + e.getMessage());
				try {
					SeleniumUtils.scrollToEndOfPage(driver);
				} catch (Exception ignore) {
				}
				try {
					SeleniumUtils.scrollToElementByVisibleText(driver, nextArrowButton.getText());
					SeleniumUtils.click(driver, nextArrowButton, timeout);
					System.out.println("Clicked on NextArrowButton on attempt " + (retries + 1));
				} catch (Exception inner) {
					System.out.println("NextArrowButton not clickable: " + inner.getMessage());
				}
			}
			retries++;
			popupPage.handelSpinner();
		}
		throw new RuntimeException("Failed to click GradeButton after " + maxRetries + " retries.");
	}
	// No SLF Pop-up

	@FindBy(xpath = "//label[@id='noSLFHistoryText']")
	private WebElement noSFPPopupLabel;

	@FindBy(xpath = "//button[@id='Discard_no']")
	private WebElement NoButtonNoSFPPopup;

	@FindBy(xpath = "//button[@id='Discard_yes']")
	private WebElement YesButtonNoSFPPopup;

	public void handelNoSlfHistoryPopup() {
		try {
			SeleniumUtils.click(driver, noSFPPopupLabel, timeout);
			SeleniumUtils.click(driver, YesButtonNoSFPPopup, timeout);
		} catch (Exception e) {
			System.out.println("No SLF Pop-up Not Observerd");
		}
	}

	// Alerts or Pop-up Handeling
	@FindBy(xpath = "//span[@id='alertBoxMsg']")
	private WebElement alertLabel;

	@FindBy(xpath = "//span[contains(text(),'OK')]")
	private WebElement alertOkButton;

	public String popupGetText() {
		SeleniumUtils.waitForVisibility(driver, alertLabel, timeout);
		return SeleniumUtils.getText(alertLabel);
	}

	public void clickPopupOkButton() {
		SeleniumUtils.click(driver, alertOkButton, timeout);
	}
}
