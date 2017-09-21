package uk.co.capita.advantagecontractor.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import uk.co.capita.advantagecontractor.pages.sessionpages.LandingPage;
import uk.co.capita.advantagecontractor.pages.sessionpages.LocationSearchPage;
import uk.co.capita.advantagecontractor.pages.sessionpages.ManageJobPage;
import uk.co.capita.advantagecontractor.util.ProjectConstants;

public class Menu {

	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest test;
	
	@FindBy(xpath = ProjectConstants.MENU_LINK)
	public WebElement menu_link;
	
	@FindBy(xpath = ProjectConstants.LOG_JOB_LINK)
	public WebElement log_job_link;
	
	@FindBy(xpath = ProjectConstants.MANAGE_JOB_LINK)
	public WebElement manage_job_link;
	
	
	
	
	
	
	//constructor
	public Menu(WebDriver driver, WebDriverWait wait, ExtentTest test) {
			this.driver = driver;
			this.wait = wait;
			this.test = test;
	}

		public Object goToLocationSearch() throws InterruptedException {
			test.log(LogStatus.INFO, "In goToLocationSearch method");
			try{
			Thread.sleep(5000);
//			wait.until(ExpectedConditions.elementToBeClickable(menu_link));//this is not working
			menu_link.click();
			log_job_link.click();
			//validate if location search page appears	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProjectConstants.LOCATION_SEARCH_TXTBOX)));
			} catch(Throwable t){
				test.log(LogStatus.INFO, "Unable to go to Location Search Page");
				LandingPage landingPage = new LandingPage(driver, wait, test);
				PageFactory.initElements(driver, landingPage);
				return landingPage;
			}

			test.log(LogStatus.INFO, "Able to go to Location Search Page");
			LocationSearchPage locationSearchPage = new LocationSearchPage(driver, wait, test);
			PageFactory.initElements(driver, locationSearchPage);
			return locationSearchPage;

}

		public Object goToManageJobPage() throws InterruptedException {
			test.log(LogStatus.INFO, "In goToManageJobPage method");
			try{
			Thread.sleep(10000);
			menu_link.click();
			manage_job_link.click();
			//validate if manage page appears	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProjectConstants.MANAGEJOB_LABEL)));
			} catch(Throwable t){
				test.log(LogStatus.INFO, "Unable to go to Manage Job Page");
				LandingPage landingPage = new LandingPage(driver, wait, test);
				PageFactory.initElements(driver, landingPage);
				return landingPage;
			}
			
			test.log(LogStatus.INFO, "Able to go to Manage Job Page");
			ManageJobPage manageJobPage = new ManageJobPage(driver, wait, test);
			PageFactory.initElements(driver, manageJobPage);
			return manageJobPage;
		}


}
