package com.tripweave.api.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

  @Mock private TripRepository tripRepository;

  @InjectMocks private TripService tripService;

  @Test
  void normalizesTimeZoneBeforeSaving() {
    var request =
        new CreateTripRequest(
            "Boston weekend",
            LocalDate.of(2026, 10, 10),
            LocalDate.of(2026, 10, 12),
            " America/New_York ",
            UUID.fromString("10000000-0000-0000-0000-000000000001"));
    var saved =
        new Trip(
            UUID.fromString("20000000-0000-0000-0000-000000000001"),
            "Boston weekend",
            request.startDate(),
            request.endDate(),
            "America/New_York",
            request.creatorId(),
            Instant.parse("2026-09-11T12:00:00Z"));
    when(tripRepository.save(any())).thenReturn(saved);

    var result = tripService.create(request);

    var requestCaptor = ArgumentCaptor.forClass(CreateTripRequest.class);
    verify(tripRepository).save(requestCaptor.capture());
    assertEquals("America/New_York", requestCaptor.getValue().timeZone());
    assertSame(saved, result);
  }

  @Test
  void rejectsUnknownTimeZoneWithoutSaving() {
    var request =
        new CreateTripRequest(
            "Boston weekend",
            LocalDate.of(2026, 10, 10),
            LocalDate.of(2026, 10, 12),
            "Not/A_Time_Zone",
            UUID.fromString("10000000-0000-0000-0000-000000000001"));

    assertThrows(InvalidTimeZoneException.class, () -> tripService.create(request));
    verifyNoInteractions(tripRepository);
  }

  @Test
  void throwsWhenTripDoesNotExist() {
    var id = UUID.fromString("30000000-0000-0000-0000-000000000001");
    when(tripRepository.findById(id)).thenReturn(Optional.empty());

    var exception = assertThrows(TripNotFoundException.class, () -> tripService.get(id));

    assertEquals("Trip not found: " + id, exception.getMessage());
  }
}
