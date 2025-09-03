package listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestListeners implements ITestListener{

	private static final Logger logger = LogManager.getLogger(TestListeners.class);
	
	private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	
	
	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Test Started: "+ result.getName());
		 ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
	        test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Successful!!!: "+ result.getName());
		test.get().pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		  	  System.out.println("Entered onTestFailure");
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        System.out.println("Driver details: "+ driver);

        if (driver != null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String methodName = result.getName();
            String fileName = "screenshots/" + methodName + "_" + timestamp + ".png";

            System.out.println("created screenshot file");
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.createDirectories(Paths.get("screenshots"));
                Files.copy(src.toPath(), Paths.get(fileName));
                System.out.println("Screenshot saved to: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        test.get().fail(result.getThrowable());
        logger.info("Test Failed....."+ result.getName());
        
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("Test Skipped-----"+ result.getName());
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/ExtentReport.html");
        reporter.config().setReportName("SauceDemo Automation Report");
        reporter.config().setDocumentTitle("SauceDemo Test Results");
        
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Pooja", "QA Engineer - T2");
        
	}

	@Override
	public void onFinish(ITestContext context) {
		  extent.flush();
	}

	
}
