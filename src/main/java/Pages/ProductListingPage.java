package Pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;


public class ProductListingPage extends Base {
	
	@FindBy(xpath="//span[text()='Best Seller']/ancestor::div[@class='sg-row']/following-sibling::div//h2//span")
	public List<WebElement> BestSellerProducts;



//Loading and initializing the objects 
  public ProductListingPage()
  {
	  PageFactory.initElements(driver, this);
	 
  }
	


  
}
