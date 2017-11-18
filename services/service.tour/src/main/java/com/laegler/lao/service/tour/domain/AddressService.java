package com.laegler.lao.service.tour.domain;

import com.laegler.lao.model.entity.Address;
import com.laegler.lao.service.tour.repo.AddressJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressService {

	private static final Logger LOG = LoggerFactory.getLogger(TourService.class);

	@Autowired
	private AddressJpaRepository jpaRepository;

	public Address addAddress(final Address address) {
		LOG.trace("addAddress({})", address);

		return jpaRepository.save(address);
	}

	@Transactional
	public Address updateAddress(final Address address) {
		LOG.trace("updateAddress({})", address);

		return jpaRepository.save(address);
	}

	@Transactional
	public void deleteAddress(final long addressId) {
		LOG.trace("deleteAddress({})", addressId);

		jpaRepository.delete(addressId);
	}

	public Address getAddressByAddressId(long addressId) {
		LOG.trace("getAddressByAddressId({})", addressId);

		return jpaRepository.findOne(addressId);
	}

	public Page<Address> getAllAddresses(final PageRequest pageRequest) {
		LOG.trace("getAllAddresses()");

		return jpaRepository.findAll(pageRequest);
	}

	public List<Address> getAllAddresses() {
		LOG.trace("getAllAddresses()");

		return jpaRepository.findAll();
	}


}
