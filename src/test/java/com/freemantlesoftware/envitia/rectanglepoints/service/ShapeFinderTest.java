package com.freemantlesoftware.envitia.rectanglepoints.service;

import com.freemantlesoftware.envitia.rectanglepoints.model.Point2D;
import com.freemantlesoftware.envitia.rectanglepoints.model.Rectangle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeFinderTest {

    private static Stream<Arguments> shapeFinder_rectangle_testData() {
        // Argument Order: [Expected Result], [Shape Data]
        return Stream.of(
                Arguments.of(new Rectangle(-1, -1, 2, 2), Set.of(new Point2D(-1, -1), new Point2D(-1, 1), new Point2D(1, 1), new Point2D(1, -1))), // Envitia Test Set 2
                Arguments.of(new Rectangle(1, 1, 9, 6), Set.of(new Point2D(1, 1), new Point2D(1, 7), new Point2D(10, 7), new Point2D(10, 1))),  // Envitia Test Set 3
                Arguments.of(new Rectangle(1, 1, 4, 2), Set.of(new Point2D(1, 1), new Point2D(1, 3), new Point2D(3, 3), new Point2D(5, 3), new Point2D(5, 2), new Point2D(5, 1))), // Envitia Test Set 4
                Arguments.of(new Rectangle(1, 1, 9, Double.MAX_VALUE - 1), Set.of(new Point2D(1, 1), new Point2D(1, Double.MAX_VALUE), new Point2D(10, Double.MAX_VALUE), new Point2D(10, 1))), // Massive Rectangle
                Arguments.of(new Rectangle(-10, -7, 9, 6), Set.of(new Point2D(-1, -1), new Point2D(-1, -7), new Point2D(-10, -7), new Point2D(-10, -1))),  // Rectangle with negative coordinates.
                Arguments.of(Rectangle.emptyRectangle(), Set.of(new Point2D(1, 1), new Point2D(1, 2), new Point2D(1, 3), new Point2D(1, 4))), // Zero width
                Arguments.of(Rectangle.emptyRectangle(), Set.of(new Point2D(1, 1), new Point2D(2, 1), new Point2D(3, 1), new Point2D(4, 1))) // Zero height
        );
    }


    @ParameterizedTest
    @MethodSource("shapeFinder_rectangle_testData")
    void test_shapeFinder_rectangle(Rectangle expectedResult, Set<Point2D> shapeData) {
        // Given
        ShapeFinder shapeFinder = new ShapeFinder();
        // When / Then
        assertEquals(expectedResult, shapeFinder.findRectangle(shapeData).orElse(Rectangle.emptyRectangle()));
    }

}