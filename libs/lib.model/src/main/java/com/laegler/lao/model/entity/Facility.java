package com.laegler.lao.model.entity;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facility")
public class Facility extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -5045884797185277420L;

	@Id
	@GeneratedValue(strategy = AUTO)
	private long facilityId;

	@Column
	private String volume;

	@OneToOne
	private Address address;

	@OneToMany
	private List<Vehicle> vehicles;

	@OneToMany
	private List<Employee> employees;

}
