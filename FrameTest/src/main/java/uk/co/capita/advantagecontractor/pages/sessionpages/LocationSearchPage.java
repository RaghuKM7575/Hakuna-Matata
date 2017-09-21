package uk.co.capita.advantagecontractor.pages.sessionpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import uk.co.capita.advantagecontractor.basepage.BasePage;
import uk.co.capita.advantagecontractor.util.ProjectConstants;

public class LocationSearchPage extends BasePage{

	@FindBy(xpath = ProjectConstants.LOCATION_SEARCH_TXTBOX)
	public WebElement location_search_txtbox; 
	
	@FindBy(xpath = ProjectConstants.SEARCH_BUTTON)
	public WebElement search_button;
	
	public LocationSearchPage(WebDriver driver, WebDriverWait wait, ExtentTest test) {
		super(driver, wait, test);	}


	//do location search
	public Object doLocationSearch(String location) {
		test.log(LogStatus.INFO, "In doLocationSearch method");
		try{
		location_search_txtbox.sendKeys(location);
		search_button.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ProjectConstants.PAGELOADSTATUS_IMAGE)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProjectConstants.SEARCH_RESULTS_TXT)));
		} catch(Throwable t){
			test.log(LogStatus.INFO, "No location search results displayed");
			LocationSearchPage locationSearchPage = new LocationSearchPage(driver, wait, test);
			PageFactory.initElements(driver, locationSearchPage);
			return locationSearchPage;
		}
		
		test.log(LogStatus.INFO, "Location search results displayed for " + location);
		LocationSearchResultsPage locationSearchResultsPage = new LocationSearchResultsPage(driver, wait, test);
		PageFactory.initElements(driver, locationSearchResultsPage);
		return locationSearchResultsPage;
		
		
		
		
		
	}









}

