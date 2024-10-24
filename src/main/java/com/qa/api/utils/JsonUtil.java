package com.qa.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class JsonUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static <T> T deserialize(Response response, Class<T> targetclass) {
		
		try {
			return objectMapper.readValue(response.getBody().asString(), targetclass);
		} catch (Exception e) {
			throw new RuntimeException("deserialization is failed" + targetclass.getClass());
		} 
	}

}
