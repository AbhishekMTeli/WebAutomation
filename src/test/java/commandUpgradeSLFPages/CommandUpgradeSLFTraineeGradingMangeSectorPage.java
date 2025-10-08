package commandUpgradeSLFPages;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CommandUpgradeSLFTraineeGradingMangeSectorPage {
	private WebDriver driver;
	private int timeout;

	public CommandUpgradeSLFTraineeGradingMangeSectorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
		initStaticTextValidationMap();
	}

	// ========== NAVIGATION/TAB ELEMENTS ==========
	@FindBy(xpath = "//span[@class='text-truncate' and text()='Electronic Grading System']")
	private WebElement pageTitleText;

	@FindBy(xpath = "//a[text()='COMMAND UPGRADE SLF_(L)']")
	private WebElement commandUpgradeSlfTab;

	@FindBy(id = "MangeSectors")
	private WebElement manageSectorsTab;

	@FindBy(linkText = "Syllabus")
	private WebElement syllabusTab;

	@FindBy(xpath = "//span[@class='glyphicon glyphicon-home']")
	private WebElement homeIcon;

	@FindBy(id = "ebtETG")
	private WebElement gradingTab;

	@FindBy(xpath = "//a[normalize-space(text())='Overall Outcome']")
	private WebElement overallOutcomeTab;

	@FindBy(id = "ebthistory")
	private WebElement historyTab;

	// ========== STATIC INFO ELEMENTS ==========
	@FindBy(id = "lhsDesignation")
	private WebElement designationText;

	@FindBy(xpath = "//div[@class='sectorsinfo']")
	private WebElement sectorsInfoSection;

	@FindBy(xpath = "//strong[normalize-space()='SLF Sector Record:']")
	private WebElement slfSectorRecordLabel;

	@FindBy(xpath = "//strong[normalize-space()='Cumulative hours prior to this SLF:']")
	private WebElement cumulativeHoursLabel;

	@FindBy(xpath = "//div[text()='Aircraft Type:']")
	private WebElement aircraftTypeLabel;

	@FindBy(xpath = "//strong[text()='Duration:']")
	private WebElement durationLabel;

	// ========== TABLE HEADERS & STATIC DATA ==========
	@FindBy(xpath = "//strong[normalize-space()='Total Sectors Completed:']")
	private WebElement totalSectorsCompletedLabel;

	@FindBy(xpath = "//strong[normalize-space()='A/C Type']")
	private WebElement aircraftTypeTableHeader;

	@FindBy(xpath = "//strong[normalize-space()='PF']")
	private WebElement pfTableHeader;

	@FindBy(xpath = "//strong[normalize-space()='PM']")
	private WebElement pmTableHeader;

	@FindBy(xpath = "//td[normalize-space()='A320']")
	private WebElement a320TypeCell;

	@FindBy(xpath = "//td[normalize-space()='A321']")
	private WebElement a321TypeCell;

	// ========== SECTOR DETAILS ==========
	@FindBy(xpath = "//div[contains(text(),'No.of sectors selected for this SLF period')]")
	private WebElement noOfSectorsSelectedLabel;

	@FindBy(xpath = "//select[@id='Lfus_Sectors']")
	private WebElement noOfSectorsDropdown;

	@FindBy(xpath = "//input[contains(@id, 'Depdate_')]")
	private List<WebElement> depDateFields;

	@FindBy(xpath = "//input[contains(@id, 'LFUS_FROM_')]")
	private List<WebElement> fromFields;

	@FindBy(xpath = "//input[contains(@id, 'LFUS_TO_')]")
	private List<WebElement> toFields;

	@FindBy(xpath = "//input[contains(@id, 'LFUS_REGNO')]")
	private List<WebElement> regNoFields;

	@FindBy(xpath = "//input[contains(@id, 'LFUS_ACTYPE_') or contains(@id, 'LFUS_actype_')]")
	private List<WebElement> aircraftTypeFields;

	@FindBy(xpath = "//input[contains(@value, 'N')]")
	private List<WebElement> pmRadioButtons;

	@FindBy(xpath = "//input[contains(@value, 'Y')]")
	private List<WebElement> pfRadioButtons;

	@FindBy(xpath = "//i[@class='glyphicon glyphicon-trash']")
	private List<WebElement> deleteIcons;

	@FindBy(xpath = "//button[@id='eventGradingDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='eventGrading']")
	private WebElement nextButton;

	@FindBy(xpath = "//input[@id='Duration_Inp']")
	private WebElement durationField;

	@FindBy(xpath = "//td[text()='Dep Date:']")
	private WebElement depDateLabel;

	// Hadeling the calander
	// input[@id='Duration_Inp']

	// ========== STATIC TEXT VALIDATION MAP ==========
	private Map<String, WebElement> staticTextElements = new HashMap<>();
	private Map<String, String> expectedStaticTexts = new HashMap<>();

	private void initStaticTextValidationMap() {
		staticTextElements.put("pageTitle", pageTitleText);
		staticTextElements.put("commandUpgradeSlfTab", commandUpgradeSlfTab);
		staticTextElements.put("slfSectorRecordLabel", slfSectorRecordLabel);
		staticTextElements.put("cumulativeHoursLabel", cumulativeHoursLabel);
		staticTextElements.put("aircraftTypeLabel", aircraftTypeLabel);
		staticTextElements.put("durationLabel", durationLabel);
		staticTextElements.put("totalSectorsCompletedLabel", totalSectorsCompletedLabel);
		staticTextElements.put("aircraftTypeTableHeader", aircraftTypeTableHeader);
		staticTextElements.put("pfTableHeader", pfTableHeader);
		staticTextElements.put("pmTableHeader", pmTableHeader);
		staticTextElements.put("a320TypeCell", a320TypeCell);
		staticTextElements.put("a321TypeCell", a321TypeCell);
		staticTextElements.put("noOfSectorsSelectedLabel", noOfSectorsSelectedLabel);

		expectedStaticTexts.put("pageTitle", "Electronic Grading System");
		expectedStaticTexts.put("commandUpgradeSlfTab", "COMMAND UPGRADE SLF_(L)");
		expectedStaticTexts.put("slfSectorRecordLabel", "SLF Sector Record:");
		expectedStaticTexts.put("cumulativeHoursLabel", "Cumulative hours prior to this SLF:");
		expectedStaticTexts.put("aircraftTypeLabel", "Aircraft Type:");
		expectedStaticTexts.put("durationLabel", "Duration:");
		expectedStaticTexts.put("totalSectorsCompletedLabel", "Total Sectors Completed:");
		expectedStaticTexts.put("aircraftTypeTableHeader", "A/C Type");
		expectedStaticTexts.put("pfTableHeader", "PF");
		expectedStaticTexts.put("pmTableHeader", "PM");
		expectedStaticTexts.put("a320TypeCell", "A320");
		expectedStaticTexts.put("a321TypeCell", "A321");
		expectedStaticTexts.put("noOfSectorsSelectedLabel", "No.of sectors selected for this SLF period");
	}

	// ========== STATIC TEXT VALIDATION ==========
	public void validateAllStaticTexts() {
		staticTextElements.forEach((key, element) -> {
			SeleniumUtils.waitForVisibility(driver, element, timeout);
			String actual = element.getText().trim();
			String expected = expectedStaticTexts.get(key);
			if (expected != null && !actual.contains(expected)) {
				throw new AssertionError(
						"Static text mismatch for '" + key + "': expected='" + expected + "', actual='" + actual + "'");
			}
		});
	}

	// ========== ACTION METHODS ==========
	public void clickManageSectorsTab() {
		SeleniumUtils.click(driver, manageSectorsTab, timeout);
	}

	public void enterDuration(String duration) {
		SeleniumUtils.waitForVisibility(driver, durationField, timeout);
		SeleniumUtils.type(driver, durationField, duration, timeout);
	}

	public void clearDuration() {
		SeleniumUtils.click(driver, durationField, timeout);
		durationField.clear();
	}

	public void clickSyllabusTab() {
		SeleniumUtils.click(driver, syllabusTab, timeout);
	}

	public void clickGradingTab() {
		SeleniumUtils.click(driver, gradingTab, timeout);
	}

	public void clickOverallOutcomeTab() {
		SeleniumUtils.click(driver, overallOutcomeTab, timeout);
	}

	public void clickHistoryTab() {
		SeleniumUtils.click(driver, historyTab, timeout);
	}

	public void selectNoOfSectors(String value) throws InterruptedException {
		SeleniumUtils.waitForVisibility(driver, depDateLabel, timeout);
		SeleniumUtils.scrollToElementByVisibleText(driver, depDateLabel.getText());
		SeleniumUtils.selectDropdownByVisibleText(driver, noOfSectorsDropdown, value, timeout);
	}

	public void enterDepDate() throws InterruptedException {
		String depDate = ConfigReader.get("depDate");
		String[] parts = depDate.split("-");
		String targetMonthYear = parts[0].trim();
		String targetDay = parts[1].trim();

		for (WebElement depDateField : depDateFields) {
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", depDateField);
			SeleniumUtils.click(driver, depDateField, timeout);
			SeleniumUtils.navigateToMonthYear(driver, targetMonthYear, 10);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			WebElement dayCell = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//td[@class='day' and text()='" + targetDay + "']")));
			dayCell.click();

			Thread.sleep(500);
		}
	}

	public void enterFrom() throws InterruptedException {
		String from = ConfigReader.get("from");
		for (WebElement fromField : fromFields) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(fromField));
			SeleniumUtils.scrollToElementByVisibleText(driver, fromField.getText());
			SeleniumUtils.type(driver, fromField, from, timeout);
			Thread.sleep(300);
			fromField.sendKeys(Keys.ENTER);
		}
		SeleniumUtils.scrollToTopOfPage(driver);

	}

	public void clearFrom() {
		fromFields.get(0).clear();
		;
	}

	public void clearTo() {
		toFields.get(0).clear();
		;
	}

	public void clearRegNo() {
		regNoFields.get(0).clear();
		;
	}

	public void enterTo() throws InterruptedException {
		String to = ConfigReader.get("to");
		for (WebElement toField : toFields) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(toField));
			toField.clear();
			SeleniumUtils.scrollToElementByVisibleText(driver, toField.getText());
			SeleniumUtils.type(driver, toField, to, timeout);
			Thread.sleep(300);
			toField.sendKeys(Keys.ENTER);
		}
		SeleniumUtils.scrollToTopOfPage(driver);
	}

	public void enterRegNo() throws InterruptedException {
		String regNo = ConfigReader.get("registrationNo");
		for (WebElement regNoField : regNoFields) {
			SeleniumUtils.scrollToElementByVisibleText(driver, regNoField.getText());
			SeleniumUtils.type(driver, regNoField, regNo, timeout);
		}
		SeleniumUtils.scrollToTopOfPage(driver);
	}

	public void enterAircraftType() throws InterruptedException {
		String aircraftType = ConfigReader.get("aircraftType");
		for (WebElement aircraftTypeField : aircraftTypeFields) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(aircraftTypeField));
			aircraftTypeField.clear();
			SeleniumUtils.scrollToElementByVisibleText(driver, aircraftTypeField.getText());
			SeleniumUtils.type(driver, aircraftTypeField, aircraftType, timeout);
			Thread.sleep(300);
			aircraftTypeField.sendKeys(Keys.ENTER);
		}
		SeleniumUtils.scrollToTopOfPage(driver);
	}

	public void clearAircraftType() {
		aircraftTypeFields.get(0).clear();
	}

	public void selectPMRadio() throws InterruptedException {
		for (WebElement pmRadioButton : pmRadioButtons) {
			SeleniumUtils.scrollToElementByVisibleText(driver, pmRadioButton.getText());
			SeleniumUtils.click(driver, pmRadioButton, timeout);
		}
		SeleniumUtils.scrollToTopOfPage(driver);
	}

	public void selectPFRadio() {
		for (WebElement pfRadioButton : pfRadioButtons) {
			SeleniumUtils.scrollToElementByVisibleText(driver, pfRadioButton.getText());
			SeleniumUtils.click(driver, pfRadioButton, timeout);
		}
		SeleniumUtils.scrollToTopOfPage(driver);
	}

	public void clickDeleteIcon() throws InterruptedException {
		for (WebElement deleteIcon : deleteIcons) {
			SeleniumUtils.click(driver, deleteIcon, timeout);
			try {
				clickDeleteSectorDataPopupDeleteButton();
			} catch (Exception e) {
				System.out.println("Only one row left, cannot delete this row. Delete operation skipped.");
			}
		}
	}

	public void clickDiscardButton() {
		SeleniumUtils.click(driver, discardButton, timeout);
	}

	public void clickNextButton() {
		SeleniumUtils.waitForClickability(driver, nextButton, timeout);
		SeleniumUtils.scrollToElementByVisibleText(driver, nextButton.getText());
		SeleniumUtils.click(driver, nextButton, timeout);
	}

	// Alerts or Pop-up Handeling
	@FindBy(xpath = "//span[@id='alertBoxMsg']")
	private WebElement alertLabel;

	@FindBy(xpath = "//span[contains(text(),'OK')]")
	private WebElement alertOkButton;

	public String popupGetText() {
		SeleniumUtils.waitForVisibility(driver, alertLabel, timeout);
		return SeleniumUtils.getText(alertLabel);
	}

	public void clickPopupOkButton() {
		SeleniumUtils.click(driver, alertOkButton, timeout);
	}

	// Delete Sector Record Data Pop-up
	@FindBy(xpath = "//button[@id='Sector_button_no']")
	private WebElement deleteSectorDataPopupCancelButton;

	@FindBy(xpath = "//button[@id='Sector_button_yes']")
	private WebElement deleteSectorDataPopupDeleteButton;

	@FindBy(xpath = "//label[contains(text(),'Do you want to Delete this sector record Data')]")
	private WebElement deleteSectorDataPopupGetText;

	public void getPopupText() {
		SeleniumUtils.getText(deleteSectorDataPopupGetText);
	}

	public void clickDeleteSectorDataPopupCancelButton() {
		SeleniumUtils.click(driver, deleteSectorDataPopupCancelButton, timeout);
	}

	public void clickDeleteSectorDataPopupDeleteButton() {
		SeleniumUtils.click(driver, deleteSectorDataPopupDeleteButton, timeout);
	}

	@FindBy(xpath = "//span[@id='lhsuser']")
	private WebElement tarineeId;

	public String getTrainerId() {
		SeleniumUtils.waitForVisibility(driver, tarineeId, timeout);
		return SeleniumUtils.getText(tarineeId);
	}
}
