package uk.co.capita.advantagecontractor.basetest;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import uk.co.capita.advantagecontractor.mail.SendMail;
import uk.co.capita.advantagecontractor.util.ExtentManager;
import uk.co.capita.advantagecontractor.util.ProjectConstants;
import uk.co.capita.advantagecontractor.util.Xls_Reader;

public class BaseTest {


	//global variables

	public WebDriver driver;
	public WebDriverWait wait;
	public Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir") + "\\Xls_TestData\\Test Data.xlsx");
	public ExtentReports reports = ExtentManager.getInstance();
	public ExtentTest test;
	public SoftAssert softAssert = new SoftAssert();


	//verify if AUT is up and running
	@BeforeSuite
	public void isEnvUp() throws Exception{
		test = reports.startTest("TEST_ENV STATUS");
		if(!isUrlActive(ProjectConstants.TEST_URL)){
			test.log(LogStatus.FAIL, "AUT with URL " + ProjectConstants.TEST_URL + " is  DOWN");
			if(reports != null){//we can not put this in @AfterSuite because at this point suite execution is not yet started
				reports.endTest(test);
				reports.flush();
			}
			//SendMail.sendMail();

			//No need to call reportFailure() method as there is no screenshot to be taken at this point of time. Assert.fail() is sufficient
			Assert.fail("AUT with URL -- " + ProjectConstants.TEST_URL + " -- is  DOWN!");

		}
	}

	//initialize() method initializes required browser
	public void initialize(String browserType){

		test.log(LogStatus.INFO, "Is Grid Run Mode Enabled - " + ProjectConstants.GRID_RUN_MODE);
		DesiredCapabilities capabilities = null;

		//if GRID RUN MODE is FALSE, then run the scripts locally
		if(!ProjectConstants.GRID_RUN_MODE){
			test.log(LogStatus.INFO, "Grid Run Mode NOT Enabled - Hence running the test scripts locally");
			switch(browserType) {

			case "Mozilla" : {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\DriverServers\\GeckoDriver\\geckodriver.exe");
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability("ignoreZoomSetting", true);
				capabilities.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, 1);
				capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);	
				try{
					driver = new FirefoxDriver(capabilities);
				} catch(Throwable t){
					test.log(LogStatus.FAIL, t);
					reportFailure("Unable to launch Firefox browser, see error above");
				}
				break;				
			}
			case "IE" : {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\DriverServers\\IEDriver\\IEDriverServer.exe");
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability("ignoreZoomSetting", true);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);	
				capabilities.setJavascriptEnabled(true);
				capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
				try{
					driver = new InternetExplorerDriver(capabilities);
				} catch(Throwable t){
					test.log(LogStatus.FAIL, t);
					reportFailure("Unable to launch IE browser, see error above");
				}				
				break;
			}
			case "Chrome" : {
				System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir") + "\\DriverServers\\ChromeDriver\\chromedriver.exe"));
				try{
					driver = new ChromeDriver();
				} catch(Throwable t){
					test.log(LogStatus.FAIL, t);
					reportFailure("Unable to launch Chrome browser, see error above");
				}				
				break;
			}
			case "Safari" : {
				try{
					driver = new SafariDriver();
				} catch(Throwable t){
					test.log(LogStatus.FAIL, t);
					reportFailure("Unable to launch Safari browser, see error above");
				}				
				break;
			}
			case "Opera" : {
				System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "\\DriverServers\\OperaDriver\\operadriver.exe");
				try{
					driver = new OperaDriver();
				} catch(Throwable t){
					test.log(LogStatus.FAIL, t);
					reportFailure("Unable to launch Opera browser, see error above");
				}				
				break;
			}
			}//switch()

		} else {//if GRID RUN MODE IS YES
			test.log(LogStatus.INFO, "Grid Run Mode Enabled - Hence running the test scripts on GRID");

			switch(browserType) {
			case"Mozilla": {
				capabilities = DesiredCapabilities.firefox();
				capabilities.setBrowserName("firefox");
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability("ignoreZoomSetting", true);
				capabilities.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, 1);
				capabilities.setJavascriptEnabled(true);
				capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				break;
			}

			case"Chrome": {

				capabilities = DesiredCapabilities.chrome();
				capabilities.setBrowserName("chrome");
				capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				break;
			}
			}
			try {

				driver = new RemoteWebDriver(new URL("http://localhost:9090/wd/hub"), capabilities);
			} catch (Exception e) {

				e.printStackTrace();
				test.log(LogStatus.FAIL, "Unable to run tests on GRID");
				test.log(LogStatus.FAIL, e);
				reportFailure("Unable to open browsers on GRID, see error above");

			}
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Opened browser successfully - "+ browserType);
	}//initialize()


	//Following method verifies if any URL is up and running
	//Since this function is not specific to any page or any web page's element
	//we should have it here
	//For web element URLs, we have a separate function "isElementUrlActive" in BasePage.java
	public boolean isUrlActive(String testURL) {
		test.log(LogStatus.INFO, "Verifying if URL is up and running");
		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					public void checkClientTrusted(
							java.security.cert.X509Certificate[] certs, String authType) {
					}
					public void checkServerTrusted(
							java.security.cert.X509Certificate[] certs, String authType) {
					}
				}};

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Throwable t) {
			t.printStackTrace();
		}

		try{
			URL url = new URL(testURL);
			HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
			connection.connect();
			test.log(LogStatus.INFO, "Response Message is " + connection.getResponseMessage());
			if(!connection.getResponseMessage().equalsIgnoreCase("OK")){
				test.log(LogStatus.INFO, "Response Message for the URL " + testURL + " is not OK");
				return false;
			}
			connection.disconnect();
			test.log(LogStatus.INFO, "Response Message for the URL " + testURL + " is  OK");
			return true;
		} catch(Throwable t){
			t.printStackTrace();
			test.log(LogStatus.INFO, t);
			return false;
		}
	}


	//method to report failure
	public void reportFailure(String failureMessage){
		test.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);
	}


	//method to report soft failures
	public void reportSoftFailure(String failureMessage){
		test.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		softAssert.fail();
	}



	//takeScreenShot() method takes screenshot whenever invoked
	public void takeScreenShot(){
		Date date = new Date();
		String screenshotFile = date.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath= System.getProperty("user.dir") + "//screenshots//" + screenshotFile;
		//store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,test.addScreenCapture(filePath));
	}





	//tearDown() method flushes logs into extent report and closes browser
	//Note-This code Can also be used in each test with @AfterMethod annotation
	@AfterTest
	public void tearDown(){
		if(reports != null){
			reports.endTest(test);
			reports.flush();
		}

		//	if(driver != null)
		//		driver.quit();
	}

	//@AfterSuite
	public void sendMail() throws Exception{
		SendMail.sendMail();
	}








}
