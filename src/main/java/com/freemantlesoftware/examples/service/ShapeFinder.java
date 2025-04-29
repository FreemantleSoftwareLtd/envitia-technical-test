package com.freemantlesoftware.examples.service;

import com.freemantlesoftware.examples.model.Point2D;
import com.freemantlesoftware.examples.model.Rectangle;
import com.freemantlesoftware.examples.model.validation.RectangleValidator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * Class that finds shapes given a set of points.
 */
public class ShapeFinder {

    private final RectangleValidator rectangleValidator;

    public ShapeFinder() {
        rectangleValidator = new RectangleValidator();
    }

    /**
     * Tries to find a rectangle in the given set of points.
     *
     * @param pointsInShape a set of points that define a shape.
     * @return A single rectangle object if found, otherwise empty.
     */
    public Optional<Rectangle> findRectangle(Set<Point2D> pointsInShape) {

        if (pointsInShape == null || pointsInShape.isEmpty()) {
            return Optional.empty();
        }

        // find corners
        Point2D expectedTopLeft = Collections.min(pointsInShape);
        Point2D expectedBottomRight = Collections.max(pointsInShape);
        Point2D expectedTopRight = new Point2D(expectedBottomRight.getX(), expectedTopLeft.getY());
        Point2D expectedBottomLeft = new Point2D(expectedTopLeft.getX(), expectedBottomRight.getY());
        // find width and height
        double width = expectedTopRight.getX() - expectedTopLeft.getX();
        double height = expectedBottomLeft.getY() - expectedTopLeft.getY();

        try {
            rectangleValidator.validate(expectedTopLeft.getX(), expectedTopLeft.getY(), width, height);

            boolean hasAllFourCorners = pointsInShape.containsAll(Arrays.asList(expectedTopLeft,
                    expectedTopRight,
                    expectedBottomLeft,
                    expectedBottomRight));

            if (hasAllFourCorners) {
                return Optional.of(new Rectangle(expectedTopLeft.getX(), expectedTopLeft.getY(), width, height));
            }

        } catch (IllegalArgumentException ex) {
            System.out.printf("An exception occurred while trying to process findRectangle. %s%n", ex); // basic logging.
        }

        return Optional.empty();
    }

}
