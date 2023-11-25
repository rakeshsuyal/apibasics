package DirectRequests;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;
import java.util.HashMap;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;



/*
 * 
given()
All pre-requisites: content type, set cookies, add auth, add param, set headers info etc.

when()
All requests: get, post, put, delete

then()
Validations: validate status code, assertions, extract response, extract headers cookies & response body.

 */

public class HttpRequests {
	
	
	String reqresBaseUri = "https://reqres.in/";

	@Test(priority=1) void getUsers() 

	{
		given()

		.when()
			.get("https://reqres.in/api/users?page=2")

		.then()
			.statusCode(200)
			.log().all();


	}
	
	@Test(priority=2) void createUser()
	{
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name","Rakesh");
		data.put("job","QA");
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
		
		
		.when()
			.post(reqresBaseUri+"api/users")
				
		.then()
			.statusCode(201)
			.log().all();
		
	}
	
	int createUserAndReturnId()
	{
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name","Rakesh");
		data.put("job","QA");
		
		int id = given()
			.contentType(ContentType.JSON)
			.body(data)
		
		
		.when()
			.post(reqresBaseUri+"api/users")			
			.jsonPath().getInt("id");
				
		return id;
	}

	
	@Test(priority=3, dependsOnMethods = {"createUser"})void updateUser()
	{
	HashMap<String,String> data = new HashMap<String,String>();
	data.put("name", "Raka");
	data.put("job", "Boss");
	
		given()
			.contentType(ContentType.JSON)
			.body(data)
		
		.when()
			.patch(reqresBaseUri+"api/users/"+createUserAndReturnId())		

		.then()
			.statusCode(200)		
			.log().all();
		
	}
	
	@Test(priority=4)
	void deleteUser() 
	{
		given()
		
		.when()
			.delete(reqresBaseUri+"api/users/"+createUserAndReturnId())
			
		
		.then()
			.statusCode(204)
		
		
		
		.log().all();
		
		
	}
	
	


}
