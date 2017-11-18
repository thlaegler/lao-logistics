package com.laegler.lao.model.entity;

import static javax.persistence.EnumType.STRING;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.laegler.lao.model.type.VehicleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 7222760779815326475L;

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String loadVolume;

	@Column
	private String loadWeight;

	@Column
	private String licencePlate;

	@Enumerated(STRING)
	private VehicleType vehicleType;

	@OneToMany
	private List<Tour> tours;

}
