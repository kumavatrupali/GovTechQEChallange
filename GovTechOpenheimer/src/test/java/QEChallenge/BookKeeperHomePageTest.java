package QEChallenge;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import QEChallenge.baseModule.BaseRoot;
import QEChallenge.pages.BookKeeperHomePage;
import QEChallenge.pages.ClerkHomePage;
import QEChallenge.pages.LoginPage;
import QEChallenge.pages.UploadCSVFilePage;

public class BookKeeperHomePageTest extends BaseRoot{

		LoginPage lp;
		ClerkHomePage chp;
		UploadCSVFilePage upfp;
		String user;
		String pass;
		BookKeeperHomePage bkhp;
		String downloadPath = "C:\\Users\\gole_\\Downloads";
		
			
		public BookKeeperHomePageTest()
		{
			super();
		}
		
			@BeforeMethod
		public void launch() throws IOException
		{
			
			initializeDriver();
			lp=new LoginPage();
			user = prop.getProperty("userbk");
			pass = prop.getProperty("userbk");
			try {
				bkhp =  lp.bkapplicationLogin(user,pass);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("passed");
		}
		@Test
		public void generateTaxFile() throws InterruptedException
		{
		boolean fileflag=bkhp.checkkDownloadFile();
		System.out.println(fileflag);
		Assert.assertTrue(fileflag);
		}
		
		@Test
		public void validateTaxFile() {
			bkhp.readTaxFile();
		}
		
		@AfterTest
		public void closeDriver()
		{
			quitDriver();
					
		}
		
}
