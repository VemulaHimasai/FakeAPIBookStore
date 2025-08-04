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

import api.endpoints.AuthorEndPoints;
import api.payload.Author;
import io.restassured.response.Response;

public class AuthorTests {
	
	Faker faker;
	Author authorPayload;
	int staticid = 3;
	public Logger logger;
	
	@BeforeClass()
	public void setup() {
		faker = new Faker();
		authorPayload = new Author();
		
		authorPayload.setId(staticid);
        authorPayload.setIdBook(2);  // Assuming book with id=2 exists
        authorPayload.setFirstName(faker.name().firstName());
        authorPayload.setLastName(faker.name().lastName());
        
             // logs
     		 logger=LogManager.getLogger(this.getClass());
     		 
     		 logger.debug("debugging....");
	}
	@Test(priority=1)
	public void testPostAuthor() {
		
		logger.info("************Creating Author****************");
		
		Response response = AuthorEndPoints.createAuthor(authorPayload);
		response.then().log().all();
		
		 Assert.assertEquals(response.statusCode(), 200); // FakeRestAPI always returns 200
		 
		 logger.info("***************Author Created***************");
		
	}
	@Test(priority=2)
	public void testGetAuthor() throws InterruptedException {
		logger.info("**************Reading Author Info**************");
		 //Thread.sleep(1000);  // Wait 1 second
		
		
		  Response response = AuthorEndPoints.readAuthor(staticid);
		response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        
        logger.info("***********Author is displayed************");
	}
	
	@Test(priority=3)
	public void testUpdateAuthor() {
		
		logger.info("**************Updating Author*************");
		
		authorPayload.setFirstName("Updated_" + faker.name().firstName());

        Response response = AuthorEndPoints.updateAuthor(authorPayload.getId(), authorPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        logger.info("************ Author Updated ************");
	}
	
	@Test(priority=4)
	public void testDeleteAuthor() {
		
		logger.info("************ Deleting Author ************");

        Response response = AuthorEndPoints.deleteAuthor(authorPayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        logger.info("************ Author Deleted ************");
		
	}

}
