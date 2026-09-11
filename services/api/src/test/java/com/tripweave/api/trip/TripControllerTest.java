package com.tripweave.api.trip;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TripController.class)
class TripControllerTest {

  private static final UUID TRIP_ID = UUID.fromString("20000000-0000-0000-0000-000000000001");

  @Autowired private MockMvc mockMvc;

  @MockitoBean private TripService tripService;

  @Test
  void createsTrip() throws Exception {
    var trip =
        new Trip(
            TRIP_ID,
            "Boston weekend",
            LocalDate.of(2026, 10, 10),
            LocalDate.of(2026, 10, 12),
            "America/New_York",
            UUID.fromString("10000000-0000-0000-0000-000000000001"),
            Instant.parse("2026-09-11T12:00:00Z"));
    when(tripService.create(any())).thenReturn(trip);

    mockMvc
        .perform(
            post("/api/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                      "name": "Boston weekend",
                      "startDate": "2026-10-10",
                      "endDate": "2026-10-12",
                      "timeZone": "America/New_York",
                      "creatorId": "10000000-0000-0000-0000-000000000001"
                    }
                    """))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/api/trips/" + TRIP_ID))
        .andExpect(jsonPath("$.id").value(TRIP_ID.toString()))
        .andExpect(jsonPath("$.name").value("Boston weekend"))
        .andExpect(jsonPath("$.timeZone").value("America/New_York"));
  }

  @Test
  void retrievesTrip() throws Exception {
    var trip =
        new Trip(
            TRIP_ID,
            "Boston weekend",
            LocalDate.of(2026, 10, 10),
            LocalDate.of(2026, 10, 12),
            "America/New_York",
            UUID.fromString("10000000-0000-0000-0000-000000000001"),
            Instant.parse("2026-09-11T12:00:00Z"));
    when(tripService.get(TRIP_ID)).thenReturn(trip);

    mockMvc
        .perform(get("/api/trips/{id}", TRIP_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(TRIP_ID.toString()))
        .andExpect(jsonPath("$.name").value("Boston weekend"));
  }

  @Test
  void rejectsInvalidRequest() throws Exception {
    mockMvc
        .perform(
            post("/api/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                      "name": "",
                      "startDate": "2026-10-12",
                      "endDate": "2026-10-10",
                      "timeZone": "",
                      "creatorId": null
                    }
                    """))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Validation failed"))
        .andExpect(jsonPath("$.fieldErrors.name").exists())
        .andExpect(jsonPath("$.fieldErrors.timeZone").exists())
        .andExpect(jsonPath("$.fieldErrors.creatorId").exists())
        .andExpect(jsonPath("$.fieldErrors.dateRangeValid").exists());

    verifyNoInteractions(tripService);
  }

  @Test
  void returnsNotFoundError() throws Exception {
    when(tripService.get(TRIP_ID)).thenThrow(new TripNotFoundException(TRIP_ID));

    mockMvc
        .perform(get("/api/trips/{id}", TRIP_ID))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Trip not found: " + TRIP_ID));
  }
}
