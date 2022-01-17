package tests;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.TestDriver;
import common.Enums.*;
import processes.DataTableProcess;
import processes.ListProcess;

import static org.testng.Assert.assertTrue;
//import org.sikuli.script.FindFailed;
//import org.sikuli.script.Screen;

public class Exe2 {

	private TestDriver mTestDriver = null;
	// pages
	private DataTableProcess dataTableP;
	private ListProcess listP;
	private SoftAssert softAssert;
	
	@BeforeClass
	@Parameters("url")
	public void setUp(String url) {
		mTestDriver = new TestDriver();
		assertTrue(mTestDriver.startWebSession(eBrowserType.Chrome, true));
		assertTrue(mTestDriver.getWebsite(url), "");
		initPrecesses();
		softAssert=new SoftAssert();
	}

	private void initPrecesses() {
		dataTableP = new DataTableProcess(mTestDriver);
		listP = new ListProcess(mTestDriver);
	}

	@BeforeMethod
	public void beforeTest() {

	}

	@Test
	public void dataTableTest(Method method) {
		//set configuration checkbox to true and verify that checboxes had been added to the row in the table
		softAssert.assertTrue(dataTableP.setConfiguration(true));
		
		softAssert.assertTrue(dataTableP.checkRows(new int[]{0}));
		
		softAssert.assertTrue(dataTableP.validateRowIsCheck(null));
		
	}

	@Test
	public void listTest(Method method) {
		
		//switch to default content
		assertTrue(mTestDriver.switchToContent(null),"the main menu is not upload");
		
		assertTrue(listP.nevigateToListContent(), "list item in menu is not selected");
		
		assertTrue(listP.checkIcon(1));
		
		assertTrue(listP.validateHeartsNum());
	}
}
