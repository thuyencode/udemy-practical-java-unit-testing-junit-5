package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BMICalculatorTest {
  @Test
  void test() {
    assertTrue(BMICalculator.isDietRecommended(89, 1.72));
  }
}
