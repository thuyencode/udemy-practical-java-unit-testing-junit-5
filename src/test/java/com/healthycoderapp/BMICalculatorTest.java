package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class BMICalculatorTest {
  @Test
  void should_ReturnTrue_When_DietRecommended() {
    // Given
    double weight = 89;
    double height = 1.72;

    // When
    boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

    // Then
    assertTrue(isRecommended);
  }

  @Test
  void should_ReturnFalse_When_DietNotRecommended() {
    // Given
    double weight = 50;
    double height = 1.92;

    // When
    boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

    // Then
    assertFalse(isRecommended);
  }

  @Test
  void should_ThrowArithmeticException_When_HeightIsZero() {
    // Given
    double weight = 50;
    double height = 0;

    // When
    Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

    // Then
    assertThrows(ArithmeticException.class, executable);
  }
}
