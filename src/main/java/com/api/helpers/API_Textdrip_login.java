package com.api.helpers;

import org.json.JSONObject;

import com.api.constants.Endpoints;

import com.api.utilities.ConfigManager;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_Textdrip_login {
	public static final String BASE_URL = ConfigManager.getInstance().getString("base_url");// From this we get value
																							// from the Config file.
	public static final String email = ConfigManager.getInstance().getString("email");// From this we get value from the
																						// Config file.
	public static final String password = ConfigManager.getInstance().getString("password");// From this we get value
																							// from the Config file.

	public static Response Login_user() {

		JSONObject data = new JSONObject(); // Json object used to pass the body in API
		data.put("email", email);
		data.put("password", password);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(data.toString())
                .when()
                .post(BASE_URL + Endpoints.login);


        return response;

	}

}
