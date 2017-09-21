package uk.co.capita.advantagecontractor.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import uk.co.capita.advantagecontractor.basepage.BasePage;
import uk.co.capita.advantagecontractor.util.ProjectConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LaunchPage extends BasePage{

	public LaunchPage(WebDriver driver, WebDriverWait wait, ExtentTest test) {
		super(driver, wait, test);
	}

//The following method launches the AUT and takes the user to login page	
	public Object goToLoginPage(String browserType){
		test.log(LogStatus.INFO, "Going to Login Page...");

		driver.navigate().to(ProjectConstants.TEST_URL);
		//the following code clicks on "Continue to this website (not recommended)" text and will push us to the site.
		//This is specifically required for IE
		if(browserType.equalsIgnoreCase("IE")){
		driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		}
		
		//validate if Login page is displayed
		if(!isElementPresent(ProjectConstants.LOGIN_BUTTON)){
			test.log(LogStatus.INFO, "LoginPage did not appear");
			LaunchPage launchPage = new LaunchPage(driver, wait, test);
			PageFactory.initElements(driver, launchPage);
			return launchPage;
		}
		test.log(LogStatus.INFO, "Reached Login Page...");
		LoginPage loginPage = new LoginPage(driver, wait, test);
		PageFactory.initElements(driver, loginPage);
		return loginPage;
	}










}
