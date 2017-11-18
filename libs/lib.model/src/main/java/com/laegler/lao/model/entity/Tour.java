package com.laegler.lao.model.entity;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tour")
public class Tour extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 7222760779815326475L;

	@Id
	@GeneratedValue(strategy = AUTO)
	private long tourId;

	// @JsonIgnore
	// @Column
	// private long employeeId;

	@Column
	private LocalDateTime scheduledDateTime;

	@Column
	private Duration scheduledDuration;

	@ManyToOne
	private Vehicle vehicle;

	@ManyToOne
	private Employee employee;

	@ManyToMany
	private List<Shipment> shipments;

	@ManyToOne
	private Facility facility;

	@ManyToOne
	private Address destination;

}
