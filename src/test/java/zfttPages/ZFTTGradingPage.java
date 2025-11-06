package zfttPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class ZFTTGradingPage {
	private WebDriver driver;
	private int timeout;
	private Actions actions;

	public ZFTTGradingPage(WebDriver driver) {
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

	@FindBy(xpath = "//h2[normalize-space(text())='Registration:']")
	private WebElement registrationNumberLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Device:']")
	private WebElement deviceLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Location:']")
	private WebElement locationLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Scheduled Date:']")
	private WebElement scheduledDatelabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Duration:']")
	private WebElement durationLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Seat Occupied:']")
	private WebElement seatOccupiedLabel;

	@FindBy(xpath = "//div[@id='LFUS_nav_body']//span[@id='lhsDesignation']")
	private WebElement designationLabel;

	public void validateAllBaseTrainingGradingPageTexts() {
		SeleniumUtils.waitForVisibility(driver, registrationNumberLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(registrationNumberLabel), "Registration: *",
				"Registration Number label text mismatch");

		SeleniumUtils.waitForVisibility(driver, deviceLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(deviceLabel), "Device: *", "Device label text mismatch");

		SeleniumUtils.waitForVisibility(driver, locationLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(locationLabel), "Location: *", "Location label text mismatch");

		SeleniumUtils.waitForVisibility(driver, scheduledDatelabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(scheduledDatelabel), "Scheduled Date: *", "Scheduled Date label text mismatch");

		SeleniumUtils.waitForVisibility(driver, durationLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(durationLabel), "Duration: *", "Duration label text mismatch");

		SeleniumUtils.waitForVisibility(driver, seatOccupiedLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(seatOccupiedLabel), "Seat Occupied: *",
				"Seat Occupied label text mismatch");

		SeleniumUtils.waitForVisibility(driver, designationLabel, timeout);
		String actualDesignationText = SeleniumUtils.getText(designationLabel);
		String expectedDesignationSubstring = "Designation - ";
		Assert.assertTrue(actualDesignationText.contains(expectedDesignationSubstring),
				"Designation label text mismatch. Expected to contain: '" + expectedDesignationSubstring
						+ "' but found: '" + actualDesignationText + "'");
	}

	@FindBy(xpath = "//div[@id='LFUS_nav_body']//span[@id='lhsDesignation']/preceding-sibling::span[@class='text-truncate Tfont Traineename']")
	private WebElement lhsUsernameAndId;

	public String getTraineeUsernameAndId() {
		return SeleniumUtils.getText(lhsUsernameAndId);
	}

	@FindBy(xpath = "//input[@id='RegNo']")
	private WebElement registrationNumberTextField;

	@FindBy(xpath = "//select[@id='LOCATION']")
	private WebElement locationDropdown;

	@FindBy(xpath = "//input[@id='Duration']")
	private WebElement durationTextField;

	@FindBy(xpath = "//select[@id='SEATOCCUPIED']")
	private WebElement seatOccupiedDropdown;

	public void enterRegistrationNumber(String from) {
		registrationNumberTextField.clear();
		SeleniumUtils.type(driver, registrationNumberTextField, from, timeout);
	}

	public void selectLocation(String location) {
		SeleniumUtils.selectDropdownByValue(driver, locationDropdown, location, timeout);
	}

	public void enterDuration(String duration) {
		SeleniumUtils.type(driver, durationTextField, duration, timeout);
	}

	public void selectSeatOccupied(String aircraftType) {
		SeleniumUtils.selectDropdownByValue(driver, seatOccupiedDropdown, aircraftType, timeout);
	}

	@FindBy(xpath = "//button[@id='eventGradingDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='eventGrading']")
	private WebElement saveAndNextButton;

	public void clickDiscardButton() {
		SeleniumUtils.waitForClickability(driver, discardButton, timeout);
		SeleniumUtils.click(driver, discardButton, timeout);
	}

	public void clickSaveAndNextButton() {
		SeleniumUtils.waitForClickability(driver, saveAndNextButton, timeout);
		SeleniumUtils.click(driver, saveAndNextButton, timeout);
	}
}
