package api.test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.CoverPhotoEndPoints;
import api.payload.CoverPhoto;
import io.restassured.response.Response;

public class CoverPhotoTests {
	
	Faker faker;
	CoverPhoto coverPayload;
	int staticid = 3;
	int idBook=1;
	public Logger logger;
	
	@BeforeClass()
	public void setup() {
		faker = new Faker();
		coverPayload = new CoverPhoto();
		
		coverPayload.setId(staticid);
		coverPayload.setIdBook(idBook);
		coverPayload.setUrl(faker.internet().image());
		
		// logs
		 logger=LogManager.getLogger(this.getClass());
		 
		 logger.debug("debugging....");
		
	}
	
	@Test(priority=1)
	public void testPostCoverPhoto() {
		
		logger.info("*******creating CoverPhoto***********");
		
		Response response = CoverPhotoEndPoints.createCoverPhoto(coverPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200); 
		
		logger.info("**********CoverPhoto created**********");
		
	}
	@Test(priority=2)
	public void testGetCoverPhoto() {
		
		logger.info("**********reading CoverPhoto***********");
		
		Response response = CoverPhotoEndPoints.readCoverPhoto(idBook);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("********CoverPhoto is displayed***********");
	}
	
	@Test(priority=3)
	public void testUpdateCoverPhoto() {
		
		logger.info("*********updating CoverPhoto**********");
		
		coverPayload.setUrl("Updated_" + faker.internet().image());
		
		Response response = CoverPhotoEndPoints.updateCoverPhoto(staticid, coverPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		
		logger.info("*********CoverPhoto updated***********");
	}
	
	@Test(priority=4)
	public void testDeleteCoverPhoto() {
		
		logger.info("***********deleting CoverPhoto***********");
		
		Response response = CoverPhotoEndPoints.deleteCoverPhoto(staticid);
		response.then().log().all();
		
		 Assert.assertEquals(response.statusCode(), 200);
		 
		 logger.info("**********CoverPhoto deleted*************");
		
		
	}

}
