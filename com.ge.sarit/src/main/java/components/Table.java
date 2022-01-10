package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.TestDriver;

public class Table {

	WebElement mTable;
	private TestDriver mTestDriver;
	private By configTB;
	private By unCheckItem=By.xpath("//tr");
	private By checkItem;
	private By headerCheckbox;
	
	public Table(TestDriver testDriver){
		mTestDriver=testDriver;
		configTB = By.xpath("//table[@class='mdc-data-table__table']");
		checkItem=By.xpath(".//*[contains(@class,'mdc-checkbox--selected')]");
		headerCheckbox = By.className("mdc-data-table__header-row-checkbox");
	}
	
	public WebElement getTable(){
		return mTestDriver.fluentWaitWebElement(configTB);
	}
	
	public List<WebElement> getRows(){
		return mTestDriver.getChildrensOfWebElement(getTable(),unCheckItem );
	}
	
	public List<WebElement> getCheckList(){
		return mTestDriver.getChildrensOfWebElement(getTable(),checkItem );
	}
	
	public boolean checkRow(int numRow){
		switch (numRow) {
		case 0:// all	
			return mTestDriver.onClick(headerCheckbox);
		}
		return false;
	}
}
