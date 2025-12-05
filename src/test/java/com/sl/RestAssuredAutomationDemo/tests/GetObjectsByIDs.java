package com.sl.RestAssuredAutomationDemo.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GetObjectsByIDs {

	@Test
	public void testGetObjectsByID() {
		// please code it here
		Response getResponse =
			given()
				.baseUri("https://api.restful-api.dev")
			.when()
				.get("/objects?id=3&id=5&id=10")
			.then()
				.statusCode(200)
				//.body("name", equalTo("Apple MacBook Pro 16"))
				.extract()
				.response();

		System.out.println("GET /objects/{ids} response");
		getResponse.prettyPrint();

	}

}
