package com.laegler.lao.model.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.GenerationType.TABLE;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.io.Serializable;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "user")
@Inheritance(strategy = TABLE_PER_CLASS)
abstract public class User extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 7222760779815326475L;

	@ApiModelProperty(example = "123")
	@Id
	@TableGenerator(name = "USER_ID_GEN", table = "ID_Generator", pkColumnName = "userId", valueColumnName = "sequence", allocationSize = 1)
	@GeneratedValue(strategy = TABLE, generator = "USER_ID_GEN")
	private long userId;

	@ApiModelProperty(example = "tester")
	@Column
	private String username;

	@ApiModelProperty(example = "tester@example.org")
	@Column
	private String email;

	@ApiModelProperty(example = "jvukg42u4yukvv2u424y24")
	@Column
	private String password;

	@ApiModelProperty(example = "en-US")
	@Column
	private String locale;

	@OneToOne
	private Address address;

}
