package com.laegler.lao.service.tour.util;

import java.util.Comparator;

import com.google.maps.model.Distance;

public class DistanceComparator implements Comparator<Distance> {

	@Override
	public int compare(Distance o1, Distance o2) {
		long diff = o1.inMeters - o2.inMeters;
		if (diff > 0) {
			return 1;
		} else if (diff < 0) {
			return -1;
		} else {
			return 0;
		}
	}

}
