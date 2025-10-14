package rhsPages;

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

public class RHSGradingPage {
	private WebDriver driver;
	private int timeout;

	public RHSGradingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//h2[normalize-space(text())='Training Device:']")
	private WebElement trainingDeviceLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Registration No:']")
	private WebElement registrationNoLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Location:']")
	private WebElement locationLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Scheduled Date:']")
	private WebElement scheduledDateLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Simulator Level:']")
	private WebElement simulatorLevelLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Duration:']")
	private WebElement durationLabel;

	@FindBy(xpath = "//label[normalize-space(text())='RHS Training']")
	private WebElement rhsTrainingLabel;

	@FindBy(xpath = "//label[normalize-space(text())='RHS Check']")
	private WebElement rhsCheckLabel;

	public void validateAllTextForRHSGradingPage() {
		SeleniumUtils.waitForVisibility(driver, trainingDeviceLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(trainingDeviceLabel), "Training Device:",
				"Text Missmatch expected: 'Training Device:' but got :" + SeleniumUtils.getText(trainingDeviceLabel));
		Assert.assertEquals(SeleniumUtils.getText(registrationNoLabel), "Registration No:",
				"Text Missmatch expected: 'Registration No:' but got :" + SeleniumUtils.getText(registrationNoLabel));
		Assert.assertEquals(SeleniumUtils.getText(locationLabel), "Location:",
				"Text Missmatch expected: 'Location:' but got :" + SeleniumUtils.getText(locationLabel));
		Assert.assertEquals(SeleniumUtils.getText(scheduledDateLabel), "Scheduled Date:",
				"Text Missmatch expected: 'Scheduled Date:' but got :" + SeleniumUtils.getText(scheduledDateLabel));
		Assert.assertEquals(SeleniumUtils.getText(simulatorLevelLabel), "Simulator Level:",
				"Text Missmatch expected: 'Simulator Level:' but got :" + SeleniumUtils.getText(simulatorLevelLabel));
		Assert.assertEquals(SeleniumUtils.getText(durationLabel), "Duration:",
				"Text Missmatch expected: 'Duration:' but got :" + SeleniumUtils.getText(durationLabel));
		Assert.assertEquals(SeleniumUtils.getText(rhsTrainingLabel), "RHS Training",
				"Text Missmatch expected: 'RHS Training' but got :" + SeleniumUtils.getText(rhsTrainingLabel));
		Assert.assertEquals(SeleniumUtils.getText(rhsCheckLabel), "RHS Check",
				"Text Missmatch expected: 'RHS Check' but got :" + SeleniumUtils.getText(rhsCheckLabel));
	}

	@FindBy(xpath = "//input[@id='RegNo']")
	private WebElement registrationTextField;

	public void enterRegistrationNumber(String regNo) {
		SeleniumUtils.type(driver, registrationTextField, regNo, timeout);
	}

	@FindBy(xpath = "//select[@id='LOCATION']")
	private WebElement locationDropdown;

	public void selectLocationDropDown(String location) {
		SeleniumUtils.selectDropdownByValue(driver, locationDropdown, location, timeout);
	}

	@FindBy(xpath = "//input[@id='schDate']")
	private WebElement scheduledDateDatePicker;

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

	@FindBy(xpath = "//input[@id='rhsTraining']")
	private WebElement rhsTrainingCheckBox;

	@FindBy(xpath = "//input[@id='rhsCheck']")
	private WebElement rhsCheckCheckBox;

	public void clickRhsTrainingCheckBox() {
		SeleniumUtils.click(driver, rhsTrainingCheckBox, timeout);
	}

	public void clickRhsCheckCheckBox() {
		SeleniumUtils.click(driver, rhsCheckCheckBox, timeout);
	}

	@FindBy(xpath = "//button[@id='eventGradingDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='eventGrading']")
	private WebElement nextButton;

	public void clickDiscardButton() {
		SeleniumUtils.click(driver, discardButton, timeout);
	}

	public void clickNextButton() {
		SeleniumUtils.click(driver, nextButton, timeout);
	}
}
