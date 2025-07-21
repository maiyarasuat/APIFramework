package com.qa.products.api.test;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductApiTestWithJsonPath extends BaseTest{
	
	
	@Test
	public void getProductTest() {
		Response response = restClient.get(BASE_URL_PRODUCT,"/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		
		List<Number> prices = JsonPathValidator.readList(response, "$[?(@.price > 100)].price");
		System.out.println(prices);
		
		List<Integer> rates = JsonPathValidator.readList(response, "$[?(@.price > 100)].rating.rate");
		System.out.println(rates);
		System.out.println("------------");

		
		//get map
		List<Map<String,Object>> jeweleryList = JsonPathValidator.readListofMap(response, "$.[?(@.category=='jewelery')].['title','price','id']");
		System.out.println(jeweleryList.size());
		System.out.println("-----------------");
		for(Map<String,Object> product : jeweleryList) {
			
			String title = (String)product.get("title");
			Number price = (Number)product.get("price");
			Integer id = (Integer)product.get("id");
			System.out.println("price:"+ " "+ price);
			System.out.println("title:"+ " " + title);
			System.out.println("id:"+ " " +id);
			
		}	
		
		System.out.println("================");
		//get single data
		
		Double minPrice =JsonPathValidator.read(response, "min($[*].price)");
		
		System.out.println("minimum price is:" + minPrice);
		
		
	}

}
