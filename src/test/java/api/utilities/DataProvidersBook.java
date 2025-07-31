package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProvidersBook {
	
	 @DataProvider(name = "bookData")
	public Object[][] getBooks() {
	    return XLUtilityBook.getTestData("test-data/BookData.xlsx", "Sheet1");
	}

}
