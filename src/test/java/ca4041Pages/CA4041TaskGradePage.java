package ca4041Pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CA4041TaskGradePage {
	private WebDriver driver;
	private int timeout;

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
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", flightPreparationPanel);
	}

	public void clickTakeOffPanel() {
		SeleniumUtils.waitForVisibility(driver, takeOffPanel, timeout);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", takeOffPanel);
	}

	public void clickFlightmanoeuresAndProcedurePanel() {
		SeleniumUtils.waitForVisibility(driver, flightmanoeuresAndProcedurePanel, timeout);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", flightmanoeuresAndProcedurePanel);
	}

	public void clickNormalAndAbnormalOperationsPanel() {
		SeleniumUtils.waitForVisibility(driver, normalAndAbnormalOperationsPanel, timeout);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", normalAndAbnormalOperationsPanel);
	}

	public void clickNormalAndAbnormalEmergencyProceduresPanel() {
		SeleniumUtils.waitForVisibility(driver, normalAndAbnormalEmergencyProceduresPanel, timeout);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", normalAndAbnormalEmergencyProceduresPanel);
	}

	public void clickMIRILS_ApprochesPanel() {
		SeleniumUtils.waitForVisibility(driver, mIRILS_ApprochesPanel, timeout);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", mIRILS_ApprochesPanel);
	}

	public void clickMissedApproacheProceduresPanel() {
		SeleniumUtils.waitForVisibility(driver, missedApproacheProceduresPanel, timeout);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", missedApproacheProceduresPanel);
	}

	public void clickLandingPanel() {
		SeleniumUtils.waitForVisibility(driver, landingPanel, timeout);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", landingPanel);
	}

	public void clickLowVisibilityOperationsPanel() {
		SeleniumUtils.waitForVisibility(driver, lowVisibilityOperationsPanel, timeout);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", lowVisibilityOperationsPanel);
	}

	// Locator for all YES labels inside rows with radio buttons
	@FindBy(xpath = "//label[contains(normalize-space(string(.)), 'YES')]")
	private List<WebElement> allYesLabels;

	// Locator for all NO labels inside rows with radio buttons
	@FindBy(xpath = "//tr[.//input[@type='radio']]//label[normalize-space(text())='NO']")
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
		List<WebElement> yesLabels = getVisibleYesLabels();
		if (yesLabels != null && !yesLabels.isEmpty()) {
			for (WebElement label : yesLabels) {
				String inputId = label.getAttribute("for");
				WebElement input = driver.findElement(By.id(inputId));

				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", input);

				try {
					new WebDriverWait(driver, Duration.ofSeconds(timeout))
							.until(ExpectedConditions.elementToBeClickable(input));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
				} catch (Exception e) {
					System.out.println("Failed to click YES button input with id: " + inputId);
					// Optionally re-throw or handle error
				}

				Thread.sleep(500);
			}
		}
	}

	public void clickAllNoButtons() {
		List<WebElement> noButtons = getVisibleNoLabels();
		if (noButtons != null && !noButtons.isEmpty()) {
			for (WebElement noButton : noButtons) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", noButton);
			}
		}
	}

}
