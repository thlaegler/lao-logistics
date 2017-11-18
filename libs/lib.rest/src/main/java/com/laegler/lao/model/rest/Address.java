package com.laegler.lao.model.rest;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

	private static final long serialVersionUID = -5045884797185277420L;

	@ApiModelProperty(readOnly = true)
	private long id;

	@ApiModelProperty
	private String receiverName;

	@ApiModelProperty
	private String street;

	@ApiModelProperty
	private Customer customer;
}
