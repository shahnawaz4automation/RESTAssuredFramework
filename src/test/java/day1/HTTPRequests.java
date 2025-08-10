package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class HTTPRequests {

	int id = 0;
	@Test(priority = 1)
	void getUsers() {
		        given()

				.when()
				     .get("https://reqres.in/api/users?page=2")

				.then()
				     .statusCode(200)
				     .body("page", equalTo(2))
				     .log().all();
	}

	@Test(priority = 2)
	void createUser() {
		
		HashMap data = new HashMap();
		data.put("name", "sam");
		data.put("job", "bahadur");
		
		id = given()
		     .contentType("application/json")
		     .header("x-api-key", "reqres-free-v1")
		     .body(data)
		.when()
		     .post("https://reqres.in/api/users")
		     .jsonPath().getInt("id");
		/*.then()
		     .statusCode(201)
		     .log().all();*/
		//System.out.println(id);
	}
	
	@Test(priority = 3,dependsOnMethods={"createUser"})
	void updateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "sam");
		data.put("job", "trainer");
		
		
		given()
		     .contentType("application/json")
		     .header("x-api-key", "reqres-free-v1")
		     .body(data)
		.when()
		     .put("https://reqres.in/api/users/"+id)
		.then()
		     .statusCode(200)
		     .log().all();
		
	}
	@Test(priority = 4,dependsOnMethods = {"updateUser"})
	void deleteUser()
	{
		given()
		     .header("x-api-key", "reqres-free-v1")
		.when()
		     .delete("https://reqres.in/api/users/"+id)
		.then()
		     .statusCode(204)
		     .log().all();
	}
}
