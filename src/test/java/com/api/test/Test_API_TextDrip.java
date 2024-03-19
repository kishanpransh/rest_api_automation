package com.api.test;

import static org.hamcrest.Matchers.equalTo;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.helpers.API_Textdrip_Check_DeliveryRate_Archive;
import com.api.helpers.API_Textdrip_CreateContact;
import com.api.helpers.API_Textdrip_GetCampaignQuickTagPhone;
import com.api.helpers.API_Textdrip_GetContactConverChatDripMsg;
import com.api.helpers.API_Textdrip_createContactWithTag;
import com.api.helpers.API_Textdrip_login;
import com.api.helpers.API_Textdrip_profile;
import com.api.helpers.API_Textdrip_Add_CampaignSendMsgScheduleAssignCampaign;

import com.api.payload.Create_contact;

import com.github.javafaker.Faker;




import io.restassured.response.Response;

public class Test_API_TextDrip {

	//This is the general Class in which all the code is enlisted.
	public static String token;
	public static String cid;
	public static String id;
	Faker faker;
	Create_contact userPayload;
	public Logger logger;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new Create_contact();

		userPayload.setName(faker.name().firstName());
		userPayload.setPhone("15623141235");
		userPayload.setCampaign(cid);

		logger = LogManager.getLogger(this.getClass());
		logger.debug("This is for test");
		logger.info("This is an Info Message!");
		logger.error("And here comes the Error Message!");

	}

	@BeforeMethod
	public void before_method(Method method) {
		
		System.out.println("TEST STARTED # " +"Priority:-  "+ method.getAnnotation(Test.class).priority() +"."+ method.getAnnotation(Test.class).description());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}


	@Test(priority = 1, description = "In this test we are verifying to user login API.")
	public void Login_user_API() {
		logger.info("*************** Login the user *******************************");
		Response response = API_Textdrip_login.Login_user();
		response.then().body("success", equalTo(true)).body("message", equalTo("Login successfully"));
		// .log().all();
		token = response.getBody().jsonPath().getString("token");
		System.out.println("The bearer token is :- " + token);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User Login Successfully. *******************************");
	}

	@Test(priority = 2, description = "In this test we are verifying the get profile details.")
	public void Get_Profile() {
		logger.info("*************** Get the user profile details.*******************************");

		Response response = API_Textdrip_profile.Profile_get_user(token);
		response.then();

		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User profile details get Successfully. *******************************");
	}

	@Test(priority = 3, description = "In this test we are verifying the get campaigns details.")
	public void Get_campaign() {
		logger.info("***************  Get the user drip Campaign id *******************************");

		Response response = API_Textdrip_GetCampaignQuickTagPhone.Get_campaigns(token);
		response.then();
		// .log().all();
		// .body("status", equalTo(true));
		cid = response.getBody().jsonPath().getString("data[1].id"); // here we extract the id of the campaign and then
																		// the we access it globaly.
		System.out.println("The id of campaign is :- " + cid);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User drips campaign id's get Successfully. *******************************");
	}

	@Test(priority = 4, description = "In this test we are verifying the tags details.")
	public void Get_Tag() {
		logger.info("***************  Get the user tags details. *******************************");

		Response response = API_Textdrip_GetCampaignQuickTagPhone.Get_tags(token);
		response.then()
				// .log().all()
				.body("status", equalTo(true));
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User tags details get Successfully. *******************************");
	}

	@Test(priority = 5, description = "In this test we are verifying the Quick Responses details.")
	public void Get_QuickResponse() {
		logger.info("***************  Get the user Quick Responses details. *******************************");

		Response response = API_Textdrip_GetCampaignQuickTagPhone.Get_QuickResponses(token);
		response.then();
		// .log().all()
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User Quick responses details get Successfully. *******************************");
	}

	@Test(priority = 6, description = "In this test we are verifying the phone list details.")
	public void Get_PhoneList() {
		logger.info("***************  Get the user phone list details. *******************************");

		Response response = API_Textdrip_GetCampaignQuickTagPhone.Get_phone_list(token);
		response.then().body("status", equalTo(true));
		// .log().all()
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(
				"*************** User Quick responses phone list get Successfully. *******************************");
	}

	@Test(priority = 7, description = "In this test we are verifying the create contact.")
	public void Create_contact() {
		logger.info("***************  Create the contact API. *******************************");
		Response response = API_Textdrip_CreateContact.Create_contact(token, cid);
		response.then().body("status", equalTo(true)).body("message", equalTo("Contact saved successfully."));
		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User Create the contact from API Successfully. *******************************");
	}

	@Test(priority = 8, description = "In this test we are verifying the get contact details.")
	public void get_contact_details() {
		logger.info("***************  Create the contact API. *******************************");
		Response response = API_Textdrip_GetContactConverChatDripMsg.get_contact_details(token);
		response.then().body("status", equalTo(true));
		// .log().all();
		id = response.getBody().jsonPath().getString("contact.id");
		System.out.println("The id of the created contact is the:- " + id);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User get the contact details Successfully. *******************************");
	}

	@Test(priority = 9, description = "In this test we are verifying the get conversation details.")
	public void get_conversations() {
		logger.info("***************  get the conversation of API. *******************************");
		Response response = API_Textdrip_GetContactConverChatDripMsg.get_conversation(token);
		response.then();
		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(
				"*************** User get the contact conversation details Successfully. *******************************");
	}

	@Test(priority = 10, description = "In this test we are verifying the get chat details.")
	public void get_chat() {
		logger.info("***************  get the chat of API. *******************************");
		Response response = API_Textdrip_GetContactConverChatDripMsg.get_chat(token);
		response.then();
		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User get the contact chat details Successfully. *******************************");
	}

	@Test(priority = 11, description = "In this test we are verifying the get selected campaign all drip messages.")
	public void get_drip_messages() {
		logger.info("***************  get the drip messages of API. *******************************");
		Response response = API_Textdrip_GetContactConverChatDripMsg.get_drip_messages(token, cid);
		response.then().body("status", equalTo(true));
		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(
				"*************** User get the drip messagess details Successfully. *******************************");
	}

	@Test(priority = 12, description = "In this test we are verifying the get delivery report of selected date.")
	public void get_delivery_rate() {
		logger.info("***************  get the drip messages of API. *******************************");
		Response response = API_Textdrip_Check_DeliveryRate_Archive.get_delivery_rate(token);
		response.then().body("status", equalTo(true));
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(
				"*************** User get the drip messagess details Successfully. *******************************");
	}

	@Test(priority = 13, description = "In this test we are verifying the archive contact.")
	public void archive_contact() {
		logger.info("***************  Checking the contact archive API. *******************************");
		Response response = API_Textdrip_Check_DeliveryRate_Archive.archive_contact(token, id);
		response.then().body("status", equalTo(true)).body("message", equalTo("Chat archive successfully."));
		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User archived the contact Successfully. *******************************");
	}

	@Test(priority = 14, description = "In this test we are verifying the add campaign to contact API.")
	public void add_campaign() {
		logger.info("***************  Checking the add campaign to contact API. *******************************");
		Response response = API_Textdrip_Add_CampaignSendMsgScheduleAssignCampaign.Get_campaigns(token, id, cid);
		response.then().body("status", equalTo(true)).body("message", equalTo("Campaign added successfully."));
		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(
				"*************** User added drip campaign to the contact Successfully. *******************************");
	}

	@Test(priority = 15, description = "In this test we are verifying the send message to contact API.")
	public void send_message() {
		logger.info("***************  Checking the send message to contact API. *******************************");
		Response response = API_Textdrip_Add_CampaignSendMsgScheduleAssignCampaign.Send_message(token);
		response.then().body("status", equalTo(true));
		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User send message to the contact Successfully. *******************************");
	}

	@Test(priority = 16, description = "In this test we are verifying the add schedule message to contact API.")
	public void add_schedule() {
		logger.info(
				"***************  Checking the add schedule message to contact API. *******************************");
		Response response = API_Textdrip_Add_CampaignSendMsgScheduleAssignCampaign.add_schedule_message(token);
		response.then().body("status", equalTo(true)).body("message", equalTo("Schedule saved successfully."));

		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(
				"*************** User add schedule message to the contact Successfully. *******************************");
	}

	@Test(priority = 17, description = "In this test we are verifying the assign campaign to contact API.")
	public void assign_campaign_contact() {
		logger.info("***************  Checking the assign campaign to contact API. *******************************");
		Response response = API_Textdrip_Add_CampaignSendMsgScheduleAssignCampaign.assign_campaign_contact(token, cid);
		response.then().body("status", equalTo(true)).body("message",
				equalTo("Contact new campaign assigned successfully."));

		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(
				"*************** User assign campaign  to the contact Successfully. *******************************");
	}

	@Test(priority = 18, description = "In this test we are verifying the update contact API.")
	public void update_contact() {
		logger.info("***************  Checking the update contact API. *******************************");
		Response response = API_Textdrip_CreateContact.Update_contact(token);
		response.then().body("status", equalTo(true)).body("message", equalTo("Contact updated successfully."));
		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User update the contact Successfully. *******************************");
	}

	@Test(priority = 19, description = "In this test we are verifying the create contact with tag API.")
	public void create_contact_with_tag() {
		logger.info("***************  Checking the create contact with tag API. *******************************");
		Response response = API_Textdrip_createContactWithTag.Create_contact(token, cid);
		response.then().body("status", equalTo(true)).body("message", equalTo("Contact saved successfully."));
		// .log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** User created contact with tag Successfully. *******************************");
	}

}
