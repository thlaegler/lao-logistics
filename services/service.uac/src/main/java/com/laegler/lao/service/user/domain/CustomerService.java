package com.laegler.lao.service.user.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.laegler.lao.model.entity.Customer;
import com.laegler.lao.service.user.repo.CustomerJpaRepository;

@Service
public class CustomerService {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerJpaRepository customerJpaRepository;

	public Customer addCustomer(final Customer customer) {
		return customerJpaRepository.save(customer);
	}

	public Customer updateCustomer(final Customer customer) {
		return customerJpaRepository.save(customer);
	}

	public void deleteCustomer(final long customerId) {
		customerJpaRepository.delete(customerId);
	}

	public Page<Customer> getAllCustomers(final PageRequest pageRequest) {
		return customerJpaRepository.findAll(pageRequest);
	}

	public Customer getCustomerByCustomerId(final long customerId) {
		return customerJpaRepository.findOne(customerId);
	}

	public Customer getCustomerByUsername(final String username) {
		return customerJpaRepository.findByUsername(username);
	}

	public Customer getCustomerByEmail(final String email) {
		return customerJpaRepository.findByEmail(email);
	}
}
