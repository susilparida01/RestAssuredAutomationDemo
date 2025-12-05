package com.sl.RestAssuredAutomationDemo.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostObject {

	@Test
	public void testPostObject() {
		// please code it here
		String createPayload = """
			{
			    "name": "Apple MacBook Pro 19",
			    "data": {
			        "year": 2025,
			        "price": 22222.22,
			        "CPU model": "Intel Core i13",
			        "Hard disk size": "2 TB"
			    }
			}
			""";

		Response postResponse =
			given()
				.baseUri("https://api.restful-api.dev")
				.contentType(ContentType.JSON)
				.body(createPayload)
			.when()
				.post("/objects")
			.then()
				.statusCode(200)
				.header("Content-Type", containsString("application/json"))
				.body("name", equalTo("Apple MacBook Pro 19"))
				.body("data.price", equalTo(22222.22f))
				.extract()
				.response();

		System.out.println("POST /objects response");
		postResponse.prettyPrint();

	}

}
