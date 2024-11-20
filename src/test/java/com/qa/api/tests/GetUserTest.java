package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest {
	
	
	@Test
	public void getAllUsersTest() {
		
		Map<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("name", "Maiyarasu");
		queryParams.put("status", "inactive");
		
		Response response = restClient.get(BASE_URL_GOREST,"/public/v2/users", queryParams, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void getSingleUsersTest() {
		
		Response response = restClient.get(BASE_URL_GOREST,"/public/v2/users/7516306", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
