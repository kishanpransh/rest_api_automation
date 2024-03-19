package com.api.helpers;

import org.json.JSONObject;

import com.api.constants.Endpoints;
import com.api.utilities.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_Textdrip_profile {
	
	public static final String BASE_URL = ConfigManager.getInstance().getString("base_url");
	
	public static Response Profile_get_user(String token) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
            
                .when()
                .post(BASE_URL + Endpoints.profile);
      

                return response;

	}

}
