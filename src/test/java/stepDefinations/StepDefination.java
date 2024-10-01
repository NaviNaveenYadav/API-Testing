package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	public ResponseSpecification resSpec;
	public RequestSpecification res;
	public Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("^Add place payload with \"(.+)\" \"(.+)\" \"(.+)\"$")
	public void add_place_payload(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification()).body(data.addPayLoad(name, language, address));	    
	}
	
	@When("^user calls \"(.+)\" with \"(.+)\" http request$")
	public void user_calls_with_post_http_request(String resource, String httpMethod) {
		APIResources resourceAPI = APIResources.valueOf(resource);
	    resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	    if (httpMethod.equalsIgnoreCase("POST")) 	    	
	    	response = res.when().post(resourceAPI.getResource());
	    else if (httpMethod.equalsIgnoreCase("GET"))
	    	response = res.when().get(resourceAPI.getResource());
	    else 
	    	response = res.when().delete(resourceAPI.getResource());
	}
	
	@Then("^the API call got success with status code \"(.+)\"$")
	public void the_api_call_got_success_with_status_code(int int1) {
	    assertEquals(response.getStatusCode(), 200);
	    
	}
	
	@Then("^\"(.+)\" in response body is \"(.+)\"$")
	public void in_response_body_is(String key, String value) {
		
	    assertEquals(getJsonPath(response,key),value);
	}
	
	@And("^verify placeId created to the \"(.+)\" using \"(.+)\"$")
	public void verify_placeId_created_to_the_using(String name, String resource) throws IOException {
		
		place_id = getJsonPath(response,"place_id");
		
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_post_http_request(resource, "get");
		String actualName = getJsonPath(response,"name");
		assertEquals(actualName, name);
	}
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePlacePayoad(place_id));
		
	}

	
	
}
