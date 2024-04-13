package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DietPlannerTest {
  private DietPlanner dietPlanner;

  @BeforeEach
  void setup() {
    this.dietPlanner = new DietPlanner(20, 30, 50);
  }

  @AfterEach
  void afterEach() {
    System.err.println("A unit test was finished.");
  }

  @Test
  void should_ReturnCorrectDietPlan_When_CorrectCoder() {
    // Given
    Coder coder = new Coder(1.82, 75, 26, Gender.MALE);
    DietPlan expected = new DietPlan(2202, 110, 73, 275);

    // When
    DietPlan actual = dietPlanner.calculateDiet(coder);

    // Then
    assertAll(
        () -> assertEquals(expected.getCalories(), actual.getCalories()),
        () -> assertEquals(expected.getProtein(), actual.getProtein()),
        () -> assertEquals(expected.getFat(), actual.getFat()),
        () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()));
  }
}
