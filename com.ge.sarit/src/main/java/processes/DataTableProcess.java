package processes;

import org.openqa.selenium.WebElement;
import common.TestDriver;
import components.Table;
import pages.DataTablePage;

public class DataTableProcess {

	private TestDriver mTestDriver;
	private DataTablePage dataTablePage = null;
	

	public DataTableProcess(TestDriver mTestDriver) {
		super();
		this.mTestDriver = mTestDriver;
		dataTablePage = new DataTablePage(mTestDriver);
	}

	/**
	 * set the configuration table by check a specific row
	 * 
	 * @param numRow
	 *            - the number of row to check. 0=header row= all rows
	 */
	public boolean setConfiguration(int[] numRows) {

		boolean result = false;
		// step
		result = dataTablePage.goToIframe();
		System.out.println("data table page is upload->" + result);
		if (!result)
			return result;

		// step
		result = dataTablePage.clickCheckboxRow();
		System.out.println("configuration table can be set->" + result);
		if (!result)
			return result;

		// step
		result = validateRowIsCheck(numRows);

		return result;
	}

	private boolean validateRowIsCheck(int[] numRows) {
		boolean result = false;

		Table configTable = new Table(mTestDriver);

		WebElement table = configTable.getTable();
		result = table != null ? true : false;
		if (result) {
			result = configTable.getRows() != null ? true : false;
			if (result) {
				for (int row : numRows)
					if (!configTable.checkRow(row))
						return false;
				result = configTable.getCheckList() != null ? true : false;
				if (result)
					result = configTable.getRows().size() == configTable.getCheckList().size() ? true : false;
			}
		}
		return result;
	}

}
