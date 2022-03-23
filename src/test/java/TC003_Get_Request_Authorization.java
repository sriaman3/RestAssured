import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;

public class TC003_Get_Request_Authorization {
	
	@Test
	public void createUsers() {
		
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Basic Authentication
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("Aman");
		authScheme.setPassword("1234");
		
		RestAssured.authentication = authScheme;
		
		
		//For Oauth2 authentication
		PreemptiveOAuth2HeaderScheme oauth2 = new PreemptiveOAuth2HeaderScheme();
		oauth2.setAccessToken("");	
	}
		
}
