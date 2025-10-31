package commonPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class PopupPage {

	private WebDriver driver;

	public PopupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@id='alertBoxMsg']")
	private WebElement alertLabel;

	@FindBy(xpath = "//span[contains(text(),'OK')]")
	private WebElement alertOkButton;

	@FindBy(xpath = "//div[@id='alertBoxMsgNEW']")
	private WebElement newAlertLabel;

	public String alertGetText() {
		SeleniumUtils.waitForVisibility(driver, alertLabel, 2);
		return SeleniumUtils.getText(alertLabel);
	}

	public String alertNewGetText() {
		SeleniumUtils.waitForVisibility(driver, newAlertLabel, 2);
		return SeleniumUtils.getText(newAlertLabel);
	}

	public void clickAlertOkButton() {
		SeleniumUtils.click(driver, alertOkButton, 2);
	}

	@FindBy(xpath = "//div[@id='popup']")
	private WebElement popupLabel;

	@FindBy(xpath = "//span[text()='OK']")
	private WebElement popupOkButton;

	public String popupGetText() {
		SeleniumUtils.waitForVisibility(driver, popupLabel, 2);
		return SeleniumUtils.getText(popupLabel);
	}

	public void clickPopupOkButton() {
		SeleniumUtils.click(driver, popupOkButton, 2);
	}

	@FindBy(xpath = "//div[@id='popup_modall']//div[@class='modal-body']")
	private WebElement modalBody;

	@FindBy(xpath = "//button[@id='popup_modal_no']")
	private WebElement modalBodyNoButton;

	@FindBy(xpath = "//button[@id='popup_modal_yes']")
	private WebElement modalBodyYesButton;

	public String modalBodyGetText() {
		SeleniumUtils.waitForVisibility(driver, modalBody, 2);
		return SeleniumUtils.getText(modalBody);
	}

	public void clickModalBodyYesButton() {
		SeleniumUtils.click(driver, modalBodyYesButton, 2);
	}

	public void clickModalBodyNoButton() {
		SeleniumUtils.click(driver, modalBodyNoButton, 2);
	}

	public void handelSpinner() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
	}

	@FindBy(xpath = "//button[@id='alertYesBtn']")
	private WebElement popupOrAlertYesButton;

	@FindBy(xpath = "//button[@id='alertNoBtn']")
	private WebElement popupOrAlertNoButton;

	public void clickPopupOrAlertNoButton() {
		try {
			SeleniumUtils.click(driver, popupOrAlertNoButton, 2);
		} catch (Exception e) {
			System.out.println("Alert not found");
		}
	}

	public void clickPopupOrAlertYesButton() {
		try {
			SeleniumUtils.click(driver, popupOrAlertYesButton, 2);
		} catch (Exception e) {
			System.out.println("Alert not found");
		}
	}
}
