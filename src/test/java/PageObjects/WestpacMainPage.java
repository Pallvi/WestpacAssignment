package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WestpacMainPage extends basePage{
	public WestpacMainPage(WebDriver driver)
	{
		this.driver = driver;
	}

	String menuItems = "nav[id=ubermenu-ps] li[class*=sw-ubermenu-section] a[class*=sw-ubermenu-section-link]";

	public void GoToPage()
	{
		driver.navigate().to("https://www.westpac.co.nz/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	public void HoverOverMenuItem(final String menuItem){
		List<WebElement> menuItems = driver.findElements(By.cssSelector(menuItem));
		for (WebElement item:menuItems)
		{
			if(item.getText().trim().equals(menuItem))
				hoverOverElement(item);
		}
	}
	public void hoverOverElement(WebElement e)
	{
		Actions builder = new Actions(driver);
		builder.moveToElement(e).build().perform();
	}
}
