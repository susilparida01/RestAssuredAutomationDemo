package com.sl.RestAssuredAutomationDemo.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PostObjectGetAfterPostMethod {
	
	@Test
	public void testGetObjectsByID() {
		// please code it here
		Response getResponse =
			given()
				.baseUri("https://api.restful-api.dev")
			.when()
				.get("/objects/ff8081819782e69e019ae830ce117823")
			.then()
				.statusCode(200)
				.body("name", equalTo("Apple MacBook Pro 19"))
				.extract()
				.response();
		
		System.out.println("GET /objects/{id} response");
		getResponse.prettyPrint();
		
	}

}
