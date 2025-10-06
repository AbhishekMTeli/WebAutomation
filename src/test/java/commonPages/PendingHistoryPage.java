package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class PendingHistoryPage {
	private WebDriver driver;
	private int timeout;

	public PendingHistoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//span[normalize-space()='Pending Grading']")
	private WebElement pendingGradingLabel;

	@FindBy(xpath = "//table[@id='AimsDatatableCompleted']//th[normalize-space()='Curriculum']")
	private WebElement curriculumLabel;

	@FindBy(xpath = "//table[@id='AimsDatatableCompleted']//th[normalize-space()='Lesson']")
	private WebElement lessonLabel;

	@FindBy(xpath = "//table[@id='AimsDatatableCompleted']//th[normalize-space()='Trainees']")
	private WebElement traineesLabel;

	@FindBy(xpath = "//table[@id='AimsDatatableCompleted']//th[normalize-space()='Sectors']")
	private WebElement sectorsLabel;

	public void validatePengingGradingStaticTexts() {
		SeleniumUtils.scrollToElementByVisibleText(driver, pendingGradingLabel.getText());
		SeleniumUtils.waitForVisibility(driver, pendingGradingLabel, timeout);
		Assert.assertEquals(pendingGradingLabel.getText(), "Pending Grading",
				"Text mismatch expected is 'Pending Grading but got' " + pendingGradingLabel.getText());
		Assert.assertEquals(curriculumLabel.getText(), "Curriculum",
				"Text mismatch expected is 'Curriculum' but got " + curriculumLabel.getText());
		Assert.assertEquals(lessonLabel.getText(), "Lesson",
				"Text mismatch expected is 'Lesson' but got " + lessonLabel.getText());
		Assert.assertEquals(traineesLabel.getText(), "Trainees",
				"Text mismatch expected is 'Trainees' but got " + traineesLabel.getText());
		Assert.assertEquals(sectorsLabel.getText(), "Sectors",
				"Text mismatch expected is 'Sectors' but got " + sectorsLabel.getText());
	}

	@FindBy(xpath = "//div[@id='AimsDatatableCompleted_wrapper']//input[@class=' form-control search_field']")
	private WebElement serachTextField;

	@FindBy(xpath = "//span[normalize-space()='Pending Grading']")
	private WebElement pendingGradingLabel;

	@FindBy(xpath = "//span[normalize-space()='Pending Grading']")
	private WebElement pendingGradingLabel;
}
