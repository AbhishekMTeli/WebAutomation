package cat_II_III_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CAT_II_III_OverallOutcomePage {
	private WebDriver driver;
	private int timeout;

	public CAT_II_III_OverallOutcomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
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
		SeleniumUtils.waitForPresence(driver, By.xpath("//span[@id='alertBoxMsg']"), timeout);
	}

	public void clickOkPop_up() {
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
		SeleniumUtils.click(driver, previewNextButton, timeout);
	}
}
