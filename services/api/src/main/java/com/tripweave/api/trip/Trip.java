package com.tripweave.api.trip;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

record Trip(
    UUID id,
    String name,
    LocalDate startDate,
    LocalDate endDate,
    String timeZone,
    UUID creatorId,
    Instant createdAt) {}
