package com.laegler.lao.service.uac.domain;

import com.laegler.lao.error.NotFoundException;
import com.laegler.lao.model.entity.Customer;
import com.laegler.lao.service.uac.repo.CustomerJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CustomerService {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerJpaRepository customerJpaRepository;

	@Transactional
	public Customer addCustomer(final Customer customer) {
		LOG.trace("addCustomer({})", customer);

		return customerJpaRepository.save(customer);
	}

	@Transactional
	public Customer updateCustomer(final Customer customer) {
		LOG.trace("updateCustomer({})", customer);

		Optional.ofNullable(customerJpaRepository.findOne(customer.getCustomerId()))
				.orElseThrow(() -> new NotFoundException(String.format("Customer with ID %s cannot be found", customer.getCustomerId())));

		return customerJpaRepository.save(customer);
	}

	@Transactional
	public void deleteCustomer(final long customerId) {
		LOG.trace("deleteCustomer({})", customerId);

		Optional.ofNullable(customerJpaRepository.findOne(customerId))
				.orElseThrow(() -> new NotFoundException(String.format("Customer with ID %s cannot be found", customerId)));

		customerJpaRepository.delete(customerId);
	}

	public Customer getCustomerByName(final String name) {
		LOG.trace("getCustomerByCustomername({})", name);

		return Optional.ofNullable(customerJpaRepository.findFirstByLastName(name))
				.orElseThrow(() -> new NotFoundException(String.format("Customer with name %s cannot be found", name)));
	}

	public Customer getCustomerByEmail(final String email) {
		LOG.trace("getCustomerByCustomerId({})", email);

		return Optional.ofNullable(customerJpaRepository.findFirstByEmail(email))
				.orElseThrow(() -> new NotFoundException(String.format("Customer with email %s cannot be found", email)));
	}

	public Customer getCustomerByCustomerId(final long customerId) {
		LOG.trace("getCustomerByCustomerId({})", customerId);

		return Optional.ofNullable(customerJpaRepository.findOne(customerId))
				.orElseThrow(() -> new NotFoundException(String.format("Customer with ID %s cannot be found", customerId)));
	}

	public Page<Customer> getAllCustomers(final PageRequest pageRequest) {
		LOG.trace("getAllCustomers({})", pageRequest);

		return customerJpaRepository.findAll(pageRequest);
	}
}
