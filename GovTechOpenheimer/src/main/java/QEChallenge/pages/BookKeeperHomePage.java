package QEChallenge.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v115.browser.Browser;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import QEChallenge.baseModule.BaseRoot;

public class BookKeeperHomePage extends BaseRoot{

	private static String downloadPath = "C:\\Users\\gole_\\Downloads";
	public BookKeeperHomePage()
	{	
		//get initialized driver here
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="tax_relief_btn")
	WebElement GenerateTaxReliefButton;
	
	//@FindBy(xpath="//*[@class=\"dropdown-menu dropdown-menu-dark show\"]/li[2]/a")
	//WebElement UploadCSV;

	public boolean checkkDownloadFile() throws InterruptedException {
		// TODO Auto-generated method stub
	
		GenerateTaxReliefButton.click();
	//	  Assert.assertTrue(isFileDownloaded(downloadPath, "mailmerge.xls"), "Failed to download Expected document");
		Thread.sleep(2000);
		  boolean fileDownloadflag = false;
		    File dir = new File(downloadPath);
		    File[] dir_contents = dir.listFiles();
		  	    
		    for (int i = 0; i < dir_contents.length; i++) {
		        System.out.println("file path = "+downloadPath);
		        System.out.println(dir_contents[i].getName());
		        if (dir_contents[i].getName().equals("tax_relief_file.txt")) {
		             fileDownloadflag=true;
		        	 break;
		    }
		        System.out.println(fileDownloadflag);
		        
		       
		            }

		    return fileDownloadflag;
	}
	
	public void readTaxFile() {
		
		try {
            FileReader reader = new FileReader("C:\\Users\\gole_\\Downloads\\tax_relief_file.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
               String row = line;
           		String[] data=row.split(",");
           		if(data.length==2) {
           			System.out.println("NatId" +data[0]);
           			System.out.println("Tax" +data[1]);
           		}
           		else
           			//if(row.contentEquals(count))
           			System.out.println("number of records" +row);
                count++;
            }
  
            System.out.println(count);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}

	
