package com.api.helpers;

import org.json.JSONObject;

import com.api.constants.Endpoints;
import com.api.utilities.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_Textdrip_Add_CampaignSendMsgScheduleAssignCampaign {
public static final String BASE_URL = ConfigManager.getInstance().getString("base_url");
	
	public static Response Get_campaigns(String token, String id, String cid) {
		JSONObject data= new JSONObject();

		String []ids ={id};
		data.put("contact_id", ids);  
		data.put("campaign_id", cid);
		data.put("already_scheduled_remove", false);
		
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .body(data)
                .body(data.toString())
            
                .when()
                .post(BASE_URL + Endpoints.add_campaign);
                return response;

	}

	public static Response Send_message(String token) {
		JSONObject data= new JSONObject();
		data.put("receiver", "15623141235");  
		data.put("message", "Hello, Contact Created from the API.");		
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .body(data)
                .body(data.toString())
                .when()
                .post(BASE_URL + Endpoints.send_message);
                return response;

	}
	
	public static Response add_schedule_message(String token) {
		JSONObject data= new JSONObject();
		data.put("recipient", "15623141235");  
		data.put("datetime", "2025-01-01 05:10:00");
		data.put("body", "Hello, I'm Scheduling the Meeting with you!");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .body(data)
                .body(data.toString())
                .when()
                .post(BASE_URL + Endpoints.add_schedule);
                return response;

	}
	public static Response assign_campaign_contact(String token,String cid) {
		JSONObject data= new JSONObject();
		data.put("phone", "15623141235");  
		data.put("campaign", cid);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .body(data)
                .body(data.toString())
                .when()
                .post(BASE_URL + Endpoints.campaign_assign_to_contact);
                return response;

	}
	
}
