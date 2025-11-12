package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
	
	@Test(groups= {"Regression","Master"})
	public void TestLlogin()
	{
		logger.info("TC_002_LoginTest Started");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		Thread.sleep(2000);
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(2000);
		lp.setEmail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clickLogin();
		
		
		MyAccountPage myacc= new MyAccountPage(driver);
		boolean targetpage=myacc.MyAccountmgsdisplay();
		Assert.assertEquals(targetpage, true);
		}
		
		catch(Exception e)
		{
		Assert.fail();
		}
		
		
		logger.info("TC_002_LoginTest Finished");
		
		
		
		
		
		
		
		
	}
	
	
	

}
