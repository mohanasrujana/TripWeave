package com.tripweave.api.trip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
class TripRepository {

  private final JdbcClient jdbcClient;

  TripRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  Trip save(CreateTripRequest request) {
    var id = UUID.randomUUID();

    return jdbcClient
        .sql(
            """
            INSERT INTO trips (
                id, name, start_date, end_date, time_zone, creator_id
            )
            VALUES (
                :id, :name, :startDate, :endDate, :timeZone, :creatorId
            )
            RETURNING *
            """)
        .param("id", id)
        .param("name", request.name().trim())
        .param("startDate", request.startDate())
        .param("endDate", request.endDate())
        .param("timeZone", request.timeZone())
        .param("creatorId", request.creatorId())
        .query(TripRepository::mapTrip)
        .single();
  }

  Optional<Trip> findById(UUID id) {
    return jdbcClient
        .sql("SELECT * FROM trips WHERE id = :id")
        .param("id", id)
        .query(TripRepository::mapTrip)
        .optional();
  }

  private static Trip mapTrip(ResultSet resultSet, int rowNumber) throws SQLException {
    return new Trip(
        resultSet.getObject("id", UUID.class),
        resultSet.getString("name"),
        resultSet.getObject("start_date", java.time.LocalDate.class),
        resultSet.getObject("end_date", java.time.LocalDate.class),
        resultSet.getString("time_zone"),
        resultSet.getObject("creator_id", UUID.class),
        resultSet.getTimestamp("created_at").toInstant());
  }
}
