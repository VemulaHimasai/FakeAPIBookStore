package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.CoverPhoto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CoverPhotoEndPoints2 {
	
	//method created for getting urls from properties file
		 static ResourceBundle getURL() {
			ResourceBundle routes = ResourceBundle.getBundle("routes"); // load the properties file
			return routes;
		}
	
	public static Response createCoverPhoto(CoverPhoto payload) {
		
		String post_cover_url = getURL().getString("post_cover_url");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("text/plain")
				.body(payload)
				
				.when()
				.post(post_cover_url);
		
		return response;
	}
	
	public static Response readCoverPhoto(int id) {
		
		String get_cover_url = getURL().getString("get_cover_url");
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.get(get_cover_url);
		
		return response;
	}
	
	public static Response updateCoverPhoto(int id, CoverPhoto payload) {
		
		String update_cover_url = getURL().getString("update_cover_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("text/plain")
				.pathParam("id", id)
				.body(payload)
				
				.when()
				.put(update_cover_url);
		
		return response;
	}
	
	public static Response deleteCoverPhoto(int id) {
		
		String delete_cover_url = getURL().getString("delete_cover_url");
		
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.delete(delete_cover_url);
		
		return response;
	}
	
	

}
