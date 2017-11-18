package com.laegler.lao.model.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.GenerationType.AUTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "shipment")
public class Shipment extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 8442665317458168773L;

	@Id
	@GeneratedValue(strategy = AUTO)
	private long shipmentId;

	@Column
	private UUID trackingUuid;

	@Column
	private String volume;

	@Column
	private String weight;

	@Column
	private LocalDateTime createDateTime;

	@Column
	private LocalDateTime lastEditDate;

	@ManyToOne
	private Facility facility;

	@ManyToOne
	private Customer customer;

	@OneToMany(cascade = MERGE)
	private List<ShipmentStatus> shipmentStatus;

	@OneToOne
	private Payment payment;

	@ManyToMany
	private List<Tour> tours;

}
