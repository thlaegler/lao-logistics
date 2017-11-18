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
public class Order implements Serializable {

	private static final long serialVersionUID = -3099645754476227424L;

	@ApiModelProperty(readOnly = true)
	private long id;

	@ApiModelProperty
	private String trackingNumber;
}
