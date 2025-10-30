package cat_II_III_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CAT_II_III_GradingPage {
	private WebDriver driver;
	private int timeout;

	public CAT_II_III_GradingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//h2[normalize-space(text())='Training Device :']")
	private WebElement trainingDeviceLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Registration:']")
	private WebElement registrationLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Scheduled Date:']")
	private WebElement scheduledDateLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Device Type:']")
	private WebElement deviceTypeLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Location:']")
	private WebElement locationLabel;

	@FindBy(xpath = "//span[@id='leftDesign']")
	private WebElement lhsDesignationLabel;

	@FindBy(xpath = "//span[@id='rightDesign']")
	private WebElement rhsDesignationLabel;

	public void validateCAT_II_III_GradingPageTexts() {
		SeleniumUtils.waitForVisibility(driver, trainingDeviceLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(trainingDeviceLabel), "Training Device : *",
				"Text mismatch extected : 'Training Device : *' but got : "
						+ SeleniumUtils.getText(trainingDeviceLabel));
		Assert.assertEquals(SeleniumUtils.getText(registrationLabel), "Registration: *",
				"Text mismatch extected : 'Registration: *' but got : " + SeleniumUtils.getText(registrationLabel));
		Assert.assertEquals(SeleniumUtils.getText(scheduledDateLabel), "Scheduled Date: *",
				"Text mismatch extected : 'Scheduled Date: *' but got : " + SeleniumUtils.getText(scheduledDateLabel));
		Assert.assertEquals(SeleniumUtils.getText(deviceTypeLabel), "Device Type: *",
				"Text mismatch extected : 'Device Type: *' but got : " + SeleniumUtils.getText(deviceTypeLabel));
		Assert.assertEquals(SeleniumUtils.getText(locationLabel), "Location: *",
				"Text mismatch extected : 'Location: *' but got : " + SeleniumUtils.getText(locationLabel));
		Assert.assertTrue(SeleniumUtils.getText(lhsDesignationLabel).contains("Designation -"),
				"LHS Designation Text is missing");
		Assert.assertTrue(SeleniumUtils.getText(rhsDesignationLabel).contains("Designation -"),
				"RHS Designation Text is missing");
	}

	@FindBy(xpath = "//input[@id='RegNo']")
	private WebElement registrationNumberField;

	@FindBy(xpath = "//select[@id='LOCATION']")
	private WebElement locationDropdown;

	public void enterRegistrationNumber(String regNo) {
		SeleniumUtils.waitForVisibility(driver, registrationNumberField, timeout);
		SeleniumUtils.type(driver, registrationNumberField, regNo, timeout);
	}

	public void selectLocationDropdown(String location) throws InterruptedException {
		SeleniumUtils.waitForVisibility(driver, locationDropdown, timeout);
		SeleniumUtils.selectDropdownByVisibleText(driver, locationDropdown, location, timeout);
	}

	@FindBy(xpath = "//input[@id='CM1_LHS']")
	private WebElement cm1LHSRadioButton;

	@FindBy(xpath = "//input[@id='CM2_LHS']")
	private WebElement cm2LHSRadioButton;

	@FindBy(xpath = "//input[@id='CM1_RHS']")
	private WebElement cm1RHSRadioButton;

	@FindBy(xpath = "//input[@id='CM2_RHS']")
	private WebElement cm2RHSRadioButton;

	public void clickCM1LHSRadioButton() {
		SeleniumUtils.click(driver, cm1LHSRadioButton, timeout);
	}

	public void clickCM2LHSRadioButton() {
		SeleniumUtils.click(driver, cm2LHSRadioButton, timeout);
	}

	public void clickCM1RHSRadioButton() {
		SeleniumUtils.click(driver, cm1RHSRadioButton, timeout);
	}

	public void clickCM2RHSRadioButton() {
		SeleniumUtils.click(driver, cm2RHSRadioButton, timeout);
	}

	public boolean isCM1LHSRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cm1LHSRadioButton, timeout);
		return cm1LHSRadioButton.isSelected();
	}

	public boolean isCM2LHSRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cm2LHSRadioButton, timeout);
		return cm2LHSRadioButton.isSelected();
	}

	public boolean isCM1RHSRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cm1RHSRadioButton, timeout);
		return cm1RHSRadioButton.isSelected();
	}

	public boolean isCM2RHSRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cm2RHSRadioButton, timeout);
		return cm2RHSRadioButton.isSelected();
	}

	@FindBy(xpath = "//input[@id='seatSupport_LHS']")
	private WebElement seatSupportLHSCheckbox;

	@FindBy(xpath = "//input[@id='seatSupport_RHS']")
	private WebElement seatSupportRHSCheckbox;

	public void selectSeatSupportLHSCheckbox() {
		SeleniumUtils.click(driver, seatSupportLHSCheckbox, timeout);
	}

	public void selectSeatSupportRHSCheckbox() {
		SeleniumUtils.click(driver, seatSupportRHSCheckbox, timeout);
	}

	public boolean isSeatSupportLHSCheckboxSelected() {
		SeleniumUtils.waitForVisibility(driver, seatSupportLHSCheckbox, timeout);
		return seatSupportLHSCheckbox.isSelected();
	}

	public boolean isSeatSupportRHSCheckboxSelected() {
		SeleniumUtils.waitForVisibility(driver, seatSupportRHSCheckbox, timeout);
		return seatSupportRHSCheckbox.isSelected();
	}

	@FindBy(xpath = "//button[@id='eventGrading']/preceding-sibling::button[@id='eventGradingDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='eventGrading']")
	private WebElement saveAndNextButton;

	public void clickDiscardButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(discardButton));
		SeleniumUtils.click(driver, discardButton, timeout);
	}

	public void clickSaveAndNextButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(saveAndNextButton));
		SeleniumUtils.click(driver, saveAndNextButton, timeout);
	}
}
