package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


import base.BaseClass;
import pageObjects.CartPageObjects;
import pageObjects.InventoryObjects;
import pageObjects.LoginPageObjects;
import pageObjects.testInventoryObject;
import utils.ProjectContants;

public class CartTest extends BaseClass{

	private static final Logger logger = LogManager.getLogger(CartTest.class);
	@Test
    public void addItemToCartTest() throws InterruptedException {
		
		
		
       new LoginPageObjects(driver).login(ProjectContants.username, ProjectContants.password);

       // Add an item to cart
       InventoryObjects inventory = new InventoryObjects(driver);
       inventory.addItemToCart("Bike");
       inventory.goToCart();
       logger.info("CartTest: addItemToCartTest : Completed the test");
    }	
	
	@Test
	public void addMultipleItemsToCart() throws InterruptedException {
		 // Add multiple items to cart
	       new LoginPageObjects(driver).login(ProjectContants.username, ProjectContants.password);
	       InventoryObjects inventory = new InventoryObjects(driver);
	       inventory.addItemToCart("Bike");
	       inventory.addItemToCart("Fleece");
	       Assert.assertEquals(inventory.cartBadgeNumber(), "2");
	       
	       Thread.sleep(3000);
	}
	
	
	@Test
	public void addMultipleItemsToCartAndRemove() throws InterruptedException {
		// Add multiple items to cart and remove one
	       new LoginPageObjects(driver).login(ProjectContants.username, ProjectContants.password);
	       InventoryObjects inventory = new InventoryObjects(driver);
	       inventory.addItemToCart("Bike");
	       inventory.addItemToCart("Fleece");
	       inventory.RemoveItem("Bike");
	       Assert.assertEquals(inventory.cartBadgeNumber(), "1");
	}
	
	@Test
	public void verifyContentsOnCartandInventory() throws InterruptedException {
		// Add items to cart and verify the details of the product added matches with cart items
	       new LoginPageObjects(driver).login(ProjectContants.username, ProjectContants.password);
	       InventoryObjects inventory = new InventoryObjects(driver);
	      // inventory.addItemToCart("Bike");
	       testInventoryObject t1= new testInventoryObject();
	       t1=inventory.addItemToCart("Jacket");
	       
	       System.out.println(t1);
	       System.out.println("Entering cart objects");
	       
	       CartPageObjects co = new CartPageObjects(driver); 
	       testInventoryObject t2= new testInventoryObject();
	       
		   t2=co.verifyItemList("Jacket");
	    
		   Assert.assertEquals(t1.getTitle(),t2.getTitle());
		   Assert.assertEquals(t1.getDesc(),t2.getDesc());
		   Assert.assertEquals(t1.getPrice(),t2.getPrice());

		   // Assert.assertEquals(inventory.cartBadgeNumber(), "1");
	       Thread.sleep(3000);
	}
		
}
