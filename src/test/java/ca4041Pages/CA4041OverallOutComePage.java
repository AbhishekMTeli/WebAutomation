package ca4041Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.SeleniumUtils;

public class CA4041OverallOutComePage {
	private WebDriver driver;
	private int timeout;

	public CA4041OverallOutComePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.timeout = Integer.parseInt(ConfigReader.get("timeout"));
	}

	@FindBy(xpath = "")
	private WebElement ele;

	public void click() {
		SeleniumUtils.click(driver, ele, timeout);
	}
}
