package com.laegler.lao.model.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
// @Table(name = "employee")
public class Employee extends User implements Serializable {

	private static final long serialVersionUID = 7222760779815326475L;

	@ApiModelProperty(example = "123", required = true)
	@Column
	private long employeeId;

	@ApiModelProperty(example = "truck and car")
	@Column
	private String licence;

	@OneToMany
	private List<Tour> tours;

}
