package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProvidersCoverPhoto {
	
	@DataProvider(name = "coverPhotoData")
	public Object[][] getCoverPhotoData(){
		return XLUtilityCoverPhoto.getTestData("test-data/CoverPhotoData.xlsx", "Sheet1");
	}

}
