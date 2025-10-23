package ca4041Pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CA4041TaskGradePage {
	private WebDriver driver;
	private int timeout;
	private String headerText;

	public CA4041TaskGradePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//a[text()='1 FLIGHT PREPARATION']")
	private WebElement flightPreparationPanel;

	@FindBy(xpath = "//a[text()='2 TAKE-OFF(s)']")
	private WebElement takeOffPanel;

	@FindBy(xpath = "//a[normalize-space(text())='3 FLIGHT MANOEUVRES AND PROCEDURES']")
	private WebElement flightmanoeuresAndProcedurePanel;

	@FindBy(xpath = "//a[normalize-space(text())='3.4 Normal and abnormal operations of following systems (minimum of 3 M items shall be selected from 3.4 to 3.5 inclusive)']")
	private WebElement normalAndAbnormalOperationsPanel;

	@FindBy(xpath = "//a[normalize-space(text())='3.6 Abnormal and emergency procedures (minimum of 3 M items shall be selected from 3.6.1 to 3.6.8 Inclusive).']")
	private WebElement normalAndAbnormalEmergencyProceduresPanel;

	@FindBy(xpath = "//a[normalize-space(text())='3.9.3 M IR ILS-approaches down to a decision height (DH) not less than 60 m (200 ft):']")
	private WebElement mIRILS_ApprochesPanel;

	@FindBy(xpath = "//a[normalize-space(text())='4 MISSED APPROACH PROCEDURES']")
	private WebElement missedApproacheProceduresPanel;

	@FindBy(xpath = "//a[normalize-space(text())='5 LANDING(s)']")
	private WebElement landingPanel;

	@FindBy(xpath = "//a[normalize-space(text())='6 LOW VISIBILITY OPERATIONS INCLUDING CAT II/III (if applicable)']")
	private WebElement lowVisibilityOperationsPanel;

	public void clickFlightPreparationPanel() {
		SeleniumUtils.waitForVisibility(driver, flightPreparationPanel, timeout);
		headerText = SeleniumUtils.getText(flightPreparationPanel);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", flightPreparationPanel);
	}

	public void clickTakeOffPanel() {
		SeleniumUtils.waitForVisibility(driver, takeOffPanel, timeout);
		headerText = SeleniumUtils.getText(takeOffPanel);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", takeOffPanel);
	}

	public void clickFlightmanoeuresAndProcedurePanel() {
		SeleniumUtils.waitForVisibility(driver, flightmanoeuresAndProcedurePanel, timeout);
		headerText = SeleniumUtils.getText(flightmanoeuresAndProcedurePanel);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", flightmanoeuresAndProcedurePanel);
	}

	public void clickNormalAndAbnormalOperationsPanel() {
		SeleniumUtils.waitForVisibility(driver, normalAndAbnormalOperationsPanel, timeout);
		headerText = SeleniumUtils.getText(normalAndAbnormalOperationsPanel);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", normalAndAbnormalOperationsPanel);
	}

	public void clickNormalAndAbnormalEmergencyProceduresPanel() {
		SeleniumUtils.waitForVisibility(driver, normalAndAbnormalEmergencyProceduresPanel, timeout);
		headerText = SeleniumUtils.getText(normalAndAbnormalEmergencyProceduresPanel);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", normalAndAbnormalEmergencyProceduresPanel);
	}

	public void clickMIRILS_ApprochesPanel() {
		SeleniumUtils.waitForVisibility(driver, mIRILS_ApprochesPanel, timeout);
		headerText = SeleniumUtils.getText(mIRILS_ApprochesPanel);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", mIRILS_ApprochesPanel);
	}

	public void clickMissedApproacheProceduresPanel() {
		SeleniumUtils.waitForVisibility(driver, missedApproacheProceduresPanel, timeout);
		headerText = SeleniumUtils.getText(missedApproacheProceduresPanel);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", missedApproacheProceduresPanel);
	}

	public void clickLandingPanel() {
		SeleniumUtils.waitForVisibility(driver, landingPanel, timeout);
		headerText = SeleniumUtils.getText(landingPanel);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", landingPanel);
	}

	public void clickLowVisibilityOperationsPanel() {
		SeleniumUtils.waitForVisibility(driver, lowVisibilityOperationsPanel, timeout);
		headerText = SeleniumUtils.getText(lowVisibilityOperationsPanel);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", lowVisibilityOperationsPanel);
	}

	// Locator for all YES labels inside rows with radio buttons
	@FindBy(xpath = "//label[contains(normalize-space(string(.)), 'YES')]")
	private List<WebElement> allYesLabels;

	// Locator for all NO labels inside rows with radio buttons
	@FindBy(xpath = "//label[contains(normalize-space(string(.)), 'NO')]")
	private List<WebElement> allNoLabels;

	// Method to get visible YES labels
	public List<WebElement> getVisibleYesLabels() {
		return allYesLabels.stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
	}

	// Method to get visible NO labels
	public List<WebElement> getVisibleNoLabels() {
		return allNoLabels.stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
	}

	public void clickAllYesButtons() throws InterruptedException {
		Thread.sleep(500);
		List<WebElement> yesButtons = getVisibleYesLabels();
		if (yesButtons != null && !yesButtons.isEmpty()) {
			for (WebElement yesButton : yesButtons) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesButton);
			}
		}
	}

	public void clickAllNoButtons() throws InterruptedException {
		Thread.sleep(500);
		List<WebElement> noButtons = getVisibleNoLabels();
		if (noButtons != null && !noButtons.isEmpty()) {
			int count = 0;
			for (WebElement noButton : noButtons) {
				if (count < 4) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", noButton);
				} else {
					Thread.sleep(1000);
					break;
				}
				count++;
			}
		}
	}

	@FindBy(xpath = "//button[@id='eventGrading']")
	private WebElement saveButton;

	@FindBy(xpath = "//button[@id='eventGradingDiscard']")
	private WebElement discardButton;

	public void clickSaveButton() {
		SeleniumUtils.click(driver, saveButton, timeout);
	}

	public void clickDiscardButton() {
		SeleniumUtils.click(driver, discardButton, timeout);
	}

	public String getDynamicGradeXPath(String section, String grade) {
		return String.format("//tr[td[normalize-space(text())='%s']]//td[@data-grade-val='%s']", section, grade);
	}

	public void selectGarde(String section, String grade) {
		String xpath = String.format(
				"//a[text()='%s']" + "/ancestor::div[contains(@class,'panel-heading')]"
						+ "/following-sibling::div[contains(@class,'panel-collapse') and contains(@class,'in')]"
						+ "//tr[td[normalize-space(text())='%s']]" + "//td[@data-grade-val='%s']",
				headerText, section, grade);

		WebElement gradeCell = driver.findElement(By.xpath(xpath));
		SeleniumUtils.click(driver, gradeCell, timeout);
	}

	// Get all "+" buttons for a specific competency table
	// Get all "+" buttons for a specific competency table
	public List<WebElement> getPlusButtonsForTable(String competencyTableId) {
		String plusXpath = String.format(
				"//table[contains(@id,'%s_Task_Table_')]//td/button[contains(@class,'fa-plus')]", competencyTableId);
		return driver.findElements(By.xpath(plusXpath));
	}

	// Get all "-" buttons for a specific competency table
	public List<WebElement> getMinusButtonsForTable(String competencyTableId) {
		String minusXpath = String.format(
				"//table[contains(@id,'%s_Task_Table_')]//td/button[contains(@class,'fa-minus')]", competencyTableId);
		return driver.findElements(By.xpath(minusXpath));
	}

	// Click all visible "+" buttons
	public void clickAllPlusButtons(String competencyTableId) {
		List<WebElement> plusButtons = getPlusButtonsForTable(competencyTableId);
		int clickedCount = 0;

		for (WebElement plus : plusButtons) {
			try {
				if (plus.isDisplayed() && plus.isEnabled()) {
					SeleniumUtils.waitForClickability(driver, plus, 1);
					SeleniumUtils.click(driver, plus, 1);
					clickedCount++;
				}
			} catch (StaleElementReferenceException e) {
				System.out.println("[WARN] Skipped stale plus button: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("[ERROR] Could not click plus button: " + e.getMessage());
			}
		}

		System.out.println("[INFO] Clicked " + clickedCount + " visible '+' buttons in table: " + competencyTableId);
	}

	// Click all visible "-" buttons
	// Click all visible "-" buttons in the table safely
	public void clickAllMinusButtons(String competencyTableId) {
		List<WebElement> minusButtons = getMinusButtonsForTable(competencyTableId);
		int clickedCount = 0;

		for (WebElement minus : minusButtons) {
			try {
				// Wait for element to be visible
				SeleniumUtils.waitForVisibility(driver, minus, 1);

				if (minus.isDisplayed() && minus.isEnabled()) {
					// Scroll element into view
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
							minus);
					try {
						SeleniumUtils.waitForClickability(driver, minus, 1);
						minus.click();
					} catch (ElementClickInterceptedException e) {
						// fallback using JS click if blocked
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", minus);
					}

					clickedCount++;
					System.out.println("Clicked '-' button #" + clickedCount + " in table: " + competencyTableId);
				}
			} catch (Exception e) {
				System.out.println("Skipped one '-' button due to: " + e.getMessage());
			}
		}

		System.out.println("[INFO] Clicked " + clickedCount + " visible '-' buttons in table: " + competencyTableId);
	}

	// Enter OB comment for a given competency
	public void enterObComment(String comment, String section) {
		if (comment == null) {
			comment = "";
		}

		String xpath = String.format(
				"//div[text()='Characters remaining: 3000']/preceding-sibling::textarea[contains(@id,'%s_competency_comment_txtarea')]",
				section);

		List<WebElement> textareas = driver.findElements(By.xpath(xpath));
		int filledCount = 0;

		for (WebElement textarea : textareas) {
			try {
				// Use explicit waits for both visibility and enabling
				SeleniumUtils.waitForVisibility(driver, textarea, timeout);

				if (textarea.isDisplayed() && textarea.isEnabled()) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
							textarea);

					// Wait for enabled status
					new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(driver1 -> textarea.isEnabled());

					textarea.clear();
					SeleniumUtils.type(driver, textarea, comment, timeout);
					filledCount++;
					System.out.println("Entered comment in textarea #" + filledCount + " for section: " + section);
				} else {
					System.out.println("Skipped textarea as it is not displayed or not enabled.");
				}
			} catch (Exception e) {
				System.out.println("Skipped one textarea due to: " + e.getMessage());
			}
		}

		System.out.println(
				"[INFO] Entered comment in " + filledCount + " visible/enabled textareas for section: " + section);
	}

	public void clickObDoneButton(String section) {
		String xpath = String.format("//button[contains(@class, 'btn-success') and contains(@onclick,'%s')]", section);

		List<WebElement> obDoneButtons = driver.findElements(By.xpath(xpath));
		int clickedCount = 0;

		for (WebElement obDoneButton : obDoneButtons) {
			try {
				if (obDoneButton.isDisplayed() && obDoneButton.isEnabled()) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
							obDoneButton);
					try {
						SeleniumUtils.waitForClickability(driver, obDoneButton, timeout);
						obDoneButton.click();
					} catch (ElementClickInterceptedException e) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", obDoneButton);
					}

					clickedCount++;
					System.out.println("Clicked 'Done' button #" + clickedCount + " for section: " + section);
				}
			} catch (Exception e) {
				System.out.println("Skipped one 'Done' button due to: " + e.getMessage());
			}
		}

		System.out.println("[INFO] Clicked " + clickedCount + " visible 'Done' buttons for section: " + section);
	}

	public void clickObCancelButton(String section) {
		String xpath = String.format(
				"//button[contains(@class, 'btn-success') and contains(@onclick,'%s')]/preceding-sibling::a", section);

		List<WebElement> obCancelButtons = driver.findElements(By.xpath(xpath));
		int clickedCount = 0;

		if (obCancelButtons.isEmpty()) {
			System.out.println("[INFO] No 'Cancel' buttons found for section: " + section);
			return;
		}

		for (WebElement obCancelButton : obCancelButtons) {
			try {
				if (obCancelButton.isDisplayed() && obCancelButton.isEnabled()) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
							obCancelButton);
					try {
						SeleniumUtils.waitForClickability(driver, obCancelButton, timeout);
						obCancelButton.click();
					} catch (ElementClickInterceptedException e) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", obCancelButton);
					}

					clickedCount++;
					System.out.println("Clicked 'Cancel' button #" + clickedCount + " for section: " + section);
				}
			} catch (Exception e) {
				System.out.println("Skipped one 'Cancel' button due to: " + e.getMessage());
			}
		}

		System.out.println("[INFO] Clicked " + clickedCount + " visible 'Cancel' buttons for section: " + section);
	}

	@FindBy(xpath = "//label[normalize-space(text())='Do you wish to maintain the default Grade of 3 for all Competencies in the following tasks:']")
	private WebElement wishToMaintainDefaultGradingPopupText;

	@FindBy(xpath = "//label[normalize-space(text())='Do you wish to maintain the default Grade of 3 for all Competencies in the following tasks:']/following-sibling::button[text()='NO']")
	private WebElement wishToMaintainDefaultGradingPopupNoButton;

	@FindBy(xpath = "//label[normalize-space(text())='Do you wish to maintain the default Grade of 3 for all Competencies in the following tasks:']/following-sibling::button[text()='YES']")
	private WebElement wishToMaintainDefaultGradingPopupYesButton;

	public void getWishToMaintainDefaultGradingPopupText() {
		SeleniumUtils.waitForVisibility(driver, wishToMaintainDefaultGradingPopupText, timeout);
		SeleniumUtils.getText(wishToMaintainDefaultGradingPopupText);
	}

	public void clickWishToMaintainDefaultGradingPopupNoButton() {
		SeleniumUtils.click(driver, wishToMaintainDefaultGradingPopupNoButton, timeout);
	}

	public void clickWishToMaintainDefaultGradingPopupYesButton() {
		SeleniumUtils.click(driver, wishToMaintainDefaultGradingPopupYesButton, timeout);
	}
}
