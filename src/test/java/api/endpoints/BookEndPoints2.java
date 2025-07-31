package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.Book;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookEndPoints2 {
	
	//method created for getting urls from properties file
	 static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // load the properties file
		return routes;
	}
	
	public static Response createBook(Book payload) {
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("*/*")
				.body(payload)
				
				.when()
				.post(Routes.post_book_url);
		
		return response;
		
	}
	
	public static Response readBook(int id) {
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.get(Routes.get_book_url);
		
		return response;
	}
	
	public static Response updateBook(int id, Book payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("*/*")
				.pathParam("id", id)
				.body(payload)
				
				.when()
				 .put(Routes.update_book_url);
		
		return response;
	}
	
	public static Response deleteBook(int id) {
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.delete(Routes.delete_book_url);
		
		return response;
	}

}
