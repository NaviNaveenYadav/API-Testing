package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//Write a code that will give you Place Id
		//Execute this code when place Id is null
		StepDefination m = new StepDefination();
		if (StepDefination.place_id == null) {
			m.add_place_payload("Naveen", "Telugu", "Nandyal");
			m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
			m.verify_placeId_created_to_the_using("Naveen", "getPlaceAPI");
		
		}
		
		
	}
	
}
