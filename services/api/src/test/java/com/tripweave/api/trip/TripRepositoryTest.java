package com.tripweave.api.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TripRepositoryTest {

  @Autowired private TripRepository tripRepository;

  @Test
  void savesAndFindsTrip() {
    var request =
        new CreateTripRequest(
            "Boston weekend",
            LocalDate.of(2026, 10, 10),
            LocalDate.of(2026, 10, 12),
            "America/New_York",
            UUID.fromString("10000000-0000-0000-0000-000000000001"));

    var saved = tripRepository.save(request);
    var found = tripRepository.findById(saved.id()).orElseThrow();

    assertNotNull(saved.id());
    assertNotNull(saved.createdAt());
    assertEquals("Boston weekend", found.name());
    assertEquals(request.startDate(), found.startDate());
    assertEquals(request.endDate(), found.endDate());
    assertEquals(request.timeZone(), found.timeZone());
    assertEquals(request.creatorId(), found.creatorId());
  }
}
