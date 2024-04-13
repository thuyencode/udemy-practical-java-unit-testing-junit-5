package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class BMICalculatorTest {
  @BeforeAll
  static void beforeAll() {
    System.out.println("Before all the unit tests.");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("After all the unit tests.");
  }

  @ParameterizedTest(name = "weight={0}, height={1}")
  @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
  void should_ReturnTrue_When_DietRecommended(double coderWeight, double coderHeight) {
    // Given
    double weight = coderWeight;
    double height = coderHeight;

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

  @Test
  void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
    // Given
    List<Coder> coders = new ArrayList<>();
    coders.add(new Coder(1.8, 60));
    coders.add(new Coder(1.82, 98));
    coders.add(new Coder(1.82, 64.7));

    // When
    Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

    // Then
    assertAll(
        () -> assertEquals(1.82, coderWithWorstBMI.getHeight()),
        () -> assertEquals(98, coderWithWorstBMI.getWeight()));
  }

  @Test
  void should_ReturnNullWorstBMICoder_When_CoderListEmpty() {
    // Given
    List<Coder> coders = new ArrayList<>();

    // When
    Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

    // Then
    assertNull(coderWithWorstBMI);
  }

  @Test
  void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
    // Given
    List<Coder> coders = new ArrayList<>();
    coders.add(new Coder(1.8, 60));
    coders.add(new Coder(1.82, 98));
    coders.add(new Coder(1.82, 64.7));

    double[] expected = { 18.52, 29.59, 19.53 };

    // When
    double[] bmiScores = BMICalculator.getBMIScores(coders);

    // Then
    assertArrayEquals(expected, bmiScores);
  }

  @Test
  void should_ReturnCoderWithWorstBMIIn1Ms_When_CoderListHas10000Elements() {
    // Given
    List<Coder> coders = new ArrayList<>();

    for (int i = 0; i < 10_000; i++) {
      coders.add(new Coder(1 + i, 10 + i));
    }

    // When
    Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

    // Then
    assertTimeout(Duration.ofMillis(500), executable);
  }
}
