package processes;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import common.TestDriver;
import components.Table;
import pages.DataTablePage;

public class DataTableProcess {

	private TestDriver mTestDriver;
	private DataTablePage dataTablePage = null;
	Table configTable;
	List<Row> rows;
	List<WebElement> unCheckList;
	
	public DataTableProcess(TestDriver testDriver) {
		mTestDriver = testDriver;
		dataTablePage = new DataTablePage(mTestDriver);
		configTable= new Table(mTestDriver);
		rows=new ArrayList<Row>();
		initRowArray();
	}

	private void initRowArray() {
		int length=configTable.getRows().size();
		for(int i=0;i<length;i++)
			rows.add(new Row(i,false));
	}

	/**
	 * set the configuration table by check a specific row
	 * @param numRow - the number of row to check. 0=header row= all rows
	 */
	public boolean setConfiguration(boolean isCheck){//int[] numRows) {

		boolean result = false;

		result = dataTablePage.goToIframe();
		System.out.println("data table page is upload->" + result);
		if (!result)
			return result;

		result= dataTablePage.clickRowCheckbox();
		System.out.println("row checkbox is not check->" + result);
		if (!result)
			return result;
	
		setRowArray(isCheck);
		List<WebElement> rowList=configTable.getRows();
		unCheckList=configTable.getUnCheckList();
		if(rowList!=null&&unCheckList!=null)
			return rowList.size()==unCheckList.size();
		return false;
	}

	private void setRowArray(boolean isCheck) {
		for(Row row: rows)
			row.setCheck(isCheck);
	}

	public boolean checkRows(int[] numRows) {
		boolean result = false;

		//WebElement table = configTable.getTable();
		//result = table != null ? true : false;
		//if (result) {
			//result = configTable.getRows() != null ? true : false;
			//if (result) {
				for (int row : numRows){
					if (!configTable.checkRow(row))
						return false;
					if(row==0){
						setRowArray(true);
						break;
					}else
						rows.get(row).setCheck(true);
				}
			//}
		//}
		return true;
	}
	
	public boolean validateRowIsCheck(List<Row> rowsToValidate) {
		boolean result = false;

		//WebElement table = configTable.getTable();
		//result = table != null ? true : false;
		//if (result) {
			//result = configTable.getRows() != null ? true : false;
			//if (result) {
		if(rowsToValidate==null)
		{
			List<WebElement> checkList=configTable.getCheckList();
			if(checkList!=null&&unCheckList!=null)
			return unCheckList.size()==checkList.size();
		}
				for (Row row : rowsToValidate){
					if (!configTable.validateStatusRow(row.getIndex(), row.isCheck()))
						return false;
				}
			//}
		//}
		return true;
	}

	public class Row{
		private int index;
		private boolean isCheck;
		public Row(int i, boolean isCheck) {
			index=i;
			this.isCheck=isCheck;
		}
		public int getIndex() {
			return index;
		}
		/*public void setIndex(int index) {
			this.index = index;
		}*/
		public boolean isCheck() {
			return isCheck;
		}
		public void setCheck(boolean isCheck) {
			this.isCheck = isCheck;
		}
	}
}
