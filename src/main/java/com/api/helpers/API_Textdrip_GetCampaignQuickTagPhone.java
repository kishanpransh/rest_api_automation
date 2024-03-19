package com.api.helpers;

import com.api.constants.Endpoints;
import com.api.utilities.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_Textdrip_GetCampaignQuickTagPhone {
	
	public static final String BASE_URL = ConfigManager.getInstance().getString("base_url");
	
	public static Response Get_campaigns(String token) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
            
                .when()
                .post(BASE_URL + Endpoints.get_campaign);
      

                return response;

	}
	public static Response Get_tags(String token) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
            
                .when()
                .post(BASE_URL + Endpoints.get_tags);
      

                return response;

	}

	public static Response Get_QuickResponses(String token) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
            
                .when()
                .post(BASE_URL + Endpoints.get_QuickResponses);
      

                return response;

	}
	
	public static Response Get_phone_list(String token) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
            
                .when()
                .post(BASE_URL + Endpoints.get_phone_list);
      

                return response;

	}
}
