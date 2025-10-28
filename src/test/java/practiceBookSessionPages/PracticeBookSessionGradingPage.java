package practiceBookSessionPages;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class PracticeBookSessionGradingPage {
	private WebDriver driver;
	private int timeout;

	public PracticeBookSessionGradingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//h2[normalize-space(text())='Training Device:']")
	private WebElement trainingDeviceLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Registration Number:']")
	private WebElement registrationNumberLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Location:']")
	private WebElement locationLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Scheduled Date:']")
	private WebElement scheduledDateLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Simulator Level:']")
	private WebElement simulatorLevelLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Duration:']")
	private WebElement durationLabel;

	@FindBy(xpath = "//h2[normalize-space(text())='Aircraft Type:']")
	private WebElement aircraftTypeLabel;

	public void validatePBSGradePageStaticTexts() {
		SeleniumUtils.waitForVisibility(driver, trainingDeviceLabel, timeout);
		Assert.assertEquals(SeleniumUtils.getText(trainingDeviceLabel), "Training Device: *",
				"Text Mismatch expected is : 'Training Device: *' but got : "
						+ SeleniumUtils.getText(trainingDeviceLabel));
		Assert.assertEquals(SeleniumUtils.getText(registrationNumberLabel), "Registration Number: *",
				"Text Mismatch expected is : 'Registration Number: *' but got : "
						+ SeleniumUtils.getText(registrationNumberLabel));
		Assert.assertEquals(SeleniumUtils.getText(locationLabel), "Location: *",
				"Text Mismatch expected is : 'Location: *' but got : " + SeleniumUtils.getText(locationLabel));
		Assert.assertEquals(SeleniumUtils.getText(scheduledDateLabel), "Scheduled Date: *",
				"Text Mismatch expected is : 'Scheduled Date: *' but got : "
						+ SeleniumUtils.getText(scheduledDateLabel));
		Assert.assertEquals(SeleniumUtils.getText(simulatorLevelLabel), "Simulator Level: *",
				"Text Mismatch expected is : 'Simulator Level: *' but got : "
						+ SeleniumUtils.getText(simulatorLevelLabel));
		Assert.assertEquals(SeleniumUtils.getText(durationLabel), "Duration: *",
				"Text Mismatch expected is : 'Duration: *' but got : " + SeleniumUtils.getText(durationLabel));
		Assert.assertEquals(SeleniumUtils.getText(aircraftTypeLabel), "Aircraft Type:*",
				"Text Mismatch expected is : 'Aircraft Type:*' but got : " + SeleniumUtils.getText(aircraftTypeLabel));
	}

	@FindBy(xpath = "//input[@id='RegNo']")
	private WebElement registrationNumberTextField;

	@FindBy(xpath = "//select[@id='LOCATION']")
	private WebElement locationDropdown;

	@FindBy(xpath = "//input[@id='schDate']")
	private WebElement scheduledDateInput;

	@FindBy(xpath = "//select[@id='SIMULATORLEVEL']")
	private WebElement simulatorLevelDropdown;

	@FindBy(xpath = "//input[@id='Duration']")
	private WebElement durationTextField;

	@FindBy(xpath = "//select[@id='type']")
	private WebElement aircraftTypeDropdown;

	public void eneterRegistrationNumber(String registrationNumber) {
		SeleniumUtils.type(driver, registrationNumberTextField, registrationNumber, timeout);
	}

	public void enterDuration(String duration) {
		SeleniumUtils.type(driver, durationTextField, duration, timeout);
	}

	public void enterScheduledDate() throws InterruptedException {
		String depDate = ConfigReader.get("depDate");
		String[] parts = depDate.split("-");
		String targetMonthYear = parts[0].trim();
		String targetDay = parts[1].trim();

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", scheduledDateInput);
		SeleniumUtils.click(driver, scheduledDateInput, timeout);
		SeleniumUtils.navigateToMonthYear(driver, targetMonthYear, 10);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement dayCell = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='day' and text()='" + targetDay + "']")));
		dayCell.click();

		Thread.sleep(500);
	}

	public void selectLocation(String location) throws InterruptedException {
		SeleniumUtils.selectDropdownByVisibleText(driver, locationDropdown, location, timeout);
	}

	public void selectSimulatorLevel(String simulatorType) throws InterruptedException {
		SeleniumUtils.selectDropdownByVisibleText(driver, simulatorLevelDropdown, simulatorType, timeout);
	}

	public void selectAircraftType(String aircraftType) throws InterruptedException {
		SeleniumUtils.selectDropdownByVisibleText(driver, aircraftTypeDropdown, aircraftType, timeout);
	}

	@FindBy(xpath = "//div[@class='myDiv LHS']//a[@data-toggle='collapse' and contains(@id,'e') and contains(@class,'scenario_name')]")
	private List<WebElement> panels;

	public void clickPanel(int index) {
		SeleniumUtils.scrollToElementByVisibleText(driver, panels.get(index).getText());
		SeleniumUtils.click(driver, panels.get(index), index);
	}

	public WebElement getGradeElement(String panelSide, String section, String optionIndex) {
		int panelIndex = panelSide.equalsIgnoreCase("LHS") ? 1 : 2;
		String xpath = String.format(
				"(//a[contains(text(),'LOFT')])[%d]/ancestor::div[contains(@class,'panel')]//td[normalize-space()='%s']/following-sibling::td//td[@data-grade-val='%s']",
				panelIndex, section, optionIndex);
		return driver.findElement(By.xpath(xpath));
	}

	public void selectGrade(String panelSide, String section, String optionIndex) {
		WebElement grade = getGradeElement(panelSide, section, optionIndex);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", grade);
	}

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

	public void clickOnObDoneButton(String section) throws InterruptedException {
		Thread.sleep(1000);
		String xpath = String.format("//button[contains(@class, 'btn-success') and contains(@onclick,'%s')]", section);
		driver.findElement(By.xpath(xpath)).click();
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

	@FindBy(xpath = "//button[@id='eventGradingDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='eventGrading']")
	private WebElement saveAndNextButton;

	public void clickDiscardButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(discardButton));
		SeleniumUtils.click(driver, discardButton, timeout);
	}

	public void clickSaveAndNextButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
		SeleniumUtils.waitForClickability(driver, saveAndNextButton, timeout);
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(saveAndNextButton));
		SeleniumUtils.click(driver, saveAndNextButton, timeout);
	}

	@FindBy(xpath = "//div[@id='LHS_GradingData']//span[@id='lhsuser']")
	private WebElement lhsUserNameAndId;

	@FindBy(xpath = "//span[@id='Rhsuser']")
	private WebElement rhsUserNameAndId;

	public String getLHSTrainerId() {
		SeleniumUtils.waitForVisibility(driver, lhsUserNameAndId, timeout);
		return SeleniumUtils.getText(lhsUserNameAndId);
	}

	public String getRHSTrainerId() {
		SeleniumUtils.waitForVisibility(driver, rhsUserNameAndId, timeout);
		return SeleniumUtils.getText(rhsUserNameAndId);
	}
}
