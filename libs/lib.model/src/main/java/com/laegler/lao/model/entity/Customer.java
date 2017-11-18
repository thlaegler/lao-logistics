package com.laegler.lao.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
// @Table(name = "customer")
public class Customer extends User implements Serializable {

	private static final long serialVersionUID = 7222760779815326475L;

	@Column
	private long customerId;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@OneToOne
	private Payment defaultPayment;
}
