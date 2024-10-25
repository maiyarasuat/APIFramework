package com.qa.api.amadeus.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmadeusAPITest extends BaseTest {
	
	@Test
	public void getFlightDetailsTest() {
		
		Map<String, String> queryMaps = Map.of("origin" , "PAR" , "maxPrice" , "200");
		Response response = restClient.get(BASE_URL_AMADEUS, "/v1/shopping/flight-destinations", queryMaps, null, AuthType.OAUTH2, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
	}

}
