package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.TestDriver;

public class Table {

	WebElement mTable;
	private TestDriver mTestDriver;
	private By configTB;
	private By rowItem;// =By.xpath("//tr");
	private By checkboxSelected;
	private By checkboxUnselected;
	//private By checkItem;
	private By headerCheckbox;

	public Table(TestDriver testDriver) {
		mTestDriver = testDriver;
		configTB = By.xpath("//table[@class='mdc-data-table__table']");
		checkboxSelected = By.xpath(".//*[contains(@class,'mdc-checkbox--selected')]");
		checkboxUnselected = By.xpath(".//*[contains(@class,'mdc-data-table__cell--checkbox') or contains(@class,'mdc-data-table__header-row-checkbox')]");
		rowItem = By.xpath(".//tr");
		headerCheckbox = By.className("mdc-data-table__header-row-checkbox");
	}

	public WebElement getTable() {
		return mTestDriver.fluentWaitWebElement(configTB);
	}

	public List<WebElement> getRows() {
		WebElement table = getTable();
		if (table != null)
			return mTestDriver.getChildrensOfWebElement(getTable(), rowItem);
		return null;
	}

	public List<WebElement> getCheckList() {
		WebElement table = getTable();
		if (table != null)
			return mTestDriver.getChildrensOfWebElement(getTable(), checkboxSelected);
		return null;
	}

	public List<WebElement> getUnCheckList() {
		return mTestDriver.getChildrensOfWebElement(getTable(), checkboxUnselected);
	}

	public boolean checkRow(int numRow) {
		switch (numRow) {
		case 0:// all
			return mTestDriver.onClick(headerCheckbox);
		}
		return false;
	}                            
	
	public boolean validateStatusRow(int index,boolean status){
		String xpath="//tr["+index+"]";
		WebElement rowAsParent=mTestDriver.fluentWaitWebElement(By.xpath(xpath));
		List<WebElement> checkboxList;
		if(rowAsParent!=null){
		if(status)
			checkboxList=mTestDriver.getChildrensOfWebElement(rowAsParent, checkboxSelected);
		else
			checkboxList=mTestDriver.getChildrensOfWebElement(rowAsParent, checkboxUnselected);
		if(checkboxList!=null&&checkboxList.size()>0)
			return true;
		}
		return false;
	}
}
