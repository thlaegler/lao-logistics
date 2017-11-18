package com.laegler.lao.service.user.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laegler.lao.model.entity.User;
import com.laegler.lao.service.user.domain.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("Users Service")
@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
public class UserRest {

	private static final Logger LOG = LoggerFactory.getLogger(UserRest.class);

	@Autowired
	private UserService userService;

	@GetMapping
	@ApiOperation(value = "Get all Users", response = User.class, responseContainer = "List")
	public ResponseEntity<?> getAllUsers(
			@ApiParam(value = "Page number, starting from zero") @RequestParam(value = "page", required = false, defaultValue = "0") final Integer page,
			@ApiParam(value = "Number of records per page") @RequestParam(value = "limit", required = false, defaultValue = "20") final Integer limit) {
		LOG.trace("getAllUsers()");

		return ResponseEntity.ok(userService.getAllUsers(new PageRequest(page, limit)));
	}
}
