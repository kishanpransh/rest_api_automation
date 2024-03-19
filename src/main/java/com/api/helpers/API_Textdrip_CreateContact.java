package com.api.helpers;

import org.json.JSONObject;

import com.api.constants.Endpoints;
import com.api.utilities.ConfigManager;
import com.github.javafaker.Faker;
import com.github.javafaker.FunnyName;
import com.github.javafaker.Name;
import com.api.payload.Create_contact;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_Textdrip_CreateContact {

	public static final String BASE_URL = ConfigManager.getInstance().getString("base_url");

	public static Response Create_contact(String token, String cid) {
		Faker faker = new Faker();
		String fullName = faker.name().fullName();
		;
		String email = faker.internet().emailAddress();
		String address = faker.address().fullAddress();
		JSONObject data = new JSONObject();
		data.put("name", fullName);
		data.put("phone", "15623141235");// We are passing the phone number static because the DNC and compliance hence
											// Body Passed with JsonObject.
		data.put("campaign", cid);
		data.put("email", email);
		data.put("address", address);
		data.put("birthdate", "27.12.1995");
		data.put("state", "Gujrat");
		data.put("zipcode", "422620");
		data.put("custom_field1", "Hello cf 1");
		data.put("custom_field4", "Hello cf 4");
		data.put("quote", "Happy life");

		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.headers("Authorization", "Bearer " + token).body(data).body(data.toString())

				.when().post(BASE_URL + Endpoints.Create_contact);

		return response;

	}

	public static Response Update_contact(String token) {
		JSONObject data = new JSONObject();
		data.put("phone", "15623141235");
		data.put("name", "Update contact");
		data.put("email", "test@textdrip.com");
		data.put("address", "Test");
		data.put("state", "Test");
		data.put("zipcode", "123456");
		data.put("birthdate", "2000-02-03");
		data.put("custom_field1", "C F 1");
		data.put("custom_field2", "C F 2");
		data.put("custom_field3", "C F 3");
		data.put("custom_field4", "C F 4");
		data.put("custom_field5", "C F 5");
		data.put("quote", "Test quote");
		data.put("opted_out", 0);
		JSONObject tag = new JSONObject();
		tag.put("value", "Tag API");
		data.append("tags", tag);

		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.headers("Authorization", "Bearer " + token).body(data).body(data.toString())

				.when().post(BASE_URL + Endpoints.contact_update);

		return response;

	}
}
