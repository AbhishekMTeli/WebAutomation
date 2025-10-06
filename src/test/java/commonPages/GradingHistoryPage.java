package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class GradingHistoryPage {
	private WebDriver driver;
	private int timeout;

	public GradingHistoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
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

	@FindBy(xpath = "//th[normalize-space(text())='Action']")
	private WebElement actionLabel;

	@FindBy(xpath = "//button[text()='View']")
	private WebElement viewButton;

	@FindBy(xpath = "//h3[@id='pdfModalLabel']")
	private WebElement formPreviewLabel;

	@FindBy(xpath = "//span[normalize-space()='×']")
	private WebElement formPreviewPopupCloseIcon;

	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	private WebElement formPreviewPopupCloseButton;

	public void clickCloseIcon() {
		SeleniumUtils.click(driver, formPreviewPopupCloseIcon, timeout);
	}

	public void clickCloseButton() {
		SeleniumUtils.click(driver, formPreviewPopupCloseButton, timeout);
	}

	public void validatePreviewHeader() {
		SeleniumUtils.waitForVisibility(driver, formPreviewLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(formPreviewLabel), "Form Preview", "Form Preview label mismatch");
	}

	public void clickGradingHistoryButton() {
		SeleniumUtils.click(driver, gradingHistoryButton, timeout);
	}

	public void clickViewButton() {
		SeleniumUtils.click(driver, viewButton, timeout);
	}

	public void enterLessonName(String lesson) {
		SeleniumUtils.type(driver, searchTextField, lesson, timeout);
	}

	public void validateAllStaticTexts() {
		SeleniumUtils.waitForVisibility(driver, gradingHistoryLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(gradingHistoryLabel), "Grading History",
				"Grading History label mismatch");
		Assert.assertEquals(SeleniumUtils.getText(lessonDetailsLabel), "Lesson Details",
				"Lesson Details label mismatch");
		Assert.assertEquals(SeleniumUtils.getText(cadetDetailsLabel), "Cadet Details", "Cadet Details label mismatch");
		Assert.assertEquals(SeleniumUtils.getText(submittedDateLabel), "Submitted Date",
				"Submitted Date label mismatch");
		Assert.assertEquals(SeleniumUtils.getText(sectorsLabel), "Sectors", "Sectors label mismatch");
		Assert.assertEquals(SeleniumUtils.getText(actionLabel), "Action", "Action label mismatch");
	}

	public void test() {

	}
}