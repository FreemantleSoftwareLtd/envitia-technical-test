package com.freemantlesoftware.envitia.rectanglepoints.model.validation;

import com.freemantlesoftware.envitia.rectanglepoints.model.validation.util.ValidationUtils;

/**
 * Validates a Point2D object.
 */
public class Point2DValidator {

    private static final int COORDINATE_ARRAY_SIZE = 2;

    /**
     * Validates a given array to ensure that there are only two elements and that their values are finite.
     * Throws IllegalArgumentException if values are invalid.
     *
     * @param coordinates double array with an x and y coordinate.
     */
    public void validate(double[] coordinates) throws IllegalArgumentException {

        if (coordinates == null || coordinates.length != COORDINATE_ARRAY_SIZE) {
            throw new IllegalArgumentException("Point2D requires two elements (x and y coordinates).");
        }

        if (ValidationUtils.isNotFinite(coordinates[0]) || ValidationUtils.isNotFinite(coordinates[1])) {
            throw new IllegalArgumentException("Point2D requires argument values to be finite.");
        }
    }
}
