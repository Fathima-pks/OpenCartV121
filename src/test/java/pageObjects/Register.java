package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Register extends BasePage {

	public Register(WebDriver driver) 
	{
		super(driver);
		
	}
	

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_lastname;
	
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_confirmpwd;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement btn_newsletter;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chk_privacypolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmation_mgs;
	

	
	
	public void setFirstName(String fname)
	{
		txt_firstname.sendKeys(fname);
	}

	
	
	public void setLastName(String lname)
	{
		txt_lastname.sendKeys(lname);
	}

	
	public void setTelephone(String phno)
	{
		txt_telephone.sendKeys(phno);
	}

	
	
	public void setEmail(String email)
	{
		txt_email.sendKeys(email);
	}

	public void setPassword(String pwd)
	{
		txt_password.sendKeys(pwd);

	}

	public void setConfirmPassword(String pwd)
	{
		txt_confirmpwd.sendKeys(pwd);

	}
	
	
	public void clickNewsletter()
	{
		btn_newsletter.click();
	}

	
	public void clickPrivacyPolicy() {
		chk_privacypolicy.click();

	}

	
	public void clickcontinue()
	{
		btn_continue.click();
	}
	
	
	public String getconfirmationmessage()
	{
		try
		{
		String mgs=confirmation_mgs.getText();
		return mgs;
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
}
