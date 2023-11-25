package DirectRequests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {
	@Test
	void testCookies()
	{
		given()
		
		
		.when()
		.get("https://google.com")
		.then()
		.cookie("AEC")
		.log().all();
		
	}
	
		@Test
		void GetCookieInfo()
		{
			Response response = given()
			
			
			.when()
			.get("https://google.com")

			.then().extract().response();
			
//			get single cookie info.
			
			String cookie_values = response.getCookie("AEC");		
						
			
//          get all the cookies.			
			
			Map<String, String> cookies_values = response.getCookies();
			
//			System.out.println("The value of cookie is : "+ cookies_values.keySet());
			
			for(String k:cookies_values.keySet()) {
				String cookie_value= response.getCookie(k);
			System.out.println(k+"   :  "+cookie_value);
			
			}
			/*
			
		.cookie("AEC")
		.log().all();
			*/
 
			
		}

}
