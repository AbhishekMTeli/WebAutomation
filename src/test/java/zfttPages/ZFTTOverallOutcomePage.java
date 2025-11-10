package zfttPages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class ZFTTOverallOutcomePage {
	private WebDriver driver;
	private int timeout;

	public ZFTTOverallOutcomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	public String getGradingCell(String section, String gradeNumber) {
		return String.format("//td[@id='LHS_%s_%s']", section, gradeNumber);
	}

	public void clickGrade(String section, String gradeNumber) {
		String gradeXpath = getGradingCell(section, gradeNumber);
		WebElement gradeButton = driver.findElement(By.xpath(gradeXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", gradeButton);
	}

	@FindBy(xpath = "//td[@data-grade-val='N/O']")
	private List<WebElement> noGradeButtons;

	public void clickLHSNOGrade() {
		int size = noGradeButtons.size();
		for (int i = 0; i < size - 2; i++) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", noGradeButtons.get(i));
		}
	}

	public String getPlusIconsXpath(String section) {
		return String.format("//table[contains(@id,'%s_Task_Table_')]//button[@class='btn btn-pure btn fa fa-plus']",
				section);
	}

	public String getMinusIconsXpath(String section) {
		return String.format("//table[contains(@id,'%s_Task_Table_')]//button[@class='btn btn-pure btn fa fa-minus']",
				section);
	}

	public void clickAllPlus(String section) {
		String plusXpath = getPlusIconsXpath(section);
		List<WebElement> plusButtons = driver.findElements(By.xpath(plusXpath));
		for (WebElement plusButton : plusButtons) {
			SeleniumUtils.click(driver, plusButton, timeout);
		}
	}

	public void clickAllMinus(String section) {
		String minusXpath = getMinusIconsXpath(section);
		List<WebElement> minusButtons = driver.findElements(By.xpath(minusXpath));
		for (WebElement minusButton : minusButtons) {
			SeleniumUtils.click(driver, minusButton, timeout);
		}
	}

	public String getCommentsArea(String section) {
		return String.format(
				"//div[@id='%s_charCountDisplay']/preceding-sibling::textarea[@id='%s_competency_comment_txtarea']",
				section, section);
	}

	public void enterOBComments(String section, String comment) {
		String commentXpath = getCommentsArea(section);
		WebElement commentField = driver.findElement(By.xpath(commentXpath));
		SeleniumUtils.type(driver, commentField, comment, timeout);
	}

	public String getOBDoneXpath(String section) {
		return String.format(
				"//div[@id='%s_competency_txtArea']//ancestor::div[contains(@class,'modal-content')]//button[@class='btn btn-success btn-ranger-save' and @data-event-side='LHS']",
				section);
	}

	public String getLHSOBCancelXpath(String section) {
		return String.format(
				"//div[@id='%s_competency_txtArea']//ancestor::div[contains(@class,'modal-content')]//a[contains(@data-pi-block-div,'%s_Task_Modal_')]",
				section);
	}

	public void clickOBDoneButton(String section) {
		String obDoneButtonXpath = getOBDoneXpath(section);
		WebElement obDoneButton = driver.findElement(By.xpath(obDoneButtonXpath));
		SeleniumUtils.click(driver, obDoneButton, timeout);
	}

	public void clickLHSOBCancelButton(String section) {
		String obDoneButtonXpath = getLHSOBCancelXpath(section);
		WebElement obCancelIcon = driver.findElement(By.xpath(obDoneButtonXpath));
		SeleniumUtils.click(driver, obCancelIcon, timeout);
	}

	@FindBy(xpath = "//i[@id='lsnplan']")
	private WebElement lessonInfoIcon;

	@FindBy(xpath = "//span[@class='close']")
	private WebElement lessonInfoCloseIcon;

	public void clickLessonInfoIcon() {
		SeleniumUtils.click(driver, lessonInfoIcon, timeout);
	}

	public void clickLessonInfoCloseIcon() {
		SeleniumUtils.click(driver, lessonInfoCloseIcon, timeout);
	}

	// starts from here
	@FindBy(xpath = "//div[@id='EBT_overallOutcome']//span[@id='lhsDesignation']")
	private WebElement designationLabel;

	@FindBy(xpath = "//div[@id='overall_Asst_LHS']//h4[@class='panel-title'][normalize-space()='OVERALL ASSESSMENT']")
	private WebElement overallAssessmentLable;

	public void validateZFTTOverallOutComePage() {
		SeleniumUtils.waitForVisibility(driver, designationLabel, timeout);
		Assert.assertTrue(SeleniumUtils.getText(designationLabel).contains("Designation -"),
				"LHS designation label text mismatch");
		Assert.assertEquals(SeleniumUtils.getText(overallAssessmentLable), "OVERALL ASSESSMENT",
				"Text Mismatch expected : OVERALL ASSESSMENT but got : "
						+ SeleniumUtils.getText(overallAssessmentLable));
	}

	@FindBy(xpath = "//input[@id='LHS_C']")
	private WebElement competentRadioButton;

	@FindBy(xpath = "//input[@id='LHS_ATR']")
	private WebElement notYetCompetentRadioButton;

	public void selectCompetentRadioButton() {
		SeleniumUtils.click(driver, competentRadioButton, timeout);
	}

	public void selectNotYetCompetentRadioButton() {
		SeleniumUtils.click(driver, notYetCompetentRadioButton, timeout);
	}

	public boolean notYetCompetentIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, overallAssessmentLable.getText());
		SeleniumUtils.waitForVisibility(driver, notYetCompetentRadioButton, timeout);
		return notYetCompetentRadioButton.isSelected();
	}

	public boolean competentIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, overallAssessmentLable.getText());
		SeleniumUtils.waitForVisibility(driver, competentRadioButton, timeout);
		return competentRadioButton.isSelected();
	}

	@FindBy(xpath = "//textarea[@id='overallcomment_textarea_LHS1']")
	private WebElement remarksTextAreaField;

	@FindBy(xpath = "//label[normalize-space()='OVERALL REMARKS']")
	private WebElement remarksLabel;

	public void enterRemarks(String remarks) {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(remarksLabel));
		SeleniumUtils.waitForVisibility(driver, remarksTextAreaField, timeout);
		SeleniumUtils.type(driver, remarksTextAreaField, remarks, timeout);
	}

	@FindBy(xpath = "//div[@id='textarea_feedback_LHS']//b")
	private WebElement remainingCharacterLabel;

	public int lhsCharacterCount() throws InterruptedException {
		Thread.sleep(1000);
		return Integer.parseInt(SeleniumUtils.getText(remainingCharacterLabel).replaceAll("[^0-9]", ""));
	}

	@FindBy(xpath = "(//h2[normalize-space(text())='QUALIFICATION:'])[2]")
	private WebElement qualificationLabel;

	@FindBy(xpath = "//select[@id='TRIDE']")
	private WebElement qualificationDropdown;

	@FindBy(xpath = "//select[contains(@id,'TRIDE')]")
	private WebElement qualification1Dropdown;

	@FindBy(xpath = "//button[@id='overallDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='overallOC_next']")
	private WebElement saveAndNextButton;

	public void selecteQualificationInreviewPage(String qualification) {
		SeleniumUtils.selectDropdownByValue(driver, qualification1Dropdown, qualification, timeout);
	}

	public void selectQualification(String qualification) throws InterruptedException {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(qualificationLabel));
		SeleniumUtils.selectDropdownByVisibleText(driver, qualificationDropdown, qualification, timeout);
	}

	public void clickDiscardButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(discardButton));
		SeleniumUtils.click(driver, discardButton, timeout);
	}

	public void clickSaveAndNextButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
		SeleniumUtils.waitForVisibility(driver, saveAndNextButton, timeout);
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(saveAndNextButton));
		SeleniumUtils.click(driver, saveAndNextButton, timeout);
	}

	@FindBy(xpath = "//div[@id='EBT_overallOutcome']//div[@id='takeof']//h2[1]")
	private WebElement takeOffAndLandingsConductedLabel;

	@FindBy(xpath = "//select[@id='TAKEOFFLANDING']")
	private WebElement takeOffAndLandingsConductedDropdown;

	public void selectTakeOffAndLandingsConducted(String takeOffAndLandingsConducted) throws InterruptedException {
		SeleniumUtils.waitForVisibility(driver, takeOffAndLandingsConductedLabel, timeout);
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(takeOffAndLandingsConductedLabel));
		SeleniumUtils.selectDropdownByVisibleText(driver, takeOffAndLandingsConductedDropdown,
				takeOffAndLandingsConducted, timeout);
	}

	// Reason for Delay Pop-up
	@FindBy(xpath = "//h5[text()='Reason for Delayed Grading']")
	private WebElement reasonForDelayLabel;

	@FindBy(xpath = "//textarea[@id='comment_textarea']")
	private WebElement reasonForDelayCommentTextArea;

	@FindBy(xpath = "//button[normalize-space(text())='Submit Comment']")
	private WebElement reasonForDelaySubmitButton;

	@FindBy(xpath = "//button[@class='btn btn-secondary']")
	private WebElement reasonForDelayCloseButton;

	public void reasonForDelayLabelIsPresent() {
		try {
			SeleniumUtils.waitForPresence(driver, By.xpath("//h5[text()='Reason for Delayed Grading']"), timeout);
		} catch (Exception e) {
			System.out.println("Delay Popup not present");
		}
	}

	public void clickSubmitCommentButtonForDelayComment() {
		try {
			SeleniumUtils.click(driver, reasonForDelaySubmitButton, timeout);
		} catch (Exception e) {

		}
	}

	public void addDelayComments(String comment) {
		try {
			SeleniumUtils.type(driver, reasonForDelayCommentTextArea, comment, timeout);
		} catch (Exception e) {

		}
	}

	public void clickCloseCommentButtonForDelayComment() {
		try {
			SeleniumUtils.click(driver, reasonForDelayCloseButton, timeout);
		} catch (Exception e) {

		}
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

	@FindBy(xpath = "//h4[@id='loginModalLabel']")
	private WebElement dataSuccessfullyUploadedLabel;

	@FindBy(xpath = "//button[@id='ebtack_login_btn']")
	private WebElement dataSuccessfullyUploadedOkButton;

	public void dataSuccessfullyUploadedIsPresent() {
		SeleniumUtils.waitForPresence(driver, By.xpath("//span[@id='alertBoxMsg']"), timeout);
	}

	public void clickOkPop_up() throws InterruptedException {
		Thread.sleep(500);
		SeleniumUtils.click(driver, dataSuccessfullyUploadedOkButton, timeout);
	}

	// Preview Popup
	@FindBy(xpath = "//h4[normalize-space()='Preview']")
	private WebElement previewHeader;

	@FindBy(xpath = "//div[@role='document']//span[@id='closeButton']")
	private WebElement previewCloseIcon;

	@FindBy(xpath = "//span[@class='glyphicon glyphicon-share']")
	private WebElement previewShareIcon;

	@FindBy(xpath = "//button[@id='previewNext']")
	private WebElement previewNextButton;

	public void visibilityOfPreviewHeader() {
		SeleniumUtils.waitForVisibility(driver, previewHeader, timeout);
	}

	public void clickPreviewCloseIcon() {
		SeleniumUtils.click(driver, previewCloseIcon, timeout);
	}

	public void clickPreviewShareIcon() {
		SeleniumUtils.click(driver, previewShareIcon, timeout);
	}

	public void clickPreviewNextButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(previewNextButton));
		SeleniumUtils.click(driver, previewNextButton, timeout);
	}
}
