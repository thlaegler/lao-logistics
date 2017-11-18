package com.laegler.lao.service.tour.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laegler.lao.model.Route;
import com.laegler.lao.service.tour.domain.RouteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Route Service")
@RestController
@RequestMapping(value = "/routes", produces = APPLICATION_JSON_VALUE)
public class RouteRest {

	private static final Logger LOG = LoggerFactory.getLogger(RouteRest.class);

	@Autowired
	private RouteService routeService;

	@GetMapping("/tourId/{tourId:.+}")
	@ApiOperation(value = "Get Route by Tour ID", response = Route.class)
	public ResponseEntity<?> getRouteByTourId(@PathVariable(value = "Tour ID") final long tourId) {
		LOG.trace("getRouteByTourId({})", tourId);

		return ResponseEntity.ok(routeService.getRoute(tourId));
	}

}
