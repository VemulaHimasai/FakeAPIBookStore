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

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.RetryUtil;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	int staticUserId = 5;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(staticUserId);
		userPayload.setUserName(faker.name().username());
		userPayload.setPassword(faker.internet().password());
		
		 // logs
		 logger=LogManager.getLogger(this.getClass());
		 
		 logger.debug("debugging....");
		
	}
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("*********Creating User*********");
		
		 Response response = UserEndPoints.createUser(userPayload);
	        response.then().log().all();
	        Assert.assertEquals(response.statusCode(), 200); // FakeRestAPI always returns 200
	        
	        logger.info("*********User is created **********");
		
		
	}
	
	@Test(priority=2)
	public void testGetUser() {
		
		logger.info("*********Reading User Info***************");
		
		Response response = UserEndPoints.readUser(staticUserId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        
        logger.info("*******User is displayed*******");
	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		
		logger.info("*******Updating user ***********");
		
		userPayload.setUserName("updated." + faker.name().username());
        Response response = UserEndPoints.updateUser(staticUserId, userPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        
        logger.info("**********User Updated***********");
		
		
	}
	
	
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		logger.info("**********Deleting User***********");
		
		Response response = UserEndPoints.deleteUser(staticUserId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        
        logger.info("************User Deleted***********");
	}

}
