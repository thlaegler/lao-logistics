package com.laegler.lao.service.tour.repo;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laegler.lao.model.entity.Tour;

@Repository
public interface TourJpaRepository extends JpaRepository<Tour, Long> {

	Tour findFirstByShipments(final long shipmentId);

	Page<Tour> findByScheduledDateTimeAfterAndScheduledDateTimeBefore(final LocalDateTime from, final LocalDateTime to,
			final Pageable pageable);

	Tour findFirstByEmployeeAndScheduledDateTimeAfterAndScheduledDateTimeBeforeOrderByScheduledDateTimeDesc(
			final long userId, final LocalDateTime from, final LocalDateTime to);
}
