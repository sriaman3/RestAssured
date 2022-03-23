import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Post_Request {
	
	@Test
	public void createUsers() {
		
		RestAssured.baseURI = "https://reqres.in/api";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Aman");
		requestParams.put("job", "QA Engineer");
		
		httpRequest.header("Content-Type","application/json").body(requestParams.toJSONString());
		
		//httpRequest.body(requestParams.toJSONString());
		
		Response response = httpRequest.request(Method.POST, "/users");
		
		String responseBody = response.getBody().asPrettyString();
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains("Aman"),true);
		
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
		
		
		String data = response.jsonPath().get("name");  
		Assert.assertEquals(data, "Aman");
		
		
		JsonPath jsonData = response.jsonPath();
		jsonData.get("name");
		
		System.out.println(response.header("Content-Type"));
		
		Headers allHeader = response.headers();
		
		for(Header header : allHeader) {
			System.out.println(header.getName()+ " : " + header.getValue());
		}
	}

}
