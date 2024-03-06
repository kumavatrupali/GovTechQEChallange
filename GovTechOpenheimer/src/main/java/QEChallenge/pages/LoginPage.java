package QEChallenge.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import QEChallenge.baseModule.BaseRoot;


public class LoginPage extends BaseRoot{

	// List all web elements from Login Page 
	//WebDriver driver;
	public LoginPage()
	{	
		//get initialized driver here
		PageFactory.initElements(driver,this);
	}/*
	
	public LoginPage()
	{	
		PageFactory.initElements(driver,this);
	}*/
	//Page factory Elements declaration
	@FindBy(id="username-in")
	WebElement userName;
	
	@FindBy(id="password-in")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submit;

	
	public ClerkHomePage applicationLogin(String userID,String appPassword)
	{
		userName.sendKeys(userID);
		password.sendKeys(appPassword);
		submit.click();
		return new ClerkHomePage();
		
	}
	public BookKeeperHomePage bkapplicationLogin(String userID,String appPassword)
	{
		userName.sendKeys(userID);
		password.sendKeys(appPassword);
		submit.click();
		return new BookKeeperHomePage();
		
	}
	
}
