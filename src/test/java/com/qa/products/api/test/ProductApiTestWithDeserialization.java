package com.qa.products.api.test;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductApiTestWithDeserialization extends BaseTest{
	
	@Test
	public void getProductsTest() {
		
		Response response = restClient.get(BASE_URL_PRODUCT, "/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		
		Product[] product = JsonUtil.deserialize(response, Product[].class);
		
		System.out.println("product is" + Arrays.toString(product));
		
		for(Product e : product) {
			System.out.println("Id is :" + e.getId());
			System.out.println("Id is :" + e.getTitle());
			System.out.println("Id is :" + e.getPrice());
			System.out.println("Id is :" + e.getRating().getRate());
			System.out.println("Id is :" + e.getRating().getCount());

			System.out.println("-------------------");
		}
	}
	
	

}
