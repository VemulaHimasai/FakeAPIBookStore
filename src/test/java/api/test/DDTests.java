package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(dataProvider = "excelUserData", dataProviderClass=DataProviders.class)
	public void testUserCRUDFlow(int id, String username, String password) {
		 // 1. Create
	    User user = new User();
	   user.setId(id);
	    user.setUserName(username);
	    user.setPassword(password);

	    Response createRes = UserEndPoints.createUser(user);
	    createRes.then().log().all();
	    Assert.assertEquals(createRes.statusCode(), 200, "User creation failed");
	    
	 // Get the actual ID returned by the API (avoid using Excel ID)
	    int createdId = createRes.jsonPath().getInt("id");
	    System.out.println("Created ID: " + createdId);
	    
	    // 2. Read
	    Response readRes = UserEndPoints.readUser(createdId);
	    readRes.then().log().all();
	    Assert.assertEquals(readRes.statusCode(), 200, "User read failed");
	    
	    Assert.assertEquals(readRes.jsonPath().getString("userName"), username,"Username mismatch");
	    
	    // 3. Update
	    user.setUserName(username + "_updated");
	    user.setPassword(password + "123");
	    Response updateRes = UserEndPoints.updateUser(createdId, user);
	    updateRes.then().log().all();
	    Assert.assertEquals(updateRes.statusCode(), 200, "User update failed");
	    
	    // 4. Read Updated
	    Response readUpdatedRes = UserEndPoints.readUser(createdId);
	    readUpdatedRes.then().log().all();
	    Assert.assertEquals(readUpdatedRes.statusCode(), 200);
	    Assert.assertEquals(readUpdatedRes.jsonPath().getString("userName"), username + "_updated", "Updated username mismatch");
	    
	 // 5. Delete
	    Response deleteRes = UserEndPoints.deleteUser(createdId);
	    deleteRes.then().log().all();
	    Assert.assertEquals(deleteRes.statusCode(), 200, "User deletion failed");
	    
	 // 6. Confirm Deletion
	    Response readAfterDelete = UserEndPoints.readUser(createdId);
	    readAfterDelete.then().log().all();
	    Assert.assertEquals(readAfterDelete.statusCode(), 404, "Deleted user should return 404");
	}
}
