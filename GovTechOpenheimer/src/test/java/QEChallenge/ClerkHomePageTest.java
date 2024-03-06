package QEChallenge;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import QEChallenge.baseModule.BaseRoot;
import QEChallenge.pages.ClerkHomePage;
import QEChallenge.pages.LoginPage;
import QEChallenge.pages.UploadCSVFilePage;

public class ClerkHomePageTest extends BaseRoot{

	LoginPage lp;
	ClerkHomePage chp;
	UploadCSVFilePage upfp;
	String user;
	String pass;
		
	public ClerkHomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void launch() throws IOException
	{
		
		initializeDriver();
		lp=new LoginPage();
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
	@Test
	public void navigateToFileUpload() throws InterruptedException
	{
		upfp = chp.clickOnUploadCSV();
		
	}
}

