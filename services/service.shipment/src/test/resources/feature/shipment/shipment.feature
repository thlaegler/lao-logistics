@language=en-US 
@user=adam 
@password=lao 
Feature: Manage shipments 

Scenario: As admin I want to add a new shipment 
	When I request to add a shipment 
		| shipmentId | volume   |  
		| 0  | 300x440x500 | 
	Then the response code should be 201 
	And the response should contain a shipment 
	And the shipment volume should be "300x440x500" 
	
Scenario: As admin I want to update a shipment 
	Given  a shipment 
		| shipmentId | volume   |  
		|   0 | 600x440x500 | 
	When  I request to update this shipment by ID 
		| shipmentId | volume   |  
		| 0  | 600x440x500 | 
	Then  the response code should be 200 
	And  the response should contain a shipment 
	And  the shipment volume should be "600x440x500" 
	
Scenario: As admin I want to delete an existing shipment 
	Given  a shipment 
		| shipmentId | volume   |  
		| 0  | 600x440x500 | 
	When  I request to delete this shipment by ID 
	Then  the response code should be 204 
	
Scenario: As admin, I want to get an existing shipment by ID 
	Given  a shipment 
		| shipmentId | volume   |  
		| 0  | 600x440x500 | 
	When  I request to get this shipment by ID 
	Then  the response code should be 200 
	And  the response should contain a shipment 
	And  the shipment volume should be "600x440x500" 
	
Scenario: As admin, I want to get all existing shipments 
	When  I request to get all shipments 
	Then  the response code should be 200 
	And  the response should contain a page of shipments 
	And  the page should contain at least 1 shipment(s) 
