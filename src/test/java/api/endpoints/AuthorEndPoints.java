package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Author;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthorEndPoints {

	public static Response createAuthor(Author payload) {
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("text/plain")
				.body(payload)
				
				.when()
				.post(Routes.post_author_url);
		
		return response;
	}
	
	public static Response readAuthor(int id) {
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.get(Routes.get_author_url);
		
		return response;
	}
	
	public static Response updateAuthor(int id, Author payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("text/plain")
				.pathParam("id", id)
				.body(payload)
				
				.when()
				.put(Routes.update_author_url);
		
		return response;
	}
	
	public static Response deleteAuthor(int id) {
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.delete(Routes.delete_author_url);
		
		return response;
	}
}
