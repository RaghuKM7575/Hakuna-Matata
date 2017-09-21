package uk.co.capita.advantagecontractor.pages.sessionpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import uk.co.capita.advantagecontractor.basepage.BasePage;
import uk.co.capita.advantagecontractor.util.ProjectConstants;

public class ManageJobPage extends BasePage{

//	@FindBy(xpath = ProjectConstants.TARGET_LINK)
//	public WebElement target_link;
	
	
	//constructor
	public ManageJobPage(WebDriver driver, WebDriverWait wait, ExtentTest test) {
		super(driver, wait, test);
	}
	
	

	public void modifyTargetDateAndTime() throws InterruptedException {
		test.log(LogStatus.INFO, "In modifyTargetDateAndTime method");
		//executor.executeScript("arguments[0].click();", target_link);
		//target_link.click();
		Thread.sleep(10000);
		driver.get("https://ec2-54-171-188-122.eu-west-1.compute.amazonaws.com/log_job/manage/A0000132/");
		Thread.sleep(8000);

		WebElement target_link = driver.findElement(By.xpath("//div[@id='job-timeline-holder']/job-timeline/div/div/div[2]/div/*[name() = 'svg']/*[name() = 'g']/*[name() = 'g'][1]/*[name() = 'g'][15]/*[name() = 'g']/*[name() = 'text'][1]"));
		Actions action = new Actions(driver);
		action.moveToElement(target_link).click().build().perform();
		
		
		
	}
	
	
	

}
