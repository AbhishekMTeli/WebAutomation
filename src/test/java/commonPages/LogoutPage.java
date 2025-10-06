package commonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class LogoutPage {
	private WebDriver driver;
	private int timeout;

	public LogoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "//span[@class='avatar avatar-online']")
	private WebElement profileIcon;

	@FindBy(xpath = "//a[normalize-space()='Profile']")
	private WebElement profileButton;

	@FindBy(xpath = "//a[@role='menuitem']")
	private WebElement logoutButton;

	public void clickProfileIcon() {
		SeleniumUtils.click(driver, profileIcon, timeout);
	}

	public void clickProfileButton() {
		SeleniumUtils.click(driver, profileButton, timeout);
	}

	public void clickLogoutButton() {
		SeleniumUtils.click(driver, logoutButton, timeout);
	}
}
