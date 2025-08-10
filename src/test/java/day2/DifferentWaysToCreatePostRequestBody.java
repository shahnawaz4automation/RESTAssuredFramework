package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DifferentWaysToCreatePostRequestBody {
	/*
	 * Different ways to create POST request body:
	 * 1) Post request body using Hashmap
	 * 2) Post request body creation using using Org.JSON
	 * 3) Post request body creation using POJO class
	 * 4) Post request using external json file data
	 */
	String id = "";

	//1) Post request body using Hashmap
	//@Test(priority = 1)
	void testPostUsingHashMap() {

		HashMap data = new HashMap();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "8121162627");
		String courseArr[] = { "C", "C++" };
		data.put("courses", courseArr);
		/*
		 * data.put("courses", courseArr[]); will give compile time error In Java, when
		 * you pass an array to a method, you don’t use [] after the variable name — you
		 * just pass the variable itself. Why? courseArr[] is only valid when declaring
		 * or accessing an array element (like courseArr[0]).
		 * 
		 * When passing an array as an object to a method like put(), you just give the
		 * array variable name.
		 */

		given()
		     .contentType("application/json")
		     .body(data)
		.when()
		     .post("http://localhost:3000/students")
		     .jsonPath().getInt("id");
		/*
		.then()
		     .statusCode(201) 
		     .body("name", equalTo("Scott"))
		     .body("courses[0]",equalTo("C"))
		     .header("Content-Type", "application/json")
		     .log().all();
		 */
	}
	
	//2) Post request body creation using using Org.JSON
	//@Test(priority = 1)
	void testPostUsingJsonLibrary() {

		JSONObject data = new JSONObject();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "8121162627");
		String courseArr[] = { "C", "C++" };
		data.put("courses", courseArr);
		
		given()
	         .contentType("application/json")
	         .body(data)
	    .when()
	         .post("http://localhost:3000/students")
	         .jsonPath().getString("id");
		
	}
	
	//3) Post request body creation using POJO class
	//@Test(priority = 1)
	void testPostUsingPOJOClass() {
		
		Pojo_PostRequest data = new Pojo_PostRequest();
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("8121162627");
		String courseArr[] = { "C", "C++" };
		data.setCourses(courseArr);
		
		given()
	         .contentType("application/json")
	         .body(data)
	    .when()
	         .post("http://localhost:3000/students")
	         .jsonPath().getString("id");
		
	}
	
	//4) Post request body creation using External JSON File
		@Test(priority = 1)
		void testPostUsingExternalJsonFile() throws FileNotFoundException {
			
			File f = new File("./src/test/resources/body.json");
			
			FileReader fr = new FileReader(f);
			
			JSONTokener jt = new JSONTokener(fr);
			
			JSONObject data = new JSONObject(jt);
			
			given()
		         .contentType("application/json")
		         .body(data)
		    .when()
		         .post("http://localhost:3000/students")
		         .jsonPath().getString("id");
			
		}
		
	// deleting student record
	@Test(priority = 2)
	void testDelete() {
		given()
		
		.when()
		     .delete("http://localhost:3000/students/" + id)
		.then()
		     .statusCode(404);
	}
}
