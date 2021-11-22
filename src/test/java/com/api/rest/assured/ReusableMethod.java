package com.api.rest.assured;

import io.restassured.path.json.JsonPath;

public class ReusableMethod 
{
public static JsonPath rawJson(String file)
{
	JsonPath path=new JsonPath(file);
	return path;

}
}
