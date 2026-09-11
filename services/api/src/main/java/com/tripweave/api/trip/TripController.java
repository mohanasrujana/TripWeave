package com.tripweave.api.trip;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trips")
class TripController {

  private final TripService tripService;

  TripController(TripService tripService) {
    this.tripService = tripService;
  }

  @PostMapping
  ResponseEntity<Trip> create(@Valid @RequestBody CreateTripRequest request) {
    var trip = tripService.create(request);
    var location = URI.create("/api/trips/" + trip.id());

    return ResponseEntity.created(location).body(trip);
  }

  @GetMapping("/{id}")
  Trip get(@PathVariable UUID id) {
    return tripService.get(id);
  }
}
