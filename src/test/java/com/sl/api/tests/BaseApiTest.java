package com.sl.api.tests;

import org.testng.annotations.BeforeClass;
import org.testng.log4testng.Logger;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BaseApiTest {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	@BeforeClass
	public void setup() {
		// Base URI for Restful API
		RestAssured.baseURI = "https://api.restful-api.dev";
		
		// Log all requests and responses to console
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
		
		log.info("initialized RestAssured with Base URI: " + RestAssured.baseURI);
	}

}
