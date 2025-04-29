package com.freemantlesoftware.examples.investigation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


// Q: Will the standard .java.awt.Rectangle2D.Float do what we need?
// A: No. The rectangle class can handle multiple points to describe the rectangle but has some problems
// it needs to initalised with a point otherwise coordinate 'x0, y0' is included.
// The contains method does not return true for points on the right or bottom edges of a rectangle.
// Therefore, if the added point falls on the right or bottom edge of the enlarged rectangle, contains returns false for that point.
@Disabled
public class Rectangle2DFloatTest {


    @Test
    void testIsInsideRectangle1_failure() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(1, 1);
        rectangle.add(2, 7);
        rectangle.add(10, 7);
        rectangle.add(9, 1);

        // When
        boolean result = rectangle.contains(8, 2);

        // Then expect false.
        assertFalse(result);
    }


    @Test
    void testIsInsideRectangle2_success() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(-1, -1);
        rectangle.add(-1, 1);
        rectangle.add(1, 1);
        rectangle.add(1, -1);
        rectangle.add(-1, -1);

        // When
        boolean result = rectangle.contains(0, 0);

        // Then expect true.
        assertTrue(result);
    }

    @Test
    void testIsInsideRectangle3_success() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(1, 1);
        rectangle.add(10, 7);
        rectangle.add(10, 1);
        rectangle.add(1, 7);

        // When
        boolean result = rectangle.contains(2, 6);

        // Then expect true.
        assertTrue(result);
    }

    @Test
    void testIsInsideRectangle4_success() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(1, 1);
        rectangle.add(1, 3);
        rectangle.add(3, 3);
        rectangle.add(5, 3);
        rectangle.add(5, 2);
        rectangle.add(5, 1);

        // When
        boolean result = rectangle.contains(4, 2);

        // Then expect true.
        assertTrue(result);
    }

    @Test
    void testIsInsideRectangle5_false() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(0, 0);
        rectangle.add(3, 0);
        rectangle.add(3, 10);
        rectangle.add(0, 10);

        // When
        boolean result = rectangle.contains(2, 10);

        // Then expect false.
        assertFalse(result);
    }


    @Test
    void testIsInsideRectangle6_definitely_outside_false() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(1, 1);
        rectangle.add(1, 7);
        rectangle.add(10, 7);
        rectangle.add(10, 1);

        // When
        boolean result = rectangle.contains(-2, 6);

        // Then expect false.
        assertFalse(result);
    }

    @Test
    void testIsInsideRectangle7_on_left_edge_false() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(0, 0);
        rectangle.add(3, 0);
        rectangle.add(3, 10);
        rectangle.add(0, 10);

        // When
        boolean result = rectangle.contains(0, 1);

        // Then expect false.
        assertFalse(result);
    }

    @Test
    void testIsInsideRectangle8_on_top_edge_false() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(0, 0);
        rectangle.add(3, 0);
        rectangle.add(3, 10);
        rectangle.add(0, 10);

        // When
        boolean result = rectangle.contains(1, 0);

        // Then expect false.
        assertFalse(result);
    }

    @Test
    void testIsInsideRectangle9_on_right_edge_false() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(0, 0);
        rectangle.add(3, 0);
        rectangle.add(3, 10);
        rectangle.add(0, 10);

        // When
        boolean result = rectangle.contains(3, 1);

        // Then expect false.
        assertFalse(result);
    }

    @Test
    void testIsInsideRectangle10_on_bottom_edge_false() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(0, 0);
        rectangle.add(3, 0);
        rectangle.add(3, 10);
        rectangle.add(0, 10);

        // When
        boolean result = rectangle.contains(1, 10);

        // Then expect false.
        assertFalse(result);
    }


    @Test
    void testIsInsideRectangle11_top_left_corner_positive_false() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float(1f, 1f, 0f, 0f);
        // rectangle.add(1, 1);
        rectangle.add(3f, 1f);
        rectangle.add(3f, 10f);
        rectangle.add(1f, 10f);

        // When
        boolean result = rectangle.contains(1f, 1f);

        // Then expect false.
        assertFalse(result);
    }


    @Test
    void testIsInsideRectangle12_bottom_right_corner_false() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(1, 1);
        rectangle.add(3, 1);
        rectangle.add(3, 10);
        rectangle.add(1, 10);

        // When
        boolean result = rectangle.contains(10, 10);

        // Then expect false.
        assertFalse(result);
    }

    @Test
    void testIsInsideRectangle13_corners_false() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(0, 0);
        rectangle.add(3, 0);
        rectangle.add(3, 10);
        rectangle.add(0, 10);

        // When / Then expect false.
        assertFalse(rectangle.contains(3, 10));
        assertFalse(rectangle.contains(3, 0));
        assertFalse(rectangle.contains(0, 10));
    }

    @Test
    void testIsInsideRectangle14_top_left_corner_negative_false() {
        // Given
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        rectangle.add(-1.0, -1.0);
        rectangle.add(-1.0, 1.0);
        rectangle.add(1.0, 1.0);
        rectangle.add(1.0, -1.0);
        rectangle.add(-1.0, -1.0);

        // When
        boolean result = rectangle.contains(-1.0, -1.0);

        // Then expect false.
        assertFalse(result);
    }


}
