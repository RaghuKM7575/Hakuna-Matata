package uk.co.capita.advantagecontractor.pages.sessionpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.io.support.ResourcePropertySource;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import uk.co.capita.advantagecontractor.basepage.BasePage;
import uk.co.capita.advantagecontractor.util.ProjectConstants;


public class JobAssessmentPage extends BasePage{

	@FindBy(xpath = ProjectConstants.JOBTYPE_DROPDOWN)
	public WebElement jobtype_dropdown;
	
	@FindBy(xpath = ProjectConstants.STDJOB_DROPDOWN)
	public WebElement stdjob_dropdown;
	
	@FindBy(xpath = ProjectConstants.SUBLOC_DROPDOWN)
	public WebElement subloc_dropdown;
	
	@FindBy(xpath = ProjectConstants.SELECTFILE_BTN)
	public WebElement selectFile_btn;
	
	@FindBy(xpath = ProjectConstants.JAP_CONTINUE_BTN)
	public WebElement jap_continue_btn;
	
	//constructor
	public JobAssessmentPage(WebDriver driver, WebDriverWait wait, ExtentTest test) {
		super(driver, wait, test);
	}

	public Object enterJobAssesmentDetailsAndContinue(String jobType, String stdJob, String subLoc, String filePath) throws InterruptedException{
		
		test.log(LogStatus.INFO, "In enterJobAssesmmentDetailsAndContinue method");
		try{
		
			//select job type
		xpath = "//span[text() = '" + jobType + "']";
		jobtype_dropdown.click();
		Thread.sleep(1000);
		WebElement job_Type = driver.findElement(By.xpath(xpath));
		executor.executeScript("arguments[0].scrollIntoView(true);",job_Type);
//		executor.executeScript("arguments[0].click();", job_Type);
		job_Type.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ProjectConstants.PAGELOADSTATUS_IMAGE)));
		} catch(Throwable t){//if selection of job type fails, then fail the test case
			test.log(LogStatus.INFO, "Unable to select a job type, failing the test case");
			JobAssessmentPage jobAssessmentPage = new JobAssessmentPage(driver, wait, test);
			PageFactory.initElements(driver, jobAssessmentPage);
			return jobAssessmentPage;
			
		}
		try{
		//select std job
		xpath = "//span[text() = '" + stdJob + "']";
		stdjob_dropdown.click();
		Thread.sleep(1000);
		executor = (JavascriptExecutor) driver;
		WebElement std_job = driver.findElement(By.xpath(xpath));
		executor.executeScript("arguments[0].scrollIntoView(true);",std_job);
		executor.executeScript("arguments[0].click();", std_job);
//		std_job.click();
		} catch(Throwable t){//if selection of job type fails, then fail the test case
			test.log(LogStatus.ERROR, "Unable to select std job");
			takeScreenShot();
		}
		try{
		//select sub location
		xpath = "//span[text() = '" + subLoc + "']";
		subloc_dropdown.click();
		Thread.sleep(1000);
		executor = (JavascriptExecutor) driver;
		WebElement sub_loc = driver.findElement(By.xpath(xpath));
		executor.executeScript("arguments[0].scrollIntoView(true);",sub_loc);
//		executor.executeScript("arguments[0].click();", sub_loc);
		sub_loc.click();
		} catch(Throwable t){
			test.log(LogStatus.ERROR, "Unable to select sub location");
			takeScreenShot();
		}
		try{
		//upload a file
		selectFile_btn.sendKeys(filePath);

		} catch(Throwable t){
			test.log(LogStatus.ERROR, "Unable to upload a file");
			takeScreenShot();
		}
		
		try{
		//click on continue btn
		jap_continue_btn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ProjectConstants.PAGELOADSTATUS_IMAGE)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProjectConstants.JOBDETAIL_LABEL)));
		} catch(Throwable t){
			test.log(LogStatus.INFO, "Unable to enter job assessment details and go to Job Details page");
			JobAssessmentPage jobAssessmentPage = new JobAssessmentPage(driver, wait, test);
			PageFactory.initElements(driver, jobAssessmentPage);
			return jobAssessmentPage;
		}
		
		test.log(LogStatus.INFO, "Able to enter job assessment details and go to Job Details page successfully");
		JobDetailsPage jobDetailsPage = new JobDetailsPage(driver, wait, test);
		PageFactory.initElements(driver, jobDetailsPage);
		return jobDetailsPage;
		
		
		
	
	}







}
