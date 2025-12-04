package com.sl.RestAssuredAutomationDemo.tests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PostObjectGetDeleteAfterPostMethod {
	
	@Test
	public void testGetObjectsByID() {
		// please code it here
		Response getResponse =
			given()
				.baseUri("https://api.restful-api.dev")
			.when()
				.delete("/objects/ff8081819782e69e019ae83a4e477863")
			.then()
				.statusCode(200)
				.extract()
				.response();
		
		System.out.println("Delete /objects/{id} response");
		getResponse.prettyPrint();
		
	}

}
