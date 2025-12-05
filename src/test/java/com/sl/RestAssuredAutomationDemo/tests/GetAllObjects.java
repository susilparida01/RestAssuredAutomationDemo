package com.sl.RestAssuredAutomationDemo.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GetAllObjects {

	@Test
	public void testGetAllObjectsMethod() {
		// please code it here
		Response getResponse =
			given()
				.baseUri("https://api.restful-api.dev")
			.when()
				.get("/objects")
			.then()
				.statusCode(200)
				//.body("name", equalTo("Apple MacBook Pro 16"))
				.extract()
				.response();

		System.out.println("GET /objects/{id} response");
		getResponse.prettyPrint();

	}

}
