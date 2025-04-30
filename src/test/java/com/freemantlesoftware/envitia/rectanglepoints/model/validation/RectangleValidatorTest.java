package com.freemantlesoftware.envitia.rectanglepoints.model.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleValidatorTest {

    private static Stream<Arguments> rectangleValidator_unhappyPath_testData() {
        // Argument Order: [Expected Result], [Rectangle Data x,y,width,height]
        return Stream.of(
                Arguments.of("Rectangle width and height values cannot be zero.", 0, 0, 0, 0),
                Arguments.of("Rectangle requires argument values to be finite.", Double.NaN, 0, 0, 0),
                Arguments.of("Rectangle requires argument values to be finite.", 0, Double.NaN, 0, 0),
                Arguments.of("Rectangle requires argument values to be finite.", 0, 0, Double.NaN, 0),
                Arguments.of("Rectangle requires argument values to be finite.", 0, 0, 0, Double.NaN),
                Arguments.of("Rectangle requires argument values to be finite.", Double.NaN, Double.NaN, Double.NaN, Double.NaN),
                Arguments.of("Rectangle requires argument values to be finite.", Double.NEGATIVE_INFINITY, 0, 0, 0),
                Arguments.of("Rectangle requires argument values to be finite.", Double.POSITIVE_INFINITY, 0, 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("rectangleValidator_unhappyPath_testData")
    void test_RectangleValidator(String expectedResult, double x, double y, double width, double height) {
        // Given
        RectangleValidator validator = new RectangleValidator();
        // When / Then
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> validator.validate(x, y, width, height));
        assertEquals(expectedResult, result.getMessage());
    }
}
