package Base;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import Utility.Utilities;
import Utility.WebEventListener;


import org.testng.ITestResult;

public class Base {
	
	 public  static WebDriver driver ;
	public  static EventFiringWebDriver e_driver;
    public ExtentHtmlReporter htmlReporter;
	 public ExtentReports extent;
	 public static ExtentTest test;
	 public static ITestResult result;
	 public static String workingDirectory;
	 public static String driverPath;
	 public static String pageLoadTimeOut;
	
 protected void initialize() throws IOException 
	
	{   
	    workingDirectory= System.getProperty("user.dir");
		String BrowserName=Utilities.GetPropertyValue("Browser");
		String SiteURL=Utilities.GetPropertyValue("SiteURL");
		 pageLoadTimeOut=Utilities.GetPropertyValue("PageLoadTimeOutTimeInSeconds");
		
		if(BrowserName.equalsIgnoreCase("chrome")) 
		{
		driverPath=workingDirectory+"/Drivers"+"/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",driverPath );
		 driver= new ChromeDriver();
		}
		else if(BrowserName.equalsIgnoreCase("firefox"))
		{
			
			driverPath=workingDirectory+"/Drivers"+"/geckodriver.exe";
			System.setProperty("webdriver.gecko.driver",driverPath );
			 driver= new FirefoxDriver();
		}
		//Registering listener to driver object for logging purpose
		  EventFiringWebDriver newDriver=new EventFiringWebDriver(driver);
		//creating object of class WebEventListener which is created under Utility package
		  WebEventListener newEventListner =new WebEventListener();
		  newDriver.register(newEventListner );
		  driver=newDriver ;
		
		  driver.get(SiteURL);
		  driver.manage().window().maximize();
		  ReportSetUp();
		 
	}

	public void ReportSetUp() {
		System.out.println("Inside ReportSetUp Method");
		
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  String dateFolder=new SimpleDateFormat("dd-MM-yyyy").format(new Date());  
		  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/TestResult"+"/Reports/"+dateFolder+"/"+dateName+".html");
		  System.out.println("Report will be saved on : "+htmlReporter.getFilePath());
		  htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		  htmlReporter.config().setReportName("Amazon Search Headphone Testing"); // Name of the report
		  htmlReporter.config().setTheme(Theme.DARK);
		  extent = new ExtentReports();
		  extent.attachReporter(htmlReporter);
	}
}
