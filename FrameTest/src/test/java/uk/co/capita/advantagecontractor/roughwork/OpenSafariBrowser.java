package uk.co.capita.advantagecontractor.roughwork;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class OpenSafariBrowser {

	public static void main(String[] args) {
		//To use Apple's SafariDriver, you need to upgrade to El Capitan (Mac OS) and Safari 10. 
		//To use the long deprecated Legacy SafariDriver, you need to downgrade to Selenium 2.x.
		//Since we are using Selenium 3.4, we can not support Safari.
		
		
		DesiredCapabilities capabilities = DesiredCapabilities.safari();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability("ignoreZoomSetting", true);
		WebDriver driver=new SafariDriver();
		driver.navigate().to("https://ec2-54-171-188-122.eu-west-1.compute.amazonaws.com/auth/AdvantageLogin.html");
		driver.findElement(By.xpath(".//*[@id='j_username']")).sendKeys("IDCUser1");
		driver.findElement(By.xpath(".//*[@id='j_password']")).sendKeys("0penHousing!23");
		driver.findElement(By.xpath(".//button[@id='login']")).click();
	}

}

