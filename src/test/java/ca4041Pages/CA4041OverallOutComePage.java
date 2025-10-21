package ca4041Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CA4041OverallOutComePage {
	private WebDriver driver;
	private int timeout;

	public CA4041OverallOutComePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	// Validation texts for OB Tasks
	@FindBy(xpath = "//table[contains(@id,'_Task_Table_')]//td/p[starts-with(text(),'OB') and contains(text(),'.')]")
	private List<WebElement> validationTexts;

	@FindBy(xpath = "//textarea[contains(@id,'FPM_competency_comment_txtarea')]")
	private WebElement obCommentTextarea;

	// Overall Assessment radios
	@FindBy(xpath = "//input[@id='LHS_S']")
	private WebElement passRadioButton;

	@FindBy(xpath = "//input[@id='LHS_US']")
	private WebElement failedRadioButton;

	// Remark text and label elements
	@FindBy(xpath = "//textarea[@id='overallcomment_textarea_LHS']")
	private WebElement remarkTextArea;

	@FindBy(xpath = "//div[text()='6000 characters remaining' and @id='textarea_feedback_LHS']")
	private WebElement characterRemainingCount;

	@FindBy(xpath = "//label[normalize-space(text())='REMARKS']")
	private WebElement remarksLabel;

	@FindBy(xpath = "(//h2[normalize-space(text())='QUALIFICATION:'])[2]")
	private WebElement qualificationLabel;

	@FindBy(xpath = "//div[@id='overallOC_txtAreaLHS']//h4[contains(text(),'OVERALL')]")
	private WebElement overallAssessmentLabel;

	@FindBy(xpath = "//select[@id='TRIDE']")
	private WebElement qualificationDropdown;

	@FindBy(xpath = "//button[normalize-space(text())='Save and next']")
	private WebElement saveAndNextButton;

	@FindBy(xpath = "//button[normalize-space(text())='Save and next']")
	private WebElement discardButton;

	public void validateRadioSelectionBasedOnOptionIndex(int optionIndex) throws InterruptedException {
		switch (optionIndex) {
		case 1:
			Assert.assertTrue(failedRadioButton.isSelected(), "Expected 'FAIL' to be auto-selected for optionIndex 1");
			break;
		case 2:
		case 3:
		case 4:
		case 5:
			Assert.assertTrue(passRadioButton.isSelected(),
					"Expected 'PASS' to be auto-selected for optionIndex " + optionIndex);
			break;
		default:
			throw new IllegalArgumentException("Unexpected optionIndex: " + optionIndex);
		}
	}

	public void isSelectedPassRadioButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, overallAssessmentLabel.getText());
		passRadioButton.isSelected();
	}

	public void isSelectedFailRadioButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, overallAssessmentLabel.getText());
		failedRadioButton.isSelected();
	}

	public void addRemark(String comment) {
		SeleniumUtils.type(driver, remarkTextArea, comment, timeout);
	}

	public void remarkLabelIsPresent() {
		SeleniumUtils.waitForPresence(driver, By.xpath("//label[normalize-space(text())='REMARKS']"), timeout);
	}

	public String getCharacterRemainingCount() {
		return characterRemainingCount.getText();
	}

	public void competencyRemarksLabelIsPresent() {
		SeleniumUtils.waitForPresence(driver, By.xpath("//label[normalize-space(text())='REMARKS']"), timeout);
	}

	public void qualificationLabelIsPresent() {
		SeleniumUtils.waitForPresence(driver, By.xpath("(//h2[normalize-space(text())='QUALIFICATION:'])[2]"), timeout);
	}

	// Qaulification Dropdown

	public void selectQualification(String qualification) throws InterruptedException {
		SeleniumUtils.waitForVisibility(driver, qualificationLabel, timeout);
		SeleniumUtils.scrollToElementByVisibleText(driver, qualificationLabel.getText());
		SeleniumUtils.selectDropdownByVisibleText(driver, qualificationDropdown, qualification, timeout);
	}

	public void clickSaveAndNextButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, saveAndNextButton.getText());
		SeleniumUtils.click(driver, saveAndNextButton, timeout);
	}

	public void clickDiscardButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, discardButton.getText());
		SeleniumUtils.click(driver, discardButton, timeout);
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

	@FindBy(xpath = "//span[@id='alertBoxMsg']")
	private WebElement dataSuccessfullyUploadedLabel;

	@FindBy(xpath = "//span[contains(text(),'OK')]")
	private WebElement dataSuccessfullyUploadedOkButton;

	public void dataSuccessfullyUploadedIsPresent() {
		try {
			SeleniumUtils.waitForPresence(driver, By.xpath("//span[@id='alertBoxMsg']"), timeout);
		} catch (Exception e) {

		}
	}

	public void clickOkPop_up() {
		try {
			SeleniumUtils.click(driver, dataSuccessfullyUploadedOkButton, timeout);
		} catch (Exception e) {
			System.out.println("Data successfully popup not found");
		}
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
		SeleniumUtils.click(driver, previewNextButton, timeout);
	}
}
