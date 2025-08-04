package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProvidersAuthor {
	
	@DataProvider(name = "authorData")
	public Object[][] provideData(){
		
		return XLUtilityAuthor.getTestData("test-data/AuthorData.xlsx", "Sheet1");
	}

}
