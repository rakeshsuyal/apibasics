package DirectRequests;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class PathAndQueryParameter {
	
//  https://reqres.in/api/users?page=2
String baseUrl= "https://reqres.in";
@Test
	void passingpathParam()
	{
		given().log().all()
			.pathParams("path1","api")
			.pathParam("path2", "users")
			.queryParam("page", "2")
			
		.when()
		.get(baseUrl+"/{path1}/{path2}")
		.then()
		.log().all();
	}
	
	void usingQueryParam()
	{
		given()
			.queryParam("", "")
		
		.when()
		.get("")
		.then()
		
		
		.log().all();	
		
	}
	
}
