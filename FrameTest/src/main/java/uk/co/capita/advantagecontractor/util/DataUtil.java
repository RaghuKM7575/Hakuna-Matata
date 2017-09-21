package uk.co.capita.advantagecontractor.util;
import java.util.Hashtable;
public class DataUtil {

	//This function actually returns two dimensional Object array with only one column and with each row containing one hash table
		//And each hash table contains column name as key, corresponding data as value
		//The object array being returned will have only ONE column always
		//There will be as many rows as there are data sets-each data set representing one hash table
		public static Object[][] getData(Xls_Reader xls, String testCaseName){
			
			String sheetName = ProjectConstants.TESTDATA_SHEET_NAME;
				
			int testStartRowNum = 1;
			while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)){
				testStartRowNum++;
			}
			//System.out.println("Test starts from row - "+ testStartRowNum);
			
			int colLabelRowNum = testStartRowNum + 1;
			int dataStartRowNum = testStartRowNum + 2;
			
			// calculate rows of data
			int rows = 0;
			while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
				rows++;
			}
			System.out.println("Total rows are  - " + rows );
			
			//calculate total cols
			int cols = 0;
			while(!xls.getCellData(sheetName, cols, colLabelRowNum).equals("")){
				cols++;
			}
			System.out.println("Total cols are  - " + cols );
			
			//rows represent no. of hashtables, cols represent no. of key-value pairs
			//So, if there is a data table with 2 rows of data and 5 columns it means there will be 2 hashtables and 5 key-value pairs
			
			Object[][] data = new Object[rows][1];//if there are two rows of data, we will have two hashtables
			//read the data
			int dataRow = 0;
			Hashtable<String,String> table=null;
			for(int rNum = dataStartRowNum; rNum < dataStartRowNum+rows; rNum++){
				table = new Hashtable<String, String>();//create one hashtable for each row of data
				for(int cNum = 0; cNum < cols; cNum++){
					String key = xls.getCellData(sheetName, cNum, colLabelRowNum);
					String value = xls.getCellData(sheetName, cNum, rNum);
					table.put(key, value);
				}
				data[dataRow][0] = table;
				dataRow++;
			}
			return data;
		}
		
		public static boolean isTestExecutable(Xls_Reader xls, String testCaseName){
			int rows = xls.getRowCount(ProjectConstants.TESTCASES_SHEET_NAME);
			for(int rNum = 2; rNum <= rows; rNum++){
				String tcid = xls.getCellData(ProjectConstants.TESTCASES_SHEET_NAME, "Test Scenario", rNum);
				if(tcid.equals(testCaseName)){
					String runmode = xls.getCellData(ProjectConstants.TESTCASES_SHEET_NAME, "Runmode", rNum);
					System.out.println("RUNMODE" + runmode);
					if(runmode.equalsIgnoreCase("Y"))
						return true;
					else
						return false;
				}
			}
			return false;
		}

}
