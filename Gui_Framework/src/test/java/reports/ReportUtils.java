package reports;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
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
	 * Method Name		: getScreenshot()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static String getScreenshot(WebDriver oBrowser)
	{
		File objSource = null;
		String strDestination = null;
		File objDestination = null;
		try {
			strDestination = screenshotLocation +"\\" + "screenshot_" + ReusableMethods.getDateTime("ddMMYYYY_hhmmss")+".png";
			TakesScreenshot ts = (TakesScreenshot) oBrowser;
			objSource = ts.getScreenshotAs(OutputType.FILE);
			
			objDestination = new File(strDestination);
			
			FileHandler.copy(objSource, objDestination);
			
			return strDestination;
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'getScreenshot()' method. "+e.getMessage());
			return null;
		}
		finally
		{
			objSource = null;
			objDestination = null;
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
	public static void writeResult(WebDriver oBrowser, String status, String strDescriptoin, ExtentTest test)
	{
		try {
			switch(status.toLowerCase())
			{
				case "pass":
					test.log(LogStatus.PASS, strDescriptoin);
					break;
				case "fail":
					test.log(LogStatus.FAIL, strDescriptoin + " : " 
							+ test.addScreenCapture(ReportUtils.getScreenshot(oBrowser)));
					break;
				case "warning":
					test.log(LogStatus.WARNING, strDescriptoin);
					break;
				case "info":
					test.log(LogStatus.INFO, strDescriptoin);
					break;
				case "exception":
					test.log(LogStatus.FATAL, strDescriptoin+ " : " 
							+ test.addScreenCapture(ReportUtils.getScreenshot(oBrowser)));
					break;
				case "screenshot":
					test.log(LogStatus.PASS, strDescriptoin + " : " 
							+ test.addScreenCapture(ReportUtils.getScreenshot(oBrowser)));
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
