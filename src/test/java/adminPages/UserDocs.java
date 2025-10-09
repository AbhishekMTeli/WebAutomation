package adminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class UserDocs {
	private WebDriver driver;
	private int timeout;

	public UserDocs(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//span[normalize-space()='User Docs']")
	private WebElement userDocsTab;

	@FindBy(xpath = "//span[normalize-space()='Pending Docs']")
	private WebElement pendingDocsTab;

	@FindBy(xpath = "//span[normalize-space()='Approved Docs']")
	private WebElement approvedDocsTab;

	@FindBy(xpath = "//input[@id='user_id']")
	private WebElement igaTextField;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	private WebElement searchButton;

	public void clickUserDocsTab() {
		SeleniumUtils.click(driver, userDocsTab, timeout);
	}

	public void clickPendingDocsTab() {
		SeleniumUtils.click(driver, pendingDocsTab, timeout);
	}

	public void clickApprovedDocsTab() {
		SeleniumUtils.click(driver, approvedDocsTab, timeout);
	}

	public void enterIgaId(String traineeId) {
		SeleniumUtils.type(driver, igaTextField, traineeId, timeout);
	}

	public void clickSearchButton() {
		SeleniumUtils.click(driver, searchButton, timeout);
	}

	@FindBy(xpath = "//label[@for='igaNameOrNumber']")
	private WebElement igaNameOrNumberLabel;

	@FindBy(xpath = "//label[@for='documentType']")
	private WebElement documentTypeLabel;

	@FindBy(xpath = "//label[@for='startDate']")
	private WebElement startDateLabel;

	@FindBy(xpath = "//label[normalize-space()='End Date']")
	private WebElement endDateLabel;

	@FindBy(xpath = "//label[normalize-space()='IGA Type']")
	private WebElement igaTypeLabel;

	@FindBy(xpath = "//th[text()='IGA']")
	private WebElement igaLabel;

	@FindBy(xpath = "//th[text()='Name']")
	private WebElement nameLabel;

	@FindBy(xpath = "//th[text()='Document Type']")
	private WebElement documentTypeTableLabel;

	@FindBy(xpath = "//th[text()='Issue Date']")
	private WebElement issueDateLabel;

	@FindBy(xpath = "//th[text()='Expiry Date']")
	private WebElement expiryDateLabel;

	@FindBy(xpath = "//th[text()='Uploaded On']")
	private WebElement uploadedOnLabel;

	@FindBy(xpath = "//th[text()='Status']")
	private WebElement statusLabel;

	@FindBy(xpath = "//th[text()='Action']")
	private WebElement actionLabel;

	@FindBy(xpath = "//i[@class='bi bi-eye']")
	private WebElement eyeSybmol;

	@FindBy(xpath = "//span[@id='closeButton']")
	private WebElement closeIcon;

	@FindBy(xpath = "//h4[normalize-space(text())='Training Records Approval']")
	private WebElement trainingRecordsApprovalLabel;

	@FindBy(xpath = "//a[contains(text(),'.pdf')]")
	private WebElement uploadedDocumentLink;
}
