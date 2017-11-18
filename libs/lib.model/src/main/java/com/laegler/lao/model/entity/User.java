package com.laegler.lao.model.entity;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Inheritance(strategy = TABLE_PER_CLASS)
abstract public class User extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 7222760779815326475L;

	@Id
	private long userId;

	@Column
	private String username;

	@ApiModelProperty(example = "test@test.com")
	@Column
	private String email;

	@ApiModelProperty(example = "jvukg42u4yukvv2u424y24")
	@Column
	private String password;

	@OneToOne
	private Address address;

}
