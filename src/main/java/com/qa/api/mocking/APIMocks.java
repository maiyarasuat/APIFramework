package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class APIMocks {
	
	public static void getDummyUser() {
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("content-type", "application/json")
						.withBody("{\n"
								+ "    \"name\": \"Tom\"\n"
								+ "}")
						
						)
				);	
	}
	
	public static void getDummyUserWithJson() {
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("content-type", "application/json")
						.withBodyFile("user.json")
						
						)
				);	
	}
	
	
	
	
	
	

}
