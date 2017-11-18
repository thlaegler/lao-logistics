package com.laegler.lao.service.tour.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laegler.lao.model.entity.Address;

@Repository
public interface AddressJpaRepository extends JpaRepository<Address, Long> {

	Address findNearToFirstByLatitudeAndLongitude(final String latitude, final String longitude);

	// Page<Tour> findPageAll(PageRequest pageRequest);

}
