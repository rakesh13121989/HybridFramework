package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;


public class ProductDetailsPage extends Base {
	
	@FindBy(xpath="//div[@data-feature-name='addToCart']")
	 WebElement AddToCartButton;
	

	
	@FindBy(xpath="//span[contains(text(),'Proceed to checkout')]")
	public  WebElement ItemsCountinCart;

//Loading and initializing the objects 
  public ProductDetailsPage()
  {
	  PageFactory.initElements(driver, this);
	 
  }
	
 public void AddToCartButtonClick() {
	 AddToCartButton.click();
	 
 }
 
 public boolean validateItemsCount(String ExpectedText) throws InterruptedException {
	 /*WebDriverWait wait= new WebDriverWait(driver,10);
	 wait.until(ExpectedConditions.visibilityOfElementLocated((By) ItemsCountinCart));*/
	 Thread.sleep(4000);
	 if(ItemsCountinCart.getText().contains(ExpectedText)) {
		 System.out.println(ExpectedText + " found sucessfully");
		 return true;
	}
	else {
		 System.out.println(ExpectedText + " doesn't match with : "+ItemsCountinCart.getText());
		return false;
	
	}
 }

  
}
