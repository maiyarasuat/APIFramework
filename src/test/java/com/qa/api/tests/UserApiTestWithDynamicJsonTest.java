package com.qa.api.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qa.api.base.BaseTest;
import com.qa.api.utils.StringUtil;

public class UserApiTestWithDynamicJsonTest extends BaseTest{
	
	@Test
	public void createUserWithJsonFileTest() {
		
		String jsonFilePath = "src/test/resources/jsons/user.json";
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode userNode = mapper.readTree(Files.readAllBytes(Paths.get(jsonFilePath)));
			
			//create a unique email id:
			String uniqueEmail = StringUtil.getRandomEmailID();
			
			//update the email id:
			ObjectNode obj = (ObjectNode)userNode;
			obj.put("email", uniqueEmail);
			obj.put("gender", "female");

			
			//convert json node into json string
			String updatedJsonString = mapper.writeValueAsString(userNode);
			System.out.println("updated json string===>" + updatedJsonString);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
	}

}
