package adminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class AdminDashBoardPage {
	private WebDriver driver;
	private int timeout;

	public AdminDashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	// ===== MAIN MENU TABS =====
	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Records']")
	private WebElement recordTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Become User']")
	private WebElement becomeUserTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Grading & Assessment']")
	private WebElement gradingAndAssessmentTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Reports']")
	private WebElement reportsTab;

	// ===== GRADING & ASSESSMENT SUB TABS =====
	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Grading']")
	private WebElement gradingSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Trainee Review']")
	private WebElement traineeReviewSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Training Manager Review']")
	private WebElement trainingManagerReviewSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Grading Audit Log']")
	private WebElement gradingAuditLogSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='SLF History']")
	private WebElement slfHistorySubTab;

	// ===== ADMIN SUB TABS =====
	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Records Admin']")
	private WebElement recordsAdminSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Audit Log']")
	private WebElement auditLogSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='General Requirements']")
	private WebElement generalRequirementsSubTab;

	// ===== REPORTS SUB TABS =====
	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())=\"Eform Names & No's\"]")
	private WebElement eformNamesAndNosSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='E-Form Reports']")
	private WebElement eformReportsSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Summary Reports']")
	private WebElement summaryReportsSubTab;

	// ===== SUMMARY REPORT TABS =====
	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Line Training Forms']")
	private WebElement lineTrainingFormsSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Sim Training Forms']")
	private WebElement simTrainingFormsSubTab;

	@FindBy(xpath = "//span[@class='site-menu-title' and normalize-space(text())='Ground Training Forms']")
	private WebElement groundTrainingFormsSubTab;

	@FindBy(xpath = "//span[@class='avatar avatar-online']")
	private WebElement profileIcon;

	@FindBy(xpath = "//a[normalize-space(text())='Profile']")
	private WebElement profileButton;

	@FindBy(xpath = "//a[@role='menuitem']")
	private WebElement logoutButton;

	@FindBy(xpath = "//span[normalize-space()='User Docs']")
	private WebElement userDocsTab;

	@FindBy(xpath = "//span[normalize-space()='Approved Docs']")
	private WebElement approvedDocsSubTab;

	// ===== ACTION METHODS =====
	public void clickProfileIcon() {
		SeleniumUtils.click(driver, profileIcon, timeout);
	}

	public void clickProfileButton() {
		SeleniumUtils.click(driver, profileButton, timeout);
	}

	public void clickLogoutButton() {
		SeleniumUtils.click(driver, logoutButton, timeout);
	}

	public void clickRecordTab() {
		SeleniumUtils.click(driver, recordTab, timeout);
	}

	public void clickBecomeUserTab() {
		SeleniumUtils.click(driver, becomeUserTab, timeout);
	}

	public void clickGradingAndAssessmentTab() {
		SeleniumUtils.click(driver, gradingAndAssessmentTab, timeout);
	}

	public void clickReportsTab() {
		SeleniumUtils.click(driver, reportsTab, timeout);
	}

	public void clickGradingSubTab() {
		SeleniumUtils.click(driver, gradingSubTab, timeout);
	}

	public void clickTraineeReviewSubTab() {
		SeleniumUtils.click(driver, traineeReviewSubTab, timeout);
	}

	public void clickTrainingManagerReviewSubTab() {
		SeleniumUtils.click(driver, trainingManagerReviewSubTab, timeout);
	}

	public void clickGradingAuditLogSubTab() {
		SeleniumUtils.click(driver, gradingAuditLogSubTab, timeout);
	}

	public void clickSlfHistorySubTab() {
		SeleniumUtils.click(driver, slfHistorySubTab, timeout);
	}

	public void clickRecordsAdminSubTab() {
		SeleniumUtils.click(driver, recordsAdminSubTab, timeout);
	}

	public void clickAuditLogSubTab() {
		SeleniumUtils.click(driver, auditLogSubTab, timeout);
	}

	public void clickGeneralRequirementsSubTab() {
		SeleniumUtils.click(driver, generalRequirementsSubTab, timeout);
	}

	public void clickEformNamesAndNosSubTab() {
		SeleniumUtils.click(driver, eformNamesAndNosSubTab, timeout);
	}

	public void clickEformReportsSubTab() {
		SeleniumUtils.click(driver, eformReportsSubTab, timeout);
	}

	public void clickSummaryReportsSubTab() {
		SeleniumUtils.click(driver, summaryReportsSubTab, timeout);
	}

	public void clickLineTrainingFormsSubTab() {
		SeleniumUtils.click(driver, lineTrainingFormsSubTab, timeout);
	}

	public void clickSimTrainingFormsSubTab() {
		SeleniumUtils.click(driver, simTrainingFormsSubTab, timeout);
	}

	public void clickGroundTrainingFormsSubTab() {
		SeleniumUtils.click(driver, groundTrainingFormsSubTab, timeout);
	}

	// Extensible: Add more methods for actions (hover, wait, getText, etc.) if
	// needed
}
