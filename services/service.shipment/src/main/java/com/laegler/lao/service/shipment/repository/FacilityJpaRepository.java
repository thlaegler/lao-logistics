package com.laegler.lao.service.shipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laegler.lao.model.entity.Facility;

@Repository
public interface FacilityJpaRepository extends JpaRepository<Facility, Long> {

}
