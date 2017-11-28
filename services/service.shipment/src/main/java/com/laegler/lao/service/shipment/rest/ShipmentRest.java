package com.laegler.lao.service.shipment.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.laegler.lao.model.entity.Shipment;
import com.laegler.lao.service.shipment.domain.ShipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@Api("Shipment Service")
@RestController
@RequestMapping(value = "/shipments", produces = APPLICATION_JSON_VALUE)
public class ShipmentRest {

	private static final Logger LOG = LoggerFactory.getLogger(ShipmentRest.class);

	@Autowired
	private ShipmentService shipmentService;

	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a new Shipment", response = Shipment.class)
	public ResponseEntity<?> addShipment(@Valid @RequestBody @ApiParam(value = "Shipment") final Shipment shipment)
			throws URISyntaxException {
		LOG.trace("addShipment({})", shipment);

		Shipment s = shipmentService.addShipment(shipment);
		s.add(getSelfLink(s.getShipmentId()).withSelfRel());
		s.add(getListLink().withRel("list"));

		return ResponseEntity.created(getSelfLink(s.getShipmentId()).toUri()).body(s);
	}

	@PutMapping(value = "/shipmentId/{shipmentId:.+}", consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a Shipment by Shipment ID", response = Shipment.class)
	public ResponseEntity<?> updateShipment(
			@NotNull @PathVariable(name = "shipmentId") @ApiParam(name = "shipmentId", value = "Shipment ID",
					example = "1") final long shipmentId,
			@Valid @RequestBody @ApiParam(value = "Shipment") final Shipment shipment) throws URISyntaxException {
		LOG.trace("updateShipment({})", shipment);

		shipment.setShipmentId(shipmentId);

		Shipment s = shipmentService.updateShipment(shipment);
		s.add(getSelfLink(s.getShipmentId()).withSelfRel());
		s.add(getListLink().withRel("list"));

		return ResponseEntity.ok(s);
	}

	@DeleteMapping(value = "/shipmentId/{shipmentId:.+}")
	@ApiOperation(value = "Delete a Shipment by Shipment ID")
	public ResponseEntity<?> deleteShipment(@NotNull @PathVariable(name = "shipmentId") @ApiParam(name = "shipmentId", value = "Shipment ID",
			example = "1") final long shipmentId) {
		LOG.trace("deleteShipment({})", shipmentId);

		shipmentService.deleteShipment(shipmentId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/trackingNumber/{trackingNumber:.+}")
	@ApiOperation(value = "Get a Shipment by Tracking UUID", response = Shipment.class)
	public ResponseEntity<?> getShipmentByTrackingNumber(@NotNull @PathVariable(value = "Tracking UUID") @ApiParam(name = "trackingNumber",
			value = "Tracking UUID", example = "1234-3xr434-2sr43s-2r3s") final UUID trackingNumber) {
		LOG.trace("getShipmentByTrackingNumber({})", trackingNumber);

		Shipment s = shipmentService.getShipmentByTrackingNumber(trackingNumber);

		s.add(getSelfLink(s.getShipmentId()).withSelfRel());
		s.add(getListLink().withRel("list"));

		return ResponseEntity.ok(s);
		// shipmentService.getShipmentByTrackingNumber(trackingNumber));
	}

	@GetMapping("/customerId/{customerId:.+}")
	@ApiOperation(value = "Get a Shipment by Customer ID", response = Shipment.class, responseContainer = "List")
	public ResponseEntity<?> getShipmentsByCustomerId(@NotNull @PathVariable(value = "customerId") @ApiParam(name = "customerId",
			value = "Customer ID", example = "1") final long customerId) {
		LOG.trace("getShipmentsByCustomerId({})", customerId);

		List<Shipment> shipments = shipmentService.getShipmentsByCustomerId(customerId);

		shipments.forEach(s -> {
			s.add(getSelfLink(s.getShipmentId()).withSelfRel());
		});

		Resources<Shipment> responseResources = new Resources<>(shipments, getListLink().withRel("list"));

		return ResponseEntity.ok(responseResources);
	}

	@GetMapping("/shipmentId/{shipmentId:.+}")
	@ApiOperation(value = "Get a Shipment by Shipment ID", response = Shipment.class)
	public ResponseEntity<?> getShipmentByShipmentId(@NotNull @PathVariable(name = "shipmentId") @ApiParam(name = "shipmentId",
			value = "Shipment ID", example = "1") final long shipmentId) {
		LOG.trace("getShipmentByShipmentId({})", shipmentId);

		Shipment s = shipmentService.getShipmentByShipmentId(shipmentId);

		s.add(getSelfLink(s.getShipmentId()).withSelfRel());
		s.add(getListLink().withRel("list"));

		return ResponseEntity.ok(s);
	}

	@GetMapping
	@ApiOperation(value = "Get all Shipments", response = Shipment.class, responseContainer = "List")
	public ResponseEntity<?> getAllShipments(
			@RequestParam(name = "page", required = false, defaultValue = "0") @ApiParam(name = "page",
					value = "Page number, starting from zero") final int page,
			@RequestParam(name = "size", required = false, defaultValue = "20") @ApiParam(name = "size", value = "Number of records per page",
					example = "20") final int size) {
		LOG.trace("getAllShipments()");

		Page<Shipment> shipments = shipmentService.getAllShipments(new PageRequest(page, size));

		// Resources<Shipment> responseResources =
		// new Resources<>(shipments.getContent(),
		// linkTo(methodOn(ShipmentRest.class).getAllShipments(page, size)).withRel("list"));

		return ResponseEntity.ok().header("Access-Control-Allow-Origin", "*").body(shipments.getContent());
	}

	private ControllerLinkBuilder getSelfLink(final long shipmentId) {
		return linkTo(methodOn(ShipmentRest.class).getShipmentByShipmentId(shipmentId));
	}

	private ControllerLinkBuilder getListLink() {
		return linkTo(methodOn(ShipmentRest.class).getAllShipments(0, 20));
	}

}
