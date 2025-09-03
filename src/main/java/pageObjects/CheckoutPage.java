package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this );	
	}
	
	@FindBy(id="first-name")
	WebElement firstName;

	@FindBy(id="last-name")
	WebElement lastName;
	
	@FindBy(id="postal-code")
	WebElement zip;
	
	@FindBy(id="continue")
	WebElement continueBtn;
	
	@FindBy(id="cancel")
	WebElement cancelBtn;

	@FindBy(id="finish")
	WebElement finishBtn;
	
	@FindBy(xpath="//span[.='Checkout: Complete!']")
	WebElement checkOutComplete;
			
	@FindBy(xpath="//h2[.='Thank you for your order!']")
	WebElement thankYouMessage;
	
	@FindBy(xpath="//div/h3[@data-test='error']")
	WebElement missingTextMessage;
		
	@FindBy(xpath="//div[@class='summary_subtotal_label']")
	WebElement subTotal;

	@FindBy(xpath="//div[@class='summary_total_label']")
	WebElement Total;
	
	@FindBy(xpath="//div[@class='summary_tax_label']")
	WebElement tax;

	@FindBy(xpath="//div[@class='inventory_item_price']")
	List<WebElement> prices;	
	
	public void enterCheckoutInfo(String fName, String lName, String postalCode) throws InterruptedException {
		Thread.sleep(500);
		firstName.sendKeys(fName);
		System.out.println("Entered first Name");
		
		lastName.sendKeys(lName);
		zip.sendKeys(postalCode);
		
	}
	public void clickContinue() {
		continueBtn.click();
		
	}
	public void clickCancel() {
		cancelBtn.click();
		
	}
	public void clickFinish() {
		finishBtn.click();
	}
	
	public String checkOutCompleteText()
	{
		return checkOutComplete.getText();
	}
	public String thankYouMessageText()
	{
		return thankYouMessage.getText();
	}
	public String missingData() {
		return missingTextMessage.getText();
	}

	public double getCalculatedTotal() {
		
		double total = 0.0;
		for(WebElement price: prices) {
			total += Double.parseDouble(price.getText().replace("$", ""));
		}
		
		return total;
	}
	
	public double getSubTotal() {
		return Double.parseDouble(subTotal.getText().replace("Item total: $", ""));
	}

	public double getTax() {
	return Double.parseDouble(tax.getText().replace("Tax: $", ""));
	}
	
	public double getTotalDisplayed() {
		return Double.parseDouble(Total.getText().replace("Total: $", ""));
	}
		
}
