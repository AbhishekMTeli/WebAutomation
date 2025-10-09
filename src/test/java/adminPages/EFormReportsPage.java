package adminPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class EFormReportsPage {
	private WebDriver driver;
	private int timeout;

	public EFormReportsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//th[normalize-space(text())='Trainee Name[IGA]']")
	private WebElement traineeNameLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Trainer Name[IGA]']")
	private WebElement trainerNameLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Curriculum Description']")
	private WebElement cirriculumDescriptionLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Lesson Details']")
	private WebElement lessonDetailsLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Completed Date']")
	private WebElement completeDateLabel;

	@FindBy(xpath = "//th[normalize-space(text())='Action']")
	private WebElement actionLabel;

	@FindBy(xpath = "//h4[normalize-space(text())='Date range']")
	private WebElement dateRangeLabel;

	@FindBy(xpath = "//span[normalize-space(text())='Form Reports']")
	private WebElement formReportsLabel;

	public void validateAllStaticElements() {
		SeleniumUtils.waitForVisibility(driver, traineeNameLabel, timeout);
		Assert.assertEquals(traineeNameLabel.getText(), "Trainee Name[IGA]", "Trainee Name[IGA]] label mismatch!");
		Assert.assertEquals(trainerNameLabel.getText(), "Trainer Name[IGA]", "Trainer Name[IGA] label mismatch!");
		Assert.assertEquals(cirriculumDescriptionLabel.getText(), "Curriculum Description",
				"Curriculum Description label mismatch!");
		Assert.assertEquals(lessonDetailsLabel.getText(), "Lesson Details", "Lesson Details label mismatch!");
		Assert.assertEquals(completeDateLabel.getText(), "Completed Date", "Completed Date label mismatch!");
		Assert.assertEquals(actionLabel.getText(), "Action", "Action label mismatch!");
		Assert.assertEquals(dateRangeLabel.getText(), "Date range*", "Date range label mismatch!");
		Assert.assertEquals(formReportsLabel.getText(), "Form Reports", "Form Reports label mismatch!");
	}

	@FindBy(xpath = "//input[@class=' form-control search_field']")
	private WebElement searchTextField;

	@FindBy(xpath = "//input[@id='fromdate']")
	private WebElement fromDateCalander;

	@FindBy(xpath = "//input[@id='todate']")
	private WebElement toDateCalander;

	@FindBy(xpath = "//button[@id='clickUsers']")
	private WebElement searchButton;

	@FindBy(xpath = "//i[@class='fa fa-filter']")
	private WebElement filterButton;

	@FindBy(xpath = "//button[@class='btn btn-save' and normalize-space(text())='Download']")
	private WebElement downloadButton;

	@FindBy(xpath = "//button[@id='reportid']")
	private List<WebElement> actionButtons;

	@FindBy(xpath = "//a[@class='paginate_button >']")
	private WebElement paginateButton;

	@FindBy(xpath = "//a[@class='paginate_button >|']")
	private WebElement paginateToLastPageButton;

	public void searchForTrainee(String trineeId) {
		SeleniumUtils.type(driver, searchTextField, trineeId, timeout);
	}

	public void enterFromDate(String date) throws InterruptedException {
		SeleniumUtils.click(driver, fromDateCalander, timeout);
		SeleniumUtils.navigateToMonthYear(driver, date, timeout);
	}

	public void enterToDate(String date) throws InterruptedException {
		SeleniumUtils.click(driver, toDateCalander, timeout);
		SeleniumUtils.navigateToMonthYear(driver, date, timeout);
	}

	public void clickSearchButtton() {
		SeleniumUtils.click(driver, searchButton, timeout);
	}

	public void clickFilterButtton() {
		SeleniumUtils.click(driver, filterButton, timeout);
	}

	public void clickDownloadButtton() {
		SeleniumUtils.scrollToTopOfPage(driver);
		SeleniumUtils.click(driver, downloadButton, timeout);
	}

	public void clickActionButtton() {
		for (WebElement actionButton : actionButtons) {
			SeleniumUtils.click(driver, actionButton, timeout);
		}
	}

	public void clickPaginateButtton() {
		SeleniumUtils.click(driver, paginateButton, timeout);
	}

	public void clickPaginateLastPageButtton() {
		SeleniumUtils.click(driver, paginateToLastPageButton, timeout);
	}

	public void clickCheckBoxesButton() {
		List<WebElement> inputs = driver.findElements(By.xpath("//input[contains(@id,'jasper')]"));

		for (WebElement input : inputs) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", input);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
		}
	}

}
