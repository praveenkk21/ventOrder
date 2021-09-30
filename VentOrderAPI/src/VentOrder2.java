	
	import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

	public class VentOrder2 {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			//import org.testng.annotations.Test;
			  RestAssured.baseURI="http://192.168.1.105:31604";
			  String response=given().log().all().header("Accept-Encoding","gzip, deflate, br").header("accept","*/*").header("hha",1).header("user",1).
					  header("instance_code","working").header("Content-Type","application/json")
					  .body("")
							  .when().log().all()
					  .get("/orders/1045077/subforms/ventilators")
					  .then().assertThat().statusCode(200).log().all()
					  .extract().response().asString();
					  
					  JsonPath js=new JsonPath(response);
					  System.out.println("This is response : "+response);
					 String OrderNo=js.getString("OrderNo");
					  System.out.println("This is response : "+OrderNo);
		}
}