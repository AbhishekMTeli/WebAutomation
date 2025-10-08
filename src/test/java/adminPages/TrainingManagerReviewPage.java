package adminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class TrainingManagerReviewPage {
	private WebDriver driver;
	private int timeout;

	public TrainingManagerReviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//th[normalize-space(text())='Scheduled Date']")
	private WebElement scheduledDateStaticText;

	@FindBy(xpath = "//th[normalize-space(text())='Trainee']")
	private WebElement traineeStaticText;

	@FindBy(xpath = "//th[normalize-space(text())='Instructor']")
	private WebElement instructorStaticText;

	@FindBy(xpath = "//th[normalize-space(text())='Curriculum']")
	private WebElement curriculumStaticText;

	@FindBy(xpath = "//th[normalize-space(text())='Lesson']")
	private WebElement lessonStaticText;

	@FindBy(xpath = "//th[normalize-space(text())='OverAllOutCome']")
	private WebElement overallOutcomeStaticText;

	@FindBy(xpath = "//th[normalize-space(text())='CompleteDate']")
	private WebElement completeDateStaticText;

	@FindBy(xpath = "//th[normalize-space(text())='Sch Code']")
	private WebElement schCodeStaticText;

	@FindBy(xpath = "//th[normalize-space(text())='View']")
	private WebElement viewStaticText;

	@FindBy(xpath = "//th[@class='sorting' and normalize-space(text())='Status']")
	private WebElement statusStaticText;

	public void validateAllTableHeaders() {
		SeleniumUtils.waitForVisibility(driver, scheduledDateStaticText, timeout);
		Assert.assertEquals(scheduledDateStaticText.getText(), "Scheduled Date", "Scheduled Date label mismatch!");
		Assert.assertEquals(traineeStaticText.getText(), "Trainee", "Trainee label mismatch!");
		Assert.assertEquals(instructorStaticText.getText(), "Instructor", "Instructor label mismatch!");
		Assert.assertEquals(curriculumStaticText.getText(), "Curriculum", "Curriculum label mismatch!");
		Assert.assertEquals(lessonStaticText.getText(), "Lesson", "Lesson label mismatch!");
		Assert.assertEquals(overallOutcomeStaticText.getText(), "OverAllOutCome", "OverAllOutCome label mismatch!");
		Assert.assertEquals(completeDateStaticText.getText(), "CompleteDate", "CompleteDate label mismatch!");
		Assert.assertEquals(schCodeStaticText.getText(), "Sch Code", "Scheduled Date label mismatch!");
		Assert.assertEquals(viewStaticText.getText(), "View", "View label mismatch!");
		Assert.assertEquals(statusStaticText.getText(), "Status", "Status label mismatch!");
	}

	@FindBy(xpath = "//input[@class=' form-control search_field']")
	private WebElement searchTextField;

	@FindBy(xpath = "//button[@class='btn btn-add']")
	private WebElement viewButton;

	// Training Records Approval Page or Popup
	@FindBy(xpath = "//a[@id='expandPdf']/preceding-sibling::h4[normalize-space(text())='Training Records Approval']")
	private WebElement trainingRecordsPopupHeader;

	@FindBy(xpath = "//a[@id='expandPdf']/preceding-sibling::button[@class='close']")
	private WebElement closeIcon;

	@FindBy(xpath = "//span[@class='glyphicon glyphicon-share']")
	private WebElement shareIcon;

	@FindBy(xpath = "//textarea[@id='commentsmodal']")
	private WebElement commentTextAreaField;

	@FindBy(xpath = "//button[@id='approveBtn']")
	private WebElement approveButton;

	@FindBy(xpath = "//button[@id='markForReviewbtn']")
	private WebElement markForReviewButton;

	@FindBy(xpath = "//button[@id='markForReviewbtn']/following-sibling::button[@class='btn btn-cancel']")
	private WebElement cancelButton;

	@FindBy(xpath = "//button[@id='markForReviewbtn']/following-sibling::button[@class='Feedback']")
	private WebElement feedBackButton;

	@FindBy(xpath = "//label[normalize-space(text())='Are you sure you want to Approve?']")
	private WebElement areSureYouWantToApproveLabel;

	@FindBy(xpath = "//label[normalize-space(text())='Are you sure you want to mark this event for review by trainer ?']")
	private WebElement areSureYouWantToMarkForReview;

	@FindBy(xpath = "//button[@id='home_button_yes']")
	private WebElement yesButtonForApprove;

	@FindBy(xpath = "//button[@id='home_button_no']")
	private WebElement noButtonForApprove;

	@FindBy(xpath = "//button[@id='markForReview_button_yes']")
	private WebElement yesButtonForReview;

	@FindBy(xpath = "//button[@id='markForReview_button_yes']")
	private WebElement noButtonForReview;

	public void searchforTrainee(String traineeId) {
		SeleniumUtils.type(driver, searchTextField, traineeId, timeout);
	}

	public void clickViewButton() {
		SeleniumUtils.click(driver, viewButton, timeout);
	}

	public void presenceOfViewButton() {
		SeleniumUtils.waitForVisibility(driver, viewButton, 60);
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

	public void clickApproveButton() {
		SeleniumUtils.waitForClickability(driver, approveButton, timeout);
		SeleniumUtils.click(driver, approveButton, timeout);
	}

	public void clickMarkForReviewButton() {
		SeleniumUtils.click(driver, markForReviewButton, timeout);
	}

	public void clickCancelButton() {
		SeleniumUtils.click(driver, cancelButton, timeout);
	}

	public void clickFeedbackButton() {
		SeleniumUtils.click(driver, feedBackButton, timeout);
	}

	public void clickYesButtonForApprove() {
		SeleniumUtils.click(driver, yesButtonForApprove, timeout);
	}

	public void clickNoButtonForApprove() {
		SeleniumUtils.click(driver, noButtonForApprove, timeout);
	}

	public void clickYesButtonForReview() {
		SeleniumUtils.click(driver, yesButtonForReview, timeout);
	}

	public void clickNoButtonForReview() {
		SeleniumUtils.click(driver, noButtonForReview, timeout);
	}

	public void validateTextAreSureYouWantToApprove() {
		SeleniumUtils.waitForVisibility(driver, areSureYouWantToApproveLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(areSureYouWantToApproveLabel), "Are you sure you want to Approve?",
				"Are you sure you want to Approve? Label Mismatch");
	}

	public void validateTextAreSureYouWantToMarkForReview() {
		SeleniumUtils.waitForVisibility(driver, areSureYouWantToMarkForReview, timeout);
		Assert.assertEquals(SeleniumUtils.getText(areSureYouWantToMarkForReview),
				"Are you sure you want to mark this event for review by trainer ?",
				"'Are you sure you want to mark this event for review by trainer ?' Label Mismatch");
	}

	public void validateTrainingRecordsApproval() {
		Assert.assertEquals(SeleniumUtils.getText(trainingRecordsPopupHeader), "Training Records Approval",
				"Training Records Approval Label Mismatch");
	}

	@FindBy(xpath = "//div[@id='popup']")
	private WebElement popUpLabel;

	@FindBy(xpath = "//span[text()='OK']")
	private WebElement popUpOkButton;

	public String getTextOfPopup() {
		SeleniumUtils.waitForVisibility(driver, popUpLabel, timeout);
		return SeleniumUtils.getText(popUpLabel);
	}

	public void clickOkPopupButton() {
		SeleniumUtils.click(driver, popUpOkButton, timeout);
	}
}
