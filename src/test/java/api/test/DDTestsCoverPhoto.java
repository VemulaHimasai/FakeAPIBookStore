package api.test;

import org.testng.annotations.Test;

import api.endpoints.CoverPhotoEndPoints;
import api.payload.CoverPhoto;
import api.utilities.DataProvidersCoverPhoto;
import io.restassured.response.Response;

public class DDTestsCoverPhoto {
	
	@Test(dataProvider = "coverPhotoData", dataProviderClass = DataProvidersCoverPhoto.class)
    public void testCreateCoverPhoto(String id, String idBook, String url) {
        CoverPhoto cover = new CoverPhoto();
        cover.setId((int) Double.parseDouble(id));
        cover.setIdBook((int) Double.parseDouble(idBook));
        cover.setUrl(url);

        Response res = CoverPhotoEndPoints.createCoverPhoto(cover);
        res.then().log().all().statusCode(200);
    }
	@Test(priority = 2, dataProvider = "coverPhotoData", dataProviderClass = DataProvidersCoverPhoto.class)
    public void testGetCoverPhotoById(String id, String idBook, String url) {
        int coverId = (int) Double.parseDouble(id);
        Response res = CoverPhotoEndPoints.readCoverPhoto(coverId);
        res.then().log().all().statusCode(200);
    }
	
	 @Test(priority = 3, dataProvider = "coverPhotoData", dataProviderClass = DataProvidersCoverPhoto.class)
	    public void testUpdateCoverPhoto(String id, String idBook, String url) {
	        int coverId = (int) Double.parseDouble(id);

	        CoverPhoto updatedCover = new CoverPhoto();
	        updatedCover.setId(coverId);
	        updatedCover.setIdBook((int) Double.parseDouble(idBook));
	        updatedCover.setUrl(url + "?updated=true");

	        Response res = CoverPhotoEndPoints.updateCoverPhoto(coverId, updatedCover);
	        res.then().log().all().statusCode(200);
	    }
	 
	 @Test(priority = 4, dataProvider = "coverPhotoData", dataProviderClass = DataProvidersCoverPhoto.class)
	    public void testDeleteCoverPhotoById(String id, String idBook, String url) {
	        int coverId = (int) Double.parseDouble(id);

	        Response res = CoverPhotoEndPoints.deleteCoverPhoto(coverId);
	        res.then().log().all().statusCode(200);
	    }

}
