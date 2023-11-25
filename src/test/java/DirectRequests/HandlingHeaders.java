package DirectRequests;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HandlingHeaders {
	@Test
	void getHeaderInfo() {
		
//		Response response = 
				
		given()
		.when()
		.get("https://google.com") 
		.then()
		.header("Content-Encoding", "gzip")
		.header("Content-Type", "text/html; charset=ISO-8859-1")
		.header("Server","gws");
//		.extract().response();

		
		
//		.log().all();
		
	}
	
	
	@Test
	void CaptureHeaderInfo() {
		
		Response response = 
				
		given()
		.when()
		.get("https://google.com") 
		.then()
		/*
		.header("Content-Encoding", "gzip")
		.header("Content-Type", "text/html; charset=ISO-8859-1")
		.header("Server","gws");
		*/
		.extract().response();
		String hd1 = response.getHeader("Content-Encoding");
		System.out.println("The value of content type header is : "+ hd1);

		
		
//		.log().all();
		
	}
	
//		Retrieve the value of all the headers	
	@Test
	void getAllHeaderInfo() {
		
		Response response = 
				
		given()
		.when()
		.get("https://google.com") 
		.then()
		
		.extract().response();
		Headers headers = response.getHeaders();
		
		for (Header hd : headers) {
			
		System.out.println(hd.getName()+ "  :  "+ hd.getValue());		
		
		}
		
	}


}
