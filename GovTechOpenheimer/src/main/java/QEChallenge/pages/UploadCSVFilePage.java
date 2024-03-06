package QEChallenge.pages;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import  org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import QEChallenge.baseModule.BaseRoot;


public class UploadCSVFilePage extends BaseRoot {

	public CSVReader csr;	
		
	public UploadCSVFilePage()
	{	
		//get initialized driver here
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="upload-csv-file")
	WebElement chooseFile;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement CreateButton;
	
	@FindBy(xpath="//*[@id='notification-block']/div/h3")
	WebElement FileUploadSuccessMessage;
	
	//ErrorTitle
	@FindBy(xpath="//*[@class='bg-warning pt-2']/h3")
	WebElement ErrorTitle;
	
	@FindBy(xpath="//*[@id=\"notification-block\"]/div/div/p")
	WebElement ErrorDescription;

	//error description
	
	
	@SuppressWarnings("deprecation")
	public void uploadFile(String filePath) throws IOException, InterruptedException
	{
		//chooseFile.click();
		chooseFile.sendKeys(filePath);
		Thread.sleep(2000);
		CreateButton.click();
	    String message = FileUploadSuccessMessage.getText();
	    System.out.println("File upload message "+message);
	    Assert.assertEquals(message,"Created Successfully!");
	    
	}

	public void uploadErrorFile(String filePath) throws InterruptedException {
		// TODO Auto-generated method stub
		chooseFile.sendKeys(filePath);
		Thread.sleep(2000);
		CreateButton.click();
	  //  String message = ErrorTitle.getText();
	    Assert.assertEquals(ErrorTitle.getText(),"Unable to create hero!");
	    Assert.assertEquals(ErrorDescription.getText(),"Unable to process csv file! Please contact tech support for help!");

	    
	}
	
	public boolean validateCSVagainstDB(Statement st,String filePath) throws IOException, CsvException, SQLException
	{
		ResultSet rs = null;
		boolean flag = false;
		//String query = "Select natid from WORKING_CLASS_HEROES";
		csr = new CSVReader(new FileReader(filePath));
		//Read All data into list
		List<String[]> data = csr.readAll();
		Iterator<String[]>it= data.iterator();
		String[] csvData = null;
		while(it.hasNext())
		{
			csvData = it.next();
			System.out.println(csvData.length);
			for(int i=0; i<csvData.length; i++)
			{	
				String csvrow =csvData[i];
			//	String[] csvcell=csvrow.split(";");
			//	System.out.println(csvcell[i]);
				String query = "select natid from testdb.working_class_heroes "
								+ "where natid="+"\"" +csvData[i]+ "\"";
					System.out.println(query);
				
				 rs = st.executeQuery(query);	
				/* if(rs.next())
				 * 
				 */if(csvData[i]==null)
						flag= true;
					else
						flag=false;
				
			}
			
		}
		return flag;
		}
		

	}
	

	


