package resources;

//enum is a special class in Java which has collection of Constants(Variables like final) and methods
//default access specifier and syntax for variables - public static final

public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	deletePlaceAPI("/maps/api/place/delete/json"),
	getPlaceAPI("/maps/api/place/get/json");
	private String resource;
	
	APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
	
}
