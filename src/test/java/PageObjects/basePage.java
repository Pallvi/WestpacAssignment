package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class basePage {

	public static WebDriver driver;

	public void SetUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\root\\Desktop\\Westpac\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.westpac.co.nz/");
		driver.manage().window().maximize();
	}

}