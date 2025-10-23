package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class PopupPage {

	private WebDriver driver;
	private int timeout;

	public PopupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//span[@id='alertBoxMsg']")
	private WebElement alertLabel;

	@FindBy(xpath = "//span[contains(text(),'OK')]")
	private WebElement alertOkButton;

	@FindBy(xpath = "//div[@id='alertBoxMsgNEW']")
	private WebElement newAlertLabel;

	public String alertGetText() {
		SeleniumUtils.waitForVisibility(driver, alertLabel, timeout);
		return SeleniumUtils.getText(alertLabel);
	}

	public String alertNewGetText() {
		SeleniumUtils.waitForVisibility(driver, newAlertLabel, timeout);
		return SeleniumUtils.getText(newAlertLabel);
	}

	public void clickAlertOkButton() {
		SeleniumUtils.click(driver, alertOkButton, timeout);
	}

	@FindBy(xpath = "//div[@id='popup']")
	private WebElement popupLabel;

	@FindBy(xpath = "//span[text()='OK']")
	private WebElement popupOkButton;

	public String popupGetText() {
		SeleniumUtils.waitForVisibility(driver, popupLabel, timeout);
		return SeleniumUtils.getText(popupLabel);
	}

	public void clickPopupOkButton() {
		SeleniumUtils.click(driver, popupOkButton, timeout);
	}

	@FindBy(xpath = "//div[@id='popup_modall']//div[@class='modal-body']")
	private WebElement modalBody;

	@FindBy(xpath = "//button[@id='popup_modal_no']")
	private WebElement modalBodyNoButton;

	@FindBy(xpath = "//button[@id='popup_modal_yes']")
	private WebElement modalBodyYesButton;

	public String modalBodyGetText() {
		SeleniumUtils.waitForVisibility(driver, modalBody, timeout);
		return SeleniumUtils.getText(modalBody);
	}

	public void clickModalBodyYesButton() {
		SeleniumUtils.click(driver, modalBodyYesButton, timeout);
	}

	public void clickModalBodyNoButton() {
		SeleniumUtils.click(driver, modalBodyNoButton, timeout);
	}

}
