package com.laegler.lao.service.tour.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.laegler.lao.model.entity.Tour;
import com.laegler.lao.service.tour.domain.TourService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("Tours Service")
@RestController
@RequestMapping(value = "/tours", produces = APPLICATION_JSON_VALUE)
public class TourRest {

	private static final Logger LOG = LoggerFactory.getLogger(TourRest.class);

	@Autowired
	private TourService tourService;

	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a new Tour", response = Tour.class)
	public ResponseEntity<?> addTour(@RequestBody final Tour tour) {
		LOG.trace("addTour({})", tour);

		return ResponseEntity.ok(tourService.addTour(tour));
	}

	@PutMapping(value = "/tourId/{tourId:.+}", consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a Tour by Tour ID", response = Tour.class)
	public ResponseEntity<?> updateTour(@PathVariable(name = "tourId") @ApiParam(name = "tourId", value = "Tour ID") final long tourId,
			@RequestBody final Tour tour) {
		LOG.trace("addTour({})", tour);

		tour.setTourId(tourId);

		return ResponseEntity.ok(tourService.updateTour(tour));
	}

	@DeleteMapping(value = "/tourId/{tourId:.+}")
	@ApiOperation(value = "Delete a Tour by Tour ID")
	public ResponseEntity<?> deleteTour(@PathVariable(name = "tourId") @ApiParam(name = "tourId", value = "Tour ID") final long tourId) {
		LOG.trace("deleteTour({})", tourId);

		tourService.deleteTour(tourId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/current")
	@ApiOperation(value = "Get current Tours", response = Tour.class, responseContainer = "Page")
	public ResponseEntity<?> getCurrentTours(
			@ApiParam(value = "Page number, starting from zero") @RequestParam(value = "page", required = false,
					defaultValue = "0") final Integer page,
			@ApiParam(value = "Number of records per page") @RequestParam(value = "limit", required = false,
					defaultValue = "20") final Integer limit) {
		LOG.debug("getCurrentTours() called");

		return ResponseEntity.ok(tourService.getCurrentTours(new PageRequest(page, limit)));
	}

	@GetMapping("/current/driverId/{driverId:.+}")
	@ApiOperation(value = "Get current Tour By Driver ID", response = Tour.class)
	public ResponseEntity<?> getCurrentTourByDriverId(
			@PathVariable(name = "driverId") @ApiParam(name = "driverId", value = "Driver ID") final long driverId) {
		LOG.trace("getCurrentTourByDriverId({})", driverId);

		return ResponseEntity.ok(tourService.getCurrentTourByDriverId(driverId));
	}

	@GetMapping("/tourId/{tourId:.+}")
	@ApiOperation(value = "Get a Tour by Tour ID", response = Tour.class, responseContainer = "Page")
	public ResponseEntity<?> getTourByTourId(@PathVariable(name = "tourId") @ApiParam(name = "tourId", value = "Tour ID") final long tourId) {
		LOG.trace("getTourByTourId({})", tourId);

		return ResponseEntity.ok(tourService.getTourByTourId(tourId));
	}

	@GetMapping
	@ApiOperation(value = "Get all Tours", response = Tour.class, responseContainer = "Page")
	public ResponseEntity<?> getAllTours(
			@ApiParam(value = "Page number, starting from zero") @RequestParam(value = "page", required = false,
					defaultValue = "0") final Integer page,
			@ApiParam(value = "Number of records per page") @RequestParam(value = "limit", required = false,
					defaultValue = "20") final Integer limit) {
		LOG.debug("getAllTours() called");

		return ResponseEntity.ok(tourService.getAllTours(new PageRequest(page, limit)));
	}

}
