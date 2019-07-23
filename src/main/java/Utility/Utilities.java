package Utility;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;


import com.aventstack.extentreports.Status;

import Base.Base;

public class Utilities extends Base {
	
	
	public static String GetPropertyValue(String PropertyName) throws IOException
	{
		Properties props= new Properties();
		FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+ "/Data/Config.properties");
		props.load(ip);
		//String Androidversion=props.getProperty("androidVersion");
		return (props.getProperty(PropertyName));
	}
	
	 public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  String dateFolder=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		  TakesScreenshot ts = (TakesScreenshot) driver;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  
		  // after execution, you could see a folder "FailedTestsScreenshots" under src folder
		  String destination = System.getProperty("user.dir") +"/TestResult"+ "/Screenshots/"+dateFolder+"/" + screenshotName + dateName + ".png";
		  File finalDestination = new File(destination);
		  FileUtils.copyFile(source, finalDestination);
		  return destination;
		 }
	 public static void LogReport(ITestResult result) throws IOException {
		 System.out.println("Inside LogReport Method : "); 
		 
		 if (result.getStatus() == ITestResult.FAILURE) {
			 
			  System.out.println(result.getName() +": Test is failed "); 
		   test.log(Status.FAIL, result.getName() +" TEST CASE IS FAILED " ); // to add name in extent report
		   test.log(Status.FAIL, "Failed Message:  " + result.getThrowable()); // to add error/exception in extent report
		   String screenshotPath = getScreenshot(driver, result.getName());
		   System.out.println("Screenshot willl be saved in path :"+screenshotPath); 
		   test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		  } else if (result.getStatus() == ITestResult.SKIP) {
			  System.out.println(result.getName() +": Test is skipped "); 
		   test.log(Status.SKIP, result.getName() +" TEST CASE IS Skipped ");
		  }
		  else if (result.getStatus() == ITestResult.SUCCESS) {
			  System.out.println(result.getName() +": Test is Passed "); 
		   test.log(Status.PASS, result.getName() +" TEST CASE IS Passed ");
		  }
		 
		 }	

	 public static boolean VerifyElementPresent(WebElement ele)
	 {
		try
		{
			ele.isDisplayed();
			return true;
		}
		catch(Exception e){
			return false;
		}
		 
	 }
	 
	 public static void ScrollToElement(WebElement ele) {
		 
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("arguments[0].scrollIntoView(true);",ele);
	 }
	 public static void WaitForPagetoLoad() 
	 {
		 driver.manage().timeouts().pageLoadTimeout(Long.parseLong(pageLoadTimeOut), TimeUnit.SECONDS);
	 }
	 public static Boolean ValidateTextOfElement(WebElement ele , String ExpectedText) 
	 {
		if(ele.getText().contains(ExpectedText)) {
			 System.out.println(ExpectedText + " found sucessfully");
			 return true;
		}
		else {
			 System.out.println(ExpectedText + " doesn't match with : "+ele.getText());
			return false;
		
		}
		
	 }
	 
	 
}
