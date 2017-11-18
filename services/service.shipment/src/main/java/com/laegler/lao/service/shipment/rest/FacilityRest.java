package com.laegler.lao.service.shipment.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.laegler.lao.model.entity.Facility;
import com.laegler.lao.service.shipment.domain.FacilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.net.URISyntaxException;

@Api("Facility Service")
@RestController
@RequestMapping(value = "/facilities", produces = APPLICATION_JSON_VALUE)
public class FacilityRest {

	private static final Logger LOG = LoggerFactory.getLogger(FacilityRest.class);

	@Autowired
	private FacilityService facilityService;

	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a new Facility", response = Facility.class)
	public ResponseEntity<?> addFacility(@RequestBody @ApiParam(value = "Facility") final Facility facility) throws URISyntaxException {
		LOG.trace("addFacility({})", facility);

		Facility facilityResponse = facilityService.addFacility(facility);
		facilityResponse.add(linkTo(methodOn(FacilityRest.class).getFacilityByFacilityId(facility.getFacilityId())).withSelfRel());
		facilityResponse.add(linkTo(methodOn(FacilityRest.class).getAllFacilities(0, 20)).withRel("list"));

		return ResponseEntity.created(new URI("")).body(facilityResponse);
	}

	@PutMapping(value = "/facilityId/{facilityId:.+}", consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a Facility by Facility ID", response = Facility.class)
	public ResponseEntity<?> updateFacility(
			@PathVariable(name = "facilityId") @ApiParam(value = "Facility ID", example = "1") final long facilityId,
			@RequestBody @ApiParam(value = "Facility") final Facility facility) throws URISyntaxException {
		LOG.trace("updateFacility({})", facility);

		facility.setFacilityId(facilityId);

		Facility facilityResponse = facilityService.updateFacility(facility);
		facilityResponse.add(linkTo(methodOn(FacilityRest.class).getFacilityByFacilityId(facilityResponse.getFacilityId())).withSelfRel());
		facilityResponse.add(linkTo(methodOn(FacilityRest.class).getAllFacilities(0, 20)).withRel("list"));

		return ResponseEntity.ok(facilityResponse);
	}

	@DeleteMapping(value = "/facilityId/{facilityId:.+}")
	@ApiOperation(value = "Delete a Facility by Facility ID")
	public ResponseEntity<?> deleteFacility(
			@PathVariable(name = "facilityId") @ApiParam(value = "Facility ID", example = "1") final long facilityId) {
		LOG.trace("deleteFacility({})", facilityId);

		facilityService.deleteFacility(facilityId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/facilityId/{facilityId:.+}")
	@ApiOperation(value = "Get a Facility by Facility ID", response = Facility.class)
	public ResponseEntity<?> getFacilityByFacilityId(
			@PathVariable(name = "facilityId") @ApiParam(value = "Facility ID", example = "1") final long facilityId) {
		LOG.trace("getFacilityByFacilityId({})", facilityId);

		Facility facility = facilityService.getFacilityByFacilityId(facilityId);

		facility.add(linkTo(methodOn(FacilityRest.class).getFacilityByFacilityId(facility.getFacilityId())).withSelfRel());
		facility.add(linkTo(methodOn(FacilityRest.class).getAllFacilities(0, 20)).withRel("list"));

		return ResponseEntity.ok(facility);
	}

	@GetMapping
	@ApiOperation(value = "Get all Facilities", response = Facility.class, responseContainer = "List")
	public ResponseEntity<?> getAllFacilities(
			@RequestParam(name = "page", required = false, defaultValue = "0") @ApiParam(name = "page",
					value = "Page number, starting from zero") final int page,
			@RequestParam(name = "size", required = false, defaultValue = "20") @ApiParam(name = "size", value = "Number of records per page",
					example = "20") final int size) {
		LOG.trace("getAllFacilities()");

		Page<Facility> facilities = facilityService.getAllFacilities(new PageRequest(page, size));

		// Resources<Facility> responseResources =
		// new Resources<>(facilities.getContent(),
		// linkTo(methodOn(FacilityRest.class).getAllFacilities(page, size)).withRel("list"));

		return ResponseEntity.ok(facilities.getContent());
	}

}
