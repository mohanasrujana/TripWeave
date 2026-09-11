package com.tripweave.api.trip;

class InvalidTimeZoneException extends RuntimeException {

  InvalidTimeZoneException(String timeZone) {
    super("Unknown time zone: " + timeZone);
  }
}
