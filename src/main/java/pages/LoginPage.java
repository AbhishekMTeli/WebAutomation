package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class LoginPage {

	private WebDriver driver;
	private int timeout;

	// ✅ Constructor with PageFactory
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	// ✅ Page Elements
	@FindBy(xpath = "//a[contains(text(),'Are you a Temporary User?')]")
	private WebElement temporaryUserLink;

	@FindBy(xpath = "//input[@id='textbox1']")
	private WebElement userIdTextField;

	@FindBy(xpath = "//input[@id='textbox2']")
	private WebElement passwordTextField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	// ✅ Page Actions
	public void clickTemporaryUserLink() {
		SeleniumUtils.click(driver, temporaryUserLink, timeout);
	}

	public void enterUserId(String userId) {
		SeleniumUtils.type(driver, userIdTextField, userId, timeout);
	}

	public void enterPassword(String password) {
		SeleniumUtils.type(driver, passwordTextField, password, timeout);
	}

	public void clickLogin() {
		SeleniumUtils.click(driver, loginButton, timeout);
	}

	// ✅ Business Method (Reusable login flow)
	public void login(String userId, String password) {
		enterUserId(userId);
		enterPassword(password);
		clickLogin();
	}
}
