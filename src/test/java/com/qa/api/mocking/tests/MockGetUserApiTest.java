package com.qa.api.mocking.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockGetUserApiTest extends BaseTest{
	
	@Test
	public void getDummyUserTest() {
		
		APIMocks.getDummyUser();
		
		Response response = restClient.get(BASE_URL_LOCAL_HOST, "/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
		
		response.then()
					.assertThat()
						.statusCode(200)
							.body("name",equalTo("Tom"));
		
		
	}
	
	@Test
	public void getDummyUserWithJsonFileTest() {
		
		APIMocks.getDummyUserWithJson();
		
		Response response = restClient.get(BASE_URL_LOCAL_HOST, "/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
		
		response.then()
					.assertThat()
						.statusCode(200)
							.body("name",equalTo("api"));
		
		
	}

}
