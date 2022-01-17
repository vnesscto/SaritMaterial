package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.TestDriver;
import components.Table;

public class DataTablePage {
	private TestDriver mTestDriver = null;
	private By iframe;
	private By rowCheckbox;
	private Table table;

	public DataTablePage(TestDriver mTestDriver) {
		this.mTestDriver = mTestDriver;
		init();
	}

	private void init() {
		iframe = By.xpath("//iframe[@title='data table demo']");
		rowCheckbox = By.className("mdc-checkbox__native-control");

		// checkboxSelected=By.className("mdc-data-table__row--selected");
	}

	public boolean clickRowCheckbox() {
		WebElement checkbox = mTestDriver.fluentWaitWebElement(rowCheckbox);
		if (checkbox != null) {
			checkbox.click();
			return true;
		}
		return false;
	}

	public boolean goToIframe() {
		return mTestDriver.switchToContent(iframe);
	}

	public Table getTable() {
		if (table == null)
			return table = new Table(mTestDriver);
		return table;
	}
}
