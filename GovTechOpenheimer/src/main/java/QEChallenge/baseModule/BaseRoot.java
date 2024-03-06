package QEChallenge.baseModule;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Properties;

import javax.print.DocFlavor.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import QEChallenge.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseRoot {

	// all common procedures to be mentioned here
	
	public static WebDriver driver;
	public static Properties prop;
	public Connection connect;
	public Statement st;
	public BaseRoot() {
		try
		{
			prop = new Properties();
		//	FileInputStream file= new FileInputStream("C:\\Users\\gole_\\OneDrive\\Documents\\new Govtech\\GovTechOpenheimer\\src\\main\\java\\QEChallenge\\resources\\DataRepository.properties");
			
			FileInputStream file= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\QEChallenge\\resources\\DataRepository.properties");
			prop.load(file);
			System.out.println("pass1");
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
		public static void initializeDriver() throws IOException
		{	
		String browsername =prop.getProperty("browser");
	
		if(browsername.equals("chrome"))
		{	
		System.out.println("pass2");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\gole_\\OneDrive\\Documents\\Govtech\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.manage().window().maximize();

		driver.get(prop.getProperty("url"));
		}

	}
	public static void callWait() throws InterruptedException {
		driver.wait();
	}

	
	public static void quitDriver() {
		driver.quit();
	}
	public Statement connectDB() throws SQLException
	{
		 connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","user","userpassword");
		 st = connect.createStatement();
		
		return st;
		
	}
	
	
	/*public LoginPage loadApplication() throws IOException
	{
		driver = initializeDriver();
		LoginPage lp = new LoginPage(driver);
		lp.loadurl();
		return lp;
		
	}*/
}
