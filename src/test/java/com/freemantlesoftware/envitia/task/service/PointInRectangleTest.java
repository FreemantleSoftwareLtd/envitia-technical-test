package com.freemantlesoftware.envitia.task.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointInRectangleTest {

    private static Stream<Arguments> pointInRectangle_happyPath_testData() {
        // Argument Order: [Expected Result], [Shape Data], [Point Data]
        return Stream.of(
                Arguments.of(true, new double[][]{{1, 1}, {1, 7}, {10, 7}, {10, 1}}, new double[]{2, 6}),            // Envitia Example
                Arguments.of(true, new double[][]{{-1, -1}, {-1, 1}, {1, 1}, {1, -1}, {-1, -1}}, new double[]{0, 0}), // Envitia Test Set 2
                Arguments.of(true, new double[][]{{1, 1}, {10, 7}, {10, 1}, {1, 7}}, new double[]{2, 6}),            // Envitia Test Set 3
                Arguments.of(true, new double[][]{{1, 1}, {1, 3}, {3, 3}, {5, 3}, {5, 2}, {5, 1}}, new double[]{4, 2})   // Envitia Test Set 4
        );
    }

    @ParameterizedTest
    @MethodSource("pointInRectangle_happyPath_testData")
    void test_pointInRectangle_expect_success(boolean expectedResult, double[][] shapeData, double[] point) {
        // Given
        BoundaryDetectionService service = new BoundaryDetectionService(new ShapeFinder(), new RectangleBoundaryDetection());
        // When / Then
        assertEquals(expectedResult, service.isInsideRectangle(shapeData, point));
    }


    private static Stream<Arguments> pointInRectangle_doubles_happyPath_testData() {
        // Argument Order: [Expected Result], [Shape Data], [Point Data]
        return Stream.of(
                Arguments.of(true, new double[][]{{1.2, 1.3}, {1.2, 7.3}, {10.2, 7.3}, {10.2, 1.3}}, new double[]{2.2, 6.3}),
                Arguments.of(true, new double[][]{{-1.1, -1.1}, {-1.1, 1.1}, {1.1, 1.1}, {1.1, -1.1}, {-1.1, -1.1}}, new double[]{0.0, 0.0}),
                Arguments.of(true, new double[][]{{1.3, 1.3}, {10.3, 7.3}, {10.3, 1.3}, {1.3, 7.3}}, new double[]{2.3, 6.3}),
                Arguments.of(true, new double[][]{{1.4, 1.4}, {1.4, 3.4}, {3.4, 3.4}, {5.4, 3.4}, {5.4, 2.4}, {5.4, 1.4}}, new double[]{4.4, 2.4}),
                Arguments.of(true, new double[][]{{50.88234, 0.26285}, {50.88234, 0.26032}, {50.88215, 0.26032}, {50.88215, 0.26285}}, new double[]{50.88220, 0.26100}), // Real world coordinates
                Arguments.of(true, new double[][]{{1, 1}, {10, Double.MAX_VALUE}, {10, 1}, {1, Double.MAX_VALUE}}, new double[]{2, 6}) // Massive Rectangle
        );
    }

    @ParameterizedTest
    @MethodSource("pointInRectangle_doubles_happyPath_testData")
    void test_pointInRectangle_floating_point_values_success(boolean expectedResult, double[][] shapeData, double[] point) {
        // Given
        BoundaryDetectionService service = new BoundaryDetectionService(new ShapeFinder(), new RectangleBoundaryDetection());
        // When / Then
        assertEquals(expectedResult, service.isInsideRectangle(shapeData, point));
    }


    private static Stream<Arguments> pointInRectangle_unhappyPath_testData() {
        // Argument Order: [Expected Result], [Shape Data], [Point Data]
        return Stream.of(
                Arguments.of(false, new double[][]{{1, 1}, {2, 7}, {10, 7}, {9, 1}}, new double[]{8, 2}),   // Envitia Test Set 1
                Arguments.of(false, new double[][]{{0, 0}, {3, 0}, {3, 10}, {0, 10}}, new double[]{2, 10}), // check bottom edge
                Arguments.of(false, new double[][]{{0, 0}, {3, 0}, {3, 10}, {0, 10}}, new double[]{1, 0}),  // check top edge
                Arguments.of(false, new double[][]{{0, 0}, {3, 0}, {3, 10}, {0, 10}}, new double[]{3, 1}),  // check right edge
                Arguments.of(false, new double[][]{{0, 0}, {3, 0}, {3, 10}, {0, 10}}, new double[]{0, 1}),  // check left edge
                Arguments.of(false, new double[][]{{-1, 0}, {0, 0}, {0, 10}, {3, 10}, {3, 0}}, new double[]{1, 1}),      // check pan-shape
                Arguments.of(false, new double[][]{{3, 0}, {0, 0}, {0, 5}, {3, 5}, {3, 3}, {2, 3}}, new double[]{1, 1}), // check G-shape
                Arguments.of(false, new double[][]{{0, 2}, {0, 0}, {1, 0}, {1, 1}, {0, 1}}, new double[]{0.5, 0.5}),     // check P-shape
                Arguments.of(false, new double[][]{}, new double[]{2, 10}),  // empty shape data
                Arguments.of(false, new double[][]{{0, 0}, {3, 0}, {3, 10}, {0, 10}}, new double[]{}), // empty point data
                Arguments.of(false, null, new double[]{2, 10}),              // null shape data
                Arguments.of(false, new double[][]{{0, 0}, {3, 0}, {3, 10}, {0, 10}}, null), // null point data
                Arguments.of(false, new double[][]{{Double.NaN, 1}, {1, 7}, {10, 7}, {10, 1}}, new double[]{Double.NaN, Double.NaN}),            // Test double Not a Number.
                Arguments.of(false, new double[][]{{Double.POSITIVE_INFINITY, 1}, {1, 7}, {10, 7}, {10, 1}}, new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}),            // Test infinity.
                Arguments.of(false, new double[][]{{Double.NEGATIVE_INFINITY, 1}, {1, 7}, {10, 7}, {10, 1}}, new double[]{Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY}),            // Test infinity.
                Arguments.of(false, null, null)                             // null shape and point data
        );
    }

    @ParameterizedTest
    @MethodSource("pointInRectangle_unhappyPath_testData")
    void test_pointInRectangle_failures(boolean expectedResult, double[][] shapeData, double[] point) {
        // Given
        BoundaryDetectionService service = new BoundaryDetectionService(new ShapeFinder(), new RectangleBoundaryDetection());
        // When / Then
        assertEquals(expectedResult, service.isInsideRectangle(shapeData, point));
    }
}