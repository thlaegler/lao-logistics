package com.laegler.lao.service.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laegler.lao.model.entity.Employee;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

}
