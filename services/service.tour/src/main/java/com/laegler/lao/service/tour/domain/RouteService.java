package com.laegler.lao.service.tour.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.laegler.lao.model.Route;
import com.laegler.lao.model.entity.Tour;
import com.laegler.lao.service.tour.util.DistanceComparator;
import com.laegler.lao.service.tour.util.Metadata;
import com.laegler.lao.service.tour.util.RouteUtil;

@Service
public class RouteService {

	private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

	@Autowired
	private Metadata meta;

	@Autowired
	private TourService tourService;

	public DirectionsRoute getRoute(long tourId) {
		LOG.trace("getRoute({})", tourId);
		final Tour tour = tourService.getTourByTourId(tourId);

		return calculateRoute(tour);
	}

	private DirectionsRoute calculateRoute(Tour tour) {
		LOG.trace("calculateRoute({})", tour);

		final LatLng fromFacility = RouteUtil.toLatLng(tour.getFacility().getAddress());
		final List<LatLng> destinations = tour.getShipments().stream()
				.map(s -> RouteUtil.toLatLng(s.getCustomer().getAddress())).collect(Collectors.toList());
		List<LatLng> waypoints = new LinkedList<>();
		waypoints.add(fromFacility);
		waypoints.addAll(destinations);
		waypoints.add(fromFacility);
		final DirectionsResult directions = estimateRoutes(waypoints);
		LOG.debug("Directions: {}", directions);

		return Arrays.asList(directions.routes).get(0);
	}

	private Route calculateRoute_old(Tour tour) {
		LOG.trace("calculateRoute({})", tour);

		final LatLng fromFacility = RouteUtil.toLatLng(tour.getFacility().getAddress());
		final List<LatLng> destinations = tour.getShipments().stream()
				.map(s -> RouteUtil.toLatLng(s.getCustomer().getAddress())).collect(Collectors.toList());

		DistanceMatrix matrix = estimateRoutes_old(fromFacility, destinations);

		final Map<LatLng, Distance> distances = new LinkedHashMap<>();
		final AtomicInteger index = new AtomicInteger();
		Arrays.asList(Arrays.asList(matrix.rows).get(0).elements).forEach(elem -> {
			distances.put(destinations.get(index.incrementAndGet()), elem.distance);
		});
		Distance[] distanceArray = (Distance[]) distances.entrySet().stream().map(map -> map.getValue())
				.collect(toList()).toArray();
		Arrays.sort(distanceArray, new DistanceComparator());

		return null;
	}

	public DirectionsResult estimateRoutes(List<LatLng> waypoints) {
		final GeoApiContext context = new GeoApiContext.Builder().apiKey(meta.getApiKey()).build();

		try {
			DirectionsApiRequest req = DirectionsApi.newRequest(context).optimizeWaypoints(true)
					.waypoints(waypoints.toArray(new LatLng[waypoints.size()])).departureTime(DateTime.now())
					.language(meta.getLocaleString());
			return req.await();
		} catch (Exception e) {
			LOG.error("Fehler", e);
		}
		return null;
	}

	public DistanceMatrix estimateRoutes_old(LatLng from, List<LatLng> destinations) {
		final GeoApiContext context = new GeoApiContext.Builder().apiKey(meta.getApiKey()).build();

		try {
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
			req.departureTime(DateTime.now());
			// req.arrivalTime(DateTime.now());
			DirectionsApi.RouteRestriction routeRestriction = DirectionsApi.RouteRestriction.TOLLS;
			DistanceMatrix trix = req.origins(from).destinations(destinations.toArray(new LatLng[0]))
					.mode(TravelMode.DRIVING).avoid(routeRestriction).language("fr-FR").await();
			return trix;
		} catch (Exception e) {
			LOG.error("Fehler", e);
		}
		return null;
	}

}
