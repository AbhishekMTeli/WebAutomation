package adminPages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class UserDocsPage {
	private WebDriver driver;
	private int timeout;

	public UserDocsPage(WebDriver driver) {
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
		SeleniumUtils.scrollToElementByVisibleText(driver, userDocsTab.getText());
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

	@FindBy(xpath = "//span[normalize-space()='Crew Documents']")
	private WebElement crewDocumentsLabel;

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

	public void validateAllTexts() {
		SeleniumUtils.waitForVisibility(driver, crewDocumentsLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(crewDocumentsLabel), "Crew Documents",
				"Text Mismatch Expected is : Crew Documents but got : " + SeleniumUtils.getText(crewDocumentsLabel));
		Assert.assertEquals(SeleniumUtils.getText(igaNameOrNumberLabel), "IGA Name\\No",
				"Text Mismatch Expected is : IGA Name\\No but got : " + SeleniumUtils.getText(igaNameOrNumberLabel));
		Assert.assertEquals(SeleniumUtils.getText(documentTypeLabel), "Document Type",
				"Text Mismatch Expected is : Document Type but got : " + SeleniumUtils.getText(documentTypeLabel));
		Assert.assertEquals(SeleniumUtils.getText(startDateLabel), "Start Date",
				"Text Mismatch Expected is : Start Date but got : " + SeleniumUtils.getText(startDateLabel));
		Assert.assertEquals(SeleniumUtils.getText(endDateLabel), "End Date",
				"Text Mismatch Expected is : End Date but got : " + SeleniumUtils.getText(endDateLabel));
		Assert.assertEquals(SeleniumUtils.getText(igaTypeLabel), "IGA Type",
				"Text Mismatch Expected is : IGA Type but got : " + SeleniumUtils.getText(igaTypeLabel));
		Assert.assertEquals(SeleniumUtils.getText(igaLabel), "IGA",
				"Text Mismatch Expected is : IGA but got : " + SeleniumUtils.getText(igaLabel));
		Assert.assertEquals(SeleniumUtils.getText(nameLabel), "Name",
				"Text Mismatch Expected is : Name but got : " + SeleniumUtils.getText(nameLabel));
		Assert.assertEquals(SeleniumUtils.getText(documentTypeTableLabel), "Document Type",
				"Text Mismatch Expected is : Document Type but got : " + SeleniumUtils.getText(documentTypeTableLabel));
		Assert.assertEquals(SeleniumUtils.getText(issueDateLabel), "Issue Date",
				"Text Mismatch Expected is : Issue Date but got : " + SeleniumUtils.getText(issueDateLabel));
		Assert.assertEquals(SeleniumUtils.getText(expiryDateLabel), "Expiry Date",
				"Text Mismatch Expected is : Expiry Date but got : " + SeleniumUtils.getText(expiryDateLabel));
		Assert.assertEquals(SeleniumUtils.getText(uploadedOnLabel), "Uploaded On",
				"Text Mismatch Expected is : Uploaded On but got : " + SeleniumUtils.getText(uploadedOnLabel));
		Assert.assertEquals(SeleniumUtils.getText(statusLabel), "Status",
				"Text Mismatch Expected is : Status but got : " + SeleniumUtils.getText(statusLabel));
		Assert.assertEquals(SeleniumUtils.getText(actionLabel), "Action",
				"Text Mismatch Expected is : Action but got : " + SeleniumUtils.getText(actionLabel));
	}

	@FindBy(xpath = "//i[@class='bi bi-eye']")
	private WebElement eyeSybmol;

	@FindBy(xpath = "//span[@id='closeButton']")
	private WebElement closeIcon;

	@FindBy(xpath = "//h4[normalize-space(text())='Training Records Approval']")
	private WebElement trainingRecordsApprovalLabel;

	@FindBy(xpath = "//a[contains(text(),'.pdf')]")
	private WebElement uploadedDocumentLink;

	public void clickEyeIcon() {
		SeleniumUtils.click(driver, eyeSybmol, timeout);
	}

	public void clickCloseIcon() {
		SeleniumUtils.click(driver, closeIcon, timeout);
	}

	public void clickUploadedDocumentLink() {
		String parentWindow = driver.getWindowHandle();

		SeleniumUtils.waitForClickability(driver, uploadedDocumentLink, timeout);
		SeleniumUtils.click(driver, uploadedDocumentLink, timeout);

		// Wait for new window(s) to appear, if needed
		Set<String> allWindows = driver.getWindowHandles();

		// Optional: You may switch to the parent window again to ensure you are focused
		// there
		driver.switchTo().window(parentWindow);
	}

	public void visibilityOfTrainingRecordsApprovalLabel() {
		SeleniumUtils.waitForVisibility(driver, trainingRecordsApprovalLabel, timeout);
	}
}
