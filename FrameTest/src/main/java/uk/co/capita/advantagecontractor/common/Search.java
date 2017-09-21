package uk.co.capita.advantagecontractor.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import uk.co.capita.advantagecontractor.pages.sessionpages.SearchResultsPage;
import uk.co.capita.advantagecontractor.util.ProjectConstants;

public class Search {

	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest test;

	@FindBy(xpath = ProjectConstants.SEARCH_TXTBOX)
	public WebElement search_txtbox;
	
	@FindBy(xpath = ProjectConstants.SEARCHDISPLAY_DROPDOWN)
	public WebElement searchDisplay_dropdown;
	

	
	//constructor
	public Search(WebDriver driver, WebDriverWait wait, ExtentTest test) {
			this.driver = driver;
			this.wait = wait;
			this.test = test;
	}

	//search function
	public Object doSearch(String searchType, String searchTxt){
		test.log(LogStatus.INFO, "In doSearch method");
//		try{
		//click on search dropdown
		searchDisplay_dropdown.click();
		
		//select search type
		String xpath = "//a[text() = '" + searchType + "']";
//		System.out.println(xpath);
		WebElement _searchType = driver.findElement(By.xpath(xpath));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", _searchType);
//		_searchType.click();
		search_txtbox.sendKeys(searchTxt);
		search_txtbox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'result-item']")));
		
		
		
//		}catch(Throwable t){
//		test.log(LogStatus.INFO, "Search is not success for the search term " + searchTxt);
//		return false;
//
//	}
		test.log(LogStatus.INFO, "Search is complete for the search term " + searchTxt);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver, wait, test);
		PageFactory.initElements(driver, searchResultsPage);
		return searchResultsPage;
		
		
		
		
		
	}























}
