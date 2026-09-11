package com.tripweave.api.trip;

import java.util.UUID;

class TripNotFoundException extends RuntimeException {

  TripNotFoundException(UUID id) {
    super("Trip not found: " + id);
  }
}
