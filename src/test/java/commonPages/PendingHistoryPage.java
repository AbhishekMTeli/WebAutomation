package commonPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class PendingHistoryPage {
	private WebDriver driver;
	private int timeout;

	public PendingHistoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//span[normalize-space()='Pending Grading']")
	private WebElement pendingGradingLabel;

	@FindBy(xpath = "//table[@id='AimsDatatableCompleted']//th[normalize-space()='Curriculum']")
	private WebElement curriculumLabel;

	@FindBy(xpath = "//table[@id='AimsDatatableCompleted']//th[normalize-space()='Lesson']")
	private WebElement lessonLabel;

	@FindBy(xpath = "//table[@id='AimsDatatableCompleted']//th[normalize-space()='Trainees']")
	private WebElement traineesLabel;

	@FindBy(xpath = "//table[@id='AimsDatatableCompleted']//th[normalize-space()='Sectors']")
	private WebElement sectorsLabel;

	public void validatePengingGradingStaticTexts() {
		SeleniumUtils.scrollToElementByVisibleText(driver, pendingGradingLabel.getText());
		SeleniumUtils.waitForVisibility(driver, pendingGradingLabel, timeout);
		Assert.assertEquals(pendingGradingLabel.getText(), "Pending Grading",
				"Text mismatch expected is 'Pending Grading' but got' " + pendingGradingLabel.getText());
		Assert.assertEquals(curriculumLabel.getText(), "Curriculum",
				"Text mismatch expected is 'Curriculum' but got " + curriculumLabel.getText());
		Assert.assertEquals(lessonLabel.getText(), "Lesson",
				"Text mismatch expected is 'Lesson' but got " + lessonLabel.getText());
		Assert.assertEquals(traineesLabel.getText(), "Trainees",
				"Text mismatch expected is 'Trainees' but got " + traineesLabel.getText());
		Assert.assertEquals(sectorsLabel.getText(), "Sectors",
				"Text mismatch expected is 'Sectors' but got " + sectorsLabel.getText());
	}

	@FindBy(xpath = "//div[@id='AimsDatatableCompleted_wrapper']//input[@class=' form-control search_field']")
	private WebElement searchTextField;

	@FindBy(xpath = "//button[normalize-space()='Review']")
	private List<WebElement> reviewButtons;

	@FindBy(xpath = "//label[normalize-space(text())='Do you want to update the general info?']")
	private WebElement updateGeneralInfoPopupLabel;

	@FindBy(xpath = "//button[@id='submitEditedGeneralInfo_button_no']")
	private WebElement updateGeneralInfoPopupNoButton;

	@FindBy(xpath = "//button[@id='submitEditedGeneralInfo_button_yes']")
	private WebElement updateGeneralInfoPopupYesButton;

	public void enterSearchText(String traineeId) {
		SeleniumUtils.type(driver, searchTextField, traineeId, timeout);
	}

	public void clickReviewButton() {
		SeleniumUtils.click(driver, reviewButtons.get(0), timeout);
	}

	public void clickUpdateGeneralInfoPopupYesButton() {
		SeleniumUtils.click(driver, updateGeneralInfoPopupYesButton, timeout);
	}

	public void clickUpdateGeneralInfoPopupNoButton() {
		SeleniumUtils.click(driver, updateGeneralInfoPopupNoButton, timeout);
	}

	public void validateUpdateGeneralInfoPopupLabelText() {
		SeleniumUtils.waitForVisibility(driver, updateGeneralInfoPopupLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(updateGeneralInfoPopupLabel),
				"Do you want to update the general info?",
				"Text mismatch expected is 'Do you want to update the general info?' but got "
						+ SeleniumUtils.getText(updateGeneralInfoPopupLabel));
	}

	@FindBy(xpath = "//button[normalize-space()='Feedback']")
	private List<WebElement> feedbackButtons;

	@FindBy(xpath = "//textarea[@id='feedbackText']")
	private WebElement feedbackTextAreaField;

	@FindBy(xpath = "//button[@id='feedbackSubmitButton']")
	private WebElement feedbackSubmitButton;

	public void enterFeedBack(String feedbackComment) {
		SeleniumUtils.type(driver, feedbackTextAreaField, feedbackComment, timeout);
	}

	public void clickFeedbackSubmitButton() {
		SeleniumUtils.click(driver, feedbackSubmitButton, timeout);
	}

	public void clickFeedbackButton(String feedbackComment) {
		try {
			for (WebElement feedbackButton : feedbackButtons) {
				SeleniumUtils.scrollToElementByVisibleText(driver, feedbackButton.getText());
				SeleniumUtils.click(driver, feedbackButton, timeout);
				enterFeedBack(feedbackComment);
				clickFeedbackSubmitButton();
				clickPopupOkButton();
			}
		} catch (Exception e) {
			System.out.println("Feedback buttons not found");
		}
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
