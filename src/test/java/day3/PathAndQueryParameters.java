package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {

	//https://reqres.in/api/users?page=2&id=7
	
	@Test
	void testPathAndQueryParameters() {
		
		// prerequisites
		given() // prerequisites
		    .header("x-api-key", "reqres-free-v1")
		    .pathParam("myUsers", "users") //path parameters - we give it as key & value. we need to specify key in the url
		    .queryParam("page", 2)         //query parameters - will go by default in the url, we need not specify
		    .queryParam("id", 2)           //query parameters
		//speify the request and url
		.when()
		    .get("https://reqres.in/api/{myUsers}")		
		//validations on the response
		.then()
		    .statusCode(200)
		    .log().all();
		
	}
}
