package com.qa.api.mocking;

import com.github.tomakehurst.wiremock.WireMockServer;

public class WireMockSetup {

	private static WireMockServer server;
	
	public static void createMockServer() {
		server = new WireMockServer();
	}

	public static void stopMockServer() {

	}

}
