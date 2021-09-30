
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VentOrder {

	public static String getURL;
	public static String OrderID;
	public static JsonPath js;

	@DataProvider(name = "ChangeOrderid")
	@Test
	public static Object[] POSTAPI() {

		// TODO Auto-generated method stub
		// import org.testng.annotations.Test;
		RestAssured.baseURI = "http://192.168.1.105:31604";
		String response = RestAssured.given().headers("Accept-Encoding", "gzip, deflate, br")
				.header("User-Agent", "PostmanRuntime/7.28.4").header("accept", "*/*").header("hha", 1)
				.header("user", 1).header("instance_code", "working").header("Content-Type", "application/json")
				.body("\"{\\\"order_date\\\":\\\"08/05/2020\\\",\\\"order_effective_date\\\":\\\"08/05/2020 03:00\\\",\\\"vent_form_order_json\\\":{\\\"shifts\\\":[{\\\"shift_name\\\":\\\"s3\\\",\\\"sections\\\":[{\\\"section_name\\\":\\\"ventilator_settings\\\",\\\"section_fields\\\":[{\\\"field_name\\\":\\\"ventilator\\\",\\\"field_value\\\":\\\"LTV 950\\\"},{\\\"field_name\\\":\\\"mode\\\",\\\"field_value\\\":\\\"Control\\\"},{\\\"field_name\\\":\\\"o2_litre_flow_lpm\\\",\\\"field_value\\\":\\\"O2\\\"},{\\\"field_name\\\":\\\"breath_rate\\\",\\\"field_value\\\":\\\"RTR\\\"},{\\\"field_name\\\":\\\"tidal_volume\\\",\\\"field_value\\\":\\\"FF\\\"},{\\\"field_name\\\":\\\"avaps_on_off\\\",\\\"field_value\\\":\\\"RTR\\\"},{\\\"field_name\\\":\\\"ivaps_on_off\\\",\\\"field_value\\\":\\\"ZZ\\\"},{\\\"field_name\\\":\\\"peep\\\",\\\"field_value\\\":\\\"RT\\\"},{\\\"field_name\\\":\\\"cycle\\\",\\\"field_value\\\":\\\"AA\\\"},{\\\"field_name\\\":\\\"pressue_control\\\",\\\"field_value\\\":\\\"GRGR\\\"},{\\\"field_name\\\":\\\"peak_inspiratory_flow\\\",\\\"field_value\\\":\\\"SA\\\"},{\\\"field_name\\\":\\\"inspiratory_pressure\\\",\\\"field_value\\\":\\\"FDF\\\"},{\\\"field_name\\\":\\\"inspiratory_time\\\",\\\"field_value\\\":\\\"AADD\\\"},{\\\"field_name\\\":\\\"rise_time\\\",\\\"field_value\\\":\\\"RTR\\\"},{\\\"field_name\\\":\\\"disconnect_time\\\",\\\"field_value\\\":\\\"DAD\\\"},{\\\"field_name\\\":\\\"pressure_support\\\",\\\"field_value\\\":\\\"RTR\\\"},{\\\"field_name\\\":\\\"ipap_max_min\\\",\\\"field_value\\\":\\\"ADAS\\\"},{\\\"field_name\\\":\\\"epap_max_min\\\",\\\"field_value\\\":\\\"RYY\\\"},{\\\"field_name\\\":\\\"ramp_time_length_start_pressure\\\",\\\"field_value\\\":\\\"SADA\\\"},{\\\"field_name\\\":\\\"apnea_rate_high_low\\\",\\\"field_value\\\":\\\"RYR\\\"},{\\\"field_name\\\":\\\"apnea_alarm_high_low\\\",\\\"field_value\\\":\\\"FASF\\\"},{\\\"field_name\\\":\\\"vte_alarm_high_low\\\",\\\"field_value\\\":\\\"GDG\\\"},{\\\"field_name\\\":\\\"respiratory_rate_alarm\\\",\\\"field_value\\\":\\\"ASFS\\\"},{\\\"field_name\\\":\\\"minute_ventilation_alarm\\\",\\\"field_value\\\":\\\"GDG\\\"}]}]}]},\\\"otherOrderFieldsJson\\\":{\\\"ClientID\\\":\\\"111625\\\",\\\"EpisodeID\\\":198264,\\\"Physician\\\":\\\"41157\\\",\\\"isVerbalOrder\\\":0,\\\"Description\\\":\\\"test by praveen\\\",\\\"Comments\\\":\\\"\\\",\\\"OnBehalfOfClincian\\\":0,\\\"OrderRequestedBy\\\":\\\"39885\\\",\\\"SignedClinician\\\":0,\\\"IsSelfSign\\\":0,\\\"SentDate\\\":\\\"05/24/2021\\\",\\\"VentilatorSettingID\\\":0,\\\"PageID\\\":0,\\\"Order_No\\\":\\\"\\\",\\\"IsApprovedbyAssistant\\\":\\\"0\\\",\\\"DoNotRequirePhySign\\\":\\\"0\\\",\\\"IsPrintPreviewClick\\\":0,\\\"OrderTime\\\":\\\"05:00\\\",\\\"LibraryFormId\\\":\\\"610\\\"}}\"")
				.when().post("/orders/subforms/ventilators/clients/111625").then().assertThat().statusCode(200)
				.extract().response().asPrettyString();

		System.out.println("POST response: " + response);
		js = new JsonPath(response);
		OrderID = js.getString("OrderID");
		OrderID = OrderID.replace("[", "").replace("]", "");
		System.out.println("POST response ORDERID: " + OrderID);
		System.out.println();
		// GETAPI(OrderID);
		return new Object[] { OrderID };

	}

	@Test(dataProvider = "ChangeOrderid")
	public static void GETAPI(String orderID2) {

		getURL = "/orders/" + orderID2 + "/subforms/ventilators";
		String response2 = given().header("Accept-Encoding", "gzip, deflate, br").header("accept", "*/*")
				.header("hha", 1).header("user", 1).header("instance_code", "working")
				.header("Content-Type", "application/json").body("").when().get(getURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("GET response: " + response2);
		js = new JsonPath(response2);
		orderID2 = js.getString("Change_Order_id");
		System.out.println("GET response ORDERID: " + orderID2);
		System.out.println();
		//DELETEAPI(orderID2);
	}

	@Test(dataProvider = "ChangeOrderid")
	public static void DELETEAPI(String OrderID3) {
		getURL = "/orders/" + OrderID + "/subforms/ventilators";
		String response3 = given().header("Accept-Encoding", "gzip, deflate, br").header("accept", "*/*")
				.header("hha", 1).header("user", 1).header("instance_code", "working")
				.header("Content-Type", "application/json").body("").when().delete(getURL).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("DELETE response: " + response3);
		js = new JsonPath(response3);
		OrderID3 = js.getString("returnValue");
		System.out.println("DELETEET response ORDERID: " + OrderID3);
		System.out.println();
	}

}