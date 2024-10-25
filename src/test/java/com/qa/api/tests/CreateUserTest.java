package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest{
	
	@Test
	public void createUsersTest() {
		
		User user = new User(null, "john", StringUtil.getRandomEmailID(), "male", "active");
		
		Response response = restClient.post(BASE_URL_GOREST,"/public/v2/users", user , null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test
	public void createUsersWithBuilderTest() {
		
		//POST
		User user = User.builder()
				.name("apiname")
				.email(StringUtil.getRandomEmailID())
				.gender("male")
				.status("active")
				.build();
		
		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", user , null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);

		//FETCH
		
		String userID = response.jsonPath().getString("id");
		System.out.println("user is====>" +userID);
		
		//GET
		Response getResponse = restClient.get(BASE_URL_GOREST, "/public/v2/users/"+userID, null, null,AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(getResponse.statusCode(), 200);
		Assert.assertEquals(getResponse.jsonPath().getString("id"), userID);
		Assert.assertEquals(getResponse.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(getResponse.jsonPath().getString("email"), user.getEmail());
}
	
	@Test
	public void createUsersWithJsonFileTest() {
		
		File userJsonFile = new File("./src/test/resources/jsons/user.json");
		
		Response response = restClient.post(BASE_URL_GOREST,"/public/v2/users", userJsonFile , null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}

}
