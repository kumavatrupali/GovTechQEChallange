package QEChallenge;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import QEChallenge.baseModule.BaseRoot;
import QEChallenge.pages.ClerkHomePage;
import QEChallenge.pages.LoginPage;

public class LoginPageTest extends BaseRoot {

	LoginPage lp;
	ClerkHomePage chp;
	String user;
	String pass;
//	WebDriver driver;
	
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void launch() throws IOException
	{
		
		initializeDriver();
		lp=new LoginPage();
	}
	
	@Test
	public void loginToApp() throws IOException
	{	
		user = prop.getProperty("user1");
		pass = prop.getProperty("pass1");
		try {
			chp = (ClerkHomePage) lp.applicationLogin(user,pass);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("passed");
	}
	
	@AfterMethod
	public void closeDriver()
	{
		quitDriver();
				
	}
	

}
