package testScript;

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
	public void TestScenario1()
	{
		String strBaseURI = null;
		String strRequestType = null;
		String strConfigFile = null;
		String strInputDataFile = null;
		String strResponse = null;
		try {
			test = extent.startTest("TestScenario1");
			
			strConfigFile = System.getProperty("user.dir")+"\\Configuration\\Config.properties";
			strInputDataFile = System.getProperty("user.dir")+"\\TestData\\ExpectedData.properties";
			strBaseURI = ReusableMethods.readPropData(strConfigFile, "BaseURI");
			strRequestType = ReusableMethods.readPropData(strConfigFile, "requestType");
					
			ReportUtils.writeResult("Info", "Executing the Request URI: "+ strBaseURI , test);
			
			strResponse = ReusableMethods.httpGetMethod(strBaseURI, strRequestType);
			
			boolean blnRes = ReusableMethods.validateResponse(strResponse, strInputDataFile);
			if(blnRes) ReportUtils.writeResult("Pass", "Response validation was successful", test);
			else ReportUtils.writeResult("Fail", "Response validation was failed", test);
		}catch(Exception e)
		{
			ReportUtils.writeResult("Exception", "Exception while executing TestScenario1() script." + e.getMessage(), test);
		}
		finally {
			ReportUtils.endExtentReport(test);
			strBaseURI = null;
			strRequestType = null;
			strConfigFile = null;
			strInputDataFile = null;
			strResponse = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Script Name		: TestNegativeScenario2()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	@Test
	public void TestNegativeScenario1()
	{
		String strBaseURI = null;
		String strRequestType = null;
		String strConfigFile = null;
		String strResponse = null;
		try {
			test = extent.startTest("TestNegativeScenario1");
			
			strConfigFile = System.getProperty("user.dir")+"\\Configuration\\Config.properties";
			strBaseURI = ReusableMethods.readPropData(strConfigFile, "BaseURI_Invalid");
			strRequestType = ReusableMethods.readPropData(strConfigFile, "requestType");
					
			ReportUtils.writeResult("Info", "Executing the Request URI: "+ strBaseURI , test);
			
			strResponse = ReusableMethods.httpGetMethod_Negative(strBaseURI, strRequestType);
			
			if(strResponse.equalsIgnoreCase("Not Found")) ReportUtils.writeResult("Pass", "Response validation was successful", test);
			else ReportUtils.writeResult("Fail", "Response validation was failed", test);
		}catch(Exception e)
		{
			ReportUtils.writeResult("Exception", "Exception while executing TestScenario1() script." + e.getMessage(), test);
		}
		finally {
			ReportUtils.endExtentReport(test);
			strBaseURI = null;
			strRequestType = null;
			strConfigFile = null;
			strResponse = null;
		}
	}
}
