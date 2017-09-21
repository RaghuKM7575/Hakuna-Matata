package uk.co.capita.advantagecontractor.pages.sessionpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class JobDetailsPage extends BasePage{

	@FindBy(xpath = ProjectConstants.CONTRACT_DROPDOWN)
	public WebElement contract_dropdown;
	
	@FindBy(xpath = ProjectConstants.PRIORITY_DROPDOWN)
	public WebElement priority_dropdown;
	
	@FindBy(xpath = ProjectConstants.MAINTRADE_DROPDOWN)
	public WebElement maintrade_dropdown;
	
	@FindBy(xpath = ProjectConstants.CONTRACTOR_DROPDOWN)
	public WebElement contractor_dropdown;
	
	@FindBy(xpath = ProjectConstants.DIAGSECTION_CONTINUE_BTN)
	public WebElement diagsection_continue_btn;
	
	@FindBy(xpath = ProjectConstants.COSTCODE_TXTBOX)
	public WebElement costcode_txtbox;
	
	@FindBy(xpath = ProjectConstants.VAT_DROPDOWN)
	public WebElement vat_dropdown;
	
	@FindBy(xpath = ProjectConstants.FINSECTION_CONTINUE_BTN)
	public WebElement finsection_continue_btn;
	
	@FindBy(xpath = ProjectConstants.REPAIRSOFFICER_DROPDOWN)
	public WebElement repairsOfficer_dropdown;
	
	@FindBy(xpath = ProjectConstants.LOGJOB_BTN)
	public WebElement logjob_btn;
	
	//constructor
	public JobDetailsPage(WebDriver driver, WebDriverWait wait, ExtentTest test) {
		super(driver, wait, test);	
		}
	
	public Object enterDiagnoseDetailsAndContinue(String contract, String priority, String maintrade, String contractor) throws InterruptedException{
		
		test.log(LogStatus.INFO, "In enterDiagnoseDetailsAndContinue method");
		try{
			//select contract
			xpath = "//span[text() = '" + contract + "']";
			contract_dropdown.click();
			Thread.sleep(1000);
			WebElement _contract = driver.findElement(By.xpath(xpath));
//			executor.executeScript("arguments[0].scrollIntoView(true);",_contract);
			executor.executeScript("arguments[0].click();", _contract);
			//_contract.click();
			
			//select priority
			xpath = "//span[text() = '" + priority + "']";
			executor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight",priority_dropdown);
			priority_dropdown.click();
			Thread.sleep(1000);
			WebElement _priority = driver.findElement(By.xpath(xpath));
//			executor.executeScript("arguments[0].scrollIntoView(true);",_priority);
			executor.executeScript("arguments[0].click();", _priority);
			//_priority.click();
			
			//select main trade
			xpath = "//span[text() = '" + maintrade + "']";
			executor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight",maintrade_dropdown);
			maintrade_dropdown.click();
			Thread.sleep(1000);
			WebElement _maintrade = driver.findElement(By.xpath(xpath));
//			executor.executeScript("arguments[0].scrollIntoView(true);",_maintrade);
			executor.executeScript("arguments[0].click();", _maintrade);
			//_maintrade.click();			
			
			
			//select contractor
			xpath = "//span[text() = '" + contractor + "']";
			executor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight",contractor_dropdown);
			contractor_dropdown.click();
			Thread.sleep(1000);
			WebElement _contractor = driver.findElement(By.xpath(xpath));
//			executor.executeScript("arguments[0].scrollIntoView(true);",_contractor);
			executor.executeScript("arguments[0].click();", _contractor);
//			_contractor.click();	
			
			//click on continue button
			diagsection_continue_btn.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ProjectConstants.PAGELOADSTATUS_IMAGE)));
			
			} catch(Throwable t){//if selection of any of the above dropdown fails, then fail the test case
				test.log(LogStatus.INFO, "Unable enter Diagnose Details and continue, failing the test case");
				return false;
				
			}

		test.log(LogStatus.INFO, "Able enter Diagnose Details and continue");
		JobDetailsPage jobDetailsPage = new JobDetailsPage(driver, wait, test);
		PageFactory.initElements(driver, jobDetailsPage);
		return jobDetailsPage;		
		
	}
	
	public Object enterFinanceDetailsAndContinue(String costCode, String costCodeOption, String chargeType, String vat){
		test.log(LogStatus.INFO, "In enterFinanceDetailsAndContinue method");
		try{
		//enter cost code and select cost code option
		costcode_txtbox.sendKeys(costCode);
		
		xpath = "//label[text() = '" + costCodeOption + "']";
		WebElement _costCodeOption = driver.findElement(By.xpath(xpath));
		_costCodeOption.click();
		
		//select charge type
		xpath = "//label[text() = '" + chargeType + "']";
		WebElement _chargeType = driver.findElement(By.xpath(xpath));
		_chargeType.click();
		
		//select VAT
		xpath = "//span[text() = '" + vat + "']";
		vat_dropdown.click();
		WebElement _vat = driver.findElement(By.xpath(xpath));
		executor.executeScript("arguments[0].scrollIntoView(true);",_vat);
		_vat.click();
		
		//click on continue button
		finsection_continue_btn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ProjectConstants.PAGELOADSTATUS_IMAGE)));
		} catch(Throwable t){//if selection of any of the above dropdown fails, then fail the test case
			test.log(LogStatus.INFO, "Unable enter Finance Details and continue, failing the test case");
			return false;
			
		}
		
		test.log(LogStatus.INFO, "Able enter Finance Details and continue");
		JobDetailsPage jobDetailsPage = new JobDetailsPage(driver, wait, test);
		PageFactory.initElements(driver, jobDetailsPage);
		return jobDetailsPage;
	}
	
	public Object enterTicketDetails(String ackowledgement, String jobTicket, String multitrade, String repairsOfficer){
		test.log(LogStatus.INFO, "In enterTicketDetails method");
		try{
		//select Acknowledgment
		xpath = "//label[text() = '" + ackowledgement + "']";
		WebElement _ackowledgement = driver.findElement(By.xpath(xpath));
		_ackowledgement.click();
	
		//select job ticket
		xpath = "//label[text() = '" + jobTicket + "']";
		WebElement _jobTicket = driver.findElement(By.xpath(xpath));
		_jobTicket.click();	
	
		//select multitrade YES OR NO option
		if(multitrade.equalsIgnoreCase("YES")){
		xpath = "//span[text() = 'NO']";
		WebElement _multitrade = driver.findElement(By.xpath(xpath));
		_multitrade.click();
		}
	
		//select repairs officer
		xpath = "//span[text() = '" + repairsOfficer + "']";
		repairsOfficer_dropdown.click();
		WebElement _repairsOfficer = driver.findElement(By.xpath(xpath));
		executor.executeScript("arguments[0].scrollIntoView(true);",_repairsOfficer);
		_repairsOfficer.click();
		
		} catch(Throwable t){
			test.log(LogStatus.INFO, "Unable enter Ticket Details and continue, failing the test case");
			return false;
		}
		
		test.log(LogStatus.INFO, "Able enter Finance Details and continue");
		JobDetailsPage jobDetailsPage = new JobDetailsPage(driver, wait, test);
		PageFactory.initElements(driver, jobDetailsPage);
		return jobDetailsPage;	
	}
	
	
	public boolean logJob(){
		test.log(LogStatus.INFO, "In logJob method");
		
		//click on log job button
		try{
		logjob_btn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ProjectConstants.PAGELOADSTATUS_IMAGE)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProjectConstants.JOBLOGGED_MSG)));
		} catch(Throwable t){
			test.log(LogStatus.INFO, "Unable to log job, failing the test case");
			return false;
		}
		test.log(LogStatus.INFO, "Able to log job successfully!");
		return true;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
