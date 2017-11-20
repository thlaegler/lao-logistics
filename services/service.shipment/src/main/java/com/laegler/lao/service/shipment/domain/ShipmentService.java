package com.laegler.lao.service.shipment.domain;

import com.laegler.lao.model.entity.Shipment;
import com.laegler.lao.service.shipment.repository.ShipmentJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ShipmentService {

	private static final Logger LOG = LoggerFactory.getLogger(ShipmentService.class);

	@Autowired
	private ShipmentJpaRepository shipmentJpaRepository;

	@Transactional
	public Shipment addShipment(final Shipment shipment) {
		LOG.trace("addShipment({})", shipment);

		return shipmentJpaRepository.save(shipment);
	}

	@Transactional
	public Shipment updateShipment(final Shipment shipment) {
		LOG.trace("updateShipment({})", shipment);

		return shipmentJpaRepository.save(shipment);
	}

	@Transactional
	public void deleteShipment(final long shipmentId) {
		LOG.trace("deleteShipment({})", shipmentId);

		shipmentJpaRepository.delete(shipmentId);
	}

	public List<Shipment> getShipmentsByCustomerId(long customerId) {
		LOG.trace("getShipmentsByCustomerId({})", customerId);

		return shipmentJpaRepository.findByCustomer(customerId);
	}

	public Shipment getShipmentByTrackingNumber(UUID trackingNumber) {
		LOG.trace("getShipmentByTrackingNumber({})", trackingNumber);

		return shipmentJpaRepository.findFirstByTrackingNumber(trackingNumber);
	}

	public Shipment getShipmentByShipmentId(final long shipmentId) {
		LOG.trace("getShipmentByShipmentId({})", shipmentId);

		return shipmentJpaRepository.findOne(shipmentId);
	}

	public Page<Shipment> getAllShipments(final PageRequest pageRequest) {
		LOG.trace("getAllShipments({})", pageRequest);

		return shipmentJpaRepository.findAll(pageRequest);
	}

}
