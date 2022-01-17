package processes;
import java.util.List;
import org.openqa.selenium.WebElement;
import common.TestDriver;
import common.Enums.*;
import pages.ListPage;

public class ListProcess {

	private ListPage listPage = null;
	private List<WebElement> itemlistTextList;

	public ListProcess(TestDriver testDriver) {
		listPage = new ListPage(testDriver);
	}

	/**
	 * @param num - 1:icon before text, 2:icon after text
	 */
	public boolean checkIcon(int num) {
		boolean result = false;
		result = listPage.goToIframe();
		System.out.println("list page is upload->" + result);
		if (!result)
			return result;

		switch (num) {
		case 1:
			// get number of list item before click
			itemlistTextList = listPage.getItemList();
			result = itemlistTextList != null;
			result = result && listPage.clickBeforeListTextRadioBtn(ConfigType.Icon);
		default:
			break;
		}
		System.out.println("the radio button icon is selected->" + result);
		return result;
	}

	/**
	 * verify that icon is heart and is before text
	 * @return
	 */
	public boolean validateHeartsNum() {
		boolean result = false;
		List<WebElement> heartIconList = listPage.getIcontList();
		result = heartIconList != null;
		result = result && itemlistTextList.size() == heartIconList.size();
		for (WebElement heart : heartIconList) {
			if (!(result = result && heart.getText().equals("favorite")))
				break;
		}
		System.out.println("All icons are hearts and are before the text->" + result);
		return result;
	}

	public boolean nevigateToListContent() {
		return listPage.clickListMenu();
	}
}
