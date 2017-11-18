package com.laegler.lao.model.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.laegler.lao.model.type.ShipmentStatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipment_status")
public class ShipmentStatus implements Serializable {

	private static final long serialVersionUID = -5045884797185277420L;

	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;

	@Enumerated(STRING)
	private ShipmentStatusType shipmentType;

	@Column
	private String comment;

	@ManyToOne
	private Employee employee;
}
