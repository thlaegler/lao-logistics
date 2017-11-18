package com.laegler.lao.service.tour.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.laegler.lao.model.entity.Address;
import com.laegler.lao.model.entity.Facility;
import com.laegler.lao.service.tour.domain.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Api("Address Service")
@RestController
@RequestMapping(value = "/addresses", produces = APPLICATION_JSON_VALUE)
public class AddressRest {

	private static final Logger LOG = LoggerFactory.getLogger(AddressRest.class);

	@Autowired
	private AddressService addressService;

	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a new Address", response = Address.class)
	public ResponseEntity<?> addAddress(@RequestBody final Address address) {
		LOG.trace("addAddress({})", address);

		final Address addressResponse = addressService.addAddress(address);

		addressResponse.add(linkTo(methodOn(AddressRest.class).getAddressByAddressId(addressResponse.getAddressId())).withSelfRel());
		addressResponse.add(linkTo(methodOn(AddressRest.class).getAllAddresses(0, 20)).withRel("list"));

		return ResponseEntity.created(null).body(addressResponse);
	}

	@PutMapping(value = "/addressId/{addressId:.+}", consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a Address by Address ID", response = Address.class)
	public ResponseEntity<?> updateAddress(@PathVariable final long addressId, @RequestBody final Address address) {
		LOG.trace("addAddress({})", address);

		address.setAddressId(addressId);
		final Address addressResponse = addressService.addAddress(address);

		addressResponse.add(linkTo(methodOn(AddressRest.class).getAddressByAddressId(addressResponse.getAddressId())).withSelfRel());
		addressResponse.add(linkTo(methodOn(AddressRest.class).getAllAddresses(0, 20)).withRel("list"));

		return ResponseEntity.ok(addressResponse);
	}

	@DeleteMapping(value = "/addressId/{addressId:.+}")
	@ApiOperation(value = "Delete a Address by Address ID")
	public ResponseEntity<?> deleteAddress(@PathVariable final long addressId) {
		LOG.trace("deleteAddress({})", addressId);

		addressService.deleteAddress(addressId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/addressId/{addressId:.+}")
	@ApiOperation(value = "Get a Address by Address ID", response = Address.class)
	public ResponseEntity<?> getAddressByAddressId(@PathVariable final long addressId) {
		LOG.trace("getAddressByAddressId({})", addressId);

		return ResponseEntity.ok(addressService.getAddressByAddressId(addressId));
	}

	@GetMapping
	@ApiOperation(value = "Get all Addresses", response = Facility.class, responseContainer = "List")
	public ResponseEntity<?> getAllAddresses(
			@ApiParam(value = "Page number, starting from zero") @RequestParam(value = "page", required = false,
					defaultValue = "0") final int page,
			@ApiParam(value = "Number of records per page") @RequestParam(value = "size", required = false, defaultValue = "20") final int size) {
		LOG.trace("getAllAddresses({}, {})", page, size);

		return ResponseEntity.ok(addressService.getAllAddresses(new PageRequest(page, size)));
	}

	@GetMapping
	@ApiOperation(value = "Get a all Addresses", response = Address.class, responseContainer = "List")
	public ResponseEntity<?> getAllAddresses() {
		LOG.trace("getAllAddresses({})");

		return ResponseEntity.ok(addressService.getAllAddresses());
	}
}
