package feature.shipment;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.laegler.lao.model.entity.Shipment;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Arrays;
import java.util.List;

public class ShipmentDefinitions {

	private String host = "http://localhost:8812";
	private String domain = "shipments";

	private long shipmentId;

	private RequestSpecification req = given().baseUri(host).basePath(domain).log().body();
	private Response resp;

	private Shipment getOne(DataTable table) {
		return table.asList(Shipment.class).get(0);
	}

	private List<Shipment> getMany(DataTable table) {
		return table.asList(Shipment.class);
	}

	private RequestSpecification newReq() {
		req = given().baseUri(host).basePath(domain).log().body();
		return req;
	}

	@Given("^a shipment$")
	public void given_a_shipment(DataTable table) throws Throwable {
		Shipment shipment = newReq().contentType(JSON).body(getOne(table)).accept(JSON).post().as(Shipment.class);
		shipmentId = shipment.getShipmentId();
		newReq();
	}

	@When("^I request to add a shipment$")
	public void when_i_request_to_add_a_shipment(DataTable table) throws Throwable {
		Shipment shipment = getOne(table);
		resp = req.when().contentType(JSON).body(shipment).accept(JSON).post();
	}

	@When("^I request to update a shipment$")
	public void when_i_request_to_update_a_shipment(DataTable table) throws Throwable {
		Shipment shipment = getOne(table);
		resp = req.when().contentType(JSON).body(shipment).accept(JSON).put("/shipmentId/" + shipment.getShipmentId());
	}

	@When("^I request to update this shipment by ID$")
	public void when_i_request_to_update_this_shipment_by_ID(DataTable table) throws Throwable {
		Shipment shipment = getOne(table);
		shipment.setShipmentId(0);
		resp = req.when().contentType(JSON).body(shipment).accept(JSON).put("/shipmentId/" + shipmentId);
	}

	@When("^I request to delete a shipment by ID (\\d+)$")
	public void when_i_request_to_delete_a_shipment_by_ID(int shipmentId) throws Throwable {
		resp = req.when().delete("/shipmentId/" + shipmentId);
	}

	@When("^I request to delete this shipment by ID$")
	public void when_i_request_to_delete_this_shipment_by_ID() throws Throwable {
		resp = req.when().delete("/shipmentId/" + shipmentId);
	}

	@When("^I request to get a shipment by ID (\\d+)$")
	public void when_i_request_to_get_a_shipment_by_ID(int shipmentId) throws Throwable {
		resp = req.when().accept(JSON).get("/shipmentId/" + shipmentId);
	}

	@When("^I request to get this shipment by ID$")
	public void when_i_request_to_get_this_shipment_by_ID() throws Throwable {
		resp = req.when().accept(JSON).get("/shipmentId/" + shipmentId);
	}

	@When("^I request to get all shipments$")
	public void when_i_request_to_get_all_shipments() throws Throwable {
		resp = req.when().accept(JSON).queryParam("page", 0).queryParam("size", 20).when().get();
	}

	@When("^I request to get all shipments of page (\\d+) with size (\\d+) ordered (\\d+)$")
	public void when_i_request_to_get_all_shipments_of_page_with_size(int page, int size, String sort) throws Throwable {
		resp = req.when().accept(JSON).queryParam("page", page).queryParam("size", size).queryParam("sort", sort).get();
	}

	@Then("^the response code should be (\\d+)$")
	public void then_the_response_code_should_be(int code) throws Throwable {
		resp.then().statusCode(code);
	}

	@Then("^the response should contain a shipment$")
	public void then_the_response_should_contain_a_shipment() throws Throwable {
		assertNotNull(resp.as(Shipment.class));
	}

	@Then("^the shipment volume should be \"([^\"]*)\"$")
	public void then_the_shipment_volume_should_be(String volume) throws Throwable {
		resp.then().body("volume", equalTo(volume));
	}

	@Then("^the ID should be (\\d+)$")
	public void then_the_ID_should_be(int id) throws Throwable {
		resp.then().body("shipmentId", equalTo(id));
	}

	@Then("^the response should contain a page of shipments$")
	public void then_the_response_should_contain_a_page_of_shipments() throws Throwable {
		List<Shipment> shipments = Arrays.asList(resp.as(Shipment[].class));
		assertNotNull(shipments);
		assertTrue(shipments.size() > 0);
	}

	@Then("^the page should contain at least (\\d+) shipment\\(s\\)$")
	public void then_the_page_should_contain_at_least_shipments(int numberOf) throws Throwable {
		List<Shipment> shipments = Arrays.asList(resp.as(Shipment[].class));
		assertNotNull(shipments);
		assertTrue(shipments.size() >= numberOf);
	}
}
