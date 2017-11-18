package com.laegler.lao.model;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@ApiModel
@Data
@Builder
public class Route extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 4279032477066962059L;

}
