package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZerodhaHomePage {

	@FindBy (xpath = "//input[@type='text']") private WebElement search;
	@FindBy (xpath = "//span[@class='tradingsymbol']") private List<WebElement> searchResult;
	@FindBy (xpath = "//button[@data-balloon=\"Buy\"]") private WebElement searchBuy;


	public ZerodhaHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void searchStock(String stock,WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(1000));
		wait.until(ExpectedConditions.visibilityOf(search));
		search.sendKeys(stock);
	}
	
	public int getSearchResultNumber() {
		int listSize =searchResult.size();
		return listSize;
	}
	
	public void searchAndSelectDesiredStock(WebDriver driver,String RequiredStock) {
		for(int a = 0; a < searchResult.size();a++)
		{
			WebElement s =searchResult.get(a);
			String StockName =s.getText();
			if(StockName.equals(RequiredStock))
			{
				Actions actions = new Actions(driver);
				actions.moveToElement(s);
				actions.perform();
				break;
			}
		}
	}
	
	public void clickOnSearchResultBuy() {
		searchBuy.click();
	}
	
}