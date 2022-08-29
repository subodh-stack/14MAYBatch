package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pojo.LaunchBrowser;
import pom.ZerodhaHomePage;
import pom.ZerodhaLoginPage;
import utility.Parametrization;

public class HomePageTest {
	WebDriver driver ;
	
	@BeforeMethod
	public void LaunchBrowser() throws EncryptedDocumentException, IOException {
	driver =LaunchBrowser.chromeBrowser();
	ZerodhaLoginPage zerodhaLoginPage = new ZerodhaLoginPage(driver);
	String user = Parametrization.getData("Credentials", 0, 1);
	String pass = Parametrization.getData("Credentials", 1, 1);
	String pin = Parametrization.getData("Credentials", 2, 1);
	zerodhaLoginPage.enterUserName(user);
	zerodhaLoginPage.enterPassWord(pass);
	zerodhaLoginPage.clickOnSubmit();
	zerodhaLoginPage.enterPin(pin,driver);
	zerodhaLoginPage.clickOnContinue();
	}
	
	@Test
	public void SearchStockTest() {
		ZerodhaHomePage zerodhaHomePage = new ZerodhaHomePage(driver);
		zerodhaHomePage.searchStock("Reliance",driver);
		int number =zerodhaHomePage.getSearchResultNumber();
		Assert.assertTrue(number > 0);
	}
	
	@Test
	public void SearchStockAndClickOnBuy() {
		ZerodhaHomePage zerodhaHomePage = new ZerodhaHomePage(driver);
		zerodhaHomePage.searchStock("Tata", driver);
		zerodhaHomePage.searchAndSelectDesiredStock(driver, "TATAPOWER");
		zerodhaHomePage.clickOnSearchResultBuy();
	}
	
	
}
