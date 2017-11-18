package com.laegler.lao.service.shipment.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laegler.lao.model.entity.Shipment;
import com.laegler.lao.service.shipment.domain.ShipmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Shipment Service")
@RestController
@RequestMapping(value = "/shipment", produces = APPLICATION_JSON_VALUE)
public class ShipmentRest {

	private static final Logger LOG = LoggerFactory.getLogger(ShipmentRest.class);

	@Autowired
	private ShipmentService shipmentService;

	@GetMapping("/id/{shipmentId:.+}")
	@ApiOperation(value = "Get a Shipment by Shipment ID", response = Shipment.class, responseContainer = "Page")
	public ResponseEntity<?> getShipmentByShipmentId(@PathVariable(value = "Shipment ID") final long shipmentId) {
		LOG.trace("getShipmentById({})", shipmentId);

		return ResponseEntity.ok(shipmentService.getShipmentByShipmentId(shipmentId));
	}

	@GetMapping("/trackingUuid/{trackingUuid:.+}")
	@ApiOperation(value = "Get a Shipment by Tracking UUID", response = Shipment.class, responseContainer = "Page")
	public ResponseEntity<?> getShipmentByTrackingUuid(@PathVariable(value = "Tracking UUID") final UUID trackingUuid) {
		LOG.trace("getShipmentByTrackingUuid({})", trackingUuid);

		Shipment shipment = shipmentService.getShipmentByTrackingUuid(trackingUuid);

		shipment.add(
				linkTo(methodOn(ShipmentRest.class).getShipmentByShipmentId(shipment.getShipmentId())).withSelfRel());
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

		Resources<Shipment> responseResources = new Resources<>(shipments,
				linkTo(methodOn(ShipmentRest.class).getShipmentsByCustomerId(customerId)).withRel("list"));

		return ResponseEntity.ok(responseResources);
	}

}
