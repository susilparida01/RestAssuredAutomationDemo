package com.sl.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateObjectTest extends BaseApiTest{
	
	@Test
	public void verifyCreateObjects() {
		
		//1. POST - Create an object
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
				.contentType(ContentType.JSON)
				.body(createPayload)
			.when()
				.post("/objects")
			.then()
				// Basic validation
				.assertThat()
				.statusCode(anyOf(is(200),is(201)))
				.header("Content-Type", containsString("application/json"))

				// Response time check (example threshold: 5 seconds
				.time(lessThan(5000L))

				// Validate returned field
				.body("name", equalTo("Apple MacBook Pro 19"))
				.body("data.price", equalTo(22222.22f))   // float comparison
				.body("name", equalTo("Apple MacBook Pro 19"))
				.extract()
				.response();

		System.out.println("POST /objects response: ");
		postResponse.prettyPrint();


		// Extract Created ID
		//String createdId = postResponse.jsonPath().getString("id");
		//System.out.println("Object ID: " + createdId);
		//Assert.assertNotNull(createdId, "Created object ID should not be null");
		//System.out.println("#####***************##############################");		
		
	}

}
