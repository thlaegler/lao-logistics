package com.laegler.lao.model.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.GenerationType.AUTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table
public class Address extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -5045884797185277420L;

	@ApiModelProperty(example = "123", readOnly = true)
	@Id
	@GeneratedValue(strategy = AUTO)
	private long addressId;

	@ApiModelProperty(example = "12.3456")
	@Column
	private String longitude;

	@ApiModelProperty(example = "12.3456")
	@Column
	private String latitude;

	@ApiModelProperty(example = "John Doe")
	@Column
	private String addressName;

	@ApiModelProperty(example = "3$XYZ%123+=,.<>#")
	@Column
	private String postbox;

	@ApiModelProperty(example = "Baildon Road")
	@Column
	private String street;

	@ApiModelProperty(example = "123")
	@Column
	private String streetNumber;

	@ApiModelProperty(example = "123456")
	@Column
	private String zipcode;

	@ApiModelProperty(example = "Musterhausen")
	@Column
	private String city;

	@ApiModelProperty(example = "none")
	@Column
	private String district;

	@ApiModelProperty(example = "null")
	@Column
	private String state;

	@ApiModelProperty(example = "New Zealand")
	@Column
	private String country;

}
