package practiceBookSessionPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

	@FindBy(xpath = "//input[@id='LHS_ATR']")
	private WebElement rhsCompetentRadioButton;

	@FindBy(xpath = "//input[@id='RHS_C']")
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

	public void rhsNotYetCompetentIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsOverallAssessmentLable.getText());
		SeleniumUtils.waitForVisibility(driver, rhsNotYetCompetentRadioButton, timeout);
		rhsNotYetCompetentRadioButton.isSelected();
	}

	public void lhsNotYetCompetentIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsOverallAssessmentLable.getText());
		SeleniumUtils.waitForVisibility(driver, lhsNotYetCompetentRadioButton, timeout);
		lhsNotYetCompetentRadioButton.isSelected();
	}

	public void lhsCompetentIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsOverallAssessmentLable.getText());
		SeleniumUtils.waitForVisibility(driver, lhsCompetentRadioButton, timeout);
		lhsCompetentRadioButton.isSelected();
	}

	public void rhsCompetentIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsOverallAssessmentLable.getText());
		SeleniumUtils.waitForVisibility(driver, rhsCompetentRadioButton, timeout);
		rhsCompetentRadioButton.isSelected();
	}

	@FindBy(xpath = "//textarea[@id='overallcomment_textarea_LHS']")
	private WebElement lhsRemarksTextAreaField;

	@FindBy(xpath = "//input[@id='RHS_ATR']")
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

	@FindBy(xpath = "//div[@id='textarea_feedback_LHS']//b[contains(text(),'6000 characters remaining')]")
	private WebElement lhsRemainingCharacterLabel;

	@FindBy(xpath = "//div[@id='textarea_feedback_RHS']//b[contains(text(),'6000 characters remaining')]")
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

	public void lhsLVTOYesIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, lhsLVTOYesCheckBox, timeout);
		lhsLVTOYesCheckBox.isSelected();
	}

	public void rhsLVTOYesIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, rhsLVTOYesCheckBox, timeout);
		rhsLVTOYesCheckBox.isSelected();
	}

	public void lhsLVTONoIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, lhsLVTONoCheckBox, timeout);
		lhsLVTONoCheckBox.isSelected();
	}

	public void rhsLVTONoIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, rhsLVTONoCheckBox, timeout);
		rhsLVTONoCheckBox.isSelected();
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
		SeleniumUtils.click(driver, rhsLVTOYesCheckBox, timeout);
	}

	public void clickLHSYesCATCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, lhsLVTOYesCheckBox, timeout);
	}

	public void clickRHSNoCATCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, rhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, rhsLVTONoCheckBox, timeout);
	}

	public void clickLHSNoCATCheckBox() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.click(driver, lhsLVTONoCheckBox, timeout);
	}

	public void lhsCATYesIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, lhsLVTOYesCheckBox, timeout);
		lhsLVTOYesCheckBox.isSelected();
	}

	public void rhsCATYesIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, rhsLVTOYesCheckBox, timeout);
		rhsLVTOYesCheckBox.isSelected();
	}

	public void lhsCATNoIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, lhsLVTONoCheckBox, timeout);
		lhsLVTONoCheckBox.isSelected();
	}

	public void rhsCATNoIsSelected() {
		SeleniumUtils.scrollToElementByVisibleText(driver, lhsRemainingCharacterLabel.getText());
		SeleniumUtils.waitForVisibility(driver, rhsLVTONoCheckBox, timeout);
		rhsLVTONoCheckBox.isSelected();
	}

	@FindBy(xpath = "//h2[normalize-space(text())='QUALIFICATION:']")
	private WebElement qualificationLabel;

	@FindBy(xpath = "//select[@id='TRIDE']")
	private WebElement qualificationDropdown;

	@FindBy(xpath = "//button[@id='overallDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='overallOC_next']")
	private WebElement saveAndNextButton;
}
