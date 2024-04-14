package com.realestateapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class RandomApartmentGeneratorTest {
  private static final double MAX_MULTIPLIER = 4.0;

  @Nested
  class DefaultMinAreaMinPrice {
    RandomApartmentGenerator randomApartmentGenerator;

    @BeforeEach
    void setup() {
      randomApartmentGenerator = new RandomApartmentGenerator();
    }

    @Test
    void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
      // Given
      double minArea = 30;
      double maxArea = minArea * MAX_MULTIPLIER;
      BigDecimal minPricePerSquareMeter = new BigDecimal(3000);
      BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

      // When
      Apartment apartment = randomApartmentGenerator.generate();

      // Then
      assertAll(
          () -> assertTrue(apartment.getArea() >= minArea),
          () -> assertTrue(apartment.getArea() <= maxArea),
          () -> assertTrue(apartment.getPrice()
              .compareTo(minPricePerSquareMeter.multiply(new BigDecimal(apartment.getArea()))) >= 0),
          () -> assertTrue(apartment.getPrice()
              .compareTo(maxPricePerSquareMeter.multiply(new BigDecimal(apartment.getArea()))) <= 0));
    }
  }

  @Nested
  class CustomMinAreaMinPrice {
    RandomApartmentGenerator randomApartmentGenerator;
    Random random;

    @BeforeEach
    void setup() {
      randomApartmentGenerator = new RandomApartmentGenerator();
      random = new Random();
    }

    @Test
    void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {
      // Given
      double minArea = random.nextInt(50) + 1;
      double maxArea = minArea * MAX_MULTIPLIER;
      BigDecimal minPricePerSquareMeter = new BigDecimal((random.nextInt(1000) + 1) * 10);
      BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

      // When
      Apartment apartment = randomApartmentGenerator.generate();

      // Then
      assertAll(
          () -> assertTrue(apartment.getArea() >= minArea),
          () -> assertTrue(apartment.getArea() <= maxArea),
          () -> assertTrue(apartment.getPrice()
              .compareTo(minPricePerSquareMeter.multiply(new BigDecimal(apartment.getArea()))) >= 0),
          () -> assertTrue(apartment.getPrice()
              .compareTo(maxPricePerSquareMeter.multiply(new BigDecimal(apartment.getArea()))) <= 0));
    }
  }
}
