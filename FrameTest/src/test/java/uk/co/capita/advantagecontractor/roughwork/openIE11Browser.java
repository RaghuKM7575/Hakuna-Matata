package uk.co.capita.advantagecontractor.roughwork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class openIE11Browser {

	public static void main(String[] args) throws InterruptedException {
		//IMP-Ensure that Enable Protected mode is same for all 4 options viz Internet, Local Intranet, Trusted Sites and Restricted Sites.
		//To do this open IE, go to Tools-->Internet Options-->Security tab
		//Select "Enable Protected mode" checkbox for all 4 options.
		
		
		System.out.println("Hello World - How are you?1");

		System.setProperty("webdriver.ie.driver", "C:\\Users\\P10405304\\Downloads\\IEDriver\\IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setJavascriptEnabled(true);
		WebDriver driver = new InternetExplorerDriver(capabilities);
		driver.navigate().to("https://ec2-54-171-188-122.eu-west-1.compute.amazonaws.com/auth/AdvantageLogin.html");
		driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		Thread.sleep(5000);
		
		driver.findElement(By.xpath(".//*[@id='j_username']")).sendKeys("IDCUser1");
		driver.findElement(By.xpath(".//*[@id='j_password']")).sendKeys("0penHousing!23");
		driver.findElement(By.xpath(".//button[@id='login']")).click();;

		
		
//		driver.findElement(By.cssSelector("")).click();
//		System.out.println("Hello World - How are you?2");
	}

}
