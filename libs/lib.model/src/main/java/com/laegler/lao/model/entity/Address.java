package com.laegler.lao.model.entity;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Address implements Serializable {

	private static final long serialVersionUID = -5045884797185277420L;

	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;

	@Column
	private String longitude;

	@Column
	private String latitude;

	@Column
	private String addressName;

	@Column
	private String postbox;

	@Column
	private String street;

	@Column
	private String streetNumber;

	@Column
	private String zipcode;

	@Column
	private String city;

	@Column
	private String district;

	@Column
	private String state;

	@Column
	private String country;

}
