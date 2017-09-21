package uk.co.capita.advantagecontractor.util;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			Date date = new Date();
			String fileName = date.toString().replace(":", "_").replace(" ", "_") + ".html";
			
			//Extent Reports will be generated at the below location
			String reportPath = System.getProperty("user.dir") + "//Extent Reports//" + fileName;
 
			extent = new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);

			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "3.4").addSystemInfo("Environment", "QA");
		}
		return extent;
	}
}
