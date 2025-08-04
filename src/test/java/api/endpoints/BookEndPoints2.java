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
		
		String post_book_url = getURL().getString("post_book_url");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("*/*")
				.body(payload)
				
				.when()
				.post(post_book_url);
		
		return response;
		
	}
	
	public static Response readBook(int id) {
		String get_book_url = getURL().getString("get_book_url");
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.get(get_book_url);
		
		return response;
	}
	
	public static Response updateBook(int id, Book payload) {
		String update_book_url = getURL().getString("update_book_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept("*/*")
				.pathParam("id", id)
				.body(payload)
				
				.when()
				 .put(update_book_url);
		
		return response;
	}
	
	public static Response deleteBook(int id) {
		String delete_book_url = getURL().getString("delete_book_url");
		Response response = given()
				.pathParam("id", id)
				
				.when()
				.delete(delete_book_url);
		
		return response;
	}

}
