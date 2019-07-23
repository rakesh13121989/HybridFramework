package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import Base.Base;
import Pages.HomePage;

import Pages.ProductDetailsPage;
import Pages.ProductListingPage;
import Utility.Utilities;

public class AddToCartTestcases extends Base {
	
	HomePage homePage;
  ProductDetailsPage productDetailsPage ;
	public static ProductListingPage productListingPage ;
	public Boolean status;
	SoftAssert softAssert;
	@BeforeTest
	public void setUp() throws IOException{
		initialize();
		homePage = new HomePage();	
		productDetailsPage = new ProductDetailsPage()  ;
		softAssert= new SoftAssert();
	}
	//Search for Headphone and verify that atleast one product with Best Seller is displayed after search
    @Test(priority=0)
    public void SearchProduct() throws InterruptedException
    {   
    	test=extent.createTest("Search product");
    	productListingPage=homePage.SearchProduct();
    	status=Utilities.VerifyElementPresent(productListingPage.BestSellerProducts.get(0));
    	Assert.assertTrue(status);
    	Utilities.WaitForPagetoLoad();
    	
    }
   // This test is for adding all the Best selling products into cart
    @Test(priority=1)
    public void AddProductToCart() throws InterruptedException 
    {  
    	System.out.println("Inside AddProductToCart test");
        String CartItemCountString="";
    	test=extent.createTest("Add products to cart");
    //This loop will select all the best selling products one by one and will add to cart 
    	for(int i=0;i<productListingPage.BestSellerProducts.size();i++)
    	{
    		//Get the products  one by one depending on the "i" value
    		WebElement ele=productListingPage.BestSellerProducts.get(i);
    	    System.out.println("Inside For loop");
    	    //Scroll to the product  
    	    Utilities.ScrollToElement(ele);
    		System.out.println(i+1 +" Product selected , name of the product : " +ele.getText());
    		//Click the product link to open the product
    		ele.click();
    		Utilities.WaitForPagetoLoad();
    		//Click on add to cart button
    		productDetailsPage.AddToCartButtonClick();
    		Utilities.WaitForPagetoLoad();
    		//Below code is for validating the cart value after adding the product to cart 
    		CartItemCountString=i+1+" item";
    		Boolean TextValidation= productDetailsPage.validateItemsCount(CartItemCountString);
    		softAssert.assertTrue(TextValidation);
    		
    		// navigate back to search result page
    		driver.navigate().back();
    		Utilities.WaitForPagetoLoad();
    		
    	}
    
    	
    }
    
    // This method will execute after every test method and status of the test case will be captured by ItestResult object 
    // and based on the status report is generated and captured in extent report html file . If any fail will be there then
    // screenshot will be captured and will be included in the same html report with failed status of the particular test
    @AfterMethod
    public void ReportLogging( ITestResult result) throws IOException {
    	Utilities.LogReport(result);
    	softAssert.assertAll();
    	
    }
    //This method will execute once all the test execution is done 
    
    @AfterTest
    public void endReport() {
    System.out.println("Inside EndReport(After Test) method");	
     extent.flush();
    driver.quit();
    }
}
