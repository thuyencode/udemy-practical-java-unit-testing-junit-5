package com.realestateapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ApartmentRaterTest {
  @Nested
  class ApartmentRating {

    @ParameterizedTest(name = "area={0}, price={1}, rating={2}")
    @CsvFileSource(resources = "/apartment-rating-data.csv", numLinesToSkip = 1)
    void should_ReturnCorrectRating_When_CorrectApartment(double area, BigDecimal price, int rating) {
      // Given
      Apartment apartment = new Apartment(area, price);
      int expected = rating;

      // When
      int actual = ApartmentRater.rateApartment(apartment);

      // Then
      assertEquals(expected, actual);
    }

    @Test
    void should_ReturnErrorValue_When_IncorrectApartment() {
      // Given
      double area = 0;
      BigDecimal price = new BigDecimal(20000);
      Apartment apartment = new Apartment(area, price);
      int expected = -1;

      // When
      int actual = ApartmentRater.rateApartment(apartment);

      // Then
      assertEquals(expected, actual);
    }
  }

  @Nested
  class ApartmentList {

    @Test
    void should_CalculateAverageRating_When_CorrectApartmentList() {
      // Given
      List<Apartment> apartments = new ArrayList<>();
      apartments.add(new Apartment(30, new BigDecimal(100_000)));
      apartments.add(new Apartment(40, new BigDecimal(130_000)));
      apartments.add(new Apartment(50, new BigDecimal(150_000)));
      apartments.add(new Apartment(72, new BigDecimal(250_000)));
      double expected = 0;

      // When
      double actual = ApartmentRater.calculateAverageRating(apartments);

      // Then
      assertEquals(expected, actual);
    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
      // Given
      List<Apartment> apartments = new ArrayList<>();

      // When
      Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);

      // Then
      assertThrows(RuntimeException.class, executable);
    }
  }

}