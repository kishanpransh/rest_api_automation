package com.api.helpers;

import java.util.Date;

import org.json.JSONObject;
import com.api.constants.Endpoints;
import com.api.utilities.ConfigManager;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_Textdrip_createContactWithTag {
	public static final String BASE_URL = ConfigManager.getInstance().getString("base_url");

	public static Response Create_contact(String token, String cid) {
		Faker faker = new Faker();
		String fullName = faker.name().fullName();
		String email = faker.internet().emailAddress();
		String address = faker.address().fullAddress();
		Date bday = faker.date().birthday();
		String state = faker.address().state();
		String zip = faker.address().zipCode();
		
		JSONObject data = new JSONObject();
		data.put("name", fullName);
		data.put("phone", "17473341694");// We are passing the phone number static because the DNC and compliance hence
										// Body Passed with JsonObject.
		data.put("campaign", cid);
		data.put("email", email);
		data.put("address", address);
		data.put("birthdate", bday);
		data.put("state", state);
		data.put("zipcode", zip);
		data.put("custom_field1", "Hello cf 1");
		data.put("custom_field2", "Hello cf 2");
		data.put("quote", "Happy life");
        // Create tags array
		JSONObject tag = new JSONObject();
		tag.put("value", "Tag API");
		data.append("tags", tag);


		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.headers("Authorization", "Bearer " + token).body(data).body(data.toString())

				.when().post(BASE_URL + Endpoints.contact_create_tag);

		return response;

	}
}