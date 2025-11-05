package cat_II_III_Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CAT_II_TrainingPage {
	private WebDriver driver;
	private int timeout;

	public CAT_II_TrainingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//input[@id='LHS_C']")
	private WebElement cat_II_LHSCompetentRadioButton;

	@FindBy(xpath = "//input[@id='LHS_ATR']")
	private WebElement cat_II_LHSNotYetCompetentRadioButton;

	@FindBy(xpath = "//input[@id='RHS_C']")
	private WebElement cat_II_RHSCompetentRadioButton;

	@FindBy(xpath = "//input[@id='RHS_ATR']")
	private WebElement cat_II_RHSNotYetCompetentRadioButton;

	public void clickCAT_II_LHSCompetentRadioButton() {
		SeleniumUtils.click(driver, cat_II_LHSCompetentRadioButton, timeout);
	}

	public void clickCAT_II_LHSNotYetCompetentRadioButton() {
		SeleniumUtils.click(driver, cat_II_LHSNotYetCompetentRadioButton, timeout);
	}

	public void clickCAT_II_RHSCompetentRadioButton() {
		SeleniumUtils.click(driver, cat_II_RHSCompetentRadioButton, timeout);
	}

	public void clickCAT_II_RHSNotYetCompetentRadioButton() {
		SeleniumUtils.click(driver, cat_II_RHSNotYetCompetentRadioButton, timeout);
	}

	public boolean isCAT_II_LHSCompetentRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cat_II_LHSCompetentRadioButton, timeout);
		return cat_II_LHSCompetentRadioButton.isSelected();
	}

	public boolean isCAT_II_LHSNotYetCompetentRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cat_II_LHSNotYetCompetentRadioButton, timeout);
		return cat_II_LHSNotYetCompetentRadioButton.isSelected();
	}

	public boolean isCAT_II_RHSCompetentRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cat_II_RHSCompetentRadioButton, timeout);
		return cat_II_RHSCompetentRadioButton.isSelected();
	}

	public boolean isCAT_II_RHSNotYetCompetentRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cat_II_RHSNotYetCompetentRadioButton, timeout);
		return cat_II_RHSNotYetCompetentRadioButton.isSelected();
	}

	@FindBy(xpath = "//textarea[@id='overallcomment_textarea_LHS']")
	private WebElement cat_II_LHSRemarksTextAreaField;

	@FindBy(xpath = "//textarea[@id='overallcomment_textarea_RHS']")
	private WebElement cat_II_RHSRemarksTextAreaField;

	@FindBy(xpath = "//div[@id='textarea_feedback_LHS']")
	private WebElement cat_II_LHSCharactersRemainingLabel;

	@FindBy(xpath = "//div[@id='textarea_feedback_RHS']")
	private WebElement cat_II_RHSCharactersRemainingLabel;

	public void enterCAT_II_LHSRemarks(String remark) {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(cat_II_LHSCharactersRemainingLabel));
		SeleniumUtils.type(driver, cat_II_LHSRemarksTextAreaField, remark, timeout);
	}

	public void enterCAT_II_RHSRemarks(String remark) {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(cat_II_RHSCharactersRemainingLabel));
		SeleniumUtils.type(driver, cat_II_RHSRemarksTextAreaField, remark, timeout);
	}

	public boolean rhsRemarkIsEnabled() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(cat_II_LHSCharactersRemainingLabel));
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(cat_II_LHSRemarksTextAreaField));

			// Check if element is enabled (HTML attribute)
			boolean enabled = cat_II_RHSRemarksTextAreaField.isEnabled();

			// Check CSS classes that indicate disabled appearance
			String classAttr = cat_II_RHSRemarksTextAreaField.getAttribute("class");
			boolean visuallyDisabled = classAttr != null && classAttr.contains("disabled");

			// Check for CSS property that disables pointer events
			String pointerEvents = cat_II_RHSRemarksTextAreaField.getCssValue("pointer-events");
			boolean pointerEventsNone = "none".equalsIgnoreCase(pointerEvents);

			// Return true only if it is html-enabled AND not visually disabled
			return enabled && !visuallyDisabled && !pointerEventsNone;
		} catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
			return false; // Treat exceptions as not enabled
		}
	}

	public boolean lhsRemarkIsEnabled() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(cat_II_LHSCharactersRemainingLabel));
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(cat_II_LHSRemarksTextAreaField));

			// Check if element is enabled (HTML attribute)
			boolean enabled = cat_II_LHSRemarksTextAreaField.isEnabled();

			// Check CSS classes that indicate disabled appearance
			String classAttr = cat_II_LHSRemarksTextAreaField.getAttribute("class");
			boolean visuallyDisabled = classAttr != null && classAttr.contains("disabled");

			// Check for CSS property that disables pointer events
			String pointerEvents = cat_II_LHSRemarksTextAreaField.getCssValue("pointer-events");
			boolean pointerEventsNone = "none".equalsIgnoreCase(pointerEvents);

			// Return true only if it is html-enabled AND not visually disabled
			return enabled && !visuallyDisabled && !pointerEventsNone;
		} catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
			return false; // Treat exceptions as not enabled
		}
	}

	public String getCAT_II_LHSCharactersRemainingCount() {
		return SeleniumUtils.getText(cat_II_LHSCharactersRemainingLabel);
	}

	public String getCAT_II_RHSCharactersRemainingCount() {
		return SeleniumUtils.getText(cat_II_RHSCharactersRemainingLabel);
	}

	// lhs
	public String getLHSGradingCell(String section, String gradeNumber) {
		return String.format("//table[@id='overall_COM_GRD_LHS']//td[@id='LHS_%s_%s']", section, gradeNumber);
	}

	public void clickCAT_II_LHSGrade(String section, String gradeNumber) {
		String gradeXpath = getLHSGradingCell(section, gradeNumber);
		WebElement gradeButton = driver.findElement(By.xpath(gradeXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", gradeButton);
	}

	@FindBy(xpath = "//table[@id='overall_COM_GRD_LHS']//td[@data-grade-val='N/O']")
	private List<WebElement> noGradeButtons;

	public void clickCAT_II_LHSNOGrade() {
		int size = noGradeButtons.size();
		for (int i = 0; i < size - 2; i++) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", noGradeButtons.get(i));
		}
	}

	public String getLHSPlusIconsXpath(String section) {
		return String.format("//table[contains(@id,'%s_Task_Table_')]//button[@class='btn btn-pure btn fa fa-plus']",
				section);
	}

	public String getLHSMinusIconsXpath(String section) {
		return String.format("//table[contains(@id,'%s_Task_Table_')]//button[@class='btn btn-pure btn fa fa-minus']",
				section);
	}

	public void clickAllLHSPlus(String section) {
		String plusXpath = getLHSPlusIconsXpath(section);
		List<WebElement> plusButtons = driver.findElements(By.xpath(plusXpath));
		for (WebElement plusButton : plusButtons) {
			SeleniumUtils.click(driver, plusButton, timeout);
		}
	}

	public void clickAllLHSMinus(String section) {
		String minusXpath = getLHSMinusIconsXpath(section);
		List<WebElement> minusButtons = driver.findElements(By.xpath(minusXpath));
		for (WebElement minusButton : minusButtons) {
			SeleniumUtils.click(driver, minusButton, timeout);
		}
	}

	public String getLHSCommentsArea(String section) {
		return String.format(
				"//div[@id='%s_charCountDisplay']/preceding-sibling::textarea[@id='%s_competency_comment_txtarea']",
				section, section);
	}

	public void enterLHSComments(String section, String comment) {
		String commentXpath = getLHSCommentsArea(section);
		WebElement commentField = driver.findElement(By.xpath(commentXpath));
		SeleniumUtils.type(driver, commentField, comment, timeout);
	}

	public String getLHSOBDoneXpath(String section) {
		return String.format(
				"//h4[contains(@id,'%s_loginModalLabel_')]/preceding-sibling::button[@class='btn btn-success btn-ranger-save' and contains(@onclick,'overall_COM_')]",
				section);
	}

	public String getLHSOBCancelXpath(String section) {
		return String.format(
				"//h4[contains(@id,'%s_loginModalLabel_')]/preceding-sibling::a[@class='btn btn-cancel' and @oldval='3']",
				section);
	}

	public void clickLHSOBDoneButton(String section) {
		String obDoneButtonXpath = getLHSOBDoneXpath(section);
		WebElement obDoneButton = driver.findElement(By.xpath(obDoneButtonXpath));
		SeleniumUtils.click(driver, obDoneButton, timeout);
	}

	public void clickLHSOBCancelButton(String section) {
		String obDoneButtonXpath = getLHSOBCancelXpath(section);
		WebElement obCancelIcon = driver.findElement(By.xpath(obDoneButtonXpath));
		SeleniumUtils.click(driver, obCancelIcon, timeout);
	}

//rhs
	public String getRHSGradingCell(String section, String gradeNumber) {
		return String.format("//table[@id='overall_COM_GRD_RHS']//td[@id='RHS_%s_%s']", section, gradeNumber);
	}

	public void clickCAT_II_RHSGrade(String section, String gradeNumber) {
		String gradeXpath = getRHSGradingCell(section, gradeNumber);
		WebElement gradeButton = driver.findElement(By.xpath(gradeXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", gradeButton);
	}

	public boolean rhsGradeIsInteractable(String section, String gradeNumber) {
		String gradeXpath = getRHSGradingCell(section, gradeNumber);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			WebElement gradeButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gradeXpath)));

			// Check HTML enabled state
			boolean enabled = gradeButton.isEnabled();

			// Check CSS classes or styles that indicate visual disabled state
			String classAttr = gradeButton.getAttribute("class");
			boolean visuallyDisabled = classAttr != null
					&& (classAttr.contains("disabled") || classAttr.contains("inactive"));

			// Optionally check for CSS styles like pointer-events:none or opacity
			String pointerEvents = gradeButton.getCssValue("pointer-events");
			boolean pointerEventsDisabled = "none".equalsIgnoreCase(pointerEvents);

			// Final interactability decision
			return enabled && !visuallyDisabled && !pointerEventsDisabled;
		} catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public boolean lhsGradeIsInteractable(String section, String gradeNumber) {
		String gradeXpath = getLHSGradingCell(section, gradeNumber);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			WebElement gradeButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gradeXpath)));

			// Check HTML enabled state
			boolean enabled = gradeButton.isEnabled();

			// Check CSS classes or styles that indicate visual disabled state
			String classAttr = gradeButton.getAttribute("class");
			boolean visuallyDisabled = classAttr != null
					&& (classAttr.contains("disabled") || classAttr.contains("inactive"));

			// Optionally check for CSS styles like pointer-events:none or opacity
			String pointerEvents = gradeButton.getCssValue("pointer-events");
			boolean pointerEventsDisabled = "none".equalsIgnoreCase(pointerEvents);

			// Final interactability decision
			return enabled && !visuallyDisabled && !pointerEventsDisabled;
		} catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public String getRHSPlusIconsXpath(String section) {
		return String.format("//table[contains(@id,'%s_Task_Table_')]//button[@class='btn btn-pure btn fa fa-plus']",
				section);
	}

	public String getRHSMinusIconsXpath(String section) {
		return String.format("//table[contains(@id,'%s_Task_Table_')]//button[@class='btn btn-pure btn fa fa-minus']",
				section);
	}

	public void clickAllRHSPlus(String section) {
		String plusXpath = getRHSPlusIconsXpath(section);
		List<WebElement> plusButtons = driver.findElements(By.xpath(plusXpath));
		for (WebElement plusButton : plusButtons) {
			SeleniumUtils.click(driver, plusButton, timeout);
		}
	}

	public void clickAllRHSMinus(String section) {
		String minusXpath = getRHSMinusIconsXpath(section);
		List<WebElement> minusButtons = driver.findElements(By.xpath(minusXpath));
		for (WebElement minusButton : minusButtons) {
			SeleniumUtils.click(driver, minusButton, timeout);
		}
	}

	public String getRHSCommentsArea(String section) {
		return String.format(
				"//div[@id='%s_charCountDisplay']/preceding-sibling::textarea[@id='%s_competency_comment_txtarea']",
				section, section);
	}

	public void enterRHSComments(String section, String comment) {
		String commentXpath = getRHSCommentsArea(section);
		WebElement commentField = driver.findElement(By.xpath(commentXpath));
		SeleniumUtils.type(driver, commentField, comment, timeout);
	}

	public String getRHSOBDoneXpath(String section) {
		return String.format(
				"//h4[contains(@id,'%s_loginModalLabel_')]/preceding-sibling::button[@class='btn btn-success btn-ranger-save' and contains(@onclick,'overall_COM_')]",
				section);
	}

	public String getRHSOBCancelXpath(String section) {
		return String.format(
				"//h4[contains(@id,'%s_loginModalLabel_')]/preceding-sibling::a[@class='btn btn-cancel' and @oldval='3']",
				section);
	}

	public void clickRHSOBDoneButton(String section) {
		String obDoneButtonXpath = getRHSOBDoneXpath(section);
		WebElement obDoneButton = driver.findElement(By.xpath(obDoneButtonXpath));
		SeleniumUtils.click(driver, obDoneButton, timeout);
	}

	public void clickRHSOBCancelButton(String section) {
		String obDoneButtonXpath = getRHSOBCancelXpath(section);
		WebElement obCancelIcon = driver.findElement(By.xpath(obDoneButtonXpath));
		SeleniumUtils.click(driver, obCancelIcon, timeout);
	}
}
