package com.laegler.lao.service.tour.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laegler.lao.model.entity.Employee;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

	Employee findFirstByEmail(final String email);

	Employee findFirstByUsername(final String username);

}
