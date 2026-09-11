package com.tripweave.api.trip;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
class TripService {

  private final TripRepository tripRepository;

  TripService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  Trip create(CreateTripRequest request) {
    var normalizedTimeZone = normalizeTimeZone(request.timeZone());
    var normalizedRequest =
        new CreateTripRequest(
            request.name(),
            request.startDate(),
            request.endDate(),
            normalizedTimeZone,
            request.creatorId());

    return tripRepository.save(normalizedRequest);
  }

  Trip get(UUID id) {
    return tripRepository.findById(id).orElseThrow(() -> new TripNotFoundException(id));
  }

  private String normalizeTimeZone(String timeZone) {
    try {
      return ZoneId.of(timeZone.trim()).getId();
    } catch (DateTimeException exception) {
      throw new InvalidTimeZoneException(timeZone);
    }
  }
}
