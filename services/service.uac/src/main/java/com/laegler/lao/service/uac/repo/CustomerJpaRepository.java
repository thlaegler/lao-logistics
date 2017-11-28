package com.laegler.lao.service.uac.repo;

import com.laegler.lao.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

	Customer findFirstByLastName(final String lastname);

	Customer findFirstByEmail(final String email);

}
