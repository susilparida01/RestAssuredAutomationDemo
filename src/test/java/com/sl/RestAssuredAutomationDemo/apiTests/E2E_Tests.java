package com.sl.RestAssuredAutomationDemo.apiTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class E2E_Tests {
	
	@BeforeClass
	public void setup() {
		// Base URI for restful-api.dev
		RestAssured.baseURI = "https://api.restful-api.dev";
		
		// Enable request/response Logging
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
	}
	
	/*
	 * End-to-end CRUD flow:
	 * 1) POST -> Create Object
	 * 2) GET  -> Get single object by id and validate
	 * 3) PUT  -> Update object and validate
	 * 4) DELETE -> Delete object and validate (204)
	 * 
	 *  This single class will demonstrate all request type and print response
	 */
	
	@Test
	public void e2e_create_get_update_delete_object() {
		
		
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
		String createdId = postResponse.jsonPath().getString("id");
		System.out.println("Object ID: " + createdId);
		Assert.assertNotNull(createdId, "Created object ID should not be null");
		System.out.println("#####***************##############################");
		
		
		
		//2. GET - fetch the created object by ID and validate
		Response getResponse =
			given()
				.accept(ContentType.JSON)
			.when()
				.get("/objects/"+createdId)
			.then()
				.assertThat()
				// Basic validation
				.statusCode(200)
				.header("Content-Type", containsString("application/json"))
				
				// Response time check (example threshold: 5 seconds
				.time(lessThan(5000L))
				
				// Validate returned field
				.body("name", equalTo("Apple MacBook Pro 19"))
				.extract()
				.response();
		
		System.out.println("GET /objects/"+createdId+" response");
		getResponse.prettyPrint();		
		
		System.out.println("#####***************##############################");
		
		//3. PUT - Update the created object and validate
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
			
		Response putResponse =
			given()
				.contentType(ContentType.JSON)
				.body(updatePayload)
			.when()
				.put("/objects/"+createdId)
			.then()
				.assertThat()
				.statusCode(200)
				.header("Content-Type", containsString("application/json"))
				.body("name", equalTo("Apple MacBook Pro 19"))
				.body("data.year", equalTo(2026))
				.body("data.price", equalTo(33333.33f))
				.extract()
				.response();
		
		System.out.println("PUT /objects/"+createdId+" response");
		putResponse.prettyPrint();
		
		System.out.println("#####***************##############################");
		
		//4. DELETE - Delete the object and validate 204 No Content
		given()
		.when()
			.delete("/objects/"+createdId)
		.then()
			.assertThat()
			.statusCode(anyOf(is(200),is(404)))
			// Response time check (example threshold: 5 seconds
			.time(lessThan(5000L));
		
		System.out.println("Delete /objects/"+createdId+" response");
		System.out.println("#####***************##############################");
	
	}

}
