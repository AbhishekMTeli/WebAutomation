package adminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class SLFHistoryPage {
	private WebDriver driver;
	private int timeout;

	public SLFHistoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//i[@class='icon wb-plus']")
	private WebElement addButton;

	// SLF Sectors Details
	@FindBy(xpath = "//input[@id='sectors_radiobtn']")
	private WebElement sectorsRadioButton;

	@FindBy(xpath = "//input[@id='cumulativehours_radiobtn']")
	private WebElement cumulativeHoursRadioButton;

	// Sectors Radio Button Options
	@FindBy(xpath = "//h4[normalize-space(text())='Trainee Name']")
	private WebElement traineeNameLabel;

	@FindBy(xpath = "//h4[normalize-space(text())='Curriculum']")
	private WebElement curriculumLabel;

	@FindBy(xpath = "//h4[normalize-space(text())='Aircraft type']")
	private WebElement aircraftTypeLabel;

	@FindBy(xpath = "//h4[normalize-space(text())='PM']")
	private WebElement pmLabel;

	@FindBy(xpath = "//h4[normalize-space(text())='PF']")
	private WebElement pfLabel;

	@FindBy(xpath = "//input[@id='formName']")
	private WebElement traineeNameTextfield;

	@FindBy(xpath = "//select[@id='curriculum']")
	private WebElement curriculumDropdown;

	@FindBy(xpath = "//select[@id='a320Type']")
	private WebElement aircraftTypeDropdown;

	@FindBy(xpath = "//input[@id='pma320']")
	private WebElement pmTextField;

	@FindBy(xpath = "//input[@id='pfa320']")
	private WebElement pfTextField;

	@FindBy(xpath = "//i[@class='fas fa-plus']")
	private WebElement addIcon;

	@FindBy(xpath = "//button[@class='btn btn-danger remove-div']")
	private WebElement deleteIcon;

	@FindBy(xpath = "//button[@id='update']")
	private WebElement saveButton;

	@FindBy(xpath = "//button[@id='cancel']")
	private WebElement cancelButton;

	// Cumulative Hours
	@FindBy(xpath = "//h4[normalize-space(text())='Cumulative Hours:']")
	private WebElement cumulativeHoursLabel;

	@FindBy(xpath = "//input[@id='Duration_Inp']")
	private WebElement cumulativeHoursTextField;

	@FindBy(xpath = "//h4[text()='SLF Sectors Details']/preceding-sibling::button//span[text()='X']")
	private WebElement closeIcon;

	// Get text from labels
	public String getTraineeNameLabel() {
		SeleniumUtils.waitForVisibility(driver, traineeNameLabel, timeout);
		return SeleniumUtils.getText(traineeNameLabel);
	}

	public void clickCloseIcon() {
		SeleniumUtils.click(driver, closeIcon, timeout);
	}

	public String getCurriculumLabel() {
		SeleniumUtils.waitForVisibility(driver, curriculumLabel, timeout);
		return SeleniumUtils.getText(curriculumLabel);
	}

	public String getAircraftTypeLabel() {
		SeleniumUtils.waitForVisibility(driver, aircraftTypeLabel, timeout);
		return SeleniumUtils.getText(aircraftTypeLabel);
	}

	public String getPmLabel() {
		SeleniumUtils.waitForVisibility(driver, pmLabel, timeout);
		return SeleniumUtils.getText(pmLabel);
	}

	public String getPfLabel() {
		SeleniumUtils.waitForVisibility(driver, pfLabel, timeout);
		return SeleniumUtils.getText(pfLabel);
	}

	public String getCumulativeHoursLabel() {
		SeleniumUtils.waitForVisibility(driver, cumulativeHoursLabel, timeout);
		return SeleniumUtils.getText(cumulativeHoursLabel);
	}

	// Click Add button
	public void clickAddButton() {
		SeleniumUtils.click(driver, addButton, timeout);
	}

	// Select Sectors radio button
	public void selectSectorsRadioButton() {
		SeleniumUtils.click(driver, sectorsRadioButton, timeout);
	}

	// Select Cumulative Hours radio button
	public void selectCumulativeHoursRadioButton() {
		SeleniumUtils.click(driver, cumulativeHoursRadioButton, timeout);
	}

	// Enter Trainee Name
	public void enterTraineeName(String traineeName) {
		traineeNameTextfield.clear();
		SeleniumUtils.type(driver, traineeNameTextfield, traineeName, timeout);
	}

	// Select Curriculum
	public void selectCurriculum(String curriculum) throws InterruptedException {
		SeleniumUtils.selectDropdownByVisibleText(driver, curriculumDropdown, curriculum, timeout);
	}

	// Select Aircraft Type
	public void selectAircraftType(String aircraftType) throws InterruptedException {
		SeleniumUtils.selectDropdownByVisibleText(driver, aircraftTypeDropdown, aircraftType, timeout);
	}

	// Enter PM value
	public void enterPM(String pm) {
		pmTextField.clear();
		SeleniumUtils.type(driver, pmTextField, pm, timeout);
	}

	// Enter PF value
	public void enterPF(String pf) {
		pfTextField.clear();
		SeleniumUtils.type(driver, pfTextField, pf, timeout);
	}

	// Click Add Icon
	public void clickAddIcon() {
		SeleniumUtils.click(driver, addIcon, timeout);
	}

	// Click Delete Icon
	public void clickDeleteIcon() {
		SeleniumUtils.click(driver, deleteIcon, timeout);
	}

	// Click Save
	public void clickSaveButton() {
		SeleniumUtils.click(driver, saveButton, timeout);
	}

	// Click Cancel
	public void clickCancelButton() {
		SeleniumUtils.click(driver, cancelButton, timeout);
	}

	// Enter Cumulative Hours
	public void enterCumulativeHours(String hours) {
		cumulativeHoursTextField.clear();
		SeleniumUtils.type(driver, cumulativeHoursTextField, hours, timeout);
	}

	public void validateAllStaticTexts() {
		Assert.assertEquals(getTraineeNameLabel(), "Trainee Name", "❌ Trainee Name label mismatch!");
		Assert.assertEquals(getCurriculumLabel(), "Curriculum", "❌ Curriculum label mismatch!");
		Assert.assertEquals(getAircraftTypeLabel(), "Aircraft type", "❌ Aircraft Type label mismatch!");
		Assert.assertEquals(getPmLabel(), "PM", "❌ PM label mismatch!");
		Assert.assertEquals(getPfLabel(), "PF", "❌ PF label mismatch!");
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
	// Command Upgrade Course

	// Please enter a valid staff number.
}
