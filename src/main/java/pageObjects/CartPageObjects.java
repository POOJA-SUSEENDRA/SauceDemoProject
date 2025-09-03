package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPageObjects {

	WebDriver driver;
	public  CartPageObjects(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this );	
	}
	
	@FindBy(className="inventory_item_name")
	List<WebElement>  item_Name;
	
	@FindBy(className="inventory_item_desc")
	List<WebElement>  item_desc;
	
	@FindBy(className="inventory_item_price")
	List<WebElement>  item_Price;
	
	@FindBy(id="checkout")
	WebElement checkout;
	
	@FindBy(id="continue-shopping")
	WebElement continueShopping;

	@FindBy(id="shopping_cart_container")
	WebElement cart;
	
	public void clickContinueShopping() {
		continueShopping.click();
	}
	public void clickcheckout() {
		checkout.click();
	}

	public testInventoryObject verifyItemList(String title) {
		String Idesc=null,Iprice = null, pdTitle =null;
		cart.click();
		for(WebElement item: item_Name ) {
			 pdTitle=item.getText();
			if(pdTitle.contains(title)) {
			Idesc=driver.findElement(By.xpath("//div[.='"+pdTitle+"']/../../div")).getText();
			Iprice=driver.findElement(By.xpath("//div[.='"+pdTitle+"']/../..//div[@class='inventory_item_price']")).getText();
			}
		}
		return new testInventoryObject(pdTitle, Idesc,Iprice );
		
	}
	
	
	
}
