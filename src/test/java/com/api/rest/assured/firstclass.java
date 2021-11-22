package com.api.rest.assured;

import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class firstclass {

	public static void main(String[] args) 
	{
		//Validate if Add place API is working as expected
		/*     
		given- all input detail(queryParameter,header,body)
		when - submit api (resource,http method)
		then-validate the response
		
		*/
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	String response=given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body(payload.addPlace())
		//in payload class we put the payload and create the method as add place
		
		.when().post("maps/api/place/add/json")
	    .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
	    .extract().response().asString();
	    ;
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);  // for parsing the json
	String placeid=	js.getString("place_id");
	System.out.println(placeid);
		
	//update
	String address="70 winter walk, USA";
	given().log().all().queryParam("key", "qaclick123")
	       .header("Content-Type", "application/json")
	       .body("{\r\n" + 
	       		"\"place_id\":\""+placeid+"\",\r\n" + 
	       		"\"address\":\""+address+"\",\r\n" + 
	       		"\"key\":\"qaclick123\"\r\n" + 
	       		"}")
	.when().put("/maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
	//Get the updated adddress
	
String add=	given().param("key", "qaclick123")
	       .param("place_id", placeid)
	    .when().get("/maps/api/place/get/json")
	    .then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		//created the resuable function for jsonpath
	JsonPath path=	ReusableMethod.rawJson(add);
String actualresult=path.getString("address");
System.out.println(actualresult);
Assert.assertEquals(address, actualresult);
		
		
		
		//Add place-->Update Place with new address-->Get Place to validate if new address is present 
		//response
		
		

	}

}
