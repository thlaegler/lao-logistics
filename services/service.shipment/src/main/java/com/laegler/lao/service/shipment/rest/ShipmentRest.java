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
	public ResponseEntity<?> addShipment(@RequestBody @ApiParam(value = "Shipment") final Shipment shipment) throws URISyntaxException {
		LOG.trace("addShipment({})", shipment);

		Shipment shipmentResponse = shipmentService.addShipment(shipment);
		shipmentResponse.add(linkTo(methodOn(ShipmentRest.class).getShipmentByShipmentId(shipment.getShipmentId())).withSelfRel());
		shipmentResponse.add(linkTo(methodOn(ShipmentRest.class).getAllShipments(0, 20)).withRel("list"));

		return ResponseEntity.created(new URI("")).body(shipmentResponse);
	}

	@PutMapping(value = "/shipmentId/{shipmentId:.+}", consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a Shipment by Shipment ID", response = Shipment.class)
	public ResponseEntity<?> updateShipment(
			@PathVariable(name = "shipmentId") @ApiParam(value = "Shipment ID", example = "1") final long shipmentId,
			@RequestBody @ApiParam(value = "Shipment") final Shipment shipment) throws URISyntaxException {
		LOG.trace("updateShipment({})", shipment);

		shipment.setShipmentId(shipmentId);

		Shipment shipmentResponse = shipmentService.updateShipment(shipment);
		shipmentResponse.add(linkTo(methodOn(ShipmentRest.class).getShipmentByShipmentId(shipmentResponse.getShipmentId())).withSelfRel());
		shipmentResponse.add(linkTo(methodOn(ShipmentRest.class).getAllShipments(0, 20)).withRel("list"));

		return ResponseEntity.ok(shipmentResponse);
	}

	@DeleteMapping(value = "/shipmentId/{shipmentId:.+}")
	@ApiOperation(value = "Delete a Shipment by Shipment ID")
	public ResponseEntity<?> deleteShipment(
			@PathVariable(name = "shipmentId") @ApiParam(value = "Shipment ID", example = "1") final long shipmentId) {
		LOG.trace("deleteShipment({})", shipmentId);

		shipmentService.deleteShipment(shipmentId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/trackingUuid/{trackingUuid:.+}")
	@ApiOperation(value = "Get a Shipment by Tracking UUID", response = Shipment.class, responseContainer = "Page")
	public ResponseEntity<?> getShipmentByTrackingUuid(@PathVariable(value = "Tracking UUID") final UUID trackingUuid) {
		LOG.trace("getShipmentByTrackingUuid({})", trackingUuid);

		Shipment shipment = shipmentService.getShipmentByTrackingUuid(trackingUuid);

		shipment.add(linkTo(methodOn(ShipmentRest.class).getShipmentByShipmentId(shipment.getShipmentId())).withSelfRel());
		shipment.add(linkTo(methodOn(ShipmentRest.class).getShipmentsByCustomerId(1)).withRel("list"));

		return ResponseEntity.ok().build();
		// shipmentService.getShipmentByTrackingUuid(trackingUuid));
	}

	@GetMapping("/customerId/{customerId:.+}")
	@ApiOperation(value = "Get a Shipment by Customer ID", response = Shipment.class, responseContainer = "Page")
	public ResponseEntity<?> getShipmentsByCustomerId(@PathVariable(value = "Customer ID") final long customerId) {
		LOG.trace("getShipmentsByCustomerId({})", customerId);

		List<Shipment> shipments = shipmentService.getShipmentsByCustomerId(customerId);

		shipments.forEach(s -> {
			s.add(linkTo(methodOn(ShipmentRest.class).getShipmentByShipmentId(s.getShipmentId())).withSelfRel());
		});

		Resources<Shipment> responseResources =
				new Resources<>(shipments, linkTo(methodOn(ShipmentRest.class).getShipmentsByCustomerId(customerId)).withRel("list"));

		return ResponseEntity.ok(responseResources);
	}

	@GetMapping("/shipmentId/{shipmentId:.+}")
	@ApiOperation(value = "Get a Shipment by Shipment ID", response = Shipment.class)
	public ResponseEntity<?> getShipmentByShipmentId(
			@PathVariable(name = "shipmentId") @ApiParam(value = "Shipment ID", example = "1") final long shipmentId) {
		LOG.trace("getShipmentByShipmentId({})", shipmentId);

		Shipment shipment = shipmentService.getShipmentByShipmentId(shipmentId);

		shipment.add(linkTo(methodOn(ShipmentRest.class).getShipmentByShipmentId(shipment.getShipmentId())).withSelfRel());
		shipment.add(linkTo(methodOn(ShipmentRest.class).getAllShipments(0, 20)).withRel("list"));

		return ResponseEntity.ok(shipment);
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

		return ResponseEntity.ok(shipments.getContent());
	}

}
