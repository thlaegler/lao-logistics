package com.laegler.lao.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
// @Table(name = "employee")
public class Employee extends User implements Serializable {

	private static final long serialVersionUID = 7222760779815326475L;

	@Column
	private long employeeId;

	@Column
	private String licence;

	@OneToMany
	private List<Tour> tours;

}
