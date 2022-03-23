package datadriventesting;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_RegisterUser {
	
	@Test(dataProvider="userdataprovider")
	public void addNewUser(String email, String password) {
		
		RestAssured.baseURI = "https://reqres.in/api";
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject params = new JSONObject();
		params.put("email", email);
		params.put("password", password);
		
		httpRequest.header("Content-Type", "application/json").body(params.toJSONString());
		
		Response response = httpRequest.request(Method.POST, "/register");
		
		String responseBody = response.getBody().asPrettyString();
		
		System.out.println(responseBody);
	}
	
	@DataProvider(name="userdataprovider")
	String [][] getEmpData() throws Exception{
		
		int rowNum = XLUtility.getRowCount(System.getProperty("user.dir")+"\\src\\test\\java\\datadriventesting\\TestData.xlsx", "Sheet1");
		int cellNum = XLUtility.getCellCount(System.getProperty("user.dir")+"\\src\\test\\java\\datadriventesting\\TestData.xlsx", "Sheet1", rowNum);
		
		String[][]  empData = new String[rowNum][cellNum];
		
		for(int i=1;i<=rowNum;i++) {
			
			for(int j=0; j<cellNum;j++) {
				empData[i-1][j] = XLUtility.getCellData(System.getProperty("user.dir")+"\\src\\test\\java\\datadriventesting\\TestData.xlsx", "Sheet1", i, j);
			}
		}
		return empData;
	}

}
