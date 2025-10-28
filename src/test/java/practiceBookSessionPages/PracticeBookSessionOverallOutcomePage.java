package practiceBookSessionPages;

import java.time.Duration;

import org.openqa.selenium.By;
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

public class PracticeBookSessionOverallOutcomePage {
	private WebDriver driver;
	private int timeout;

	public PracticeBookSessionOverallOutcomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//div[@class='col-sm-6']//span[@id='lhsDesignation']")
	private WebElement lhsDesignationLabel;

	@FindBy(xpath = "//div[@id='ooc_Grading_ID']//span[@id='rhsDesignation']")
	private WebElement rhsDesignationLabel;

	@FindBy(xpath = "//div[@id='overall_Asst_LHS']//h4[@class='panel-title'][normalize-space()='OVERALL ASSESSMENT']")
	private WebElement lhsOverallAssessmentLable;

	@FindBy(xpath = "//div[@id='overall_Asst_RHS']//h4[@class='panel-title'][normalize-space()='OVERALL ASSESSMENT']")
	private WebElement rhsOverallAssessmentLabel;

	public void validatePBSOverallOutComePage() {
		SeleniumUtils.waitForVisibility(driver, lhsDesignationLabel, timeout);
		Assert.assertTrue(SeleniumUtils.getText(lhsDesignationLabel).contains("Designation -"),
				"LHS designation label text mismatch");
		Assert.assertTrue(SeleniumUtils.getText(rhsDesignationLabel).contains("Designation -"),
				"RHS designation label text mismatch");
		Assert.assertEquals(SeleniumUtils.getText(lhsOverallAssessmentLable), "OVERALL ASSESSMENT",
				"Text Mismatch expected : OVERALL ASSESSMENT but got : "
						+ SeleniumUtils.getText(lhsOverallAssessmentLable));
		Assert.assertEquals(SeleniumUtils.getText(rhsOverallAssessmentLabel), "OVERALL ASSESSMENT",
				"Text Mismatch expected : OVERALL ASSESSMENT but got : "
						+ SeleniumUtils.getText(rhsOverallAssessmentLabel));
	}

	@FindBy(xpath = "//input[@id='LHS_C']")
	private WebElement lhsCompetentRadioButton;

	@FindBy(xpath = "//input[@id='RHS_C']")
	private WebElement rhsCompetentRadioButton;

	@FindBy(xpath = "//input[@id='LHS_ATR']")
	private WebElement lhsNotYetCompetentRadioButton;

	@FindBy(xpath = "//input[@id='RHS_ATR']")
	private WebElement rhsNotYetCompetentRadioButton;

	public void selectRHSCompetentRadioButton() {
		SeleniumUtils.click(driver, rhsCompetentRadioButton, timeout);
	}

	public void selectLHSCompetentRadioButton() {
		SeleniumUtils.click(driver, lhsCompetentRadioButton, timeout);
	}

	public void selectLHSNotYetCompetentRadioButton() {
		SeleniumUtils.click(driver, lhsNotYetCompetentRadioButton, timeout);
	}

	public void selectRHSNotYetCompetentRadioButton() {
		SeleniumUtils.click(driver, rhsNotYetCompetentRadioButton, timeout);
	}

	public boolean rhsNotYetCompetentIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsOverallAssessmentLable.getText());
		SeleniumUtils.waitForVisibility(driver, rhsNotYetCompetentRadioButton, timeout);
		return rhsNotYetCompetentRadioButton.isSelected();
	}

	public boolean lhsNotYetCompetentIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsOverallAssessmentLable.getText());
		SeleniumUtils.waitForVisibility(driver, lhsNotYetCompetentRadioButton, timeout);
		return lhsNotYetCompetentRadioButton.isSelected();
	}

	public boolean lhsCompetentIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsOverallAssessmentLable.getText());
		SeleniumUtils.waitForVisibility(driver, lhsCompetentRadioButton, timeout);
		return lhsCompetentRadioButton.isSelected();
	}

	public boolean rhsCompetentIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsOverallAssessmentLable.getText());
		SeleniumUtils.waitForVisibility(driver, rhsCompetentRadioButton, timeout);
		return rhsCompetentRadioButton.isSelected();
	}

	@FindBy(xpath = "//textarea[@id='overallcomment_textarea_LHS']")
	private WebElement lhsRemarksTextAreaField;

	@FindBy(xpath = "//textarea[@id='overallcomment_textarea_RHS']")
	private WebElement rhsRemarksTextAreaField;

	@FindBy(xpath = "//div[@id='overallOC_txtArea_LHS']//label[@for='comment'][normalize-space()='REMARKS']")
	private WebElement lhsRemarksLabel;

	@FindBy(xpath = "//div[@id='overallOC_txtArea_RHS']//label[@for='comment'][normalize-space()='REMARKS']")
	private WebElement rhsRemarksLabel;

	public void enterLHSRemarks(String remarks) {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(lhsRemarksLabel));
		SeleniumUtils.waitForVisibility(driver, lhsRemarksTextAreaField, timeout);
		SeleniumUtils.type(driver, lhsRemarksTextAreaField, remarks, timeout);
	}

	public void enterRHSRemarks(String remarks) {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(rhsRemarksLabel));
		SeleniumUtils.waitForVisibility(driver, rhsRemarksTextAreaField, timeout);
		SeleniumUtils.type(driver, rhsRemarksTextAreaField, remarks, timeout);
	}

	@FindBy(xpath = "//div[@id='textarea_feedback_LHS']//b")
	private WebElement lhsRemainingCharacterLabel;

	@FindBy(xpath = "//div[@id='textarea_feedback_RHS']//b")
	private WebElement rhsRemainingCharacterLabel;

	public int lhsCharacterCount() throws InterruptedException {
		Thread.sleep(1000);
		return Integer.parseInt(SeleniumUtils.getText(lhsRemainingCharacterLabel).replaceAll("[^0-9]", ""));
	}

	public int rhsCharacterCount() throws InterruptedException {
		Thread.sleep(1000);
		return Integer.parseInt(SeleniumUtils.getText(rhsRemainingCharacterLabel).replaceAll("[^0-9]", ""));
	}

	@FindBy(xpath = "//label[text()='LVTO']/parent::td/following-sibling::td/input[@id='Lvto_YES']")
	private WebElement lhsLVTOYesCheckBox;

	@FindBy(xpath = "//label[text()='LVTO']/parent::td/following-sibling::td/input[@id='Lvto_YES_rhs']")
	private WebElement rhsLVTOYesCheckBox;

	@FindBy(xpath = "//label[text()='LVTO']/parent::td/following-sibling::td/input[@id='Lvto_NO']")
	private WebElement lhsLVTONoCheckBox;

	@FindBy(xpath = "//label[text()='LVTO']/parent::td/following-sibling::td/input[@id='Lvto_NO_rhs']")
	private WebElement rhsLVTONoCheckBox;

	public void clickRHSYesLVTOCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, rhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, rhsLVTOYesCheckBox, timeout);
	}

	public void clickLHSYesLVTOCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, lhsLVTOYesCheckBox, timeout);
	}

	public void clickRHSNoLVTOCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, rhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, rhsLVTONoCheckBox, timeout);
	}

	public void clickLHSNoLVTOCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, lhsLVTONoCheckBox, timeout);
	}

	public boolean lhsLVTOYesIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, lhsLVTOYesCheckBox, timeout);
		return lhsLVTOYesCheckBox.isSelected();
	}

	public boolean rhsLVTOYesIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, rhsLVTOYesCheckBox, timeout);
		return rhsLVTOYesCheckBox.isSelected();
	}

	public boolean lhsLVTONoIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, lhsLVTONoCheckBox, timeout);
		return lhsLVTONoCheckBox.isSelected();
	}

	public boolean rhsLVTONoIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, rhsLVTONoCheckBox, timeout);
		return rhsLVTONoCheckBox.isSelected();
	}

	@FindBy(xpath = "//input[@id='Cat_YES']")
	private WebElement lhsCATYesCheckBox;

	@FindBy(xpath = "//input[@id='Cat_YES_rhs']")
	private WebElement rhsCATYesCheckBox;

	@FindBy(xpath = "//input[@id='Cat_NO']")
	private WebElement lhsCATNoCheckBox;

	@FindBy(xpath = "//input[@id='Cat_NO_rhs']")
	private WebElement rhsCATNoCheckBox;

	public void clickRHSYesCATCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, rhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, rhsCATYesCheckBox, timeout);
	}

	public void clickLHSYesCATCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, lhsCATYesCheckBox, timeout);
	}

	public void clickRHSNoCATCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, rhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, rhsCATNoCheckBox, timeout);
	}

	public void clickLHSNoCATCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, lhsCATNoCheckBox, timeout);
	}

	public boolean lhsCATYesIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, lhsCATYesCheckBox, timeout);
		return lhsCATYesCheckBox.isSelected();
	}

	public boolean rhsCATYesIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, rhsCATYesCheckBox, timeout);
		return rhsCATYesCheckBox.isSelected();
	}

	public boolean lhsCATNoIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, lhsCATNoCheckBox, timeout);
		return lhsCATNoCheckBox.isSelected();
	}

	public boolean rhsCATNoIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, rhsCATNoCheckBox, timeout);
		return rhsCATNoCheckBox.isSelected();
	}

	@FindBy(xpath = "//h2[normalize-space(text())='QUALIFICATION:']")
	private WebElement qualificationLabel;

	@FindBy(xpath = "//select[@id='TRIDE']")
	private WebElement qualificationDropdown;

	@FindBy(xpath = "//button[@id='overallDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='overallOC_next']")
	private WebElement saveAndNextButton;

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

	@FindBy(xpath = "//input[@id='Additional_YES']")
	private WebElement numberOfApprochesLHS3CheckBox;

	@FindBy(xpath = "//input[@id='Additional_YES_rhs']")
	private WebElement numberOfApprochesRHS3CheckBox;

	@FindBy(xpath = "//input[@id='Additional_NO']")
	private WebElement numberOfApprochesLHS8CheckBox;

	@FindBy(xpath = "//input[@id='Additional_NO_rhs']")
	private WebElement numberOfApprochesRHS8CheckBox;

	public void clickRHSNumberOfApproaches3CheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, rhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, numberOfApprochesRHS3CheckBox, timeout);
	}

	public void clickLHSNumberOfApproaches3CheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, numberOfApprochesLHS3CheckBox, timeout);
	}

	public void clickRHSNumberOfApproaches8CheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, rhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, numberOfApprochesRHS8CheckBox, timeout);
	}

	public void clickLHSNumberOfApproaches8CheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, numberOfApprochesLHS8CheckBox, timeout);
	}

	public boolean rHSNumberOfApproaches3IsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, numberOfApprochesRHS3CheckBox, timeout);
		return numberOfApprochesRHS3CheckBox.isSelected();
	}

	public boolean rHSNumberOfApproaches8IsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, numberOfApprochesRHS8CheckBox, timeout);
		return numberOfApprochesRHS8CheckBox.isSelected();
	}

	public boolean lHSNumberOfApproaches3IsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, numberOfApprochesLHS3CheckBox, timeout);
		return numberOfApprochesLHS3CheckBox.isSelected();
	}

	public boolean lHSNumberOfApproaches8IsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, numberOfApprochesLHS8CheckBox, timeout);
		return numberOfApprochesLHS8CheckBox.isSelected();
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
