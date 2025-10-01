package traineePages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class TraineeReviewPage {
	private WebDriver driver;
	private int timeout;

	public TraineeReviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//th[normalize-space(text())='Scheduled Date']")
	private WebElement scheduledDateLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Trainee']")
	private WebElement traineeLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Instructor']")
	private WebElement instructorLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Curriculum' and @class='sorting']")
	private WebElement curriculumLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Lesson']")
	private WebElement lessonLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Sectors']")
	private WebElement sectorsLabel;

	@FindBy(xpath = "//th[normalize-space(text())='//th[normalize-space(text())='OverAllOutCome']']")
	private WebElement overAllOutComeLabel;

	@FindBy(xpath = "//th[normalize-space(text())='CompletedDate']")
	private WebElement completedDateLabel;

	@FindBy(xpath = "//th[normalize-space(text())='View']")
	private WebElement viewLabel;

	public void validateAllStaticElements() {
		SeleniumUtils.waitForVisibility(driver, scheduledDateLabel, timeout);
		Assert.assertEquals(scheduledDateLabel.getText(), "Scheduled Date", "Scheduled Date label mismatch!");
		Assert.assertEquals(traineeLabel.getText(), "Trainee", "Trainee label mismatch!");
		Assert.assertEquals(instructorLabel.getText(), "Instructor", "Instructor label mismatch!");
		Assert.assertEquals(curriculumLabel.getText(), "Curriculum", "Curriculum label mismatch!");
		Assert.assertEquals(lessonLabel.getText(), "Lesson", "Lesson label mismatch!");
		Assert.assertEquals(sectorsLabel.getText(), "Sectors", "Sectors label mismatch!");
		Assert.assertEquals(overAllOutComeLabel.getText(), "OverAllOutCome", "OverAllOutCome label mismatch!");
		Assert.assertEquals(completedDateLabel.getText(), "CompletedDate", "CompletedDate label mismatch!");
		Assert.assertEquals(viewLabel.getText(), "View", "View label mismatch!");
	}

	@FindBy(xpath = "//span[normalize-space(text())='Grading & Assessment']")
	private WebElement gradingAndAssessmentTab;

	@FindBy(xpath = "//span[normalize-space(text())='Trainee Review']")
	private WebElement traineeReviewTab;

	@FindBy(xpath = "//input[@class=' form-control search_field']")
	private WebElement searchField;

	@FindBy(xpath = "//button[@id='viewClick']")
	private List<WebElement> viewButtons;

	public void clickGradingAndAssessmentTab() {
		SeleniumUtils.click(driver, gradingAndAssessmentTab, timeout);
	}

	public void clickTraineeReviewTab() {
		SeleniumUtils.click(driver, traineeReviewTab, timeout);
	}

	public void enterTrainId(String traineeId) {
		SeleniumUtils.type(driver, searchField, traineeId, timeout);
	}

	public void clickViewButton() {
		SeleniumUtils.click(driver, viewButtons.get(0), timeout);
	}
}
