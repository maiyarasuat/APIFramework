package com.qa.contacts.api.tests;

import org.testng.annotations.BeforeMethod;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.ContactsCredentials;

import io.restassured.http.ContentType;

public class ContactsAPITest extends BaseTest{
	
	@BeforeMethod
	public void getToken() {
		
		ContactsCredentials creds = ContactsCredentials.builder()
														.email("maiyarasu85@gmail.com")
														.password("gK@.kjshx9SRtT9")
														.build();
						
		
		restClient.post("/users/login", creds, null, null, AuthType.NO_AUTH, ContentType.JSON);
		
		
	}

}
