package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class TrainerDashBoradPage {
	private WebDriver driver;
	private int timeout;

	public TrainerDashBoradPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//span[@class='avatar avatar-online']")
	private WebElement profileIcon;

	@FindBy(xpath = "//a[normalize-space(text())='Profile']")
	private WebElement profileButton;

	@FindBy(xpath = "//a[@role='menuitem']")
	private WebElement logoutButton;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Grading & Assessment']")
	private WebElement gradingAndAssessmentTab;

	// Grading and Assessments Sub Tab
	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Grading']")
	private WebElement gradingSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Trainee Review']")
	private WebElement traineeReviewSubTab;

	public void clickOnGradingAssessmentTab() {
		SeleniumUtils.waitForClickability(driver, gradingAndAssessmentTab, timeout);
		SeleniumUtils.click(driver, gradingAndAssessmentTab, timeout);
	}

	public void clickOnGradingSubTab() {
		SeleniumUtils.waitForClickability(driver, gradingSubTab, timeout);
		SeleniumUtils.click(driver, gradingSubTab, timeout);
	}

	public void clickTraineeReviewTab() {
		SeleniumUtils.click(driver, traineeReviewSubTab, timeout);
	}

	public void clickProfileIcon() {
		SeleniumUtils.click(driver, profileIcon, timeout);
	}

	public void clickProfileButton() {
		SeleniumUtils.click(driver, profileButton, timeout);
	}

	public void clickLogoutButton() {
		SeleniumUtils.click(driver, logoutButton, timeout);
	}
}
