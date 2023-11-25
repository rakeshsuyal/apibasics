package DirectRequests;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {

	//	http://restapi.adequateshop.com/api/Traveler?page=1

	@Test
	void testXMLResponse() {

		//Approach 1
		Response response =
				given()
				.contentType(ContentType.XML)
				.pathParam("path1", "api")
				.pathParams("path2", "Traveler")

				.when()

				.get("http://restapi.adequateshop.com/{path1}/{path2}")
				.then().extract().response();


		//Approach 1
		/*				
		.statusCode(200)
		.header("Content-Type", "application/xml; charset=utf-8")
		.body("TravelerinformationResponse.travelers.Travelerinformation.id[0]", Matchers.equalTo("11133"))
		 */
		//Approach 2

		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getHeader("Content-Type"),"application/xml; charset=utf-8");
		Assert.assertEquals(response.xmlPath()
				.get("TravelerinformationResponse.travelers.Travelerinformation.id[0]"), "11133");
	}

	@Test
	void testXMLResponseUsingXMLPathClass() {

		//if respective traveler name is present set the flag to true
		boolean travlerNameFlag = false; 

		//Approach 1
		Response response =
				given()
				.contentType(ContentType.XML)
				.pathParam("path1", "api")
				.pathParams("path2", "Traveler")

				.when()

				.get("http://restapi.adequateshop.com/{path1}/{path2}")
				.then().extract().response();

		XmlPath xmlObj = new XmlPath(response.asPrettyString());
		List listOfTravelers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");

		Assert.assertEquals(listOfTravelers.size(),10);

		// Verify if the traveler name is present in the response
		List<String> travelerNames = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

		for(String travelerName : travelerNames) {
			if(travelerName.equalsIgnoreCase("karen")) {
				travlerNameFlag=true;
				Assert.assertEquals(travlerNameFlag,true);
				System.out.println("Traveler name karen is present in the traveler names list");
				break;
			} 
			// Printing all the travelers names
			// System.out.println(travelerName);
		}
	}
}
