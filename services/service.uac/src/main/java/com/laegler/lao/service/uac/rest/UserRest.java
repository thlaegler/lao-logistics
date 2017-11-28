package com.laegler.lao.service.uac.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.laegler.lao.model.entity.User;
import com.laegler.lao.service.uac.domain.UserService;
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

@Api("User Service")
@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
public class UserRest {

	private static final Logger LOG = LoggerFactory.getLogger(UserRest.class);

	@Autowired
	private UserService userService;

	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a new User", response = User.class)
	public ResponseEntity<?> addUser(@RequestBody @ApiParam(name = "user", value = "User") final User user) throws URISyntaxException {
		LOG.trace("addUser({})", user);

		User userResponse = userService.addUser(user);
		userResponse.add(linkTo(methodOn(UserRest.class).getUserByUserId(user.getUserId())).withSelfRel());
		userResponse.add(linkTo(methodOn(UserRest.class).getAllUsers(0, 20)).withRel("list"));

		return ResponseEntity.created(new URI("")).body(userResponse);
	}

	@PutMapping(value = "/userId/{userId:.+}", consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a User by User ID", response = User.class)
	public ResponseEntity<?> updateUser(
			@PathVariable(name = "userId") @ApiParam(name = "userId", value = "User ID", example = "123") final long userId,
			@RequestBody @ApiParam(name = "user", value = "User") final User user) {
		LOG.trace("updateUser({})", user);

		user.setUserId(userId);

		User userResponse = userService.updateUser(user);
		userResponse.add(linkTo(methodOn(UserRest.class).getUserByUserId(userResponse.getUserId())).withSelfRel());
		userResponse.add(linkTo(methodOn(UserRest.class).getAllUsers(0, 20)).withRel("list"));

		return ResponseEntity.ok(userResponse);
	}

	@DeleteMapping(value = "/userId/{userId:.+}")
	@ApiOperation(value = "Delete a User by User ID")
	public ResponseEntity<?> deleteUser(
			@PathVariable(name = "userId") @ApiParam(name = "userId", value = "User ID", example = "123") final long userId) {
		LOG.trace("deleteUser({})", userId);

		userService.deleteUser(userId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/userId/{userId:.+}")
	@ApiOperation(value = "Get a User by User ID", response = User.class)
	public ResponseEntity<?> getUserByUserId(
			@PathVariable(name = "userId") @ApiParam(name = "userId", value = "User ID", example = "123") final long userId) {
		LOG.trace("getUserByUserId({})", userId);

		User user = userService.getUserByUserId(userId);

		user.add(linkTo(methodOn(UserRest.class).getUserByUserId(user.getUserId())).withSelfRel());
		user.add(linkTo(methodOn(UserRest.class).getAllUsers(0, 20)).withRel("list"));

		return ResponseEntity.ok(user);
	}

	@GetMapping
	@ApiOperation(value = "Get all Users", response = User.class, responseContainer = "List")
	public ResponseEntity<?> getAllUsers(
			@RequestParam(name = "page", required = false, defaultValue = "0") @ApiParam(name = "page",
					value = "Page number, starting from zero") final int page,
			@RequestParam(name = "size", required = false, defaultValue = "20") @ApiParam(name = "size", value = "Number of records per page",
					example = "20") final int size) {
		LOG.trace("getAllUsers()");

		Page<User> users = userService.getAllUsers(new PageRequest(page, size));

		// Resources<User> responseResources =
		// new Resources<>(users.getContent(),
		// linkTo(methodOn(UserRest.class).getAllUsers(page, size)).withRel("list"));

		return ResponseEntity.ok(users.getContent());
	}

}
