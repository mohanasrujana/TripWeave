package com.tripweave.api.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class CreateTripRequestTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void acceptsValidRequest() {
    var request =
        new CreateTripRequest(
            "Boston weekend",
            LocalDate.of(2026, 10, 10),
            LocalDate.of(2026, 10, 12),
            "America/New_York",
            UUID.fromString("10000000-0000-0000-0000-000000000001"));

    assertTrue(validator.validate(request).isEmpty());
  }

  @Test
  void rejectsReversedDateRange() {
    var request =
        new CreateTripRequest(
            "Boston weekend",
            LocalDate.of(2026, 10, 12),
            LocalDate.of(2026, 10, 10),
            "America/New_York",
            UUID.fromString("10000000-0000-0000-0000-000000000001"));

    var violations = validator.validate(request);

    assertEquals(1, violations.size());
    assertEquals(
        "endDate must be on or after startDate", violations.iterator().next().getMessage());
  }

  @Test
  void rejectsMissingAndBlankFields() {
    var request = new CreateTripRequest(" ", null, null, "", null);

    Set<String> invalidFields =
        validator.validate(request).stream()
            .map(violation -> violation.getPropertyPath().toString())
            .collect(Collectors.toSet());

    assertEquals(Set.of("name", "startDate", "endDate", "timeZone", "creatorId"), invalidFields);
  }
}
