package tests;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import base.BaseClass;
import pageObjects.CartPageObjects;
import pageObjects.CheckoutPage;
import pageObjects.InventoryObjects;
import pageObjects.LoginPageObjects;
import utils.ProjectContants;

public class CheckoutTest extends BaseClass{

	private static final Logger logger = LogManager.getLogger(CheckoutTest.class);
	
	
	@Test
    public void testcase1() throws InterruptedException {
		
		
        new LoginPageObjects(driver).login(ProjectContants.username, ProjectContants.password);

        InventoryObjects inventory = new InventoryObjects(driver);
        inventory.addItemToCart("Bolt");
        inventory.goToCart();

        Thread.sleep(2000);
        CartPageObjects cart = new CartPageObjects(driver);
        cart.clickcheckout();

        CheckoutPage cp = new CheckoutPage(driver);
        Thread.sleep(2000);
        cp.enterCheckoutInfo("John", "Doe", "12345");
        cp.clickContinue();
        cp.clickFinish();
        Assert.assertEquals(cp.checkOutCompleteText(), "Checkout: Complete!");
        Assert.assertEquals(cp.thankYouMessageText(), "Thank you for your order!", "Checkout has been successfully completed!!!!!");
        
        logger.info("CheckoutTest class: completeCheckoutTest :Completed Testcase 1");
    }
	
	@Test
	public void testcase2() throws InterruptedException {
		//enter blank in checkout page(step one) and verify the message shown
		new LoginPageObjects(driver).login(ProjectContants.username, ProjectContants.password);
		InventoryObjects ino= new InventoryObjects(driver);
		ino.addItemToCart("Onesie");
		ino.goToCart();
		
		CartPageObjects cart = new CartPageObjects(driver);
		cart.clickcheckout();
		
		CheckoutPage cp= new CheckoutPage(driver);
		cp.enterCheckoutInfo("", "", "");
		cp.clickContinue();
		Assert.assertEquals(cp.missingData(), "Error: First Name is required");
		logger.info("CheckoutTest class: completeCheckoutTest :Completed Testcase 2");
	}
	
	@Test
	public void testcase3() throws InterruptedException{
		// to cancel the checkout process without entering the FName, LName and Zip(step one). It should redirect to cart page
		new LoginPageObjects(driver).login(ProjectContants.username, ProjectContants.password);
		InventoryObjects ino= new InventoryObjects(driver);
		ino.addItemToCart("Onesie");
		ino.goToCart();
		
		CartPageObjects cart = new CartPageObjects(driver);
		cart.clickcheckout();
		
		CheckoutPage cp= new CheckoutPage(driver);
		cp.clickCancel();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
		logger.info("CheckoutTest class: completeCheckoutTest :Completed Testcase 3");
	}
	@Test
	public void testcase4() throws InterruptedException{
		// to cancel the checkout process(step two). It should redirect to inventory page

		new LoginPageObjects(driver).login(ProjectContants.username, ProjectContants.password);
		InventoryObjects ino= new InventoryObjects(driver);
		ino.addItemToCart("Onesie");
		ino.goToCart();
		
		CartPageObjects cart = new CartPageObjects(driver);
		cart.clickcheckout();
		
		CheckoutPage cp= new CheckoutPage(driver);
		cp.enterCheckoutInfo("Joe", "Smith", "123123");
		cp.clickContinue();
		cp.clickCancel();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
		logger.info("CheckoutTest class: completeCheckoutTest :Completed Testcase 4");
	}
	
	@Test()
	public void testcase5() throws InterruptedException{
		// to cancel the checkout process(step two). It should redirect to inventory page

		new LoginPageObjects(driver).login(ProjectContants.username, ProjectContants.password);
		InventoryObjects ino= new InventoryObjects(driver);
		ino.addItemToCart("Onesie");
		ino.addItemToCart("Bolt");
		ino.addItemToCart("Backpack");
		ino.goToCart();
		
		CartPageObjects cart = new CartPageObjects(driver);
		cart.clickcheckout();
		
		CheckoutPage cp= new CheckoutPage(driver);
		cp.enterCheckoutInfo("Joe", "Smith", "123123");
		cp.clickContinue();
		
		double itemTotal=cp.getCalculatedTotal();
		
		Assert.assertEquals(itemTotal, cp.getSubTotal());
		
		double tax= cp.getTax();
		double total= itemTotal+tax+100;
		Assert.assertEquals(total, cp.getTotalDisplayed());
		logger.info("CheckoutTest class: completeCheckoutTest :Completed Testcase 5");
		
	}
}
