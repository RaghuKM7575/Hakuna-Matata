package uk.co.capita.advantagecontractor.pages.sessionpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import uk.co.capita.advantagecontractor.basepage.BasePage;
import uk.co.capita.advantagecontractor.util.ProjectConstants;

public class LocationSearchResultsPage extends BasePage{
	
	@FindBy(xpath = ProjectConstants.SRP_CONTINUE_BTN)
	public WebElement srp_continue_btn;
	
	String locationToBeSelected_xpath;

	
	public LocationSearchResultsPage(WebDriver driver, WebDriverWait wait, ExtentTest test) {
			super(driver, wait, test);
	}
	
	public Object selectALocationAndContinue(String locationToBeSelected){
		
		test.log(LogStatus.INFO, "In selectALocationAndContinue method");
		locationToBeSelected_xpath = "//span[text() = '" + locationToBeSelected + "']";
		//System.out.println("XPATH IS " + locationToBeSelected_xpath);
		try{
		WebElement location_ToBeSelected = driver.findElement(By.xpath(locationToBeSelected_xpath));
		location_ToBeSelected.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ProjectConstants.PAGELOADSTATUS_IMAGE)));
		srp_continue_btn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ProjectConstants.PAGELOADSTATUS_IMAGE)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProjectConstants.JOBASSES_LABEL)));
		
		} catch(Throwable t){
			test.log(LogStatus.INFO, "Unable to select a location and click on Continue button");
			LocationSearchResultsPage locationSearchResultsPage = new LocationSearchResultsPage(driver, wait, test);
			PageFactory.initElements(driver, locationSearchResultsPage);
			return locationSearchResultsPage;

		}
		test.log(LogStatus.INFO, "Able to select a location and click on Continue button");
		JobAssessmentPage jobAssessmentPage = new JobAssessmentPage(driver, wait, test);
		PageFactory.initElements(driver, jobAssessmentPage);
		return jobAssessmentPage;
	
	}
	
	
	
	
	
	

}
