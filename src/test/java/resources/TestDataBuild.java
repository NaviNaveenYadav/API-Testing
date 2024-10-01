package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.location;

public class TestDataBuild {
	
	public AddPlace addPayLoad(String name, String language, String address) {
		
		AddPlace addPlace = new AddPlace();
		
		List<String> lst = new ArrayList();
		lst.add("Shoes Trends");
		lst.add("shop");
		
		location loc = new location();
		loc.setLat(-34);
		loc.setLng(+34);
		
		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setName(name);
		addPlace.setPhoneNumber("+91 1234567890");
		addPlace.setWebsite("https://rahulshettyacademy.com");
		
		addPlace.setTypes(lst);
		addPlace.setLocation(loc);
		
		return addPlace;
		
	}
	
	public String deletePlacePayoad(String placeId) {
		
		return "{\r\n\"place_id\":\""+placeId+"\"\r\n}";
		
	}
	
}
