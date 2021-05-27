package testScript;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import methods.ReusableMethods;
import reports.ReportUtils;

public class TestScripts extends ReusableMethods{
	
	@BeforeClass
	public void preRequisites()
	{
		extent = ReportUtils.startExtentReport("TestResults");
	}
	
	
	
	/****************************************************
	 * Script Name		: TestScenario1()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	@Test
	public static void TestScenario1() throws Exception
	{
		test = extent.startTest("ScenarioTest1");
		Map<String, String> objData = ReusableMethods.getTestDataMap("TestData_Scenario1");
		WebDriver oBrowser = ReusableMethods.openBlazePage("Chrome", objData.get("url"), test);
		if(oBrowser!=null) {
			Assert.assertTrue(ReusableMethods.findFlights(oBrowser, objData, test), "Failed to find the filght");
			Assert.assertTrue(ReusableMethods.chooseTheFlight(oBrowser, test), "Failed to choose the flight");
			Assert.assertTrue(ReusableMethods.bookFlight(oBrowser, objData, test), "Failed to book the flight");
			ReportUtils.writeResult(oBrowser, "Pass", ReusableMethods.captureBookingConfirmationDetails(oBrowser, test), test);
		}else {
			ReportUtils.writeResult(oBrowser, "Fail", "Failed to open the browser", test);
		}
		oBrowser.close();
		oBrowser = null;
	}
	
	
	
	/****************************************************
	 * Script Name		: TestScenario2()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	@Test
	public static void TestScenario2() throws Exception
	{
		test = extent.startTest("ScenarioTest2");
		Map<String, String> objData = ReusableMethods.getTestDataMap("TestData_Scenario2");
		WebDriver oBrowser = ReusableMethods.openBlazePage("Chrome", objData.get("url"), test);
		if(oBrowser!=null) {
			Assert.assertTrue(ReusableMethods.findFlights(oBrowser, objData, test), "Failed to find the filght");
			Assert.assertTrue(ReusableMethods.chooseTheFlight(oBrowser, test), "Failed to choose the flight");
			Assert.assertTrue(ReusableMethods.bookFlight(oBrowser, objData, test), "Failed to book the flight");
			ReportUtils.writeResult(oBrowser, "Pass", ReusableMethods.captureBookingConfirmationDetails(oBrowser, test), test);
		}else {
			ReportUtils.writeResult(oBrowser, "Fail", "Failed to open the browser", test);
		}
		
		oBrowser.close();
		oBrowser = null;
		ReportUtils.endExtentReport(test);
	}
}
