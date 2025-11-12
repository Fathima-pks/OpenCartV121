package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass{
	

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
	public void testLlogin(String Email, String pwd, String expected)
	{
		logger.info("Data driven testing of login started");
		logger.info("TC_002_LoginTest Started");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		logger.info("clicked login button");
		
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(2000);
		lp.setEmail(Email);
		lp.setpassword(pwd);
		lp.clickLogin();
		logger.info("entered into the MyAccountpage aftersuccessful login");
		
		/*Data is valid  - login success - test pass  - logout
							login failed - test fail

		Data is invalid - login success - test fail  - logout
 				login failed - test pass
*/

		logger.info("Myaccount validation starter");
		MyAccountPage myacc= new MyAccountPage(driver);
		boolean targetpage=myacc.MyAccountmgsdisplay();
		
		if(expected.equalsIgnoreCase("valid"))
				{		
			if(targetpage==true)
			{
				myacc.clickLogout();
				Assert.assertTrue(true);
			}
				
		else
		{
			Assert.assertTrue(false);
		}
		}
		
		if(expected.equalsIgnoreCase("invalid"))

				{
			if(targetpage==true)
			{
				myacc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
			Thread.sleep(3000);
				}
	}
		catch(Exception e)
		{
			Assert.fail("An exception occured"+e.getMessage());
		}
	
		logger.info("validation ends");
		logger.info("TC_002_LoginTest Finished");
		
}
}
