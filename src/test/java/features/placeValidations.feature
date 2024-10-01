
Feature: Validating the Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being Successfully Added using AddPlaceAPI
		Given Add place payload with "<name>" "<language>" "<address>"
		When user calls "AddPlaceAPI" with "Post" http request
		Then the API call got success with status code "200"
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And verify placeId created to the "<name>" using "getPlaceAPI"
		
Examples:
	|name	  		  |language   |address  		  |		
	|Naveen Yadav |English		|Bangalore,India|
#	|Yadav				|Telugu			|Andhra Pradesh |


@DeletePlace @Regression
Scenario: Verify if delete place functionality is working
		Given DeletePlace payload
		When user calls "deletePlaceAPI" with "Post" http request
		Then the API call got success with status code "200"
		And "status" in response body is "OK"
