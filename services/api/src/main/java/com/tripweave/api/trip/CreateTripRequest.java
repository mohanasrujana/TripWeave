package com.tripweave.api.trip;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

record CreateTripRequest(
    @NotBlank @Size(max = 120) String name,
    @NotNull LocalDate startDate,
    @NotNull LocalDate endDate,
    @NotBlank @Size(max = 64) String timeZone,
    @NotNull UUID creatorId) {

  @AssertTrue(message = "endDate must be on or after startDate")
  boolean isDateRangeValid() {
    return startDate == null || endDate == null || !endDate.isBefore(startDate);
  }
}
