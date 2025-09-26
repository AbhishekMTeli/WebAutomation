package commandUpgradeSLFPages;

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

public class CommandUpgradeSLFOverallOutcomePage {
	private WebDriver driver;
	private int timeout;

	public CommandUpgradeSLFOverallOutcomePage(WebDriver driver) {
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
	@FindBy(xpath = "//input[@id='LHS_C']")
	private WebElement competentRadioButton;

	@FindBy(xpath = "//input[@id='LHS_ATR']")
	private WebElement notYetCompetentRadioButton;

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

	// ---- Dynamic plus/minus buttons retrieval ----
	public List<WebElement> getPlusButtonsForTable(String competencyTableId) {
		String plusXpath = String.format("//table[contains(@id,'%s')]//td/button[contains(@class,'fa-plus')]",
				competencyTableId);
		return driver.findElements(By.xpath(plusXpath));
	}

	public List<WebElement> getMinusButtonsForTable(String competencyTableId) {
		String minusXpath = String.format("//table[contains(@id,'%s')]//td/button[contains(@class,'fa-minus')]",
				competencyTableId);
		return driver.findElements(By.xpath(minusXpath));
	}

	public void clickAllPlusButtons(String competencyTableId) {
		for (WebElement plus : getPlusButtonsForTable(competencyTableId)) {
			SeleniumUtils.click(driver, plus, timeout);
		}
	}

	public void clickAllMinusButtons(String competencyTableId) {
		for (WebElement minus : getMinusButtonsForTable(competencyTableId)) {
			SeleniumUtils.click(driver, minus, timeout);
		}
	}

	public void clickGradingCell(String section, int optionIndex) {
		String xpath = String.format("//td[@id='LHS_%s_%d']", section, optionIndex);
		By cellBy = By.xpath(xpath);
		int maxScrolls = 5;
		int attempts = 0;
		WebElement cell = null;

		while (attempts < maxScrolls) {
			List<WebElement> cells = driver.findElements(cellBy);
			if (!cells.isEmpty()) {
				cell = cells.get(0);
				break;
			} else {
				SeleniumUtils.scrollToElementByVisibleText(driver, section);
			}
			attempts++;
		}

		if (cell == null) {
			throw new RuntimeException("Grading cell not found after scrolling: " + section + " option " + optionIndex);
		}

		SeleniumUtils.waitForClickability(driver, cell, timeout);
		SeleniumUtils.click(driver, cell, timeout);
	}

	public List<String> getValidationTextStrings() {
		return SeleniumUtils.getTexts(validationTexts);
	}

	public void enterObComment(String comment, String competencyPrefix) {
		WebElement textarea = getObCommentTextarea(competencyPrefix);
		if (comment == null) {
			comment = ""; // convert null to empty string
		}
		SeleniumUtils.type(driver, textarea, comment, timeout);
	}

	public WebElement getObCommentTextarea(String competencyPrefix) {
		String xpath = String.format(
				"//div[text()='Characters remaining: 3000']/preceding-sibling::textarea[contains(@id,'%s_competency_comment_txtarea')]",
				competencyPrefix);
		return driver.findElement(By.xpath(xpath));
	}

	public void performObAction(String section, int optionIndex, String action, String comment) {
		clickGradingCell(section, optionIndex);

		switch (action.toLowerCase()) {
		case "plus":
			clickAllPlusButtons(section + "_Task_Table_");
			break;
		case "minus":
			clickAllMinusButtons(section + "_Task_Table_");
			break;
		default:
			System.out.println(("Invalid action: " + action));
		}
		enterObComment(comment, section);
	}

	public void clickObDoneButton(String section) {
		String xpath = String.format(
				"//button[contains(@class, 'btn-success') and contains(@onclick,'save_overall') and contains(@onclick,'%s')]",
				section);
		WebElement obDoneButton = driver.findElement(By.xpath(xpath));
		SeleniumUtils.click(driver, obDoneButton, timeout);
	}

	public void clickObCancelButton(String section) {
		String xpath = String.format(
				"//button[contains(@class, 'btn-success') and contains(@onclick,'save_overall') and contains(@onclick,'%s')]/preceding-sibling::a",
				section);
		WebElement obCancelButton = driver.findElement(By.xpath(xpath));
		SeleniumUtils.click(driver, obCancelButton, timeout);
	}

	public void validateRadioSelectionBasedOnOptionIndex(int optionIndex) throws InterruptedException {
		switch (optionIndex) {
		case 1:
			// Auto-select "NOT YET COMPETENT"
			Assert.assertTrue(notYetCompetentRadioButton.isSelected(),
					"Expected 'NOT YET COMPETENT' to be auto-selected for optionIndex 1");
			break;
		case 2:
			// User must select manually
			if (!competentRadioButton.isSelected() && !notYetCompetentRadioButton.isSelected()) {
				// Default: select "NOT YET COMPETENT" (or you can pick "COMPETENT")
				clickNotYetCompetentRadioButton();
			}
			Assert.assertTrue(competentRadioButton.isSelected() || notYetCompetentRadioButton.isSelected(),
					"Expected one radio button to be selected for optionIndex 2");
			break;
		case 3:
		case 4:
		case 5:
			// Auto-select "COMPETENT"
			Assert.assertTrue(competentRadioButton.isSelected(),
					"Expected 'COMPETENT' to be auto-selected for optionIndex " + optionIndex);
			break;
		default:
			throw new IllegalArgumentException("Unexpected optionIndex: " + optionIndex);
		}
	}

	public void clickCompetentRadioButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, overallAssessmentLabel.getText());
		SeleniumUtils.click(driver, competentRadioButton, timeout);
	}

	public void clickNotYetCompetentRadioButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, overallAssessmentLabel.getText());
		SeleniumUtils.click(driver, notYetCompetentRadioButton, timeout);
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

	public void selectQualification() throws InterruptedException {
		String qualification = ConfigReader.get("qualification");
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
		SeleniumUtils.waitForPresence(driver, By.xpath("//h5[text()='Reason for Delayed Grading']"), timeout);
	}

	public void clickSubmitCommentButtonForDelayComment() {
		SeleniumUtils.click(driver, reasonForDelaySubmitButton, timeout);
	}

	public void addDelayComments(String comment) {
		SeleniumUtils.type(driver, reasonForDelayCommentTextArea, comment, timeout);
	}

	public void clickCloseCommentButtonForDelayComment() {
		SeleniumUtils.click(driver, reasonForDelayCloseButton, timeout);
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
