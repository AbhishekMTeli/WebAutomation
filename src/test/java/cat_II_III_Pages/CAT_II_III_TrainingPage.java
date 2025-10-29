package cat_II_III_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CAT_II_III_TrainingPage {
	private WebDriver driver;
	private int timeout;

	public CAT_II_III_TrainingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//span[normalize-space(text())='CAT II TRAINING']")
	private WebElement catIIPanel;

	@FindBy(xpath = "//span[normalize-space(text())='CAT III TRAINING']")
	private WebElement catIIIPanel;

	public void clickCATIIPanel() {
		SeleniumUtils.waitForVisibility(driver, catIIPanel, timeout);
		SeleniumUtils.click(driver, catIIPanel, timeout);
	}

	public void clickCATIIIPanel() {
		SeleniumUtils.waitForVisibility(driver, catIIIPanel, timeout);
		SeleniumUtils.click(driver, catIIIPanel, timeout);
	}

	@FindBy(xpath = "//button[@id='cat3formButton']/preceding-sibling::button[@id='eventGradingDiscard']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[@id='cat3formButton']")
	private WebElement saveAndNextButton;

	public void clickDiscardButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(discardButton));
		SeleniumUtils.click(driver, discardButton, timeout);
	}

	public void clickSaveAndNextButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, SeleniumUtils.getText(saveAndNextButton));
		SeleniumUtils.click(driver, saveAndNextButton, timeout);
	}
}
