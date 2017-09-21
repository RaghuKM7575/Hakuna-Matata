package uk.co.capita.advantagecontractor.pages.sessionpages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import uk.co.capita.advantagecontractor.basepage.BasePage;
import uk.co.capita.advantagecontractor.util.ProjectConstants;

public class SearchResultsPage extends BasePage{

	@FindBy(xpath = ProjectConstants.SEARCHRESULT_ELEMENT)
	public WebElement searchResults_element;
	
	
	//constructor
	public SearchResultsPage(WebDriver driver, WebDriverWait wait, ExtentTest test) {
		super(driver, wait, test);	
	}
	
	
	public boolean validateSearchResults(String searchType, String searchText){
		
		test.log(LogStatus.INFO, "In validateSearchResults method");
		if(!verifyText(ProjectConstants.SEARCHRESULTS_HEADER, searchText)){
			test.log(LogStatus.ERROR, "Search Results header text mismatch.");
			takeScreenShot();
		}
		
		test.log(LogStatus.INFO, "Search Results header text match.");
		
		//count no. of search results
		List<WebElement> searchResult = getElementList(ProjectConstants.SEARCHRESULT_ELEMENT);
		test.log(LogStatus.INFO, "No of Search results for the search text - " + searchText + "= " + searchResult.size());
		
		//validate if search results contain the search test that was entered
		WebElement element;
		for(int i = 0; i < searchResult.size(); i++){
			ArrayList<String> result = new ArrayList<String>();
			for(int j = 1; j <= 4; j++){
			element = driver.findElement(By.xpath(ProjectConstants.SEARCHRESULT_ELEMENT + j + "]"));
			if(!element.getText().toLowerCase().contains(searchText.toLowerCase())){
				result.add("FALSE");
			} else{
				result.add("TRUE");
			}
		  }
			if(!result.contains("TRUE")){
				test.log(LogStatus.INFO, "One of the search results do not the search text - " + searchText);
				return false;
			}
			
		}
		return true;
		
		
		
		
	}










}
