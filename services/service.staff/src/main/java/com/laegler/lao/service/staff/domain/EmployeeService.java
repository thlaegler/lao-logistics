package com.laegler.lao.service.staff.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.laegler.lao.model.entity.Employee;
import com.laegler.lao.service.staff.repository.EmployeeJpaRepository;

@Service
public class EmployeeService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeJpaRepository employeeJpaRepository;

	public Employee addEmployee(final Employee employee) {
		LOG.trace("addEmployee({})", employee);

		return employeeJpaRepository.save(employee);
	}

	public Employee updateEmployee(final Employee employee) {
		LOG.trace("updateEmployee({})", employee);

		return employeeJpaRepository.save(employee);
	}

	public void deleteEmployee(final long employeeId) {
		LOG.trace("deleteEmployee({})", employeeId);

		employeeJpaRepository.delete(employeeId);
	}

	public Employee getEmployeeByEmployeeId(final long employeeId) {
		LOG.trace("getEmployeeByEmployeeId({})", employeeId);

		return employeeJpaRepository.findOne(employeeId);
	}

	public Page<Employee> getAllEmployees(final PageRequest pageRequest) {
		LOG.trace("getAllEmployees({})", pageRequest);

		return employeeJpaRepository.findAll(pageRequest);
	}
}
