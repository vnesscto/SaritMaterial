package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.TestDriver;

public class DataTablePage {
	private TestDriver mTestDriver = null;
	private By iframe;
	private By checkboxRow;
	
	

	public DataTablePage(TestDriver mTestDriver) {
		
		this.mTestDriver = mTestDriver;
		init();
	}

	private void init() {
		iframe = By.xpath("//iframe[@title='data table demo']");
		checkboxRow = By.className("mdc-checkbox__native-control");
		
	}

	public boolean clickCheckboxRow() {
		
		WebElement checkbox=mTestDriver.fluentWaitWebElement(checkboxRow);
		if(checkbox!=null){
			checkbox.click();
			return true;
		}
		return false;
	}

	/*public boolean checkHeaderCbx() {
		
	}*/
	
	public boolean goToIframe()
	{
		return mTestDriver.switchToIframe(iframe);
	}
}
