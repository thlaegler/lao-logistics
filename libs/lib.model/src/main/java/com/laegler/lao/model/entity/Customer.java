package com.laegler.lao.model.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.EnumType.STRING;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.laegler.lao.model.type.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.io.Serializable;

@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
// @MappedSuperclass
// @Table(name = "customer")
public class Customer extends User implements Serializable {

	private static final long serialVersionUID = 7222760779815326475L;

	@ApiModelProperty(example = "123", required = true)
	@Column
	private long customerId;

	@ApiModelProperty(example = "ABC123")
	@Column
	private String postboxId;

	@ApiModelProperty(example = "John")
	@Column
	private String firstName;

	@ApiModelProperty(example = "The")
	@Column
	private String middleName;

	@ApiModelProperty(example = "Doe")
	@Column
	private String lastName;

	@ApiModelProperty(example = "FEMALE")
	@Column
	@Enumerated(STRING)
	private Gender gender;

	@OneToOne
	private Payment defaultPayment;
}
