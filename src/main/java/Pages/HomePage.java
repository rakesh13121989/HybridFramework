package Pages;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Base.Base;
import Utility.Utilities;


public class HomePage extends Base {
	
	@FindBy(xpath="//div[@class='nav-search-field ']//input")
	WebElement SearchBox;
	
	@FindBy(xpath="//input[@value='Go']")
	WebElement SearchButton;
	

//Loading and initializing the objects 
  public HomePage()
  {
	  PageFactory.initElements(driver, this);
	 
  }
	
 
  public  ProductListingPage SearchProduct() throws InterruptedException
  {
	  System.out.println("Inside SearchProduct method");
	  SearchBox.sendKeys("Headphones",Keys.ENTER);
	 // SearchBox.sendKeys("Headphones");
	  Utilities.WaitForPagetoLoad();
	 
	return (new ProductListingPage());
	  
}
  
 
  
}
