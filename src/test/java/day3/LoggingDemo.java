package day3;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import groovy.util.logging.Log;

public class LoggingDemo {
	    @Test(priority=1)
		void testHeaders() {
				given()
				
				.when()
				     .get("https://www.google.com/")
				.then()
				     //.log().body()
				     //.log().cookies();
				     //.log().headers()
				     .log().all();
		}
}
