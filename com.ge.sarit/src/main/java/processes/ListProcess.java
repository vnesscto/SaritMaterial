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
	List<WebElement> itemlistTextList;

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
	public boolean checkIcon(int num) {

		if (mTestDriver.switchToContent(listPage.iframe)) {// , "list page is not
															// uploaded");

			switch (num) {
			case 1:// first icon
					// get number of list item before click
				itemlistTextList = listPage.getItemList();
				if (itemlistTextList != null)
					return listPage.clickBeforeListTextRadioBtn(ConfigType.Icon);// ,
																					// "icon
																					// is
																					// not
																					// selected");
			default:
				break;
			}
		}
		return false;
	}

	public boolean validateHeartsNum() {
		boolean result = false;
		List<WebElement> heartIconList = listPage.getIcontList();
		if (heartIconList != null) {
			result = itemlistTextList.size() == heartIconList.size();
			for (WebElement heart : heartIconList) {
				if (!heart.getText().equals("favorite"))
					return false;
			}
		}
		return result;
	}

	public boolean nevigateToListContent() {
		return listPage.clickListMenu();
	}
}
