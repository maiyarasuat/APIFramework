package com.qa.api.schemavalidation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPISchemaTest extends BaseTest{

	@Test
	public void userSchemaTest() {
		
		//POST
		
		User user = User.builder()
					.name("apiname")
					.email(StringUtil.getRandomEmailID())
					.gender("male")
					.status("inactive")
					.build();
		
		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users",user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		String userId = response.jsonPath().getString("id");
		
		Response responseGET = restClient.get(BASE_URL_GOREST, "/public/v2/users/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
		Assert.assertEquals(SchemaValidator.validateSchema(responseGET, "schema/user-schema.json"), true);
		
		
		
		
		
		
		
	}
}
