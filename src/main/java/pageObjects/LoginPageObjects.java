package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {

	WebDriver driver;
    public LoginPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this );	
    }
    
	@FindBy(id="user-name")
	WebElement uname;
	
	@FindBy(id="password")
	public	WebElement pass;
	
	@FindBy(id="login-button")
	WebElement login;
	
	
	public void login(String username, String password) throws InterruptedException {
      	uname.sendKeys(username);
      	Thread.sleep(2000);
		pass.sendKeys(password);
        login.click();
        Thread.sleep(2000);
    }
	
	
	
	
}
