package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class GradingHistory {
	private WebDriver driver;
	private int timeout;

	public GradingHistory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeOut"));
	}

	@FindBy(xpath = "//button[text()='Grading History']")
	private WebElement gradingHistoryButton;

	@FindBy(xpath = "//input[normalize-space(@class)='form-control search_field']")
	private WebElement searchTextField;

	@FindBy(xpath = "//span[normalize-space(text())='Grading History']")
	private WebElement gradingHistoryLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Lesson Details']")
	private WebElement lessonDetailsLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Cadet Details']")
	private WebElement cadetDetailsLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Submitted Date']")
	private WebElement submittedDateLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Sectors']")
	private WebElement sectorsLabel;
	
	@FindBy(xpath = "//th[normalize-space(text())='Sectors']")
	private WebElement Label;

	public void clickGradingHistoryButton() {
		SeleniumUtils.click(driver, gradingHistoryButton, timeout);
	}

	public void enterLessonName(String lesson) {
		SeleniumUtils.type(driver, searchTextField, lesson, timeout);
	}

	public void validateAllStaticTexts() {
		SeleniumUtils.waitForVisibility(driver, gradingHistoryLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(gradingHistoryLabel), "Grading History",
				"Grading History label mismatch");
	}
}