package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	//method created for getting urls from properties file
		 static ResourceBundle getURL() {
			ResourceBundle routes = ResourceBundle.getBundle("routes"); // load the properties file
			return routes;
		}
	
	public static Response createUser(User payload) {
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("*/*")
				.body(payload)
			.when()
			 .post(Routes.post_user_url);
		
		return response;
		
	}

	public static Response readUser(int id) {
		Response response = given()
				.pathParam("id", id)
			
				.when()
				.get(Routes.get_user_url);
		
		return response;
	}
	
	public static Response updateUser(int id,User payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("*/*")
				.pathParam("id", id)
				.body(payload)
				
				.when()
				.put(Routes.update_user_url);
		return response;
	}
	
	public static Response deleteUser(int id) {
		Response response = given()
				.pathParam("id", id)
				
				.when()
				
				.delete(Routes.delete_user_url);
		
		return response;
	}
}
