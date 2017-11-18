package com.laegler.lao.service.tour.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laegler.lao.model.entity.Vehicle;

@Repository
public interface VehicleJpaRepository extends JpaRepository<Vehicle, Long> {

	Vehicle findFirstByLicencePlate(final String licencePlate);

	// List<Vehicles> findByDriver(final Driver driver)
}
