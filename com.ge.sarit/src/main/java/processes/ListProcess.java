package processes;

import java.util.List;

import org.openqa.selenium.WebElement;

import common.TestDriver;
import common.Enums.*;

import pages.ListPage;
import static org.testng.Assert.*;

public class ListProcess {

	private TestDriver mTestDriver;
	private ListPage listPage = null;

	public ListProcess(TestDriver mTestDriver) {
		this.mTestDriver = mTestDriver;
		listPage = new ListPage(mTestDriver);
	}

	/**
	 * select icons view
	 * 
	 * @param num
	 *            - 1: icon before text, 2: icon after text
	 */
	public void checkIcon(int num) {

		assertTrue(mTestDriver.switchToIframe(listPage.iframe), "list page is not uploaded");

		switch (num) {
		case 1:// first icon
				// get number of list item before click
			List<WebElement> itemlistTextList = listPage.getItemList();
			assertTrue(itemlistTextList != null);
			assertTrue(listPage.clickFirstRadioBtn(ConfigType.Icon), "icon is not selected");
			// get number of list item before click
			assertTrue(validateHeartsNum(itemlistTextList.size()), "there ara items without heart icon");
			break;
		default:
			break;
		}
	}

	private boolean validateHeartsNum(int itemListSize) {
		boolean result = false;
		List<WebElement> heartIconList = listPage.getHertList();
		if (heartIconList != null) {
			result = itemListSize == heartIconList.size() ? true : false;
			for (WebElement heart : heartIconList) {
				if (!heart.getText().equals("favorite"))
					return false;
			}
		}
		return result;
	}
	
	public boolean nevigateToListContent(){
		return listPage.clickListMenu();
	}
}
