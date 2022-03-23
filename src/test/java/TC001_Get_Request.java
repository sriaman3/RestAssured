import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Get_Request {
	
	
	@Test
	void getSingleUserDetails() {
		
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Request Object
		RequestSpecification httpRequest =  RestAssured.given();
		
		Response response = httpRequest.request(Method.GET, "/users/2");
		 
		String responseBody = response.getBody().asPrettyString();
		
		System.out.println(responseBody);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		//System.out.print(response.jsonPath().get("data.first_name"));
	}

}
