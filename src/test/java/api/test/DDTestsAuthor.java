package api.test;

import org.testng.annotations.Test;

import api.endpoints.AuthorEndPoints;
import api.payload.Author;
import api.utilities.DataProvidersAuthor;
import io.restassured.response.Response;

public class DDTestsAuthor {
	
	@Test(dataProvider="authorData", dataProviderClass=DataProvidersAuthor.class)
	public void testAuthorFlow(String id, String idBook, String firstName, String lastName) {
		
		// Convert Excel inputs (which may be read as decimal strings like "101.0") to int
	    int authorId = (int) Double.parseDouble(id);
	    int authorBookId = (int) Double.parseDouble(idBook);
	    
	 // Prepare payload
	    Author author = new Author();
	    author.setId(authorId);
	    author.setIdBook(authorBookId);
	    author.setFirstName(firstName);
	    author.setLastName(lastName);
	    
	    // create
	    Response createRes = AuthorEndPoints.createAuthor(author);
	    createRes.then().log().body().statusCode(200);
	    
	    //Read
	    Response readRes = AuthorEndPoints.readAuthor(authorId);
	    readRes.then().log().body().statusCode(200);
	    
	    // update 
	    author.setFirstName("Updated_" + firstName);
	    Response updateRes = AuthorEndPoints.updateAuthor(authorId, author);
	    updateRes.then().log().body().statusCode(200);
	    
	    // delete
	    Response deleteRes = AuthorEndPoints.deleteAuthor(authorId);
	    deleteRes.then().log().body().statusCode(200);
	    
	    
		
	}

}
