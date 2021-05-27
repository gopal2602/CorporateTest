package methods;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import objectLocators.ObjectLocators;
import reports.ReportUtils;

public class ReusableMethods implements ObjectLocators{
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static String screenshotLocation = null;
	public static WebDriverWait oWait = null;
	
	/****************************************************
	 * Method Name		: openBrowser()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static WebDriver openBlazePage(String browserName, String URL, ExtentTest test) throws Exception
	{
		WebDriver oDriver = null;
		switch(browserName.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", ".\\Library\\drivers\\chromedriver.exe");
				oDriver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", ".\\Library\\drivers\\geckodriver.exe");
				oDriver = new ChromeDriver();
				break;
			default:
				ReportUtils.writeResult(oDriver, "Fail", "Invalid browser name", test);
				
		}
		
		if(oDriver!=null) {
			ReportUtils.writeResult(oDriver, "Pass", "The "+browserName+" browser launched successful", test);
			oDriver.manage().window().maximize();
			oDriver.navigate().to(URL);
			waitForElement(oDriver, By.xpath(findFlights), "clickable", "", 10, test);
			return oDriver;
		}else {
			ReportUtils.writeResult(oDriver, "Fail", "Failed to launch the "+browserName+" browser", test);
			return null;
		}
	}
	
	
	
	/****************************************************
	 * Method Name		: getDateTime()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static String getDateTime(String strFormat)
	{
		Date dt = null;
		SimpleDateFormat sdf = null;
		try {
			dt = new Date();
			sdf = new SimpleDateFormat(strFormat);
			return sdf.format(dt);
		}catch(Exception e)
		{
			ReportUtils.writeResult(null, "Exception", "Exception while executing 'getDateTime()' method. "+e.getMessage(), test);
			return null;
		}
		finally
		{
			dt = null;
			sdf = null;
		}
	}
	
	
	
	/****************************************************
	 * Method Name		: getTestDataMap()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static Map<String, String> getTestDataMap(String fileName) throws IOException{
		Map<String, String> objData = new HashMap<>();
		FileInputStream fin = new FileInputStream(".\\TestData\\"+fileName+".properties");
		Properties prop = new Properties();
		prop.load(fin);
		Set<Object> oMapValue = prop.keySet();
		Iterator<Object> oSetData = oMapValue.iterator();
		while(oSetData.hasNext()) {
			Object key = oSetData.next();
			objData.put(String.valueOf(key), String.valueOf(prop.get(key)));
		}
		return objData;
	}
	
	
	
	/****************************************************
	 * Method Name		: waitForElement()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static void waitForElement(WebDriver oBrowser, By objBy, String waitFor, String text, int timeout, ExtentTest test) {
		oWait = new WebDriverWait(oBrowser, timeout);
		
		switch(waitFor.toLowerCase()) {
			case "clickable":
				oWait.until(ExpectedConditions.elementToBeClickable(objBy));
				break;
			case "visible":
				oWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(objBy));
				break;
			case "invisible":
				oWait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
				break;
			case "text":
				oWait.until(ExpectedConditions.textToBePresentInElementLocated(objBy, text));
				break;
			default:
				ReportUtils.writeResult(oBrowser, "Fail", "Invalid wait condition", test);
		}
	}
	
	
	
	
	/****************************************************
	 * Method Name		: findFlights()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static boolean findFlights(WebDriver oBrowser, Map<String, String> objData, ExtentTest test) throws Exception
	{
		if(oBrowser.getTitle().equalsIgnoreCase("BlazeDemo")) {
			new Select(oBrowser.findElement(By.xpath(departureCity))).selectByVisibleText(objData.get("departureCity"));
			new Select(oBrowser.findElement(By.xpath(destinationCity))).selectByVisibleText(objData.get("destinationCity"));
			oBrowser.findElement(By.xpath(findFlights)).click();
			waitForElement(oBrowser, By.xpath(choosethisFlight), "Clickable", "", 10, test);
			ReportUtils.writeResult(oBrowser, "Pass", "The flights were found successful", test);
			return true;
		}else {
			ReportUtils.writeResult(oBrowser, "Fail", "Failed to open the Find Flight page", test);
			return false;
		}
	}
	
	
	
	/****************************************************
	 * Method Name		: chooseTheFlight()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static boolean chooseTheFlight(WebDriver oBrowser, ExtentTest test) throws Exception
	{
		if(oBrowser.getTitle().equalsIgnoreCase("BlazeDemo - reserve")) {
			oBrowser.findElement(By.xpath(choosethisFlight)).click();
			waitForElement(oBrowser, By.xpath(name), "Visible", "", 10, test);
			ReportUtils.writeResult(oBrowser, "Pass", "The suotable flight was selected among many", test);
			return true;			
		}else {
			ReportUtils.writeResult(oBrowser, "Fail", "The choose flight page doesnot open", test);
			return false;
		}
	}
	
	
	/****************************************************
	 * Method Name		: chooseTheFlight()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static boolean bookFlight(WebDriver oBrowser, Map<String, String> objData, ExtentTest test)
	{
		if(oBrowser.getTitle().equalsIgnoreCase("BlazeDemo Purchase")) {
			oBrowser.findElement(By.xpath(name)).sendKeys(objData.get("name"));
			oBrowser.findElement(By.xpath(address)).sendKeys(objData.get("address"));
			oBrowser.findElement(By.xpath(city)).sendKeys(objData.get("city"));
			oBrowser.findElement(By.xpath(state)).sendKeys(objData.get("state"));
			oBrowser.findElement(By.xpath(zipCode)).sendKeys(objData.get("zipCode"));
			new Select(oBrowser.findElement(By.xpath(cardType))).selectByVisibleText(objData.get("cardType"));
			oBrowser.findElement(By.xpath(creditCardNumber)).sendKeys(objData.get("creditCardNumber"));
			oBrowser.findElement(By.xpath(creditCardMonth)).sendKeys(objData.get("cardMonth"));
			oBrowser.findElement(By.xpath(creditCardYear)).sendKeys(objData.get("cardYear"));
			oBrowser.findElement(By.xpath(nameOnCard)).sendKeys(objData.get("nameOnCard"));
			oBrowser.findElement(By.xpath(purchaseFlight)).click();
			waitForElement(oBrowser, By.xpath(purchaseConfirmation), "Text", "Thank you for your purchase today!", 10, test);
			ReportUtils.writeResult(oBrowser, "Pass", "The flight was booked and got confirmation", test);
			return true;
		}else {
			ReportUtils.writeResult(oBrowser, "Fail", "The book flight page doesnot open", test);
			return false;
		}
	}
	
	
	/****************************************************
	 * Method Name		: chooseTheFlight()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static String captureBookingConfirmationDetails(WebDriver oBrowser, ExtentTest test)
	{
		
		String str = oBrowser.findElement(By.xpath("//table[@class='table']")).getText();
		String s[] = str.split("\\n");
		String sRes = s[0]+"\n" + s[1] + "\n" + s[2] +"\n"+ s[3] +"\n"+ s[4] +"\n"+ s[5] +"\n";
		return sRes;		
	}
}
