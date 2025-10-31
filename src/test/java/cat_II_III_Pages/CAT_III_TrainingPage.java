package cat_II_III_Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CAT_III_TrainingPage {
	private WebDriver driver;
	private int timeout;

	public CAT_III_TrainingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//input[@id='cat3_LHS_C']")
	private WebElement cat_III_LHSCompetentRadioButton;

	@FindBy(xpath = "//input[@id='cat3_LHS_ATR']")
	private WebElement cat_III_LHSNotYetCompetentRadioButton;

	@FindBy(xpath = "//input[@id='cat3_RHS_C']")
	private WebElement cat_III_RHSCompetentRadioButton;

	@FindBy(xpath = "//input[@id='cat3_RHS_ATR']")
	private WebElement cat_III_RHSNotYetCompetentRadioButton;

	@FindBy(xpath = "//label[@for='cat3_LHS_C']")
	private WebElement cat_III_LHSCompetentLabel;

	public void clickCAT_III_LHSCompetentRadioButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(cat_III_LHSCompetentLabel));
		SeleniumUtils.waitForVisibility(driver, cat_III_LHSCompetentRadioButton, timeout);
		SeleniumUtils.click(driver, cat_III_LHSCompetentRadioButton, timeout);
	}

	public void clickCAT_III_LHSNotYetCompetentRadioButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(cat_III_LHSCompetentLabel));
		SeleniumUtils.waitForVisibility(driver, cat_III_LHSCompetentRadioButton, timeout);
		SeleniumUtils.click(driver, cat_III_LHSNotYetCompetentRadioButton, timeout);
	}

	public void clickCAT_III_RHSCompetentRadioButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(cat_III_LHSCompetentLabel));
		SeleniumUtils.waitForVisibility(driver, cat_III_LHSCompetentRadioButton, timeout);
		SeleniumUtils.click(driver, cat_III_RHSCompetentRadioButton, timeout);
	}

	public void clickCAT_III_RHSNotYetCompetentRadioButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(cat_III_LHSCompetentLabel));
		SeleniumUtils.waitForVisibility(driver, cat_III_LHSCompetentRadioButton, timeout);
		SeleniumUtils.click(driver, cat_III_RHSNotYetCompetentRadioButton, timeout);
	}

	public boolean isCAT_III_LHSCompetentRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cat_III_LHSCompetentRadioButton, timeout);
		return cat_III_LHSCompetentRadioButton.isSelected();
	}

	public boolean isCAT_III_LHSNotYetCompetentRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cat_III_LHSNotYetCompetentRadioButton, timeout);
		return cat_III_LHSNotYetCompetentRadioButton.isSelected();
	}

	public boolean isCAT_III_RHSCompetentRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cat_III_RHSCompetentRadioButton, timeout);
		return cat_III_RHSCompetentRadioButton.isSelected();
	}

	public boolean isCAT_III_RHSNotYetCompetentRadioButtonSelected() {
		SeleniumUtils.waitForVisibility(driver, cat_III_RHSNotYetCompetentRadioButton, timeout);
		return cat_III_RHSNotYetCompetentRadioButton.isSelected();
	}

	@FindBy(xpath = "//textarea[@id='cat3_overallcomment_textarea_LHS']")
	private WebElement cat_III_LHSRemarksTextAreaField;

	@FindBy(xpath = "//textarea[@id='cat3_overallcomment_textarea_RHS']")
	private WebElement cat_III_RHSRemarksTextAreaField;

	@FindBy(xpath = "//div[@id='cat3_textarea_feedback_LHS']")
	private WebElement cat_III_LHSCharactersRemainingLabel;

	@FindBy(xpath = "//div[@id='cat3_textarea_feedback_RHS']")
	private WebElement cat_III_RHSCharactersRemainingLabel;

	public void enterCAT_III_LHSRemarks(String remark) {
		SeleniumUtils.type(driver, cat_III_LHSRemarksTextAreaField, remark, timeout);
	}

	public void enterCAT_III_RHSRemarks(String remark) {
		SeleniumUtils.type(driver, cat_III_RHSRemarksTextAreaField, remark, timeout);
	}

	public String getCAT_III_LHSCharactersRemainingCount() {
		return SeleniumUtils.getText(cat_III_LHSCharactersRemainingLabel);
	}

	public String getCAT_III_RHSCharactersRemainingCount() {
		return SeleniumUtils.getText(cat_III_RHSCharactersRemainingLabel);
	}

	// lhs
	public String getLHSGradingCell(String section, String gradeNumber) {
		return String.format("//table[@id='catIIIoverall_COM_GRD_LHS']//td[@id='LHS_%s_%s']", section, gradeNumber);
	}

	public void clickCAT_III_LHSGrade(String section, String gradeNumber) {
		String gradeXpath = getLHSGradingCell(section, gradeNumber);
		WebElement gradeButton = driver.findElement(By.xpath(gradeXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", gradeButton);
	}

	public String getLHSPlusIconsXpath(String section) {
		return String.format("//table[contains(@id,'%s_Task_Table_')]//button[@class='btn btn-pure btn fa fa-plus']",
				section);
	}

	public String getLHSMinusIconsXpath(String section) {
		return String.format("//table[contains(@id,'%s_Task_Table_')]//button[@class='btn btn-pure btn fa fa-minus']",
				section);
	}

	public void clickAllLHSPlusButtons(String section) {
		String plusXpath = getLHSPlusIconsXpath(section);
		List<WebElement> plusButtons = driver.findElements(By.xpath(plusXpath));
		for (WebElement plusButton : plusButtons) {
			SeleniumUtils.click(driver, plusButton, timeout);
		}
	}

	public void clickAllLHSMinusButtons(String section) {
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
		if (commentField.isEnabled() && commentField.isDisplayed()) {
			SeleniumUtils.type(driver, commentField, comment, timeout);
		}

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

	// rhs
	public String getRHSGradingCell(String section, String gradeNumber) {
		return String.format("//table[@id='catIIIoverall_COM_GRD_RHS']//td[@id='RHS_%s_%s']", section, gradeNumber);
	}

	public void clickCAT_III_RHSGrade(String section, String gradeNumber) {
		String gradeXpath = getLHSGradingCell(section, gradeNumber);
		WebElement gradeButton = driver.findElement(By.xpath(gradeXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", gradeButton);
	}

	public String getRHSPlusIconsXpath(String section) {
		return String.format("//table[contains(@id,'%s_Task_Table_')]//button[@class='btn btn-pure btn fa fa-plus']",
				section);
	}

	public String getRHSMinusIconsXpath(String section) {
		return String.format("//table[contains(@id,'%s_Task_Table_')]//button[@class='btn btn-pure btn fa fa-minus']",
				section);
	}

	public void clickAllRHSPlusButtons(String section) {
		String plusXpath = getLHSPlusIconsXpath(section);
		List<WebElement> plusButtons = driver.findElements(By.xpath(plusXpath));
		for (WebElement plusButton : plusButtons) {
			SeleniumUtils.click(driver, plusButton, timeout);
		}
	}

	public void clickAllRHSMinusButtons(String section) {
		String minusXpath = getLHSMinusIconsXpath(section);
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
		String commentXpath = getLHSCommentsArea(section);
		WebElement commentField = driver.findElement(By.xpath(commentXpath));
		if (commentField.isEnabled() && commentField.isDisplayed()) {
			SeleniumUtils.type(driver, commentField, comment, timeout);
		}

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
		String obDoneButtonXpath = getLHSOBDoneXpath(section);
		WebElement obDoneButton = driver.findElement(By.xpath(obDoneButtonXpath));
		SeleniumUtils.click(driver, obDoneButton, timeout);
	}

	public void clickRHSOBCancelButton(String section) {
		String obDoneButtonXpath = getLHSOBCancelXpath(section);
		WebElement obCancelIcon = driver.findElement(By.xpath(obDoneButtonXpath));
		SeleniumUtils.click(driver, obCancelIcon, timeout);
	}
}
