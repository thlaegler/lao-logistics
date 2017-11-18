package com.laegler.lao.service.uac.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.net.URISyntaxException;

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

import com.laegler.lao.model.entity.Customer;
import com.laegler.lao.service.uac.domain.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("Customer Service")
@RestController
@RequestMapping(value = "/customers", produces = APPLICATION_JSON_VALUE)
public class CustomerRest {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerRest.class);

	@Autowired
	private CustomerService customerService;

	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a new Customer", response = Customer.class)
	public ResponseEntity<?> addCustomer(@RequestBody final Customer customer) throws URISyntaxException {
		LOG.trace("addCustomer({})", customer);

		Customer customerResponse = customerService.addCustomer(customer);

		return ResponseEntity.created(new URI("")).body(customerResponse);
	}

	@PutMapping(value = "/customerId/{customerId:.+}", consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a Customer by Customer ID", response = Customer.class)
	public ResponseEntity<?> updateCustomer(@PathVariable final long customerId, @RequestBody final Customer customer)
			throws URISyntaxException {
		LOG.trace("updateCustomer({})", customer);

		customer.setCustomerId(customerId);

		Customer customerResponse = customerService.updateCustomer(customer);

		return ResponseEntity.created(new URI("")).body(customerResponse);
	}

	@DeleteMapping(value = "/customerId/{customerId:.+}")
	@ApiOperation(value = "Delete a Customer by Customer ID")
	public ResponseEntity<?> deleteCustomer(@PathVariable final long customerId) {
		LOG.trace("deleteCustomer({})", customerId);

		customerService.deleteCustomer(customerId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/id/{customerId:.+}")
	@ApiOperation(value = "Get a Customer by Customer ID", response = Customer.class)
	public ResponseEntity<?> getCustomerByCustomerId(@PathVariable(value = "Customer ID") final long customerId) {
		LOG.trace("getCustomerByCustomerId({})", customerId);

		return ResponseEntity.ok(customerService.getCustomerByCustomerId(customerId));
	}

	@GetMapping
	@ApiOperation(value = "Get all Customers", response = Customer.class, responseContainer = "List")
	public ResponseEntity<?> getAllCustomers(
			@ApiParam(value = "Page number, starting from zero") @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
			@ApiParam(value = "Number of records per page") @RequestParam(value = "limit", required = false, defaultValue = "20") final int limit) {
		LOG.trace("getAllCustomers()");

		return ResponseEntity.ok(customerService.getAllCustomers(new PageRequest(page, limit)));
	}
}
