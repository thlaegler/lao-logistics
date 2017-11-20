package com.laegler.lao.model.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.GenerationType.AUTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

	@ApiModelProperty(example = "123", readOnly = true)
	@Id
	@GeneratedValue(strategy = AUTO)
	private long shipmentId;

	@ApiModelProperty(example = "1234ABC5678XYZ", required = true)
	@NotNull(message = "shipment.trackingNumber.validation.notNull")
	@Column
	private String trackingNumber;

	@ApiModelProperty(example = "20x20x30")
	@Column
	private String dimensions;

	@ApiModelProperty(example = "20x20x30")
	@Column
	private String weight;

	@ApiModelProperty(example = "12334545667")
	@CreatedDate
	@Column
	private LocalDateTime createDateTime;

	@ApiModelProperty(example = "12334545667")
	@LastModifiedDate
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
