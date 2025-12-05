package com.sl.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateObjectTest extends BaseApiTest{
	
	@Test
	public void verifyUpdateObjectTest() {
		// please code it here
		String updatePayload = """
			{   "name": "Apple MacBook Pro 19",
			    "data": {
			        "year": 2026,
			        "price": 33333.33,
			        "CPU model": "Intel Core i13",
			        "Hard disk size": "2 TB"
			    }
			}
			""";

		Response updateResponse =
			given()
				.contentType(ContentType.JSON)
				.body(updatePayload)
			.when()
				.put("/objects/ff8081819782e69e019aedade59606ed")
			.then()
				.statusCode(200)
				.header("Content-Type", containsString("application/json"))
				.body("name", equalTo("Apple MacBook Pro 19"))
				.body("data.year", equalTo(2026))
				.body("data.price", equalTo(33333.33f))
				.extract()
				.response();

		System.out.println("PUT /objects response");
		updateResponse.prettyPrint();

	}	

}
