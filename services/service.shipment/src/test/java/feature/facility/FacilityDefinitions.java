package feature.facility;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.laegler.lao.model.entity.Facility;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.CommonDefinitions;
import java.util.Arrays;
import java.util.List;

public class FacilityDefinitions extends CommonDefinitions<Facility> {

	private long facilityId;

	@Override
	protected Class<Facility> getType() {
		return Facility.class;
	}

	@Override
	protected String getHost() {
		return "http://localhost:8812";
	}

	@Override
	protected String getService() {
		return "facilities";
	}

	@Before
	public void beforeScenario() {}

	@After
	public void afterScenario() {
		given().baseUri(getHost()).basePath(getService()).log().body().when().delete("/facilityId/" + facilityId);
	}

	@Given("^a facility$")
	public void given_a_facility(DataTable table) throws Throwable {
		Facility facility = newReq().contentType(JSON).body(getOne(table)).accept(JSON).post().as(Facility.class);
		facilityId = facility.getFacilityId();
		newReq();
	}

	@When("^I request to add a facility$")
	public void when_i_request_to_add_a_facility(DataTable table) throws Throwable {
		Facility facility = getOne(table);
		resp = newReq().when().contentType(JSON).body(facility).accept(JSON).post();
	}

	@When("^I request to update a facility$")
	public void when_i_request_to_update_a_facility(DataTable table) throws Throwable {
		Facility facility = getOne(table);
		this.facilityId = facility.getFacilityId();
		resp = newReq().when().contentType(JSON).body(facility).accept(JSON).put("/facilityId/" + facility.getFacilityId());
	}

	@When("^I request to update this facility$")
	public void when_i_request_to_update_this_facility(DataTable table) throws Throwable {
		Facility facility = getOne(table);
		resp = newReq().when().contentType(JSON).body(facility).accept(JSON).put("/facilityId/" + facilityId);
	}

	@When("^I request to delete a facility by ID (\\d+)$")
	public void when_i_request_to_delete_a_facility_by_ID(int facilityId) throws Throwable {
		this.facilityId = facilityId;
		resp = newReq().when().delete("/facilityId/" + facilityId);
	}

	@When("^I request to delete this facility$")
	public void when_i_request_to_delete_this_facility() throws Throwable {
		resp = newReq().when().delete("/facilityId/" + facilityId);
	}

	@When("^I request to get a facility by ID (\\d+)$")
	public void when_i_request_to_get_a_facility_by_ID(int facilityId) throws Throwable {
		this.facilityId = facilityId;
		resp = newReq().when().accept(JSON).get("/facilityId/" + facilityId);
	}

	@When("^I request to get this facility$")
	public void when_i_request_to_get_this_facility() throws Throwable {
		resp = newReq().when().accept(JSON).get("/facilityId/" + facilityId);
	}

	@When("^I request to get all facilities$")
	public void when_i_request_to_get_all_facilities() throws Throwable {
		resp = newReq().when().accept(JSON).queryParam("page", 0).queryParam("size", 20).when().get();
	}

	@When("^I request to get all facilities of page (\\d+) with size (\\d+) ordered (\\d+)$")
	public void when_i_request_to_get_all_facilities_of_page_with_size(int page, int size, String sort) throws Throwable {
		resp = newReq().when().accept(JSON).queryParam("page", page).queryParam("size", size).queryParam("sort", sort).get();
	}

	@Then("^the response code should be (\\d+)$")
	public void then_the_response_code_should_be(int code) throws Throwable {
		resp.then().statusCode(code);
	}

	@Then("^the response should contain a facility$")
	public void then_the_response_should_contain_a_facility() throws Throwable {
		assertNotNull(resp.as(Facility.class));
	}

	@Then("^the facility dimensions should be \"([^\"]*)\"$")
	public void then_the_facility_dimensions_should_be(String dimensions) throws Throwable {
		resp.then().body("dimensions", equalTo(dimensions));
	}

	@Then("^the ID should be (\\d+)$")
	public void then_the_ID_should_be(int facilityId) throws Throwable {
		resp.then().body("facilityId", equalTo(facilityId));
	}

	@Then("^the response should contain a page of facilities$")
	public void then_the_response_should_contain_a_page_of_facilities() throws Throwable {
		List<Facility> facilities = Arrays.asList(resp.as(Facility[].class));
		assertNotNull(facilities);
		assertTrue(facilities.size() > 0);
	}

	@Then("^the page should contain at least (\\d+) facility\\(s\\)$")
	public void then_the_page_should_contain_at_least_facilitys(int numberOf) throws Throwable {
		List<Facility> facilities = Arrays.asList(resp.as(Facility[].class));
		assertNotNull(facilities);
		assertTrue(facilities.size() >= numberOf);
	}
}
