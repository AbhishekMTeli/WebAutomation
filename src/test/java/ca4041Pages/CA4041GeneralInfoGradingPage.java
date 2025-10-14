package ca4041Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CA4041GeneralInfoGradingPage {
	private WebDriver driver;
	private int timeout;

	public CA4041GeneralInfoGradingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//div[@id='LFUS_nav_body']//span[@id='lhsDesignation']")
	private WebElement designationLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Test on:']")
	private WebElement testOnLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Registration Number:']")
	private WebElement registrationNumberLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Location:']")
	private WebElement locationLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Aeroplane Type:']")
	private WebElement aeroplaneTypeLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Scheduled Date:']")
	private WebElement scheduledDateLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Simulator Type:']")
	private WebElement simulatorTypeLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Seat Occupied:']")
	private WebElement seatOccupiedLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Type of Check:']")
	private WebElement typeOfCheckLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='On/Take off:']")
	private WebElement onOrTakeOffLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Off/Landing:']")
	private WebElement offOrLandingLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Time Duration:']")
	private WebElement timeDurationLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='DAY/NIGHT:']")
	private WebElement dayOrNightLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Crew Status:']")
	private WebElement crewStatusLabel;

	public void validateGeneralInfoLabels() {
		SeleniumUtils.waitForVisibility(driver, designationLabel, 60);
		Assert.assertTrue(SeleniumUtils.getText(designationLabel).contains("Designation -"),
				"Text mismatch, expected substring 'Designation -' but got: "
						+ SeleniumUtils.getText(designationLabel));
		Assert.assertEquals(SeleniumUtils.getText(testOnLabel), "Test on: *",
				"Text mismatch expected is : Test on: * but got : " + SeleniumUtils.getText(testOnLabel));
		Assert.assertEquals(SeleniumUtils.getText(registrationNumberLabel), "Registration Number: *",
				"Text mismatch expected is : Registration Number: * but got : "
						+ SeleniumUtils.getText(registrationNumberLabel));
		Assert.assertEquals(SeleniumUtils.getText(locationLabel), "Location: *",
				"Text mismatch expected is : Location: * but got : " + SeleniumUtils.getText(locationLabel));
		Assert.assertEquals(SeleniumUtils.getText(aeroplaneTypeLabel), "Aeroplane Type: *",
				"Text mismatch expected is : Aeroplane Type: * but got : " + SeleniumUtils.getText(aeroplaneTypeLabel));
		Assert.assertEquals(SeleniumUtils.getText(scheduledDateLabel), "Scheduled Date: *",
				"Text mismatch expected is : Scheduled Date: * but got : " + SeleniumUtils.getText(scheduledDateLabel));
		Assert.assertEquals(SeleniumUtils.getText(simulatorTypeLabel), "Simulator Type: *",
				"Text mismatch expected is : Simulator Type: * but got : " + SeleniumUtils.getText(simulatorTypeLabel));
		Assert.assertEquals(SeleniumUtils.getText(seatOccupiedLabel), "Seat Occupied: *",
				"Text mismatch expected is : Seat Occupied: * but got : " + SeleniumUtils.getText(seatOccupiedLabel));
		Assert.assertEquals(SeleniumUtils.getText(typeOfCheckLabel), "Type of Check: *",
				"Text mismatch expected is : Type of Check: * but got : " + SeleniumUtils.getText(typeOfCheckLabel));
		Assert.assertEquals(SeleniumUtils.getText(onOrTakeOffLabel), "On/Take off: *",
				"Text mismatch expected is : On/Take off: * but got : " + SeleniumUtils.getText(onOrTakeOffLabel));
		Assert.assertEquals(SeleniumUtils.getText(offOrLandingLabel), "Off/Landing: *",
				"Text mismatch expected is : Off/Landing: * but got : " + SeleniumUtils.getText(offOrLandingLabel));
		Assert.assertEquals(SeleniumUtils.getText(timeDurationLabel), "Time Duration:",
				"Text mismatch expected is : Time Duration: but got : " + SeleniumUtils.getText(timeDurationLabel));
		Assert.assertEquals(SeleniumUtils.getText(dayOrNightLabel), "DAY/NIGHT: *",
				"Text mismatch expected is : DAY/NIGHT: * but got : " + SeleniumUtils.getText(dayOrNightLabel));
		Assert.assertEquals(SeleniumUtils.getText(crewStatusLabel), "Crew Status: *",
				"Text mismatch expected is : Crew Status: * but got : " + SeleniumUtils.getText(crewStatusLabel));
	}

	public String getDesignation() {
		SeleniumUtils.waitForVisibility(driver, designationLabel, timeout);
		return designationLabel.getText();
	}

	@FindBy(xpath = "//input[@id='RegNo']")
	private WebElement registrationNumberTextField;

	@FindBy(xpath = "//select[@id='LOCATION']")
	private WebElement locationDropdown;

	@FindBy(xpath = "//select[@id='type']")
	private WebElement aeroplaneTypeDropdown;

	@FindBy(xpath = "//input[@id='schDate']")
	private WebElement scheduledDateDatePicker;

	@FindBy(xpath = "//select[@id='SIMULATORLEVEL']")
	private WebElement simulatorLevelDropdown;

	@FindBy(xpath = "//select[@id='SEATOCCUPIED']")
	private WebElement seatOccupiedDropdown;

	@FindBy(xpath = "//span[@class='filter-option pull-left']")
	private WebElement typeOfCheckDropdown;

	@FindBy(xpath = "//input[@id='ONOFFTIME']")
	private WebElement onOrTakeOffDatePicker;

	@FindBy(xpath = "//input[@id='OFFLANDINGTIME']")
	private WebElement offOrLandingDatePicker;

	@FindBy(xpath = "//i[@class='fa fa-sun-o']")
	private WebElement daySymbolButton;

	@FindBy(xpath = "//i[@class='fa fa-moon-o']")
	private WebElement nightSymbolButton;

	@FindBy(xpath = "//label[@for='NA']")
	private WebElement NAButton;

	@FindBy(xpath = "//select[@id='crewstatus']")
	private WebElement crewStatusDropdown;

	public void enterRegistrationNumber(String registrationNumber) {
		SeleniumUtils.type(driver, registrationNumberTextField, registrationNumber, timeout);
	}

	public void selectLocationDropDown(String location) {
		SeleniumUtils.selectDropdownByValue(driver, locationDropdown, location, timeout);
	}

	public void selectAeroplaneTypeDropDown(String aeroPlaneType) {
		SeleniumUtils.selectDropdownByValue(driver, aeroplaneTypeDropdown, aeroPlaneType, timeout);
	}

	public void enterScheduledDate() throws InterruptedException {
		String tagetDate = ConfigReader.get("tagetDate");
		String[] parts = tagetDate.split("-");
		String targetMonthYear = parts[0].trim();
		String targetDay = parts[1].trim();

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", scheduledDateDatePicker);
		SeleniumUtils.click(driver, scheduledDateDatePicker, timeout);
		SeleniumUtils.navigateToMonthYear(driver, targetMonthYear, 10);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement dayCell = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='day' and text()='" + targetDay + "']")));
		dayCell.click();

		Thread.sleep(500);
	}

	public void selectSimulatorLevelDropDown(String simulatorLevel) {
		SeleniumUtils.selectDropdownByValue(driver, simulatorLevelDropdown, simulatorLevel, timeout);
	}

	public void selectSeatOccupiedDropDown(String seatOccupied) {
		SeleniumUtils.selectDropdownByValue(driver, seatOccupiedDropdown, seatOccupied, timeout);
	}

	public void selectTypeOfCheckDropDown(String typeOfCheck) {
		// Click the dropdown to open options
		SeleniumUtils.click(driver, typeOfCheckDropdown, timeout);

		// XPath to locate dropdown option by visible text, adjust if needed
		String optionXPath = String.format("//span[contains(text(),'%s')]", typeOfCheck);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		// Wait for option to be clickable
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));

		// Click option or fallback to JS click
		try {
			option.click();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", option);
		}
		SeleniumUtils.click(driver, typeOfCheckDropdown, timeout);
	}

	public void enterOnOrTakeOffDate() throws InterruptedException {
		String tagetDate = ConfigReader.get("tagetDate");
		String[] parts = tagetDate.split("-");
		String targetMonthYear = parts[0].trim();
		String targetDay = parts[1].trim();

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", onOrTakeOffDatePicker);
		SeleniumUtils.click(driver, onOrTakeOffDatePicker, timeout);
		SeleniumUtils.navigateToMonthYear(driver, targetMonthYear, 10);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement dayCell = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='day' and text()='" + targetDay + "']")));
		dayCell.click();

		Thread.sleep(500);
	}

	public void enterOffOrLandingDate() throws InterruptedException {
		String tagetDate = ConfigReader.get("tagetDate");
		String[] parts = tagetDate.split("-");
		String targetMonthYear = parts[0].trim();
		String targetDay = parts[1].trim();

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", offOrLandingDatePicker);
		SeleniumUtils.click(driver, offOrLandingDatePicker, timeout);
		SeleniumUtils.navigateToMonthYear(driver, targetMonthYear, 10);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement dayCell = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='day' and text()='" + targetDay + "']")));
		dayCell.click();

		Thread.sleep(500);
	}

	public void clickDayIconButton() {
		SeleniumUtils.click(driver, daySymbolButton, timeout);
	}

	public void clickNightIconButton() {
		SeleniumUtils.click(driver, nightSymbolButton, timeout);
	}

	public void clickNAButton() {
		SeleniumUtils.click(driver, NAButton, timeout);
	}

	public void selectCrewStatusDropDown(String location) {
		SeleniumUtils.selectDropdownByValue(driver, crewStatusDropdown, location, timeout);
	}

	@FindBy(xpath = "//button[@id='eventGradingDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='eventGrading']")
	private WebElement nextButton;

	@FindBy(xpath = "//a[@id='MangeSectors']")
	private WebElement generalInfoTab;

	@FindBy(xpath = "//a[@id='TaskGradingCA']")
	private WebElement taskGradesTab;

	public void clickDiscardButton() {
		SeleniumUtils.click(driver, discardButton, timeout);
	}

	public void clickNextButton() {
		SeleniumUtils.click(driver, nextButton, timeout);
	}

	public void clickGeneralinfoTab() {
		SeleniumUtils.click(driver, generalInfoTab, timeout);
	}

	public void clickTaskGradesTab() {
		SeleniumUtils.click(driver, taskGradesTab, timeout);
	}
}
