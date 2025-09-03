package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
		public WebDriver driver;
	
	@BeforeMethod
	public void beforeClass(ITestContext context) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		
		driver.get("https://www.saucedemo.com/");
		context.setAttribute("driver", driver); // setting context for listeners
	}
	
	@AfterMethod
	public void afterClass() {
		
		driver.quit();
	}
	
	
}
