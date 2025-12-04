package com.sl.RestAssuredAutomationDemo.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GetObjectWithID {
	
	@Test
	public void testGetObjectsByID() {
		// please code it here
		Response getResponse =
			given()
				.baseUri("https://api.restful-api.dev")
			.when()
				.get("/objects/10")
			.then()
				.statusCode(200)
				.body("name", equalTo("Apple iPad Mini 5th Gen"))
				.extract()
				.response();
		
		System.out.println("GET /objects/{ids} response");
		getResponse.prettyPrint();
		
	}

}
