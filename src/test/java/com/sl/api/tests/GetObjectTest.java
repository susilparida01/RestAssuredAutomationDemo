package com.sl.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetObjectTest extends BaseApiTest {
	
	@Test(groups = {"smoke","regression"})
	public void verifyGetObject() {
		
		Response getResponse =
				given()
					.accept(ContentType.JSON)
				.when()
					.get("/objects")
				.then()
					.assertThat()
					// Basic validation
					.statusCode(200)
					.header("Content-Type", containsString("application/json"))

					// Response time check (example threshold: 5 seconds
					.time(lessThan(5000L))

					// Validate returned field
					.extract()
					.response();

			System.out.println("GET /objects/"+" response");
			getResponse.prettyPrint();
	}

}
