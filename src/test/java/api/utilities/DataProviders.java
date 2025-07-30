package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="excelUserData")
	
	public Object[][] getUserDataFromExcel(){
		String excelPath = System.getProperty("user.dir") + "/test-data/Userdata-FakeAPI.xlsx";
	    return XLUtility.getTestData(excelPath, "Sheet1");
		
	}

}
