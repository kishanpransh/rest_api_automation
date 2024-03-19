package com.api.helpers;

import org.json.JSONObject;

import com.api.constants.Endpoints;
import com.api.utilities.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_Textdrip_GetContactConverChatDripMsg {

	public static final String BASE_URL = ConfigManager.getInstance().getString("base_url");
	public static Response get_contact_details(String token) {
		JSONObject data = new JSONObject(); // Json object used to pass the body in API
		data.put("phone", "15623141235");
		 Response response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .accept(ContentType.JSON)
	                .headers("Authorization", "Bearer " + token)
	                .body(data)
	                .body(data.toString())
	                .when()
	                .post(BASE_URL + Endpoints.get_contact_details);
        return response;
	}
	
	public static Response get_conversation(String token) {
		JSONObject data = new JSONObject(); // Json object used to pass the body in API
		data.put("search", "15623141235");
		data.put("page", "1");
	
		 Response response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .accept(ContentType.JSON)
	                .headers("Authorization", "Bearer " + token)
	                .body(data)
	                .body(data.toString())
	                .when()
	                .post(BASE_URL + Endpoints.get_conversations);
		
		
		return response;
	}
	
	
	public static Response get_chat(String token) {
		JSONObject data = new JSONObject(); // Json object used to pass the body in API
		data.put("phone", "15623141235");
		data.put("page", "1");
	
		 Response response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .accept(ContentType.JSON)
	                .headers("Authorization", "Bearer " + token)
	                .body(data)
	                .body(data.toString())
	                .when()
	                .post(BASE_URL + Endpoints.get_chat);
		
		
		return response;
	}
	
	
	public static Response get_drip_messages(String token,String cid) {
		JSONObject data = new JSONObject(); // Json object used to pass the body in API
		data.put("campaign_id", cid);
	
	
		 Response response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .accept(ContentType.JSON)
	                .headers("Authorization", "Bearer " + token)
	                .body(data)
	                .body(data.toString())
	                .when()
	                .post(BASE_URL + Endpoints.get_campaign_drip_msg);
		
		
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
