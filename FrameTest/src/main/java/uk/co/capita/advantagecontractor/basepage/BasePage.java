package uk.co.capita.advantagecontractor.basepage;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import uk.co.capita.advantagecontractor.common.Menu;
import uk.co.capita.advantagecontractor.common.Search;

public class BasePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest test;
	public Menu menu;
	public JavascriptExecutor executor;
	public String xpath;
	public Search search;
	
	
	//BasePage Constructor
	public BasePage(WebDriver driver, WebDriverWait wait, ExtentTest test){
		this.driver = driver;
		this.wait = wait;
		this.test = test;
		executor = (JavascriptExecutor) driver;
		
		menu = new Menu(driver, wait, test);
		PageFactory.initElements(driver, menu);
		
		search = new Search(driver, wait, test);
		PageFactory.initElements(driver, search);
	}

//common functions


	
	//Following method verifies if a web element is present on a web page
	public boolean isElementPresent(String locator){
		test.log(LogStatus.INFO, "Verifying the presence of web element " + locator);
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		} catch(Throwable t){
			t.printStackTrace();
			test.log(LogStatus.INFO, t);
			return false;
		}
		int size = driver.findElements(By.xpath(locator)).size();
		if(size==0){
			test.log(LogStatus.INFO, "Element not found " +  locator);
			return false;
		}
		else{
			test.log(LogStatus.INFO, "Element found " +  locator);
			return true;
		}
	}
	
	
	
	
	
//Following method verifies the current window title	
public boolean verifyWindowTitle(String ExpTitle){
	test.log(LogStatus.INFO, "Verifying Window Title " + ExpTitle);
	if(!driver.getTitle().equals(ExpTitle)){
		test.log(LogStatus.INFO,  driver.getTitle() + " does not match with " + ExpTitle);
		return false;
	}
	test.log(LogStatus.INFO,  driver.getTitle() + " match with " + ExpTitle);
	return true;
}


//verifies the text of a web element
public boolean verifyText(String locator, String expText){
	test.log(LogStatus.INFO, "Verify web element text");
	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	} catch(Throwable t){
		t.printStackTrace();
		test.log(LogStatus.INFO, t);
		return false;
	}
	String actText = driver.findElement(By.xpath(locator)).getText();
	if(!actText.equalsIgnoreCase(expText)){
		test.log(LogStatus.INFO, "Expected text is " + expText + " but actual text is " + actText);
		return false;
	}
		
	else{
		test.log(LogStatus.INFO, "Expected text is " + expText + " and actual text is " + actText);
		return true;
	}
		
}



//verifies the text of a web element
public boolean verifyLabel(String locator, String expText){
	test.log(LogStatus.INFO, "Verify web element text");
	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	} catch(Throwable t){
		t.printStackTrace();
		test.log(LogStatus.INFO, t);
		return false;
	}
	String actText = driver.findElement(By.xpath(locator)).getAttribute("value");
	if(!actText.equalsIgnoreCase(expText)){
		test.log(LogStatus.INFO, "Expected text is " + expText + " but actual text is " + actText);
		return false;
	}
		
	else{
		test.log(LogStatus.INFO, "Expected text is " + expText + " and actual text is " + actText);
		return true;
	}
		
}


//verifies the place holder text of a web element
public boolean verifyPlaceholderText(String locator, String expText){
	test.log(LogStatus.INFO, "Verify web element text");
	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	} catch(Throwable t){
		t.printStackTrace();
		test.log(LogStatus.INFO, t);
		return false;
	}
	String actText = driver.findElement(By.xpath(locator)).getAttribute("placeholder");
	if(!actText.equalsIgnoreCase(expText)){
		test.log(LogStatus.INFO, "Expected text is " + expText + " but actual text is " + actText);
		return false;
	}
		
	else{
		test.log(LogStatus.INFO, "Expected text is " + expText + " and actual text is " + actText);
		return true;
	}
		
}


//Following method verifies broken link for any web element
public boolean isElementUrlActive(String locator){
	test.log(LogStatus.INFO, "Verify web element link functionality");
	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	} catch(Throwable t){
		t.printStackTrace();
		test.log(LogStatus.INFO, t);
		return false;
	}
	String href = driver.findElement(By.xpath(locator)).getAttribute("href");
	//System.out.println(href);
	try{
		URL url = new URL(href);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.connect();
		test.log(LogStatus.INFO, "Response Message is " + connection.getResponseMessage());
		if(!connection.getResponseMessage().equalsIgnoreCase("OK")){
			test.log(LogStatus.INFO, "Response Message for the URL " + href + " is not OK");
			return false;
		}
		connection.disconnect();
		test.log(LogStatus.INFO, "Response Message for the URL " + href + " is  OK");
		return true;
		} catch(Throwable t){
			t.printStackTrace();
			test.log(LogStatus.INFO, t);
			return false;
		}
	
}

//Following method returns list of web elements 
public List<WebElement> getElementList(String locator) {
	test.log(LogStatus.INFO, "Extracting list of web elements");
	List<WebElement> elementList = new ArrayList<WebElement>();
	try{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	} catch(Throwable t){
		test.log(LogStatus.INFO, "Web element missing on the page --> " + locator);
		return null;
	}
	elementList = driver.findElements(By.xpath(locator));
	System.out.println(elementList.size());
	test.log(LogStatus.INFO, "Returning list of Web elements on the page");
	return elementList;
}


//takeScreenShot() method takes screenshot whenever invoked
public void takeScreenShot(){
	Date date = new Date();
	String screenshotFile = date.toString().replace(":", "_").replace(" ", "_")+".png";
	String filePath= System.getProperty("user.dir") + "//Screenshots//" + screenshotFile;
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


public Menu getMenu() {
	if(menu == null){
		menu = new Menu(driver, wait, test);
		PageFactory.initElements(driver, menu);
		return menu;
	}
	return menu;
}





















}
