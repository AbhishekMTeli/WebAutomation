package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CommandUpgradeSLFTraineeGradingSallabusPage {
	private WebDriver driver;
	private int timeout;

	public CommandUpgradeSLFTraineeGradingSallabusPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//span[text()='NO']")
	private List<WebElement> noButtons;

	public void clickAllVisibleAndInteractableNoButtons() {
		for (WebElement noButton : noButtons) {
			if (isElementVisibleAndInteractable(noButton)) {
				try {
					scrollToElementAndClick(noButton);
				} catch (Exception e) {
					// Log or handle specific buttons that can't be clicked gracefully
				}
			}
		}
	}

	private boolean isElementVisibleAndInteractable(WebElement element) {
		try {
			if (!element.isDisplayed())
				return false;

			Point location = element.getLocation();
			if (location.getY() < 100)
				return false;

			// Optionally detect if element overlaps with known blocking elements by
			// coordinates - more complex

			return true;
		} catch (StaleElementReferenceException e) {
			return false;
		}
	}

	private void scrollToElementAndClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
				element);
		SeleniumUtils.waitForVisibility(driver, element, 10);
		SeleniumUtils.click(driver, element, 10);
	}

	@FindBy(xpath = "//button[@id='eventGrading']")
	private WebElement nextButton;

	@FindBy(xpath = "//button[@id='eventGradingDiscard']")
	private WebElement discardButton;

	public void clickNextButton() {
		SeleniumUtils.scrollToElementByVisibleText(driver, nextButton.getText());
		SeleniumUtils.click(driver, nextButton, timeout);
	}

	public void clickDiscardButton() {
		SeleniumUtils.click(driver, discardButton, timeout);
	}
}
