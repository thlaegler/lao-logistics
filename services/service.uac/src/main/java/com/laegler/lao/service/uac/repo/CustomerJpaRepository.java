package com.laegler.lao.service.uac.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laegler.lao.model.entity.Customer;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

	Customer findByUsername(final String username);

	Customer findByEmail(final String email);

}
