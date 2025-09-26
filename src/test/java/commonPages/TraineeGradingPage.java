package commonPages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
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

	public void validateAllStaticTexts() {
		staticTextElements.forEach((key, element) -> {
			SeleniumUtils.waitForVisibility(driver, element, timeout);
			String actual = element.getText().trim();
			String expected = expectedStaticTexts.get(key);
			if (expected != null && !actual.contains(expected)) {
				throw new AssertionError("Static text mismatch for '" + key + "': expected to contain '" + expected
						+ "', but actual was '" + actual + "'");
			}
		});
	}

	public WebElement getGradeButton() {
		String lessonName = ConfigReader.get("lessonName");
		String xpath = "//td[text()='" + lessonName + "']";
		return driver.findElement(By.xpath(xpath));
	}

	public void clickOnGradeButtonWithRetries(int maxRetries) {
	    int retries = 0;
	    while (retries < maxRetries) {
	    	SeleniumUtils.scrollToTopOfPage(driver);
	        try {
	            WebElement gradeBtn = getGradeButton();
	            SeleniumUtils.waitForClickability(driver, gradeBtn, timeout);
	            SeleniumUtils.click(driver, gradeBtn, timeout);
	            System.out.println("Clicked GradeButton on attempt " + (retries + 1));
	            return; // ✅ success, exit method
	        } catch (Exception e) {
	            System.out.println("Attempt " + (retries + 1) + " failed: " + e.getMessage());

	            try {
	                // Scroll to end and retry
	                SeleniumUtils.scrollToEndOfPage(driver);
	            } catch (Exception ignore) {}

	            try {
	                // Try clicking arrow if grade button still not clickable
	                SeleniumUtils.scrollToElementByVisibleText(driver, nextArrowButton.getText());
	                SeleniumUtils.click(driver, nextArrowButton, timeout);
	                System.out.println("Clicked on NextArrowButton on attempt " + (retries + 1));
	            } catch (Exception inner) {
	                // log instead of throwing immediately
	                System.out.println("NextArrowButton not clickable: " + inner.getMessage());
	            }
	        }
	        retries++;
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
}
