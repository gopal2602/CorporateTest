package reports;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import methods.ReusableMethods;

public class ReportUtils extends ReusableMethods{

	/****************************************************
	 * Method Name		: startExtentReport()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static ExtentReports startExtentReport(String resultFileName)
	{
		String resultPath = null;
		try {
			//Delete the old test result
			File f1 = new File(".\\Results\\TestResults.html");
			if(f1.exists()) f1.delete();
			
			resultPath = System.getProperty("user.dir")+"\\Results\\";
			
			extent = new ExtentReports(resultPath + "\\" + resultFileName + ".html", false);
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			return extent;
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'startExtentReport()' method. "+e.getMessage());
			return null;
		}
		finally
		{
			resultPath = null;
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: endExtentReport()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static void endExtentReport(ExtentTest test)
	{
		try {
			extent.endTest(test);
			extent.flush();
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'startExtentReport()' method. "+e.getMessage());
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: writeResult()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static void writeResult(String status, String strDescriptoin, ExtentTest test)
	{
		try {
			switch(status.toLowerCase())
			{
				case "pass":
					test.log(LogStatus.PASS, strDescriptoin);
					break;
				case "fail":
					test.log(LogStatus.FAIL, strDescriptoin);
					break;
				case "warning":
					test.log(LogStatus.WARNING, strDescriptoin);
					break;
				case "info":
					test.log(LogStatus.INFO, strDescriptoin);
					break;
				case "exception":
					test.log(LogStatus.FATAL, strDescriptoin);
					break;
				default:
					System.out.println("Invalid result status '"+status+"'. Provide the appropriate status");
			}
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'writeResult()' method. "+e.getMessage());
		}
	}
}
