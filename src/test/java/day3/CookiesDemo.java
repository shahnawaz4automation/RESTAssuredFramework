package day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	//@Test(priority=1)
	void testCookies() {
		given()
		
		.when()
		     .get("https://www.google.com/")
		.then()
		     .cookie("AEC")
		     .log().all();
	}
	
	@Test(priority=2)
	void getCookiesInfo() {
		Response res = given()
				
		               .when()
		                    .get("https://www.google.com/");
		
		/*String cookie_value = res.getCookie("AEC");
		System.out.println("Value for cookie is: " + cookie_value);*/
		
		Map<String, String> cookies_values =  res.getCookies();
		
		for(String k: cookies_values.keySet()) {
			String cookie_value = res.getCookie(k);
			System.out.println(k+":  "+cookie_value);
		}
	}
}
