package com.qa.api.base;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;


public class BaseTest {
	
	protected RestClient restClient;
	
	@Parameters({"baseUrl"})
	@BeforeTest
	public void setup(@Optional String baseUrl){
		
		if(baseUrl != null) {
			ConfigManager.set("baseUrl", baseUrl);
		}
		
		restClient = new RestClient();
	}

}
