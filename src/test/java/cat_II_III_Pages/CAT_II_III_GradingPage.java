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
}
