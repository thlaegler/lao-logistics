package com.laegler.lao.model.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.EnumType.STRING;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.laegler.lao.model.type.VehicleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "vehicle")
public class Vehicle extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 7222760779815326475L;

	@ApiModelProperty(example = "123", readOnly = true)
	@Id
	@GeneratedValue
	private long vehicleId;

	@ApiModelProperty(example = "200x200x200")
	@Column
	private String loadVolume;

	@ApiModelProperty(example = "1")
	@Column
	private String loadWeight;

	@Column
	private String licencePlate;

	@ApiModelProperty(example = "TRUCK")
	@Enumerated(STRING)
	@Column
	private VehicleType vehicleType;

	@OneToMany
	private List<Tour> tours;

}
