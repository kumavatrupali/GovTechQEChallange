package QEChallenge;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;

import QEChallenge.baseModule.BaseRoot;
import QEChallenge.pages.ClerkHomePage;
import QEChallenge.pages.LoginPage;
import QEChallenge.pages.UploadCSVFilePage;
import junit.framework.Assert;

public class UploadCSVFilePageTest extends BaseRoot{
	
	LoginPage lp;
	ClerkHomePage chp;
	UploadCSVFilePage upfp;
	String[] csvData;
	Statement st;
	
	String user;
	String pass;
	
	public UploadCSVFilePageTest ()
	{	
		super();			
	}
	
	@BeforeTest
	public void launch() throws IOException, SQLException
	{
		
		initializeDriver();
		st=connectDB();
		lp=new LoginPage();
		upfp=new UploadCSVFilePage(); 
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
	
	@Test(priority=1)
	public void navigateToFileUpload() throws InterruptedException
	{
		upfp = chp.clickOnUploadCSV();
		
	}
	@Test(priority=2)
	public void uploadCSVFile() throws InterruptedException, IOException, CsvException, SQLException
	{
		String csvFilePath =System.getProperty("user.dir")+prop.getProperty("CsvFile");
		upfp.uploadFile(csvFilePath);		
		System.out.println("csvFilePath");
		boolean recordUploaded = upfp.validateCSVagainstDB(st,csvFilePath);
		System.out.println(recordUploaded);

		Assert.assertTrue(true);
	}
	
	@Test(priority=3)
	public void uploadErrorCSVFile() throws InterruptedException, IOException, CsvException, SQLException
	{	
		String errorFilePath = System.getProperty("user.dir")+prop.getProperty("errorFile");
		upfp.uploadErrorFile(errorFilePath);		
		System.out.println("File Upload Failed");
		boolean recordFailedtoUplaod =upfp.validateCSVagainstDB(st,errorFilePath);
		System.out.println(recordFailedtoUplaod);

		Assert.assertTrue(!recordFailedtoUplaod);

	}
	@AfterTest
	public void closeDriver() throws SQLException
	{
		quitDriver();
		connect.close();
	}
	

}
