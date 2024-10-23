package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest {
	@Test
	public void deleteUserWithBuilderTest() {
		
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
		
		
		//3. DELETE the same user using the same user id
		Response ResponseDelete = restClient.delete("/public/v2/users/"+userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(ResponseDelete.getStatusCode(), 204);
		
		//4. Recheck and fetch the user with the same user id
		Response ResponseGETAfterDelete = restClient.get("/public/v2/users/"+userID, null, null,AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(ResponseGETAfterDelete.statusCode(), 404);
		Assert.assertEquals(ResponseGETAfterDelete.jsonPath().getString("message"), "Resource not found");
		
		
}

}
