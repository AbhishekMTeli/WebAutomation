package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class BecomeUserPage {
	private WebDriver driver;
	private int timeout;

	public BecomeUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//input[@id='usernameField']")
	private WebElement userIdField;

	@FindBy(xpath = "//button[@id='update']")
	private WebElement becomeUserButton;

	public void sendUserId(String userId) {
		SeleniumUtils.type(driver, userIdField, userId, timeout);
	}

	public void clickOnBecomeUser() {
		SeleniumUtils.click(driver, becomeUserButton, timeout);
	}
}
