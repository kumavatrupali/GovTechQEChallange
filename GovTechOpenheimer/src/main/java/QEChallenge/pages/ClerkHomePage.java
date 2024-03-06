package QEChallenge.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import QEChallenge.baseModule.BaseRoot;

public class ClerkHomePage extends BaseRoot {

	//WebDriver driver;
	public ClerkHomePage()
	{	
		//get initialized driver here
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="dropdownMenuButton2")
	WebElement AddHero;
	
	@FindBy(xpath="//*[@class=\"dropdown-menu dropdown-menu-dark show\"]/li[2]/a")
	WebElement UploadCSV;
	
	public UploadCSVFilePage clickOnUploadCSV() throws InterruptedException
	{
		AddHero.click();
		UploadCSV.click();
		return new UploadCSVFilePage();
	}

	
}
