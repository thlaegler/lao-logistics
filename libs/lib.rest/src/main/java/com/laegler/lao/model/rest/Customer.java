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
public class Customer implements Serializable {

	private static final long serialVersionUID = 7222760779815326475L;

	@ApiModelProperty
	private long id;

	@ApiModelProperty
	private String username;

	@ApiModelProperty
	private String email;

	@ApiModelProperty
	private Address address;

}
