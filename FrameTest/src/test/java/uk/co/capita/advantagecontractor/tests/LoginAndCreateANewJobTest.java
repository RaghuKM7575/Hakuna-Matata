package uk.co.capita.advantagecontractor.tests;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import uk.co.capita.advantagecontractor.basetest.BaseTest;
import uk.co.capita.advantagecontractor.pages.LaunchPage;
import uk.co.capita.advantagecontractor.pages.LoginPage;
import uk.co.capita.advantagecontractor.pages.sessionpages.JobAssessmentPage;
import uk.co.capita.advantagecontractor.pages.sessionpages.JobDetailsPage;
import uk.co.capita.advantagecontractor.pages.sessionpages.LandingPage;
import uk.co.capita.advantagecontractor.pages.sessionpages.LocationSearchPage;
import uk.co.capita.advantagecontractor.pages.sessionpages.LocationSearchResultsPage;
import uk.co.capita.advantagecontractor.util.DataUtil;
import uk.co.capita.advantagecontractor.util.ProjectConstants;

public class LoginAndCreateANewJobTest extends BaseTest{

	public String testCaseName = this.getClass().getSimpleName(); //or we can use "LoginTest";

	@Test(dataProvider = "getTestData")
	public void loginAndCreateANewJob(Hashtable<String, String> table) throws InterruptedException{

		test = reports.startTest(testCaseName);

		//verify TC run mode and DataSet run mode
		if(!DataUtil.isTestExecutable(xls, testCaseName) || table.get(ProjectConstants.TESTDATA_RUNMODECOL_NAME).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test case as Runmode is N");
			throw new SkipException("Skipping the test case as Runmode is N");
		}

		test.log(LogStatus.INFO, "Starting " + testCaseName);
		initialize(table.get("Browser"));

		//Go to Login Page
		LaunchPage launchPage = new LaunchPage(driver, wait, test);
		PageFactory.initElements(driver, launchPage);
		Object page = launchPage.goToLoginPage(table.get("Browser"));

		if (page instanceof LaunchPage){
			reportFailure("Unable to go to Login page of the AUT");
		}
		test.log(LogStatus.INFO, "Able to go to Login page of the AUT");
		LoginPage loginPage = (LoginPage)page;

		//Do login
		page = loginPage.doLogin(table.get("Username"), table.get("Password"));
		if(!(page instanceof LandingPage)){
			reportFailure("Unable to login");
		}
		LandingPage landingPage = (LandingPage) page;

		//Go to location search page
		page = landingPage.getMenu().goToLocationSearch();;
		if(!(page instanceof LocationSearchPage)){
			reportFailure("Unable to go to Location Search Page");
		}
		LocationSearchPage locationSearchPage = (LocationSearchPage) page;

		//do location search
		page = locationSearchPage.doLocationSearch(table.get("Location"));
		if(!(page instanceof LocationSearchResultsPage)){
			reportFailure("Unable to search the specified location");
		}
		LocationSearchResultsPage locationSearchResultsPage = (LocationSearchResultsPage) page;

		//select a location and continue
		page = locationSearchResultsPage.selectALocationAndContinue(table.get("LocationToBeSelected"));
		if(!(page instanceof JobAssessmentPage)){
			reportFailure("Unable to select location and continue");
		}
		JobAssessmentPage jobAssessmentPage = (JobAssessmentPage)page;

		//Enter job assessment details and continue
		page = jobAssessmentPage.enterJobAssesmentDetailsAndContinue(table.get("JobType"), table.get("StdJob"), table.get("SubLocation"), table.get("FilePath"));	
		if(!(page instanceof JobDetailsPage)){
			reportFailure("Unable to enter job assessment details and continue");
		}
		JobDetailsPage jobDetailsPage = (JobDetailsPage)page;


		//Enter Diagnose details and continue
		page = jobDetailsPage.enterDiagnoseDetailsAndContinue(table.get("Contract"), table.get("Priority"), table.get("MainTrade"), table.get("Contractor"));
		if(!(page instanceof JobDetailsPage)){
			reportFailure("Unable to enter diagnose details and continue");
		}
		jobDetailsPage = (JobDetailsPage)page;

		//Enter Finance Details and log the job
		jobDetailsPage.enterFinanceDetailsAndContinue(table.get("CostCode"), table.get("CostCodeOption"), table.get("ChargeType"), table.get("VAT"));
		if(!(page instanceof JobDetailsPage)){
			reportFailure("Unable to enter finance details and continue");
		}
		jobDetailsPage = (JobDetailsPage)page;

		//Enter Ticket Details and continue
		jobDetailsPage.enterTicketDetails(table.get("Acknowledgement"), table.get("JobTicket"), table.get("Multitrade"), table.get("RepairsOfficer"));
		if(!(page instanceof JobDetailsPage)){
			reportFailure("Unable to enter ticket details and log the job");
		}		
		jobDetailsPage = (JobDetailsPage)page;

		//Log job
		boolean result = jobDetailsPage.logJob();
		if(!result){
			test.log(LogStatus.FAIL, "Unable to log job, failing the test case");
			reportFailure("");
		}

		test.log(LogStatus.PASS, "Able to log job successfully");

	}




	@DataProvider(name = "getTestData")
	public Object[][] getTestData(){
		Object[][] data = DataUtil.getData(xls, testCaseName);
		return data;
	}
}
