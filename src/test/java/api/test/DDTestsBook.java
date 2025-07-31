package api.test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import api.endpoints.BookEndPoints;
import api.payload.Book;
import api.utilities.DataProvidersBook;
import io.restassured.response.Response;

public class DDTestsBook {
	
	//@Test(dataProvider="bookData",dataProviderClass=DataProvidersBook.class)
	@Test(dataProvider = "bookData", dataProviderClass = DataProvidersBook.class)
	public void fullCrudForEachBook(String id, String title, String description, String pageCount, String excerpt, String publishDate) {
	    // prepare book payload
		Book book = new Book();
		book.setTitle(title);
		book.setDescription(description);
		book.setPageCount((int) Double.parseDouble(pageCount.trim()));
		book.setExcerpt(excerpt);
		book.setPublishDate((publishDate != null && !publishDate.isEmpty()) ? publishDate : Instant.now().toString());
		
		//create book
		Response createRes = BookEndPoints.createBook(book);
		createRes.then().log().all().statusCode(200);
		
		// Get actual id from api response
		int bookId = createRes.jsonPath().getInt("id");
		System.out.println("Created Book Id: "+bookId);
		
		//read book
		Response readRes = BookEndPoints.readBook(bookId);
		readRes.then().log().all().statusCode(200);
		
		// update book
		book.setId(bookId);
		book.setTitle("Updated " + title);
		book.setDescription("Updated "+ description);
		book.setExcerpt("Updated "+ excerpt);
		book.setPublishDate(Instant.now().toString());
		
		 Response updateRes = BookEndPoints.updateBook(bookId, book);
		 updateRes.then().log().all().statusCode(200);
		  
		  // Delete Book
		 
		 Response deleteRes = BookEndPoints.deleteBook(bookId);
		 deleteRes.then().log().all().statusCode(200);
		    
		    // delete confirmation
		 BookEndPoints.readBook(bookId)
	        .then().log().all().statusCode(404); // expected not found
		    
	}

	

}
