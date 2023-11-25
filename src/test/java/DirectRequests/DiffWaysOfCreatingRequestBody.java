package DirectRequests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.specification.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;


public class DiffWaysOfCreatingRequestBody {
	
	// we have 4 different ways of creating the request body
	// 1: Using hashmap
	// 2: Using Org.Json
	// 3: Using POJO(Plain Old Java Object) Class
	// 4: Using external Json File



	
	
	
	void creatingBodyViaHashMap()
	
	{
		HashMap data = new HashMap();
		
	//	data.put("id", 4);
		data.put("name","Will");
		data.put("location","LA");
		data.put("phone","3332223232");
		
		String[] courseArray =  {"Python","C"};
		data.put("course", courseArray);
		
//		Response response =	
				
		given()
		.contentType(ContentType.JSON)
		.body(data)
	
		.when()
		.post("http://localhost:3000/student_data1")
//		.then().extract().response();
		
		
		.then()
		.statusCode(201)
		.body("name", Matchers.equalTo("Will"))
		.body("location", Matchers.equalTo("LA"))
		.body("phone", Matchers.equalTo("3332223232"))
		.body("course[0]", Matchers.equalTo("Python"))
		.body("course[1]", Matchers.equalTo("C"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	
	}
	
// Creating post request using JSON Library
	@Test
	void creatingBodyViaOrgJSON()
	{
		JSONObject data= new JSONObject();
		data.put("name", "Will");
		data.put("location", "LA");
		data.put("phone", "3332223232");
		
		String courseArray[]= {"Python", "C"};
		data.put("course", courseArray);

					
			given()
			.contentType(ContentType.JSON)
			.body(data.toString())
		
			.when()
			.post("http://localhost:3000/student_data1")
//			.then().extract().response();
			
			
			.then()
			.statusCode(201)
			.body("name", Matchers.equalTo("Will"))
			.body("location", Matchers.equalTo("LA"))
			.body("phone", Matchers.equalTo("3332223232"))
			.body("course[0]", Matchers.equalTo("Python"))
			.body("course[1]", Matchers.equalTo("C"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	
	
// 3: Creating request body using POJO Class	
	@Test
	void creatingBodyViaPOJOClass()
	{
		
		PojoPostRequest data = new PojoPostRequest();
		data.setName("Tom");
		data.setLocation("LA");
		data.setPhone("222222222");
		String[] courseArr = {"C", "C++"};
		data.setCourse(courseArr);
		
		given()
		.contentType(ContentType.JSON)
		.when()
		.body(data)
		.post("http://localhost:3000/student_data1")
		.then()
		.statusCode(201)
		.body("name", Matchers.equalTo("Tom"))
		.body("location",Matchers.equalTo("LA"))
		.body("phone", Matchers.equalTo("222222222"))
		.body("course[0]", Matchers.equalTo("C"))
		.body("course[1]", Matchers.equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	
	}

// Creating request body via External JSON
	@Test
	void creatingBodyViaExternalJSON()
	
	{
		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\body.json");
		FileReader fr = null;
		try 
		{
			fr = new FileReader(f);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		JSONTokener jt= new JSONTokener(fr);
		JSONObject data= new JSONObject(jt);
		
		
		given().log().all()
		.contentType(ContentType.JSON)
		
		.when()
		.body(data.toString())
		.post("http://localhost:3000/student_data1")
		.then()
		.statusCode(201)
		.body("name", Matchers.equalTo("Tom"))
		.body("location",Matchers.equalTo("LA"))
		.body("phone", Matchers.equalTo("222222222"))
		.body("course[0]", Matchers.equalTo("C"))
		.body("course[1]", Matchers.equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	
	}


	@Test
	void deleteUser()
	{
	given()
	
	.when()
	.delete("http://localhost:3000/student_data1/6")
	
	.then()
	.statusCode(200);
	
	
	}

}
