@language=en-US 
@user=adam 
@password=lao 
Feature: Manage facilities 

Scenario: As admin I want to add a new facility 
	When I request to add a facility 
		| facilityId | volume   |  
		| 0  | 300x440x500 | 
	Then the response code should be 201 
	And the response should contain a facility 
	And the facility volume should be "300x440x500" 
	
Scenario: As admin I want to update a facility 
	Given  a facility 
		| facilityId | volume   |  
		|   0 | 600x440x500 | 
	When  I request to update this facility by ID 
		| facilityId | volume   |  
		| 0  | 600x440x500 | 
	Then  the response code should be 200 
	And  the response should contain a facility 
	And  the facility volume should be "600x440x500" 
	
Scenario: As admin I want to delete an existing facility 
	Given  a facility 
		| facilityId | volume   |  
		| 0  | 600x440x500 | 
	When  I request to delete this facility by ID 
	Then  the response code should be 204 
	
Scenario: As admin, I want to get an existing facility by ID 
	Given  a facility 
		| facilityId | volume   |  
		| 0  | 600x440x500 | 
	When  I request to get this facility by ID 
	Then  the response code should be 200 
	And  the response should contain a facility 
	And  the facility volume should be "600x440x500" 
	
Scenario: As admin, I want to get all existing facilities 
	When  I request to get all facilities 
	Then  the response code should be 200 
	And  the response should contain a page of facilities 
	And  the page should contain at least 1 facility(s) 
