package com.laegler.lao.service.shipment.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laegler.lao.model.entity.Shipment;

@Repository
public interface ShipmentJpaRepository extends JpaRepository<Shipment, Long> {

	Shipment findFirstByTrackingNumber(final UUID trackingNumber);

	List<Shipment> findByCustomer(long customerId);

}
