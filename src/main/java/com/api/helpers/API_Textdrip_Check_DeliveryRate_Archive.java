package com.api.helpers;

import org.json.JSONObject;

import com.api.constants.Endpoints;
import com.api.utilities.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_Textdrip_Check_DeliveryRate_Archive {
	public static final String BASE_URL = ConfigManager.getInstance().getString("base_url");

	public static Response get_delivery_rate(String token) {
		JSONObject data = new JSONObject(); // Json object used to pass the body in API
		
		data.put("date", "2024-01-04");
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.headers("Authorization", "Bearer " + token)
				.body(data)
				.body(data.toString())
				.when()
				.post(BASE_URL + Endpoints.get_delivery_rate);
		return response;
	}

	public static Response archive_contact(String token, String id) {
		
	
		JSONObject data = new JSONObject(); // Json object used to pass the body in API
		String []ids ={id};
		data.put("ids", ids);        
		Response response = RestAssured.given()
				.contentType(ContentType.JSON).accept(ContentType.JSON)
				.headers("Authorization", "Bearer " + token)
				.body(data)
				.body(data.toString())
				.when()
				.post(BASE_URL + Endpoints.archive_chat);
		return response;   
	}
}