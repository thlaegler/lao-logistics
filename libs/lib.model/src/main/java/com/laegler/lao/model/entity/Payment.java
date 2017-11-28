package com.laegler.lao.model.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.laegler.lao.model.type.PaymentType;
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
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "payment")
public class Payment extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -5045884797185277420L;

	@Id
	@GeneratedValue(strategy = AUTO)
	private long paymentId;

	@ApiModelProperty(example = "PAYPAL")
	@Enumerated(STRING)
	@Column
	private PaymentType paymentType;

	@ApiModelProperty(example = "1234-1234-5678-1234-567")
	@Column
	private String creditCardNumber;

	@ApiModelProperty(example = "John Doe")
	@Column
	private String creditCardHolder;

	@ApiModelProperty(example = "VISA")
	@Column
	private String creditCardProvider;

	@ApiModelProperty(example = "tester@example.org")
	@Column
	private String paypalAccount;

}
