package api.utilities;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.function.Supplier;

import io.restassured.response.Response;

public class RetryUtil {
	
	/**
     * Retries a RestAssured request up to maxRetries times if it doesn't return expected status code.
     *
     * @param requestSupplier A lambda that supplies the request (e.g., () -> given().get(...))
     * @param expectedStatus Expected HTTP status code
     * @param maxRetries Max number of retries
     * @param delayMillis Delay between retries in milliseconds
     * @return The Response object from the final attempt
     */
	
	public static Response retryRequest(Supplier<Response> requestSupplier, int expectedStatus, int maxRetries, long delayMillis) {
		
		 int attempts = 0;
	        Response response = null;
	        
	        while(attempts<maxRetries) {
	        	response = requestSupplier.get();
	        	if(response.getStatusCode() == expectedStatus) {
	        		return response;
	        	}
	        	attempts++;
	        	try {
	        		Thread.sleep(delayMillis);
	        	}catch(InterruptedException e) {
	        		Thread.currentThread().interrupt();
	                throw new RuntimeException("Retry interrupted", e);
	        	}
	        }
	        
	        return response;
		
	}

}
