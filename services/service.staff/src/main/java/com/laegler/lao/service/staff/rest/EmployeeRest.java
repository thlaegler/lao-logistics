package com.laegler.lao.service.staff.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resources;
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

import com.laegler.lao.model.entity.Employee;
import com.laegler.lao.service.staff.domain.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("Employee Service")
@RestController
@RequestMapping(value = "/employee", produces = APPLICATION_JSON_VALUE)
public class EmployeeRest {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeRest.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a new Employee", response = Employee.class)
	public ResponseEntity<?> addEmployee(@RequestBody final Employee employee) throws URISyntaxException {
		LOG.trace("addEmployee({})", employee);

		Employee employeeResponse = employeeService.addEmployee(employee);
		employeeResponse.add(
				linkTo(methodOn(EmployeeRest.class).getEmployeeByEmployeeId(employee.getEmployeeId())).withSelfRel());
		employeeResponse.add(linkTo(methodOn(EmployeeRest.class).getAllEmployees(0, 20)).withRel("list"));

		return ResponseEntity.created(new URI("")).body(employeeResponse);
	}

	@PutMapping(value = "/employeeId/{employeeId:.+}", consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a Employee by Employee ID", response = Employee.class)
	public ResponseEntity<?> updateEmployee(@PathVariable final long employeeId, @RequestBody final Employee employee)
			throws URISyntaxException {
		LOG.trace("updateEmployee({})", employee);

		employee.setEmployeeId(employeeId);

		Employee employeeResponse = employeeService.updateEmployee(employee);
		employeeResponse.add(
				linkTo(methodOn(EmployeeRest.class).getEmployeeByEmployeeId(employee.getEmployeeId())).withSelfRel());
		employeeResponse.add(linkTo(methodOn(EmployeeRest.class).getAllEmployees(0, 20)).withRel("list"));

		return ResponseEntity.created(new URI("")).body(employeeResponse);
	}

	@DeleteMapping(value = "/employeeId/{employeeId:.+}")
	@ApiOperation(value = "Delete a Employee by Employee ID")
	public ResponseEntity<?> deleteEmployee(@PathVariable final long employeeId) {
		LOG.trace("deleteEmployee({})", employeeId);

		employeeService.deleteEmployee(employeeId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/employeeId/{employeeId:.+}")
	@ApiOperation(value = "Get a Employee by Employee ID", response = Employee.class)
	public ResponseEntity<?> getEmployeeByEmployeeId(@PathVariable(value = "Employee ID") final long employeeId) {
		LOG.trace("getEmployeeByEmployeeId({})", employeeId);

		Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);

		employee.add(
				linkTo(methodOn(EmployeeRest.class).getEmployeeByEmployeeId(employee.getEmployeeId())).withSelfRel());
		employee.add(linkTo(methodOn(EmployeeRest.class).getAllEmployees(0, 20)).withRel("list"));

		return ResponseEntity.ok(employee);
	}

	@GetMapping
	@ApiOperation(value = "Get all Employees", response = Employee.class, responseContainer = "List")
	public ResponseEntity<?> getAllEmployees(
			@ApiParam(value = "Page number, starting from zero") @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
			@ApiParam(value = "Number of records per page") @RequestParam(value = "limit", required = false, defaultValue = "20") final int limit) {
		LOG.trace("getAllEmployees()");

		Page<Employee> employees = employeeService.getAllEmployees(new PageRequest(page, limit));

		Resources<Employee> responseResources = new Resources<>(employees,
				linkTo(methodOn(EmployeeRest.class).getAllEmployees(0, 20)).withRel("list"));

		return ResponseEntity.ok(responseResources);
	}

}
