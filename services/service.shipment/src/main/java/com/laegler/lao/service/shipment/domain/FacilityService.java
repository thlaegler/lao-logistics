package com.laegler.lao.service.shipment.domain;

import com.laegler.lao.model.entity.Facility;
import com.laegler.lao.service.shipment.repository.FacilityJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class FacilityService {

	private static final Logger LOG = LoggerFactory.getLogger(FacilityService.class);

	@Autowired
	private FacilityJpaRepository facilityJpaRepository;

	@Transactional
	public Facility addFacility(final Facility facility) {
		LOG.trace("addFacility({})", facility);

		return facilityJpaRepository.save(facility);
	}

	@Transactional
	public Facility updateFacility(final Facility facility) {
		LOG.trace("updateFacility({})", facility);

		return facilityJpaRepository.save(facility);
	}

	@Transactional
	public void deleteFacility(final long facilityId) {
		LOG.trace("deleteFacility({})", facilityId);

		facilityJpaRepository.delete(facilityId);
	}

	public Facility getFacilityByFacilityId(final long facilityId) {
		LOG.trace("getFacilityByFacilityId({})", facilityId);

		return facilityJpaRepository.findOne(facilityId);
	}

	public Page<Facility> getAllFacilities(final PageRequest pageRequest) {
		LOG.trace("getAllFacilities({})", pageRequest);

		return facilityJpaRepository.findAll(pageRequest);
	}
}
