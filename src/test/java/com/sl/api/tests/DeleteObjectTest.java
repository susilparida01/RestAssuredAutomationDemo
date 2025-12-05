package com.sl.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DeleteObjectTest extends BaseApiTest{
	
	@Test
	public void verifyDeleteObjectTest() {
		
		Response getResponse =
				given()
				.when()
					.delete("/objects/ff8081819782e69e019aedb1edaa06f3")
				.then()
					.statusCode(200)
					.extract()
					.response();

			System.out.println("Delete /objects/{id} response");
			getResponse.prettyPrint();		
	}

}
