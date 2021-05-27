package methods;

import java.io.FileInputStream;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import reports.ReportUtils;

public class ReusableMethods {
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	
	
	/****************************************************
	 * Method Name		: readPropData()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static String readPropData(String filePath, String strKey) {
		FileInputStream fin = null;
		Properties prop = null;
		try {
			fin = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(fin);
			
			return prop.getProperty(strKey);
		}catch(Exception e)
		{
			ReportUtils.writeResult("Exception", "Exception while executing readPropData() method. "+e.getMessage(), test);
			return null;
		}
		finally {
			try {
				fin.close();
				fin = null;
				prop = null;
			}catch(Exception e)
			{
				ReportUtils.writeResult("Exception", "Exception while executing readPropData() method. "+e.getMessage(), test);
			}
		}
	}
	
	
	
	/****************************************************
	 * Method Name		: httpGetMethod()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static String httpGetMethod(String baseURI, String requestType)
	{
		try {
			RestAssured.baseURI = baseURI;
			RequestSpecification httpRequest = RestAssured.given();
			httpRequest.header("Content-Type", requestType);
			
			Response response = httpRequest.get();

			String sBody = response.getBody().asString();
			
			//Print response in JSON format
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonOutput = gson.toJson(new JsonParser().parse(sBody));
			System.out.println(jsonOutput);
						
			if(response.getStatusLine().equalsIgnoreCase("HTTP/1.1 200 OK")) {
				ReportUtils.writeResult("Pass", "The response: "+jsonOutput, test);
				return jsonOutput;
			}else {
				ReportUtils.writeResult("Pass", "The response: "+jsonOutput, test);
				return jsonOutput;
			}
		}catch(Exception e)
		{
			ReportUtils.writeResult("Exception", "Exception while executing httpGetMethod() method. "+e.getMessage(), test);
			return null;
		}
	}
	
	
	
	/****************************************************
	 * Method Name		: httpGetMethod_Negative()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static String httpGetMethod_Negative(String baseURI, String requestType)
	{
		try {
			RestAssured.baseURI = baseURI;
			RequestSpecification httpRequest = RestAssured.given();
			httpRequest.header("Content-Type", requestType);
			
			Response response = httpRequest.get();

			String sBody = response.getBody().asString();
			
			if(sBody.equalsIgnoreCase("Not Found")) {
				ReportUtils.writeResult("Pass", "The response: "+sBody, test);
				return sBody;
			}else {
				ReportUtils.writeResult("Pass", "The response: "+sBody, test);
				return sBody;
			}
		}catch(Exception e)
		{
			ReportUtils.writeResult("Exception", "Exception while executing httpGetMethod_Negative() method. "+e.getMessage(), test);
			return null;
		}
	}
	
	
	/****************************************************
	 * Method Name		: validateResponse()
	 * Purpose			:
	 * Author			:
	 * Reviewer Name	:
	 * Params			:
	 * Return Type		:
	 ***************************************************/
	public static boolean validateResponse(String strResponse, String filePath)
	{
		JSONObject obj = null;
		try {
			 obj = new JSONObject(strResponse);
			 Assert.assertTrue(obj.getJSONObject("links").toString().contains(ReusableMethods.readPropData(filePath, "linksPatch1")));
			 Assert.assertTrue(obj.getJSONObject("links").toString().contains(ReusableMethods.readPropData(filePath, "linksPatch2")));
			 return true;
		}catch(Exception e)
		{
			ReportUtils.writeResult("Exception", "Exception while executing validateResponse() method. "+e.getMessage(), test);
			return false;
		}
	}
}


