package com.laegler.lao.model.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.laegler.lao.model.type.ShipmentStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "shipment_status")
public class ShipmentStatus implements Serializable {

	private static final long serialVersionUID = -5045884797185277420L;

	@ApiModelProperty(example = "123", readOnly = true)
	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;

	@ApiModelProperty(example = "TRUCK")
	@Enumerated(STRING)
	@Column
	private ShipmentStatusType shipmentType;

	@ApiModelProperty(example = "delivered to home")
	@Column
	private String comment;

	@ManyToOne
	private Employee employee;
}
