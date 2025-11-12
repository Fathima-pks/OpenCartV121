package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.Register;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {


	
	@Test(groups={"sanity","Master"})
	public void testRegistration()
	{
		logger.info("******Starting Registration TestCase******");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on Myaccount link");
		hp.clickRegister();
		logger.info("clicked on Register link");
		
		
		Register reg = new Register(driver);
		logger.info("Account details are start capturing");
		reg.setFirstName(randomeString());
		reg.setLastName(randomeString());
		reg.setEmail(randomAlphaNumeric()+"@gmail.com");
		reg.setTelephone(randomeNumber());
		
		String pwd=randomeString();
		reg.setPassword(pwd);
		reg.setConfirmPassword(pwd);
		reg.clickNewsletter();
		reg.clickPrivacyPolicy();
		reg.clickcontinue();
		
		logger.info("validating the confirmation message");
		try
		{
		String conf_mgs=reg.getconfirmationmessage();
		Assert.assertEquals(conf_mgs,"Your Account Has Been Created!");
		{
			
		//Assert.assertTrue(true);
		logger.info("TestPassed");
		}
		}
		catch (Exception e)
		{
			Assert.fail();
			logger.error("TestFailed"+e.getMessage());
		}
		
		finally
		{
			logger.info("******Finished Registration TestCase******");
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
