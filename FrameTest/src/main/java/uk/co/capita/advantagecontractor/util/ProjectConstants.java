package uk.co.capita.advantagecontractor.util;
import org.openqa.selenium.By;
public class ProjectConstants {
	//Grid Run FLag
	
		public static final boolean GRID_RUN_MODE = false;
		
		
		
	//TestData XLS column names
		public static final Object TESTDATA_RUNMODECOL_NAME = "Runmode";
		public static final String TESTDATA_SHEET_NAME = "Test Data";
		public static final String TESTCASES_SHEET_NAME = "Test Cases";


	//Test Environment details	
		public static final String TEST_URL = "https://ec2-54-171-188-122.eu-west-1.compute.amazonaws.com/auth/AdvantageLogin.html";
		
		

	//Wait time for webdriver
		public static final long PAGE_LOAD_TIMEOUT = 60;//in seconds
		public static final long WEBDRIVER_WAIT_TIME = 30;//in seconds
		
		
	//paths
		public static final String REPORTS_PATH = "C:\\Ragha\\Advantage Housing\\Workspace\\AdvantageContractor\\Extent Reports\\";


	// xpaths
		//Common across all pages
		public static final String PAGELOADSTATUS_IMAGE = "//*[@id='load']";
		public static final String SEARCHDISPLAY_DROPDOWN = "//button[@ng-model = 'displaydropdown']";
		public static final String SEARCH_TXTBOX = "//input[@id='usearchString']";
		//public static final String SEARCH_JOBS_OPTION = "//a[text() = 'Jobs']";

		
		
		
		
		//Login Page
		public static final String LOGIN_BUTTON = "//button[@id='login']";
		public static final String LOGOUT_LINK = "//input[@name = 'logout']";
		public static final String USERNAME_TXTBOX = "//input[@id='j_username']";
		public static final String PWD_TXTBOX = "//input[@id='j_password']";
		//Landing Page
		public static final String MENU_LINK = "//div[text() = 'Menu']";
		public static final String LOG_JOB_LINK = "//a[text() = 'Log job']";
		public static final String MANAGE_JOB_LINK = "//a[text() = 'Manage Job']";

		//Location Search Page
		public static final String LOCATION_SEARCH_TXTBOX = "//input[@name = 'searchString']";
		public static final String SEARCH_BUTTON = "//button[text() = 'Search']";
		//LOcation Search Results Page
		public static final String SEARCH_RESULTS_TXT = "//h2[text() = 'Search results']";
		public static final String SRP_CONTINUE_BTN = "//button[@ng-click = 'vm.continueToAssessment()']";
		public static final String JOBASSES_LABEL = "//span[text() = 'Job assessment']";
		//Job Assessment Page
		public static final String JOBTYPE_DROPDOWN = "//form/div[1]/span/span/span[1]";
		public static final String STDJOB_DROPDOWN = "//form/div[2]/span/span/span[1]";
		public static final String SUBLOC_DROPDOWN = "//form/div[3]/span/span/span[1]";
		public static final String SELECTFILE_BTN = "//input[@id='attachment']";
		public static final String JAP_CONTINUE_BTN = "//button[text() = 'Continue']";
		public static final String JOBDETAIL_LABEL = "//span[text() = 'Job detail']";
		//Job Detail Page
		public static final String CONTRACT_DROPDOWN = "//form/div[1]/div/span/span/span[1]";
		public static final String PRIORITY_DROPDOWN = "//div[3]/div[1]/div/div/span/span/span[1]";
		public static final String MAINTRADE_DROPDOWN = "//div[2]/div/div/span/span/span[1]";
		public static final String CONTRACTOR_DROPDOWN = "//div[4]/div/span/span/span[1]";
		public static final String COSTCODE_TXTBOX = "//input[@name = 'costcode']";
		public static final String VAT_DROPDOWN = "//div[5]/div[1]/div/div/span/span/span[1]";
		public static final String DIAGSECTION_CONTINUE_BTN = "//form/div[6]/button";
		public static final String FINSECTION_CONTINUE_BTN = "//form/div[7]/button";
		public static final String REPAIRSOFFICER_DROPDOWN = "//div[6]/div/div/div/span/span/span[1]";
		public static final String LOGJOB_BTN = "//button[text() = 'Log job']";
		public static final String JOBLOGGED_MSG = "//li[text() = 'Job logged successfuly.']";
		//search results page
		public static final String SEARCHRESULTS_HEADER = "//span[@id='frheader']";
		public static final String SEARCHRESULT_ELEMENT = "//div[@id='further-results']/div[2]/div[1]/div/div[";
		
		//Manage Job page
		public static final String MANAGEJOB_LABEL = "//span[text() = 'Manage job']";
		public static final String TARGET_LINK = "//*[name() = 'g'][15]/*[name() = 'g']/*[name() = 'text'][1]";

		





		




				
		
		




		



		
		
		
		
		
		
		
		
		
		
		
}
