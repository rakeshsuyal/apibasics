package DirectRequests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingDemo {
	
	
	@Test
	void testLog()
	{
		given()
		.when()
		.get("http://localhost:3000")
		.then()
//		.log().body()
//		.log().all()
//		.log().headers()
		.log().cookies();
	}
	
	
}
