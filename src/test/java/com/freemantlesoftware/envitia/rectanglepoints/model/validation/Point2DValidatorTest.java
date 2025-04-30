package com.freemantlesoftware.envitia.rectanglepoints.model.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Point2DValidatorTest {

    private static Stream<Arguments> point2DValidator_unhappyPathValues_testData() {
        // Argument Order: [Expected Result], [Point Data]
        return Stream.of(
                Arguments.of("Point2D requires two elements (x and y coordinates).", null),
                Arguments.of("Point2D requires two elements (x and y coordinates).", new double[]{}),
                Arguments.of("Point2D requires argument values to be finite.", new double[]{Double.NaN, 0}),
                Arguments.of("Point2D requires argument values to be finite.", new double[]{0, Double.NaN}),
                Arguments.of("Point2D requires argument values to be finite.", new double[]{Double.NaN, Double.NaN}),
                Arguments.of("Point2D requires argument values to be finite.", new double[]{Double.NEGATIVE_INFINITY, 0}),
                Arguments.of("Point2D requires argument values to be finite.", new double[]{Double.POSITIVE_INFINITY, 0})
        );
    }

    @ParameterizedTest
    @MethodSource("point2DValidator_unhappyPathValues_testData")
    void test_point2DValidator(final String expectedResult, final double[] pointData) {
        // Given
        Point2DValidator validator = new Point2DValidator();
        // When / Then
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> validator.validate(pointData));
        assertEquals(expectedResult, result.getMessage());
    }
}
