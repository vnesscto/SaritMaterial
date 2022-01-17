package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import common.Enums.*;
import common.TestDriver;

public class ListPage {
	private TestDriver mTestDriver = null;
	public By iframe;
	private By listItemMenu;
	public By firstIconRadioBtn;
	private By itemList;
	private By heartList;

	public ListPage(TestDriver mTestDriver) {
		this.mTestDriver = mTestDriver;
		init();
	}

	//Initializes all the locators of the page 
	private void init() {
		iframe = By.xpath("//iframe[@title='lists demo']");
		listItemMenu = By.xpath("//*[@title='Lists' and @tabindex='0']");
		firstIconRadioBtn = By.xpath("//div//div//label[contains(@for,'leading-1')]");
		String itemListXpath = "//*[@id='listSINGLE_LINE']/li/span[@class='mdc-list-item__text']";
		itemList = By.xpath(itemListXpath);
		heartList = By.xpath(itemListXpath + "/preceding-sibling::span[@class='mdc-list-item__graphic material-icons']");
	}

	public boolean clickListMenu() {
		WebElement listItem;
		listItem = mTestDriver.fluentWaitWebElement(listItemMenu);
		if (listItem != null) {
			((RemoteWebDriver) mTestDriver.getDriver()).executeScript("arguments[0].click();", listItem);
			return true;
		}
		return false;
	}

	public boolean clickBeforeListTextRadioBtn(ConfigType type) {
		return mTestDriver.onClick(firstIconRadioBtn);
	}
	
	//Retrieves the main list components
	public List<WebElement> getItemList() {
		return mTestDriver.fluentWaitWebElements(itemList);
	}

	//Retrieves heart icons
	public List<WebElement> getIcontList() {
		return mTestDriver.fluentWaitWebElements(heartList);
	}
}
