package com.laegler.lao.service.tour.domain;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.laegler.lao.model.entity.Tour;
import com.laegler.lao.service.tour.repo.TourJpaRepository;

@Service
public class TourService {

	private static final Logger LOG = LoggerFactory.getLogger(TourService.class);

	@Autowired
	private TourJpaRepository jpaRepository;

	public Tour addTour(final Tour order) {
		return jpaRepository.save(order);
	}

	public Tour updateTour(final Tour order) {
		return jpaRepository.save(order);
	}

	public void deleteTour(final long tourId) {
		jpaRepository.delete(tourId);
	}

	public Page<Tour> getCurrentTours(final PageRequest pageRequest) {
		return jpaRepository.findByScheduledDateTimeAfterAndScheduledDateTimeBefore(LocalDateTime.now().minusHours(12),
				LocalDateTime.now().plusHours(12), pageRequest);
	}

	public Tour getCurrentTourByDriverId(final long userId) {
		return jpaRepository
				.findFirstByEmployeeAndScheduledDateTimeAfterAndScheduledDateTimeBeforeOrderByScheduledDateTimeDesc(
						userId, LocalDateTime.now().minusHours(12), LocalDateTime.now().plusHours(12));
	}

	public Tour getTourByTourId(final long tourId) {
		return jpaRepository.findOne(tourId);
	}

	public Page<Tour> getAllTours(final PageRequest pageRequest) {
		return jpaRepository.findAll(pageRequest);
	}

}
