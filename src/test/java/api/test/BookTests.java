package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.BookEndPoints;
import api.payload.Book;
import api.payload.User;
import io.restassured.response.Response;

public class BookTests {

	Faker faker;
	Book bookPayload;
	int staticUserId = 3;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		faker = new Faker();
		bookPayload = new Book();
		
		bookPayload.setId(staticUserId);
		bookPayload.setTitle(faker.book().title());
		bookPayload.setDescription(faker.lorem().sentence());
		bookPayload.setPageCount(faker.number().numberBetween(100, 500));
		bookPayload.setExcerpt("Excerpt of " + bookPayload.getTitle());
		bookPayload.setPublishDate(java.time.LocalDateTime.now().toString());
		
		 // logs
		 logger=LogManager.getLogger(this.getClass());
		 
		 logger.debug("debugging....");
		
	}
	@Test(priority=1)
	public void testPostBook() {
		
		logger.info("*********Creating Book*********");
		
		Response response = BookEndPoints.createBook(bookPayload);
		
		response.then().log().all();
		
		 Assert.assertEquals(response.statusCode(), 200); // FakeRestAPI always returns 200
		 
		 logger.info("*********Book is created **********");
		
	}
	
	@Test(priority=2)
	public void testGetBook() {
		
		logger.info("*********Reading Book Info***************");
		
		Response response = BookEndPoints.readBook(staticUserId);
		response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        
        logger.info("*******Book is displayed*******");
		
	}
	@Test(priority=3)
	public void testUpdateBook() {
		
		logger.info("*******Updating Book ***********");
		
		bookPayload.setTitle(faker.book().title());
		bookPayload.setDescription(faker.lorem().sentence());
	    bookPayload.setPageCount(faker.number().numberBetween(100, 500));
	    bookPayload.setExcerpt("Updated excerpt for " + bookPayload.getTitle());
	    bookPayload.setPublishDate(java.time.LocalDateTime.now().toString());
	    
	    Response response = BookEndPoints.updateBook(staticUserId, bookPayload);
	    
	    response.then().log().all();
	    
        Assert.assertEquals(response.statusCode(), 200);
        
        logger.info("**********Book Updated***********");
	}
	
	@Test(priority=4)
	public void testDeleteBook() {
		
		logger.info("**********Deleting Book***********");
		
		Response response = BookEndPoints.deleteBook(staticUserId);
		
		response.then().log().all();
		
		 Assert.assertEquals(response.statusCode(), 200);
	        
	        logger.info("************Book Deleted***********");
	}
}
