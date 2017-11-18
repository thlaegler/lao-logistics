package com.laegler.lao.service.tour.util;

import com.google.maps.model.LatLng;
import com.laegler.lao.model.entity.Address;

public class RouteUtil {

	public static LatLng toLatLng(Address address) {
		return new LatLng(Double.parseDouble(address.getLatitude()), Double.parseDouble(address.getLongitude()));
	}

}
