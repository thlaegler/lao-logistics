package com.laegler.lao.service.payment.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laegler.lao.model.entity.Payment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Payment Service")
@RestController
@RequestMapping(value = "/payment", produces = APPLICATION_JSON_VALUE)
public class PaymentRest {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentRest.class);

	// @Autowired
	// private PaymentService paymentService;
	//
	// @GetMapping
	// @ApiOperation(value = "Get all Orders", response = Payment.class,
	// responseContainer = "Page")
	// public ResponseEntity<?> getAllPayments(
	// @ApiParam(value = "Page number, starting from zero") @RequestParam(value =
	// "page", required = false, defaultValue = "0") final Integer page,
	// @ApiParam(value = "Number of records per page") @RequestParam(value =
	// "limit", required = false, defaultValue = "20") final Integer limit) {
	// LOG.debug("getAllPayments() called");
	//
	// return ResponseEntity.ok(paymentService.getAllPayments(new PageRequest(page,
	// limit)));
	// }
	//
	// @GetMapping("/current")
	// @ApiOperation(value = "Get current Payments", response = Payment.class,
	// responseContainer = "Page")
	// public ResponseEntity<?> getCurrentPayments(
	// @ApiParam(value = "Page number, starting from zero") @RequestParam(value =
	// "page", required = false, defaultValue = "0") final Integer page,
	// @ApiParam(value = "Number of records per page") @RequestParam(value =
	// "limit", required = false, defaultValue = "20") final Integer limit) {
	// LOG.debug("getCurrentPayments() called");
	//
	// return ResponseEntity.ok(paymentService.getCurrentPayments(new
	// PageRequest(page,
	// limit)));
	// }
	//
	// @GetMapping("/current/{driverId:.+}")
	// @ApiOperation(value = "Get current Payment By Driver ID", response =
	// Payment.class)
	// public ResponseEntity<?> getCurrentPaymentByDriverId(@PathVariable(value =
	// "Driver ID") final long driverId) {
	// LOG.trace("getCurrentPaymentByDriverId({})", driverId);
	//
	// return
	// ResponseEntity.ok(paymentService.getCurrentPaymentByDriverId(driverId));
	// }

	@GetMapping("/id/{paymentId:.+}")
	@ApiOperation(value = "Get a Payment by Payment ID", response = Payment.class, responseContainer = "Page")
	public ResponseEntity<?> getPaymentById(@PathVariable(value = "Payment ID") final long paymentId) {
		LOG.trace("getPaymentById({})", paymentId);

		return ResponseEntity.ok().build();
		// paymentService.getPaymentById(paymentId));
	}

}
