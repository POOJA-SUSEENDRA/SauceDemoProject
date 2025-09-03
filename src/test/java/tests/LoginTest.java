package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


import base.BaseClass;
import pageObjects.LoginPageObjects;
import utils.ProjectContants;

public class LoginTest extends BaseClass{

	private static final Logger logger = LogManager.getLogger(LoginTest.class);
	@Test
	// valid credentials
	public void LoginPageTest1() throws InterruptedException {
		LoginPageObjects lpo = new LoginPageObjects(driver);
		lpo.login(ProjectContants.username, ProjectContants.password);
		logger.info("Completed Testcase 1");
	}
	
	@Test
	// invalid username and invalid password
	public void LoginPageTest2() throws InterruptedException {
		LoginPageObjects lpo = new LoginPageObjects(driver);
		lpo.login("standard_use", "common_sauce");
		logger.info("Completed Testcase 2");
	}
		
	@Test
	// valid username and invalid password
	public void LoginPageTest3() throws InterruptedException {
		LoginPageObjects lpo = new LoginPageObjects(driver);
		lpo.login("standard_user", "common_sauce");
		logger.info("Completed Testcase 3");
	}
	@Test
	// empty fields
	public void LoginPageTest4() throws InterruptedException {
		LoginPageObjects lpo = new LoginPageObjects(driver);
		lpo.login(" ", " ");
		logger.info("Completed Testcase 4");
	}
	@Test
	// test password is hidden
	public void LoginPageTest5() throws InterruptedException {
		LoginPageObjects lpo = new LoginPageObjects(driver);
		lpo.login("standard_user", "common_sauce");
		String pwdType=lpo.pass.getAttribute("type");
		Assert.assertEquals(pwdType, "password", "Password is masked");
		logger.info("Completed Testcase 5");
	}
}
