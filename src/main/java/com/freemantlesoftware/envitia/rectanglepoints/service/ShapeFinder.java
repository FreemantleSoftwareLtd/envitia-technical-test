package com.freemantlesoftware.envitia.rectanglepoints.service;

import com.freemantlesoftware.envitia.rectanglepoints.model.Point2D;
import com.freemantlesoftware.envitia.rectanglepoints.model.Rectangle;
import com.freemantlesoftware.envitia.rectanglepoints.model.validation.RectangleValidator;

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
    public Optional<Rectangle> findRectangle(final Set<Point2D> pointsInShape) {

        if (pointsInShape == null || pointsInShape.isEmpty()) {
            return Optional.empty();
        }

        // find corners
        Point2D expectedTopLeftCorner = Collections.min(pointsInShape);
        Point2D expectedBottomRightCorner = Collections.max(pointsInShape);
        Point2D expectedTopRightCorner = new Point2D(expectedBottomRightCorner.getX(), expectedTopLeftCorner.getY());
        Point2D expectedBottomLeftCorner = new Point2D(expectedTopLeftCorner.getX(), expectedBottomRightCorner.getY());
        // find width and height
        final double widthOfRectangle = expectedTopRightCorner.getX() - expectedTopLeftCorner.getX();
        final double heightOfRectangle = expectedBottomLeftCorner.getY() - expectedTopLeftCorner.getY();

        try {
            rectangleValidator.validate(expectedTopLeftCorner.getX(), expectedTopLeftCorner.getY(), widthOfRectangle, heightOfRectangle);

            boolean hasAllFourCorners = pointsInShape.containsAll(Arrays.asList(expectedTopLeftCorner,
                    expectedTopRightCorner,
                    expectedBottomLeftCorner,
                    expectedBottomRightCorner));

            if (hasAllFourCorners) {
                return Optional.of(new Rectangle(expectedTopLeftCorner.getX(), expectedTopLeftCorner.getY(), widthOfRectangle, heightOfRectangle));
            }

        } catch (IllegalArgumentException ex) {
            System.out.printf("An exception occurred while trying to process findRectangle: %s%n", ex); // basic logging.
        }

        return Optional.empty();
    }

}
