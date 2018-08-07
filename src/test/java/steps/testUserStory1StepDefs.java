package steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;


public class testUserStory1StepDefs {

    WebDriver driver;

	String items = "nav[id=ubermenu-ps] li[class*=sw-ubermenu-section] a[class*=sw-ubermenu-section-link]";
	String retirementProjectionbtn = "div[class=inner-cell] button[class*=btn-results-reveal] span[class=label]";

	@After
	public void afterScenario(){
		driver.quit();
	}

	@Given("^I am on KiwiSaver Retirement Calculator$")
	public void i_am_on_KiwiSaver_Retirement_Calculator() throws Throwable {
		navigateToRetirementCalc();
		switchToFrame();
	}

	private void switchToFrame() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.switchTo().frame(0); //TODo: not understand
	}

	@When("^I click on the information icon besides \"([^\"]*)\" field$")
	public void selectInformationIcon(String calcOption) throws Throwable {
		clickInformationIconForCalculatorOption(calcOption);
	}

	@Then("^I should verify the information message for (.*) field as : \"([^\"]*)\"$")
	public void verifyInformationIconMessage(String option, String msg) throws Throwable {
		assertEquals(getFieldMessageForIcon(option),msg);
		}

	private void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\root\\Desktop\\Westpac\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 	driver.get("https://www.westpac.co.nz/");
		driver.manage().window().maximize();
	}
	private void navigateToRetirementCalc() {
		setUp();
		hoverOverMenuItem("KiwiSaver");
		clickKiwiSaverCalculatorsBtn();
		SelectSideNavOption("KiwiSaver Retirement Calculator");
	}

	public void hoverOverMenuItem(String menuItem){
		List<WebElement> menuItems = driver.findElements(By.cssSelector(items));
		for (WebElement item:menuItems)
		{
			if(item.getText().trim().equals(menuItem))
				hoverOverElement(item);
		}
	}
	private void SelectSideNavOption(String option)  {
		waitForElementToBeVisible(driver.findElement(By.cssSelector("div[id=side-menu-ps]")));
		//TODO Change the locator

		List<WebElement> sideNavOptions = driver.findElements(By.cssSelector("ul[class*=sw-toggle-show] span[class*=last]"));
		for (WebElement sideNavOption:sideNavOptions)
		{
			 if(sideNavOption.getText().equals(option))
				sideNavOption.click();
				break;
		}
	}

	/*private void hoverOverMenuItem(final String menuItem){
		List<WebElement> menuItems = driver.findElements(By.cssSelector("nav[id=ubermenu-ps] li[class*=sw-ubermenu-section] a[class*=sw-ubermenu-section-link]"));
		for (WebElement item:menuItems)
		{
			if(item.getText().trim().equals(menuItem))
				hoverOverElement(item);
		}
	}*/

	private void hoverOverElement(WebElement e)
	{
		Actions builder = new Actions(driver);
		builder.moveToElement(e).build().perform();
	}

	private void clickKiwiSaverCalculatorsBtn()
	{
		WebElement KiwiSaverCalculatorBtn = driver.findElement(By.cssSelector("a[id=ubermenu-item-cta-kiwisaver-calculators-ps]"));
		waitForElementToBeVisible(KiwiSaverCalculatorBtn);
		KiwiSaverCalculatorBtn.click();
	}

	private WebElement waitForElementToBeVisible(WebElement e)
	{
		WebElement element = new WebDriverWait(driver,10 )
				.until(ExpectedConditions.visibilityOf(e));
		return element;
	}

	private WebElement waitForElementToBeClickAble(WebElement e) {
		WebElement element = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.elementToBeClickable(e));
		return element;
	}

	private void clickInformationIconForCalculatorOption(String calcOption) {

		 WebElement options = driver.findElement(By.cssSelector("div[help-id="+calcOption+"] button"));

		 hoverOverElement(options);
		 options.click();
	}

	private String getFieldMessageForIcon(String option)
	{
		WebElement e = driver.findElement(By.cssSelector("div[help-id="+option+"] p"));
		return e.getText();
	}

	private void inputValue(String label, String value) {
		if (value.length() != 0) {
			WebElement options = driver.findElement(By.cssSelector("div[help-id=" + label + "] input"));
			hoverOverElement(options);
			options.sendKeys(value);
		}
	}

	@When("^I input information in the Retirement Calculator for \"([^\"]*)\" as : \"([^\"]*)\"$")
	public void iInputInformationInTheRetirementCalculatorForAs(String label, String value) throws Throwable {
            inputValue(label,value);
	}

	@And("^I select \"([^\"]*)\" as \"([^\"]*)\" from the dropdown$")
	public void selectDropdownField(String field,String status) throws Throwable {
		WebElement e = driver.findElement(By.cssSelector("div[help-id="+field+"] div[class=control-well]"));
		e.click();
		List<WebElement> dropDownItems = driver.findElements(By.cssSelector("div[help-id="+field+"] div[class=dropdown] span[class*=ng-scope]"));
		for (WebElement val:dropDownItems)
		{
			if(val.getText().trim().equals(status))
				val.click();
		}
	}

	@And("^I select \"([^\"]*)\" as \"([^\"]*)\" from the frequency dropdown$")
	public void iSelectAsFromTheFrequencyDropdown(String field, String status) throws Throwable {
		if(status.length() != 0) {
			List<WebElement> dropDowns = driver.findElements(By.cssSelector("div[help-id=" + field + "] div[class=control-well]"));

			for (WebElement val : dropDowns) {
				if (val.getText().trim().equals("Frequency"))
					val.click();
			}

			List<WebElement> dropDownItems = driver.findElements(By.cssSelector("div[help-id=" + field + "] div[class=dropdown] span[class*=ng-scope]"));
			for (WebElement val : dropDownItems) {
				if (val.getText().trim().equals(status))
				val.click();
			}
		}
	}

	@And("^I select the \"([^\"]*)\" as \"([^\"]*)\"$")
	public void iSelectTheAs(String field, String status) throws Throwable {
		List<WebElement> e = driver.findElements(By.cssSelector("div[help-id="+field+"] div[class=control-well] span[class=input-label]"));
    	for (WebElement val:e)
		{
			if(val.getText().trim().equals(status))
				val.click();
		}
	}


	@When("^I click button View your KiwiSaver retirement projections >$")
	public void iClickButtonViewYourKiwiSaverRetirementProjections() throws Throwable {
		WebElement button = driver.findElement(By.cssSelector(retirementProjectionbtn));
		button.click();
	}

	@Then("^I should see the projected balance at retirement is : \"([^\"]*)\"$")
	public void iShouldSeeTheProjectedBalanceAtRetirementIs(String prjBalance) throws Throwable {
		WebElement e = driver.findElement(By.cssSelector("div[class=results-header] span[class*=result-value]"));
		assertEquals(e.getText().replaceAll("\\s+",""),prjBalance);
	}

	@And("^I select KiwiSaverMember Contribution as : \"([^\"]*)\"$")
	public void iSelectKiwiSaverMemberContributionAs(String contribution) throws Throwable {
		if(contribution.length()!=0) {
			List<WebElement> radioButtonOptions = driver.findElements(By.cssSelector("div[help-id=KiwiSaverMemberContribution] div[class=control-well] label span span"));
			for (WebElement val : radioButtonOptions) {
				if (val.getText().trim().equals(contribution))
					val.click();
			}
		}
	}

	@Given("^I am on westpac main page$")
	public void iAmOnWestpacMainPage() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\root\\Desktop\\Westpac\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.westpac.co.nz/");
		driver.manage().window().maximize();
		driver.getCurrentUrl().equals("https://www.westpac.co.nz/");
	}

	@And("^I hover over the \"([^\"]*)\" option in the menu$")
	public void hoverOverManinMenu(String menuItem) throws Throwable {
		//hoverOverMenuItem(menuItem);
		hoverOverMenuItem(menuItem);
	}

	@And("^I click the Currency converter button$")
	public void clickCurrencyConverterButton() throws Throwable {
		WebElement btn = driver.findElement(By.cssSelector("a[id=ubermenu-item-cta-currency-converter-ps]"));
		waitForElementToBeClickAble(btn);
		btn.click();
	}

	@Then("^I am on the Current converter page$")
	public void iAmOnTheCurrentConverterPage() throws Throwable {
		driver.getCurrentUrl().equals("https://www.westpac.co.nz/fx1-travel-migrant/foreign-exchange-and-international/currency-converter/");
		driver.switchTo().frame("westpac-iframe");
	}

	@When("^I enter value : \"([^\"]*)\" in the enter amount box$")
	public void iEnterValueInTheEnterAmountBox(String amount) throws Throwable {
		driver.findElement(By.cssSelector("input[id=Amount]")).sendKeys(amount);
	}

	@And("^I click the convert button on the currency converter page$")
	public void iClickTheConvertButtonOnTheCurrencyConvertorPage() throws Throwable {
		driver.findElement(By.cssSelector("input[id=convert]")).click();
	}

	@Then("^I verify the error message \"([^\"]*)\" on the currency converter page$")
	public void iVerifyTheErrorMessageOnTheCurrencyConvertorPage(String msg) throws Throwable {
		Assert.assertEquals(GetErrorMsg(),msg);
	}

	private String GetErrorMsg() {
		return driver.findElement(By.cssSelector("div[id=errordiv] li")).getText();
	}

	@When("^I select \"([^\"]*)\" dropdown option as : \"([^\"]*)\"$")
	public void iSelectDropdownOptionAs(String convert, String currency) throws Throwable {
		driver.findElement(By.cssSelector("select[id="+convert+"]")).click();
		List<WebElement> currencies = driver.findElements(By.cssSelector("select[id="+convert+"] option"));
		for (WebElement c: currencies
		     ) {
			if(c.getText().equals(currency))
			{
				c.click();
				break;
			}
		}
	}

	@Then("^the conversion is successful$")
	public void verifyConversionResult() throws Throwable {
		Assert.assertTrue(isConversionSuccess());
	}

	private boolean isConversionSuccess() {
		return  driver.findElements(By.cssSelector("div[id=resultsdiv]")).size() > 0;
	}
}
