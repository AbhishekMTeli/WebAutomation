package baseTrainingPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class BaseTrainingGradingPage {
	private WebDriver driver;
	private int timeout;
	private Actions actions;

	public BaseTrainingGradingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
		this.actions = new Actions(driver);
	}

	@FindBy(xpath = "//a[@id='lessondesc']")
	private WebElement lessonHeaderLabel;

	public void validateLessonName() {
		SeleniumUtils.waitForVisibility(driver, lessonHeaderLabel, timeout);
		String actualLesson = SeleniumUtils.getText(lessonHeaderLabel);
		String expectedLesson = "A320 AIRCRAFT FAMILIARISATION (BASE) TRAINING";
		Assert.assertEquals(actualLesson, expectedLesson,
				"Lesson Text Mismatch expected is : " + expectedLesson + " but got : " + actualLesson);
	}

	@FindBy(xpath = "//a[@id='ebtETG']")
	private WebElement gradingTab;

	@FindBy(xpath = "//a[@id='ebtOOC']")
	private WebElement overallOutcomeTab;

	@FindBy(xpath = "//a[@id='ebthistory']")
	private WebElement historyTab;

	@FindBy(xpath = "//span[@class='glyphicon glyphicon-home']")
	private WebElement homeIcon;

	public void clickGradingTab() {
		SeleniumUtils.click(driver, gradingTab, timeout);
	}

	public void clickOverallOutcomeTab() {
		SeleniumUtils.click(driver, overallOutcomeTab, timeout);
	}

	public void clickHistoryTab() {
		SeleniumUtils.click(driver, historyTab, timeout);
	}

	public void clickHomeIcon() {
		SeleniumUtils.click(driver, homeIcon, timeout);
	}

	@FindBy(xpath = "//h2[normalize-space(text())='Registration No:']")
	private WebElement registrationNumberLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Date:']")
	private WebElement dateLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='From :']")
	private WebElement fromLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='To :']")
	private WebElement tolabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Aircraft Type:']")
	private WebElement aircraftTypeLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Seat Occupied:']")
	private WebElement seatOccupiedLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Take off and landings Conducted']")
	private WebElement takeOffAndLandingsConductedLabel;

	@FindBy(xpath = "//div[@id='LHS_GradingData']//span[@id='lhsDesignation']")
	private WebElement designationLabel;

	public void validateAllBaseTrainingGradingPageTexts() {
		SeleniumUtils.waitForVisibility(driver, registrationNumberLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(registrationNumberLabel), "Registration No: *",
				"Registration Number label text mismatch");

		SeleniumUtils.waitForVisibility(driver, dateLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(dateLabel), "Date: *", "Date label text mismatch");

		SeleniumUtils.waitForVisibility(driver, fromLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(fromLabel), "From : *", "From label text mismatch");

		SeleniumUtils.waitForVisibility(driver, tolabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(tolabel), "To : *", "To label text mismatch");

		SeleniumUtils.waitForVisibility(driver, aircraftTypeLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(aircraftTypeLabel), "Aircraft Type:*",
				"Aircraft Type label text mismatch");

		SeleniumUtils.waitForVisibility(driver, seatOccupiedLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(seatOccupiedLabel), "Seat Occupied: *",
				"Seat Occupied label text mismatch");

		SeleniumUtils.waitForVisibility(driver, takeOffAndLandingsConductedLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(takeOffAndLandingsConductedLabel), "Take off and landings Conducted*",
				"Take off and landings Conducted label text mismatch");

		SeleniumUtils.waitForVisibility(driver, designationLabel, timeout);
		String actualDesignationText = SeleniumUtils.getText(designationLabel);
		String expectedDesignationSubstring = "Designation - ";
		Assert.assertTrue(actualDesignationText.contains(expectedDesignationSubstring),
				"Designation label text mismatch. Expected to contain: '" + expectedDesignationSubstring
						+ "' but found: '" + actualDesignationText + "'");
	}

	@FindBy(xpath = "//div[@id='LHS_GradingData']//span[@id='lhsDesignation']/preceding-sibling::span[@class='text-truncate Tfont Traineename']")
	private WebElement lhsUsernameAndId;

	public String getTraineeUsernameAndId() {
		return SeleniumUtils.getText(lhsUsernameAndId);
	}

	@FindBy(xpath = "//input[@id='RegNo']")
	private WebElement registrationNumberTextField;

	@FindBy(xpath = "//input[@id='fromVal']")
	private WebElement fromTextField;

	@FindBy(xpath = "//input[@id='toVal']")
	private WebElement toTextField;

	@FindBy(xpath = "//select[@id='type']")
	private WebElement aircraftTypeDropdown;

	@FindBy(xpath = "//select[@id='SEATOCCUPIED']")
	private WebElement seatOccupiedDropdown;

	@FindBy(xpath = "//select[@id='TAKEOFFLANDING']")
	private WebElement takeOffAndLandingsConductedDropdown;

	public void enterRegistrationNumber(String from) {
		registrationNumberTextField.clear();
		SeleniumUtils.type(driver, registrationNumberTextField, from, timeout);
	}

	public void enterFrom(String to) {
		fromTextField.clear();
		SeleniumUtils.type(driver, fromTextField, to, timeout);
		actions.sendKeys(Keys.ENTER).perform();
	}

	public void enterTo(String registrationNumber) {
		toTextField.clear();
		SeleniumUtils.type(driver, toTextField, registrationNumber, timeout);
		actions.sendKeys(Keys.ENTER).perform();
	}

	public void selectAircraftType(String aircraftType) {
		SeleniumUtils.selectDropdownByValue(driver, aircraftTypeDropdown, aircraftType, timeout);
	}

	public void selectSeatOccupied(String aircraftType) {
		SeleniumUtils.selectDropdownByValue(driver, seatOccupiedDropdown, aircraftType, timeout);
	}

	public void selectTakeOffAndLandingsConducted(String aircraftType) throws InterruptedException {
		SeleniumUtils.selectDropdownByValue(driver, takeOffAndLandingsConductedDropdown, aircraftType, timeout);
		Thread.sleep(2000);
	}

	@FindBy(xpath = "//button[@id='eventGradingDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='eventGrading']")
	private WebElement saveAndNextButton;

	public void clickDiscardButton() {
		SeleniumUtils.waitForClickability(driver, discardButton, timeout);
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(discardButton));
		SeleniumUtils.click(driver, discardButton, timeout);
	}

	public void clickSaveAndNextButton() {
		SeleniumUtils.waitForClickability(driver, saveAndNextButton, timeout);
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(saveAndNextButton));
		SeleniumUtils.click(driver, saveAndNextButton, timeout);
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
				"//div[@id='%s_competency_txtArea']//ancestor::div[contains(@class,'modal-content')]//button[@class='btn btn-success btn-ranger-save' and @data-cc-modal='%s']",
				section, section);
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
}
