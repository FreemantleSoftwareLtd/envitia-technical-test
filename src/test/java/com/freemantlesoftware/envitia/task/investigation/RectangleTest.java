package com.freemantlesoftware.envitia.task.investigation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Q: Will the standard .java.awt.Rectangle do what we need?
// A: No. The rectangle class can handle multiple points to describe the rectangle but has some problems
// with corners and edges, especially when 0 is involved.
@Disabled
public class RectangleTest {


    @Test
    void testIsInsideRectangle1_failure() {
        // Given
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
        rectangle.add(1, 1);
        rectangle.add(3, 1);
        rectangle.add(3, 10);
        rectangle.add(1, 10);

        // When
        boolean result = rectangle.contains(1, 1);

        // Then expect false.
        assertFalse(result);
    }

    @Test
    void testIsInsideRectangle12_bottom_right_corner_false() {
        // Given
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
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
        Rectangle rectangle = new Rectangle();
        rectangle.add(-1, -1);
        rectangle.add(-1, 1);
        rectangle.add(1, 1);
        rectangle.add(1, -1);
        rectangle.add(-1, -1);

        // When
        boolean result = rectangle.contains(-1, -1);

        // Then expect false.
        assertFalse(result);
    }


}
