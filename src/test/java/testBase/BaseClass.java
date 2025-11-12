package testBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@Parameters({"os","browser"})
	@BeforeMethod(groups= {"sanity","Regression","Master"})
	public void setup(String os, String br) throws IOException
	{
		//loading log files
		logger=LogManager.getLogger(this.getClass());
	
		
		//loading properties files
		//FileReader file= new FileReader(".//src//test//resources//config.properties");
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");

		p=new Properties(); // creating object for Properties class
		p.load(file); // load the propertyfile data
	
	
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
				{
					ChromeOptions options = new ChromeOptions();
				
					//Set the Platform (OS)
					if(os.equalsIgnoreCase("Windows"))
					{
						options.setPlatformName("Windows10");
					}
					else if(os.equalsIgnoreCase("mac"))
					{
						options.setPlatformName("MAC");
					}
					else if(os.equalsIgnoreCase("Linux"))
					{
						options.setPlatformName("Linux");
					}
					else
					{
						System.out.println("No matching OS");
						return;
					}
					
					
				//Set the Browser(chrome, firefox)
					switch (br.toLowerCase()) 
				    {
				        case "chrome":
				            ChromeOptions chromeOptions = new ChromeOptions();
				            chromeOptions.setPlatformName(os);
				            driver = new RemoteWebDriver(new URL("http://192.168.0.7:4444/wd/hub"), chromeOptions);
				            break;

				        case "edge":
				            EdgeOptions edgeOptions = new EdgeOptions();
				            edgeOptions.setPlatformName(os);
				            driver = new RemoteWebDriver(new URL("http://192.168.0.7:4444/wd/hub"), edgeOptions);
				            break;
				            
				        case "firefox":
				            FirefoxOptions firefoxOptions = new FirefoxOptions();
				            firefoxOptions.setPlatformName(os);
				            driver = new RemoteWebDriver(new URL("http://192.168.0.7:4444/wd/hub"), firefoxOptions);
				            break;
				              
				            
				        default:
				            System.out.println("Invalid Browser");
				            return;
				    }
				 	
				}
		
	/*	
		 if(br.equalsIgnoreCase("chrome")) 
			{
   			  ChromeOptions options = new ChromeOptions();
  			   options.setPlatformName(os.equalsIgnoreCase("windows") ? "WIN11" : "MAC");
    			 options.setBrowserVersion("142.0.7444.135");  // or exact version
     		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

 			} 
		else if(br.equalsIgnoreCase("edge")) 
			{
    			 EdgeOptions options = new EdgeOptions();
     		options.setPlatformName(os.equalsIgnoreCase("windows") ? "WIN11" : "MAC");
     		options.setBrowserVersion("latest");  // or exact version
    			 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
 			} 
		else 
			{
     		System.out.println("No matching browser");
    			 return;
			 }
		*/
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
				switch(br.toLowerCase())
				{
				case "chrome" : driver= new ChromeDriver();break;
				case "edge" : driver = new EdgeDriver();break;
				case "firefox" : driver=new FirefoxDriver();break;
				default: System.out.println("Invalid Browser Name");return;
				}
			}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURL")); // get the url from properties file
		
	}
	
	
	@AfterMethod(groups= {"sanity","Regression","Master"})
	public void teardown()
	{
		driver.quit();
	}
	
	
	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"*"+num);
	}


	public static String captureScreen(WebDriver driver, String name)
	{

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + name + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
		
	}

}
