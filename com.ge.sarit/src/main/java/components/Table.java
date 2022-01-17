package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.TestDriver;

public class Table {

	WebElement mTable;
	private TestDriver mTestDriver;
	private By configTB;
	private By rowItem;
	private By checkboxSelected;
	private By checkboxUnselected;
	private By headerCheckbox;

	public Table(TestDriver testDriver) {
		mTestDriver = testDriver;
		configTB = By.xpath("//table[@class='mdc-data-table__table']");
		checkboxSelected = By.xpath(".//*[contains(@class,'mdc-checkbox--selected')]");
		checkboxUnselected = By.xpath(
				".//*[contains(@class,'mdc-data-table__cell--checkbox') or contains(@class,'mdc-data-table__header-row-checkbox')]");
		rowItem = By.xpath(".//tr");
		headerCheckbox = By.className("mdc-data-table__header-row-checkbox");
	}

	private WebElement getTable() {
		return mTestDriver.fluentWaitWebElement(configTB);
	}

	public List<WebElement> getRows() {
		WebElement table = getTable();
		if (table != null)
			return mTestDriver.getChildrensOfWebElement(table, rowItem);
		return null;
	}

	public List<WebElement> getCheckList() {
		WebElement table = getTable();
		if (table != null)
			return mTestDriver.getChildrensOfWebElement(table, checkboxSelected);
		return null;
	}

	public List<WebElement> getUnCheckList() {
		WebElement table = getTable();
		if (table != null)
			return mTestDriver.getChildrensOfWebElement(table, checkboxUnselected);
		return null;
	}

	public boolean checkRow(int numRow) {
		switch (numRow) {
		case 0:// all
			return mTestDriver.onClick(headerCheckbox);
		}
		return false;
	}

	public boolean validateStatusRow(int index, boolean status) {
		String xpath = "//tr[" + index + "]";
		WebElement rowAsParent = mTestDriver.fluentWaitWebElement(By.xpath(xpath));
		List<WebElement> xheckboxByIndex;
		if (rowAsParent != null) {
			if (status)
				xheckboxByIndex = mTestDriver.getChildrensOfWebElement(rowAsParent, checkboxSelected);
			else
				xheckboxByIndex = mTestDriver.getChildrensOfWebElement(rowAsParent, checkboxUnselected);
			if (xheckboxByIndex != null && xheckboxByIndex.size() > 0)
				return true;
		}
		return false;
	}
}
