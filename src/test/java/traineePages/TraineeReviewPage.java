package traineePages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	@FindBy(xpath = "//th[text()='OverAllOutCome']")
	private WebElement overAllOutComeLabel;

	@FindBy(xpath = "//th[text()='CompleteDate']")
	private WebElement completedDateLabel;

	@FindBy(xpath = "//th[normalize-space(text())='View']")
	private WebElement viewLabel;

	public void validateAllStaticElements() {
		driver.navigate().refresh();
		SeleniumUtils.waitForVisibility(driver, scheduledDateLabel, 60);
		Assert.assertEquals(scheduledDateLabel.getText(), "Scheduled Date", "Scheduled Date label mismatch!");
		Assert.assertEquals(traineeLabel.getText(), "Trainee", "Trainee label mismatch!");
		Assert.assertEquals(instructorLabel.getText(), "Instructor", "Instructor label mismatch!");
		Assert.assertEquals(curriculumLabel.getText(), "Curriculum", "Curriculum label mismatch!");
		Assert.assertEquals(lessonLabel.getText(), "Lesson", "Lesson label mismatch!");
		Assert.assertEquals(sectorsLabel.getText(), "Sectors", "Sectors label mismatch!");
		Assert.assertEquals(overAllOutComeLabel.getText(), "OverAllOutCome", "OverAllOutCome label mismatch!");
		Assert.assertEquals(completedDateLabel.getText(), "CompleteDate", "CompleteDate label mismatch!");
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
		SeleniumUtils.waitForVisibility(driver, gradingAndAssessmentTab, timeout);
		SeleniumUtils.click(driver, gradingAndAssessmentTab, 10);
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

	@FindBy(xpath = "//input[@class=' form-control search_field']")
	private WebElement searchTextField;

	// Training Records Approval Page or Popup
	@FindBy(xpath = "//a[@id='expandPdf']/preceding-sibling::h4[normalize-space(text())='Training Records Approval']")
	private WebElement trainingRecordsPopupHeader;

	@FindBy(xpath = "//a[@id='expandPdf']/preceding-sibling::button[@class='close']")
	private WebElement closeIcon;

	@FindBy(xpath = "//span[@class='glyphicon glyphicon-share']")
	private WebElement shareIcon;

	@FindBy(xpath = "//textarea[@id='commentsmodal']")
	private WebElement commentTextAreaField;

	@FindBy(xpath = "//button[@id='markForReviewbtn']")
	private WebElement markForReviewButton;

	@FindBy(xpath = "//button[@id='Acknowledgebtn']")
	private WebElement acknowledgeButton;

	@FindBy(xpath = "//button[@id='markForReviewbtn']/following-sibling::button[@class='btn btn-cancel']")
	private WebElement cancelButton;

	@FindBy(xpath = "//button[@id='markForReviewbtn']/following-sibling::button[@class='Feedback']")
	private WebElement feedBackButton;

	@FindBy(xpath = "//label[normalize-space(text())='Are you sure you want to Approve?']")
	private WebElement areSureYouWantToApproveLabel;

	@FindBy(xpath = "//button[text()='YES']")
	private WebElement yesButton;

	@FindBy(xpath = "//button[text()='NO']")
	private WebElement noButton;

	@FindBy(xpath = "//label[normalize-space(text())='Are you sure you want to mark this event for review by trainer ?']")
	private WebElement markForReviewConfirmationLabel;

	public void markForReviewButtonNotVisible() {
		try {
			boolean isVisible = markForReviewButton.isDisplayed();
			System.out.println("Is Mark for Review button visible? " + isVisible);
			Assert.assertFalse(isVisible, "Mark for Review button should not be visible");
		} catch (NoSuchElementException e) {
			// Element not found: treat as not visible
			System.out.println("Mark for Review button not found in DOM, considered not visible");
			Assert.assertTrue(true);
		}
	}

	public String getMarkForReviewConfirmationText() {
		SeleniumUtils.waitForVisibility(driver, markForReviewConfirmationLabel, timeout);
		return markForReviewConfirmationLabel.getText();
	}

	public void clickAcknowledgeButton() {
		SeleniumUtils.click(driver, acknowledgeButton, timeout);
	}

	public void searchforTrainee(String traineeId) {
		SeleniumUtils.type(driver, searchTextField, traineeId, timeout);
	}

	public void clickCloseIcon() {
		SeleniumUtils.click(driver, closeIcon, timeout);
	}

	public void clickShareIcon() {
		SeleniumUtils.click(driver, shareIcon, timeout);
	}

	public void enterComment(String comment) {
		SeleniumUtils.type(driver, commentTextAreaField, comment, timeout);
	}

	public void clickMarkForReviewButton() throws InterruptedException {
		Thread.sleep(500);
		SeleniumUtils.click(driver, markForReviewButton, timeout);
	}

	public void clickCancelButton() {
		SeleniumUtils.click(driver, cancelButton, timeout);
	}

	public void clickFeedbackButton() {
		SeleniumUtils.click(driver, feedBackButton, timeout);
	}

	public void clickYesButton() throws InterruptedException {
		Thread.sleep(500);
		SeleniumUtils.click(driver, yesButton, timeout);
	}

	public void clickNoButton() {
		SeleniumUtils.click(driver, noButton, timeout);
	}

	public void validateTextAreSureYouWantToApprove() {
		Assert.assertEquals(SeleniumUtils.getText(areSureYouWantToApproveLabel), "Are you sure you want to Approve?",
				"Are you sure you want to Approve? Label Mismatch");
	}

	public void validateTrainingRecordsApproval() {
		Assert.assertEquals(SeleniumUtils.getText(trainingRecordsPopupHeader), "Training Records Approval",
				"Training Records Approval Label Mismatch");
	}

	// Instructor Acknowldgement
	@FindBy(xpath = "//h4[@id='loginModalLabel']")
	private WebElement instructorAcknowldgementLabel;

	@FindBy(xpath = "//button[@id='ebtack_login_btn']")
	private WebElement instructorAcknowldgementSubmitButton;

	@FindBy(xpath = "//button[@class='btn btn-secondary']")
	private WebElement instructorAcknowldgementCloseIconButton;

	public void instructorAcknowldgementLabelIsPresent() {
		SeleniumUtils.waitForPresence(driver, By.xpath("//h5[text()='Reason for Delayed Grading']"), timeout);
	}

	public void clickSubmitButtonForInstructorAcknowldgement() {
		SeleniumUtils.click(driver, instructorAcknowldgementSubmitButton, timeout);
	}

	public void clickCloseIconForInstructorAcknowldgement() {
		SeleniumUtils.click(driver, instructorAcknowldgementCloseIconButton, timeout);
	}

	// Digital Signiture Popup
	@FindBy(xpath = "//h4[normalize-space(text())='Digital Signature']")
	private WebElement digitalSignitureLabel;

	@FindBy(xpath = "//button[@id='save-button']")
	private WebElement digitalSignitureSaveSignitureButton;

	@FindBy(xpath = "//a[@class='btn btn-cancel']//span[text()='X']")
	private WebElement digitalSignitureCloseIconButton;

	@FindBy(xpath = "//button[@id='clear']")
	private WebElement digitalSignitureClearButton;

	@FindBy(xpath = "//canvas[@id='signature-pad']")
	private WebElement digitalSignitureTextAreaField;

	public void digitalSignitureLabelIsPresent() {
		SeleniumUtils.waitForPresence(driver, By.xpath("//h4[@id='loginModalLabel']"), timeout);
	}

	public void clickSaveSignitureButtonForDigitalSigniture() {
		SeleniumUtils.click(driver, digitalSignitureSaveSignitureButton, timeout);
	}

	public void clickCloseIconForDigitalSigniture() {
		SeleniumUtils.click(driver, digitalSignitureCloseIconButton, timeout);
	}

	public void clickClearForDigitalSigniture() {
		SeleniumUtils.click(driver, digitalSignitureClearButton, timeout);
	}

	public void digitalSign() {
		SeleniumUtils.click(driver, digitalSignitureTextAreaField, timeout);
		Actions drawAction = new Actions(driver);
		drawAction.moveToElement(digitalSignitureTextAreaField, 50, 60).clickAndHold().moveByOffset(30, 20)
				.moveByOffset(30, -40).moveByOffset(30, 40).moveByOffset(50, 0).release().build().perform();
	}

	// Data Uploaded Successfully Pop-up

	@FindBy(xpath = "//span[@id='alertBoxMsg']")
	private WebElement dataSuccessfullyUploadedLabel;

	@FindBy(xpath = "//span[contains(text(),'OK')]")
	private WebElement dataSuccessfullyUploadedOkButton;

	public void dataSuccessfullyUploadedIsPresent() {
		SeleniumUtils.waitForPresence(driver, By.xpath("//span[@id='alertBoxMsg']"), timeout);
	}

	public void clickOkPop_up() {
		SeleniumUtils.click(driver, dataSuccessfullyUploadedOkButton, timeout);
	}

	// Alerts or Pop-up Handeling
	@FindBy(xpath = "//span[@id='alertBoxMsg']")
	private WebElement alertLabel;

	@FindBy(xpath = "//span[text()='OK']")
	private WebElement alertOkButton;

	public String popupGetText() {
		SeleniumUtils.waitForVisibility(driver, alertLabel, timeout);
		return SeleniumUtils.getText(alertLabel);
	}

	public void clickPopupOkButton() {
		SeleniumUtils.click(driver, alertOkButton, timeout);
	}

}
