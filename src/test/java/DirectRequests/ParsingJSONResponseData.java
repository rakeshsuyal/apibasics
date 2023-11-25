package DirectRequests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.json.JSONObject;

public class ParsingJSONResponseData {
	
	@Test()
	void testJSONResponse()	{
		 Response response = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		.then().extract().response();
		
//		Assert.assertEquals(response.getStatusCode(), 200);	
//		Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
		
//		Approach 1
		 
		 response.getBody().path("book[3]", "title").equals("The lord of the Ring");
		System.out.println(response.asPrettyString());
		
//		Approach 2
		Assert.assertEquals(
		response.getBody().jsonPath().get("book[3].title"),"The lord of the Ring");
		
		/*		
			.statusCode(200)	
			.header("Content-Type", "application/json; charset=utf-8")
			.body("book[3].title",Matchers.equalTo("The lord of the Ring"))
		
		//Approach 2
		 * .log().all();
			
*/						
			
		
	}
	
	@Test()
	void retrivingMultipleJSONObjectsUsingJSONObjectClass()	{
		Response response = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		.then().extract().response();
//			.statusCode(200)
//			.header("Content-Type", "application/json; charset=utf-8")
//			.body("book[3].title",Matchers.equalTo("The lord of the Ring"))
		
//		Converting response to JSON object type
		 JSONObject jsonObj = new JSONObject(response.asPrettyString());
			int lengthOfbookArray =jsonObj.getJSONArray("book").length();	
			String[] allBooksTitles= new String[lengthOfbookArray];
		for (int i=0;i<lengthOfbookArray;i++) {
			String currentBookTitle = jsonObj.getJSONArray("book").getJSONObject(i).get("title").toString();
		
		System.out.println(currentBookTitle);
		
		allBooksTitles[i]= currentBookTitle;
		
		}
		Assert.assertEquals(allBooksTitles[3],"The lord of the Ring");
//		System.out.println(allBooksTitles);
				
	}
	
	@Test(priority=2)
	void convertingResponseToJSONObjectViaJSONObjectClass()	{
		double totalPriceOfBooks=0;
		Response response = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		.then().extract().response();
//			.statusCode(200)
//			.header("Content-Type", "application/json; charset=utf-8")
//			.body("book[3].title",Matchers.equalTo("The lord of the Ring"))
		
//		Converting response to JSON object type
		 JSONObject jsonObj = new JSONObject(response.asPrettyString());
		 
			int lengthOfbookArray =jsonObj.getJSONArray("book").length();	
			String[] allBooksTitles= new String[lengthOfbookArray];
		for (int i=0;i<lengthOfbookArray;i++) {
			String currentBookprice = jsonObj.getJSONArray("book").getJSONObject(i).get("price").toString();
		
//		System.out.println(currentBookTitle);
		
//		allBooksTitles[i]= currentBookTitle;
			 totalPriceOfBooks = totalPriceOfBooks+Double.parseDouble(currentBookprice);
		
		}
		Assert.assertEquals(totalPriceOfBooks,526.00);
		System.out.println(totalPriceOfBooks);
				
	}
	
	
	
}

