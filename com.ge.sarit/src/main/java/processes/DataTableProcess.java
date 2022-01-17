package processes;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import common.TestDriver;
import components.Table;
import pages.DataTablePage;

public class DataTableProcess {

	private DataTablePage dataTablePage = null;
	private Table configTable;
	private List<Row> rows;
	private List<WebElement> unCheckList;

	public DataTableProcess(TestDriver testDriver) {
		dataTablePage = new DataTablePage(testDriver);
		configTable = dataTablePage.getTable();
		rows = new ArrayList<Row>();
		initRowArray();
	}

	private void initRowArray() {
		int length = configTable.getRows().size();
		for (int i = 0; i < length; i++)
			rows.add(new Row(i, false));
	}

	/**
	 * set the row checkbox
	 * @param isCheck - true=check the checkbox
	 */
	public boolean setConfiguration(boolean isCheck) {

		boolean result = false;

		result = dataTablePage.goToIframe();
		System.out.println("data table page is upload->" + result);
		if (!result)
			return result;

		result = dataTablePage.clickRowCheckbox();
		System.out.println("row checkbox is not check->" + result);
		if (!result)
			return result;

		setRowArray(isCheck);
		List<WebElement> rowList = configTable.getRows();
		unCheckList = configTable.getUnCheckList();
		result = rowList != null && unCheckList != null;
		result = result && rowList.size() == unCheckList.size();
		System.out.println("all rows had checkbox->" + result);

		return result;
	}

	private void setRowArray(boolean isCheck) {
		for (Row row : rows)
			row.setCheck(isCheck);
	}

	/**
	 * check the specific rows
	 * @param numRows - an array of row index to check
	 */
	public boolean checkRows(int[] numRows) {
		boolean result = false;
		for (int row : numRows) {
			if (!(result = configTable.checkRow(row)))
				break;
			if (row == 0) {
				setRowArray(true);
				break;
			} else
				rows.get(row).setCheck(true);
		}
		System.out.println("check the rows->" + result);
		return result;
	}

	/**
	 * verify that the right rows had been check
	 * @param rowsToValidate - list of Row to verify
	 */
	public boolean validateRowIsCheck(List<Row> rowsToValidate) {
		boolean result = false;
		if (rowsToValidate == null) {
			List<WebElement> checkList = configTable.getCheckList();
			result = checkList != null && unCheckList != null;
			result = result && unCheckList.size() == checkList.size();
		} else {
			for (Row row : rowsToValidate) {
				if (!configTable.validateStatusRow(row.getIndex(), row.isCheck()))
					return false;
			}
		}
		System.out.println("all the rows you want had been checked->" + result);
		return result;
	}

	//data structure for row
	public class Row {
		private int index;
		private boolean isCheck;

		public Row(int i, boolean isCheck) {
			index = i;
			this.isCheck = isCheck;
		}

		public int getIndex() {
			return index;
		}

		public boolean isCheck() {
			return isCheck;
		}

		public void setCheck(boolean isCheck) {
			this.isCheck = isCheck;
		}
	}
}
