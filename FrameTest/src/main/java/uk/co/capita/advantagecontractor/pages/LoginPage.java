package uk.co.capita.advantagecontractor.pages;

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
import uk.co.capita.advantagecontractor.pages.sessionpages.LandingPage;
import uk.co.capita.advantagecontractor.util.ProjectConstants;

public class LoginPage extends BasePage{

	@FindBy(xpath = ProjectConstants.USERNAME_TXTBOX)
	public WebElement userName_field; 
	
	@FindBy(xpath = ProjectConstants.PWD_TXTBOX)
	public WebElement pasword_field; 
	
	@FindBy(xpath = ProjectConstants.LOGIN_BUTTON)
	public WebElement login_button; 
	
	
	public LoginPage(WebDriver driver, WebDriverWait wait, ExtentTest test) {
		super(driver, wait, test);
	}

	public Object doLogin(String userid, String pwd) {
		test.log(LogStatus.INFO, "In doLogin method");
		try{
		userName_field.sendKeys(userid);
		pasword_field.sendKeys(pwd);
		login_button.click();
		//validate if login is successful
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProjectConstants.LOGOUT_LINK)));
		
		
		} catch(Throwable t){
			test.log(LogStatus.INFO, "Unable to login");
			LoginPage loginPage = new LoginPage(driver, wait, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
		}
		test.log(LogStatus.INFO, "Logged in successfully");
		LandingPage landingPage = new LandingPage(driver, wait, test);
		PageFactory.initElements(driver, landingPage);
		return landingPage;
		
		
	}

}
