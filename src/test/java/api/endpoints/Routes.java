package api.endpoints;

public class Routes {
	
	//FAKE-Rest APIs
	
	public static String base_url = "https://fakerestapi.azurewebsites.net/";
	
	//User module
	
	public static String post_user_url = base_url+"/api/v1/Users";
	
	public static String get_user_url = base_url+"/api/v1/Users/{id}";
	
	public static String update_user_url = base_url+"api/v1/Users/{id}";
	
	public static String delete_user_url = base_url+"api/v1/Users/{id}";
	
	
	// Book Module
	
	public static String post_book_url = base_url+"/api/v1/Books";
	
	public static String get_book_url = base_url+"/api/v1/Books/{id}";
	
	public static String update_book_url = base_url+"/api/v1/Books/{id}";
	
	public static String delete_book_url = base_url+"/api/v1/Books/{id}";

}
