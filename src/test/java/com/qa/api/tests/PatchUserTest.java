package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchUserTest extends BaseTest{
	
	@Test
	public void patchUserWithBuilderTest() {
		
		//1. POST: create a user
		User user = User.builder()
				.name("apiname")
				.email(StringUtil.getRandomEmailID())
				.gender("male")
				.status("active")
				.build();
		
		Response response = restClient.post("/public/v2/users", user , null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);

		//FETCH userid
		
		String userID = response.jsonPath().getString("id");
		System.out.println("user is====>" +userID);
		
		//2. GET fetch the same user using the user id
		Response ResponseGET = restClient.get("/public/v2/users/"+userID, null, null,AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(ResponseGET.statusCode(), 200);
		Assert.assertEquals(ResponseGET.jsonPath().getString("id"), userID);
		
		//update the user details using the setter
		user.setEmail(StringUtil.getRandomEmailID());
		
		//3. PATCH the same user using the same user id
		Response ResponsePATCH = restClient.patch("/public/v2/users/"+userID, user , null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(ResponsePATCH.getStatusCode(), 200);
		Assert.assertEquals(ResponsePATCH.jsonPath().getString("id"), userID);
		Assert.assertEquals(ResponsePATCH.jsonPath().getString("email"), user.getEmail());		
		
}

}
