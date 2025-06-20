package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest{
	
//	@DataProvider
//	public Object[][] getUserTestData() {
//		
//		return new Object[][] {
//			
//			{"naveen","male","active"},
//			{"elisa","female","inactive"},
//			{"veena","female","active"},
//			{"demi","female","active"},
//			
//		};
//	}
	
	@DataProvider
	public Object[][] getUserData() {
		
		return new Object[][] {
			
			{"naveen", "male", "inactive"},
			{"kanchan", "male", "active"},
			{"shubham", "male", "active"},
			{"rekha", "female", "active"},
		};
		
	}
	
	@Test(dataProvider = "getUserData")
	public void createUsersTest(String name, String gender, String status) {
		
		User user = new User(null, name, StringUtil.getRandomEmailID(), gender, status);
		
		Response response = restClient.post(BASE_URL_GOREST,"/public/v2/users", user , null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test(dataProvider = "getUserData")
	public void createUsersWithBuilderTest(String name, String gender, String status) {
		
		//POST
		User user = User.builder()
				.name(name)
				.email(StringUtil.getRandomEmailID())
				.gender(gender)
				.status(status)
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
