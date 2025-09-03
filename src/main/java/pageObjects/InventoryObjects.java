package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryObjects {

	WebDriver driver;
	public InventoryObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this );	
	}
	
	@FindBy(className="inventory_item_name")
	List<WebElement>  item_Name;
	
	@FindBy(className="inventory_item_name")
	WebElement itemName;
	
	
	@FindBy(className="inventory_item_desc")
	List<WebElement>  item_Desc;
	
	@FindBy(className="inventory_item_price")
	List<WebElement>  item_Price;

	@FindBy(id="shopping_cart_container")
	WebElement cart;
	
	@FindBy(xpath="//span[@data-test='shopping-cart-badge']")
	WebElement cartBadge;
	
	@FindBy(xpath="//select[@data-test='product-sort-container']")
	WebElement filterDropdown;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement burgerMenu;
	
	@FindBy(id="about_sidebar_link")
	WebElement aboutSauceLabs;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logout;


	
	  public testInventoryObject addItemToCart(String product) {
		  String name =null, Idesc=null,Iprice = null;
		  System.out.println("The product is : "+product);
		  	for(WebElement item : item_Name) {
		  		name= item.getText();
		  		
		  		System.out.println("Name:  "+name);
		  		if(name.contains(product)) {
		  			driver.findElement(By.xpath("//div[.='"+ name +"']/../../following-sibling ::div/button")).click();
		  			System.out.println(product +  " Added to cart");
		  			Idesc=driver.findElement(By.xpath("//div[.='"+name+"']/../../div")).getText();
		  			System.out.println(name + Idesc + Iprice);
		  			Iprice=driver.findElement(By.xpath("//div[.='"+name+"']/../../following-sibling::div/div")).getText();
		  			
					break;
		  		}
		  		
		  		
		  	}
		  	
		  	return new testInventoryObject(name, Idesc,Iprice );
	    }
	  
	  
	
	  
	  
	  
	  public void RemoveItem(String product) {
	  
		  for(WebElement item : item_Name) {
		  		String name= item.getText();
		  		if(name.contains(product)) {
		  			driver.findElement(By.xpath("//div[.='"+ name +"']/../../following-sibling ::div/button")).click();
		  			System.out.println(product +  " Removed from cart");
		  		}
		  		
		  		}
		  
	  }
	  public void goToCart() {
		  cart.click();
	    }
	  public String cartBadgeNumber() {
		 try {
			  return cartBadge.getText();
			 
		 }
		 catch(Exception e) {
			 System.out.println("No items are added to the cart");
			 e.printStackTrace();
		 }
		 return null;
	  }
	  
	  public void clickFilter(String text) {
		  Select select = new Select(filterDropdown);
		  select.selectByVisibleText(text);
		  
	  }
	  public void clickBurgerMenu() {
		  burgerMenu.click();
	  }
	  
	  public void clickAboutSauceLabs() {
		  aboutSauceLabs.click();
	  }
	  
	  
	  public void clickLogout() {
		  logout.click();
	  }
	  
	
}