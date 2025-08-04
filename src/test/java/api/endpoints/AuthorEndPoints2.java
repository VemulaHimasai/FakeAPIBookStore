package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.Author;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthorEndPoints2 {
	
	//method created for getting urls from properties file
		 static ResourceBundle getURL() {
			ResourceBundle routes = ResourceBundle.getBundle("routes"); // load the properties file
			return routes;
		}

	public static Response createAuthor(Author payload) {
		
		String post_author_url = getURL().getString("post_author_url");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("text/plain")
				.body(payload)
				
				.when()
				.post(post_author_url);
		
		return response;
	}
	
	public static Response readAuthor(int id) {
		String get_author_url = getURL().getString("get_author_url");
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.get(get_author_url);
		
		return response;
	}
	
	public static Response updateAuthor(int id, Author payload) {
		String update_author_url = getURL().getString("update_author_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("text/plain")
				.pathParam("id", id)
				.body(payload)
				
				.when()
				.put(update_author_url);
		
		return response;
	}
	
	public static Response deleteAuthor(int id) {
		String delete_author_url = getURL().getString("delete_author_url");
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.delete(delete_author_url);
		
		return response;
	}
}
