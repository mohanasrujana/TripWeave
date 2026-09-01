package com.tripweave.api.health;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HealthControllerUnitTest {

  @Test
  void returnsUpStatusWithoutSpring() {
    var response = new HealthController().health();

    assertEquals("UP", response.status());
  }
}
