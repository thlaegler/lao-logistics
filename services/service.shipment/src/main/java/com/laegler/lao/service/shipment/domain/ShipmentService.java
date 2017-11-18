package com.laegler.lao.service.shipment.domain;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laegler.lao.model.entity.Shipment;
import com.laegler.lao.service.shipment.repository.ShipmentJpaRepository;

@Service
public class ShipmentService {

	private static final Logger LOG = LoggerFactory.getLogger(ShipmentService.class);

	@Autowired
	private ShipmentJpaRepository repository;

	public Shipment getShipmentByShipmentId(long shipmentId) {
		return repository.findOne(shipmentId);
	}

	public List<Shipment> getShipmentsByCustomerId(long customerId) {
		LOG.trace("getShipmentsByCustomerId({})", customerId);

		return repository.findByCustomer(customerId);
	}

	public Shipment getShipmentByTrackingUuid(UUID trackingUuid) {
		LOG.trace("getShipmentByTrackingUuid({})", trackingUuid);

		return repository.findFirstByTrackingUuid(trackingUuid);
	}

}
