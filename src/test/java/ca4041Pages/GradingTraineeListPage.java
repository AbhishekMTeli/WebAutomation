package ca4041Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class GradingTraineeListPage {
	private WebDriver driver;
	private int timeout;

	public GradingTraineeListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//th[text()='Trainee Id']")
	private WebElement traineeIdTableHeaderLabel;

	@FindBy(xpath = "//th[text()='Trainee Name']")
	private WebElement traineeNameTableHeaderLabel;

	@FindBy(xpath = "//th[text()='Grade']")
	private WebElement gradeTableHeaderLabel;

	public void validateTableHeadersForGradingTraineeListPage() {
		SeleniumUtils.waitForVisibility(driver, traineeIdTableHeaderLabel, 60);
		Assert.assertEquals(SeleniumUtils.getText(traineeIdTableHeaderLabel), "Trainee Id",
				"Text Mismatch expected is : Trainee Id but got : " + SeleniumUtils.getText(traineeIdTableHeaderLabel));
		Assert.assertEquals(SeleniumUtils.getText(traineeNameTableHeaderLabel), "Trainee Name",
				"Text Mismatch expected is : Trainee Name but got : "
						+ SeleniumUtils.getText(traineeNameTableHeaderLabel));
		Assert.assertEquals(SeleniumUtils.getText(gradeTableHeaderLabel), "Grade",
				"Text Mismatch expected is : Grade but got : " + SeleniumUtils.getText(gradeTableHeaderLabel));
	}

	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement searchTextField;

	public void enterTraineeId(String traineeId) {
		SeleniumUtils.type(driver, searchTextField, traineeId, timeout);
	}

	@FindBy(xpath = "//button[text()='Grade']")
	private List<WebElement> gradeButton;

	@FindBy(xpath = "//button[normalize-space()='Back']")
	private WebElement backButton;

	public void clickOnFirstGradeButton() {
		SeleniumUtils.click(driver, gradeButton.get(0), timeout);
	}

	public void clickBackButton() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", backButton);
	}
}
