package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.CoverPhoto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CoverPhotoEndPoints {
	
	public static Response createCoverPhoto(CoverPhoto payload) {
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("text/plain")
				.body(payload)
				
				.when()
				.post(Routes.post_cover_url);
		
		return response;
	}
	
	public static Response readCoverPhoto(int id) {
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.get(Routes.get_cover_url);
		
		return response;
	}
	
	public static Response updateCoverPhoto(int id, CoverPhoto payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("text/plain")
				.pathParam("id", id)
				.body(payload)
				
				.when()
				.put(Routes.update_cover_url);
		
		return response;
	}
	
	public static Response deleteCoverPhoto(int id) {
		
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.delete(Routes.delete_cover_url);
		
		return response;
	}
	
	

}
